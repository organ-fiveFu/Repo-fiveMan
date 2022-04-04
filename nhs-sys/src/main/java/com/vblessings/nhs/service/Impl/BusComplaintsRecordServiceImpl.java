package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusComplaintsRecordMapper;
import com.vblessings.nhs.model.entity.business.BusComplaintsRecord;
import com.vblessings.nhs.model.entity.business.BusInterestGroupRecord;
import com.vblessings.nhs.model.po.businessVO.QueryComplaintVO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusComplaintsRecordVO;
import com.vblessings.nhs.model.vo.business.BusInterestGroupRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusComplaintsRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusComplaintsRecordServiceImpl implements BusComplaintsRecordService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusComplaintsRecordMapper busComplaintsRecordMapper;
    @Override
    public void addComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busComplaintsRecord,userInfo,id);
        busComplaintsRecordMapper.insert(busComplaintsRecord);
    }

    @Override
    public PageVO<BusComplaintsRecordVO> pageComplaint(QueryComplaintVO queryComplaintVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<BusComplaintsRecordVO> result = PageHelper.startPage(queryComplaintVO.getPageNum(), queryComplaintVO.getPageSize());
        Example example = new Example(BusComplaintsRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusComplaintsRecord> busComplaintsRecordList = new ArrayList<>();
        List<BusComplaintsRecordVO> busComplaintsRecordVOList = new ArrayList<>();
        if(Strings.isNotBlank(queryComplaintVO.getSearch())){
            criteria.andLike("theme","%"+queryComplaintVO.getSearch()+"%");
             }
        busComplaintsRecordList = busComplaintsRecordMapper.selectByExample(example);
        for (BusComplaintsRecord busComplaintsRecord:
                busComplaintsRecordList) {
            BusComplaintsRecordVO busComplaintsRecordVO = new BusComplaintsRecordVO();
            BeanUtils.copyProperties(busComplaintsRecord, busComplaintsRecordVO);
            busComplaintsRecordVO.setComplaintDate(sdf.format(busComplaintsRecord.getComplaintDate()));
            busComplaintsRecordVOList.add(busComplaintsRecordVO);
        }
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busComplaintsRecordVOList);
    }

    @Override
    public void updateComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo) {
        busComplaintsRecord.setUpdaterId(userInfo.getUserId());
        busComplaintsRecord.setUpdateTime(new DateTime());
        busComplaintsRecordMapper.updateByPrimaryKeySelective(busComplaintsRecord);
    }

    @Override
    public void delComplaint(String ids) {
        String[] id = ids.split(",");
        busComplaintsRecordMapper.delComplaint(id);
        }

    @Override
    public List<BusComplaintsRecordVO> queryComplaintNoToken(QueryComplaintVO queryComplaintVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Example example = new Example(BusComplaintsRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusComplaintsRecord> busComplaintsRecordList = new ArrayList<>();
        List<BusComplaintsRecordVO> busComplaintsRecordVOList = new ArrayList<>();
        if(Strings.isNotBlank(queryComplaintVO.getSearch())){
            criteria.andLike("theme","%"+queryComplaintVO.getSearch()+"%");
        }
        busComplaintsRecordList = busComplaintsRecordMapper.selectByExample(example);
        for (BusComplaintsRecord busComplaintsRecord:
                busComplaintsRecordList) {
            BusComplaintsRecordVO busComplaintsRecordVO = new BusComplaintsRecordVO();
            BeanUtils.copyProperties(busComplaintsRecord, busComplaintsRecordVO);
            busComplaintsRecordVO.setComplaintDate(sdf.format(busComplaintsRecord.getComplaintDate()));
            busComplaintsRecordVOList.add(busComplaintsRecordVO);
        }
        return busComplaintsRecordVOList;
    }

}
