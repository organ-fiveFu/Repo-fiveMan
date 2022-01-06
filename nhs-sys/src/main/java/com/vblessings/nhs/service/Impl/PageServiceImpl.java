package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.vblessings.nhs.enums.DictTypeEnum;
import com.vblessings.nhs.mapper.BasePatientInfoMapper;
import com.vblessings.nhs.mapper.SysBedInfoMapper;
import com.vblessings.nhs.mapper.SysFloorInfoMapper;
import com.vblessings.nhs.mapper.SysRoomInfoMapper;
import com.vblessings.nhs.model.entity.bed.SysBedInfo;
import com.vblessings.nhs.model.entity.bed.SysFloorInfo;
import com.vblessings.nhs.model.entity.bed.SysRoomInfo;
import com.vblessings.nhs.model.po.page.PageQueryPO;
import com.vblessings.nhs.model.po.page.PatientQueryPO;
import com.vblessings.nhs.model.vo.bed.SysBuildingInfoQueryVO;
import com.vblessings.nhs.model.vo.bed.SysFloorInfoQueryVO;
import com.vblessings.nhs.model.vo.page.*;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BedService;
import com.vblessings.nhs.service.PageService;
import com.vblessings.nhs.service.SysDictDataService;
import com.vblessings.nhs.util.DateUtils;
import com.vblessings.nhs.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PageServiceImpl implements PageService {

    @Resource
    private SysBedInfoMapper sysBedInfoMapper;

    @Resource
    private SysFloorInfoMapper sysFloorInfoMapper;

    @Resource
    private SysDictDataService sysDictDataService;

    @Resource
    private BedService bedService;

    @Resource
    private SysRoomInfoMapper sysRoomInfoMapper;

    @Resource
    private BasePatientInfoMapper basePatientInfoMapper;

    @Override
    public BedPageQueryVO queryBedPage(PageQueryPO pageQueryPO) {
        //总床位数
        Example example = new Example(SysBedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", pageQueryPO.getBuildingCode());
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("useFlag", 1);
        int bedNum = sysBedInfoMapper.selectCountByExample(example);
        //空闲
        criteria.andEqualTo("status", "0");
        int freeBedNum = sysBedInfoMapper.selectCountByExample(example);
        //入住
        int checkInBedNum = 0;
        if(bedNum > freeBedNum){
            checkInBedNum = bedNum - freeBedNum;
        }
        //总房间数
        Example example1 = new Example(SysRoomInfo.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("buildingCode", pageQueryPO.getBuildingCode());
        criteria1.andEqualTo("isDel", 0);
        int roomNum = sysRoomInfoMapper.selectCountByExample(example1);
        BedPageQueryVO bedPageQueryVO = new BedPageQueryVO();
        bedPageQueryVO.setBedNum(String.valueOf(bedNum));
        bedPageQueryVO.setFreeBedNum(String.valueOf(freeBedNum));
        bedPageQueryVO.setCheckInBedNum(String.valueOf(checkInBedNum));
        bedPageQueryVO.setRoomNum(String.valueOf(roomNum));
        return bedPageQueryVO;
    }

    @Override
    public List<PageQueryVO> queryPageList(PageQueryPO pageQueryPO, UserInfoToken userInfoToken) {
        //查询楼层
        Example example = new Example(SysFloorInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", pageQueryPO.getBuildingCode());
        criteria.andEqualTo("isDel", 0);
        List<SysFloorInfo> sysFloorInfoList = sysFloorInfoMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(sysFloorInfoList)){
            return null;
        }
        //楼宇code -> name
        Map<String, String> buildingMap =
                bedService.querySysBuildingInfoGetList(userInfoToken).stream().collect(Collectors.toMap(SysBuildingInfoQueryVO::getBuildingCode, SysBuildingInfoQueryVO::getName, (v1, v2) -> v1));
        //楼层code -> name
        Map<String, String> floorMap =
                bedService.querySysFloorInfoGetList(pageQueryPO.getBuildingCode(), userInfoToken).stream().collect(Collectors.toMap(SysFloorInfoQueryVO::getFloorCode, SysFloorInfoQueryVO::getName, (v1, v2) -> v1));
        List<PageQueryVO> pageQueryVOList = new ArrayList<>();
        sysFloorInfoList.forEach(sysFloorInfo -> {
            PageQueryVO pageQueryVO = new PageQueryVO();
            pageQueryVO.setFloorCode(sysFloorInfo.getFloorCode());
            pageQueryVO.setFloorName(sysFloorInfo.getName());
            pageQueryVOList.add(pageQueryVO);
        });
        List<String> floorCodeList = sysFloorInfoList.stream().map(SysFloorInfo::getFloorCode).collect(Collectors.toList());
        //查询房间
        Example example1 = new Example(SysRoomInfo.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("buildingCode", pageQueryPO.getBuildingCode());
        criteria1.andIn("floorCode", floorCodeList);
        criteria1.andEqualTo("isDel", 0);
        List<SysRoomInfo> sysRoomInfoList = sysRoomInfoMapper.selectByExample(example1);
        if(CollectionUtil.isEmpty(sysRoomInfoList)){
            return pageQueryVOList;
        }
        List<PageRoomQueryVO> pageRoomQueryVOList = new ArrayList<>();
        sysRoomInfoList.forEach(sysRoomInfo -> {
            PageRoomQueryVO pageRoomQueryVO = new PageRoomQueryVO();
            pageRoomQueryVO.setRoomCode(sysRoomInfo.getRoomCode());
            pageRoomQueryVO.setRoomName(sysRoomInfo.getName());
            pageRoomQueryVO.setFloorCode(sysRoomInfo.getFloorCode());
            pageRoomQueryVO.setFloorName(floorMap.get(sysRoomInfo.getFloorCode()));
            pageRoomQueryVO.setRoomType(sysRoomInfo.getRoomType());
            pageRoomQueryVOList.add(pageRoomQueryVO);
        });
        List<String> roomCodeList = sysRoomInfoList.stream().map(SysRoomInfo::getRoomCode).collect(Collectors.toList());
        List<PageDetailQueryVO> pageDetailQueryVOList = sysBedInfoMapper.queryPatient(pageQueryPO.getBuildingCode(), floorCodeList, roomCodeList);
        //过滤
        pageDetailQueryVOList = pageDetailQueryVOList.stream().filter(ListUtil.distinctByKey(PageDetailQueryVO::getId)).collect(Collectors.toList());
        Map<String, List<PageRoomQueryVO>> map = pageRoomQueryVOList.stream().collect(Collectors.groupingBy(PageRoomQueryVO::getFloorCode));
        Map<String, List<PageDetailQueryVO>> roomMap = pageDetailQueryVOList.stream().collect(Collectors.groupingBy(PageDetailQueryVO::getRoomCode));
        //字典code -> name
        List<String> nursingLevelNameList = new ArrayList<>();
        List<String> sexNameList = new ArrayList<>();
        for (PageDetailQueryVO detailQueryVO : pageDetailQueryVOList) {
            nursingLevelNameList.add(detailQueryVO.getNursingLevel());
            sexNameList.add(detailQueryVO.getSex());
        }
        Map<String, String> roomTypeNameMap = sysDictDataService.getDictName(DictTypeEnum.ROOM_TYPE.getCode(), null);
        Map<String, String> nursingLevelNameMap = sysDictDataService.getDictName(DictTypeEnum.NURSING_LEVEL.getCode(), nursingLevelNameList);
        Map<String, String> sexNameMap = sysDictDataService.getDictName(DictTypeEnum.SEX.getCode(), sexNameList);
        pageDetailQueryVOList.forEach(pageDetailQueryVO -> {
            pageDetailQueryVO.setRoomTypeName(roomTypeNameMap.get(pageDetailQueryVO.getRoomType()));
            pageDetailQueryVO.setNursingLevelName(nursingLevelNameMap.get(pageDetailQueryVO.getNursingLevel()));
            pageDetailQueryVO.setBuildingName(buildingMap.get(pageDetailQueryVO.getBuildingCode()));
            pageDetailQueryVO.setFloorName(floorMap.get(pageDetailQueryVO.getFloorCode()));
            pageDetailQueryVO.setSexName(sexNameMap.get(pageDetailQueryVO.getSex()));
            Date feeDate = null;
            try {
                if(!StringUtils.isEmpty(pageDetailQueryVO.getFeesDueDate())){
                    feeDate = DateUtils.stringToDateByFormat(pageDetailQueryVO.getFeesDueDate(), "yyyy-MM-dd HH:mm:ss");
                    if(DateUtils.timeIsMore(feeDate, new Date())){
                        pageDetailQueryVO.setFeesDueStatue(1); //过期
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        pageRoomQueryVOList.forEach(pageRoomQueryVO -> {
            pageRoomQueryVO.setRoomTypeName(roomTypeNameMap.get(pageRoomQueryVO.getRoomType()));
            pageRoomQueryVO.setPageDetailQueryVOS(roomMap.get(pageRoomQueryVO.getRoomCode()));
        });
        pageQueryVOList.forEach(pageQueryVO -> {
            pageQueryVO.setPageRoomQueryVOList(map.get(pageQueryVO.getFloorCode()));
        });
        return pageQueryVOList;
    }

    @Override
    public List<PatientQueryVO> queryPatientList(PatientQueryPO patientQueryPO, UserInfoToken userInfoToken) {
        List<PatientQueryVO> patientQueryVOList = basePatientInfoMapper.queryPatientList(patientQueryPO);
        if(CollectionUtil.isEmpty(patientQueryVOList)){
            return null;
        }
        patientQueryVOList.forEach(patientQueryVO -> {
            patientQueryVO.setTotalName(patientQueryVO.getBuildingName() + patientQueryVO.getFloorName() + patientQueryVO.getRoomName() + patientQueryVO.getBedName());
        });
        return patientQueryVOList;
    }
}
