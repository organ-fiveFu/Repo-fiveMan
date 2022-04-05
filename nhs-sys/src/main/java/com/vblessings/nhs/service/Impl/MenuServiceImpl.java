package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.enums.DictTypeEnum;
import com.vblessings.nhs.mapper.BusMenuInfoMapper;
import com.vblessings.nhs.model.entity.business.BusMenuInfo;
import com.vblessings.nhs.model.po.business.BusMenuBatchUpdatePO;
import com.vblessings.nhs.model.po.business.BusMenuSelectPO;
import com.vblessings.nhs.model.vo.business.BusMenuSelectVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.MenuService;
import com.vblessings.nhs.service.SysDictDataService;
import com.vblessings.nhs.util.DateUtils;
import com.vblessings.nhs.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    private BusMenuInfoMapper busMenuInfoMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private SysDictDataService sysDictDataService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 批量更新菜谱
     * @param busMenuBatchUpdatePO      菜谱入参
     * @param userInfo                  token
     */
    @Override
    public void batchUpdate(BusMenuBatchUpdatePO busMenuBatchUpdatePO, UserInfoToken userInfo) {
        Date start = null;
        Date end = null;
        try {
            start = DateUtils.stringToDateByFormat(busMenuBatchUpdatePO.getStartTime(), "yyyy-MM-dd");
            end = DateUtils.stringToDateByFormat(busMenuBatchUpdatePO.getEndTime(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        Example example = new Example(BusMenuInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        criteria.andBetween("date", start, end);
        BusMenuInfo busMenuInfo = new BusMenuInfo();
        busMenuInfo.setIsDel(1);
        busMenuInfoMapper.updateByExampleSelective(busMenuInfo, example);
        busMenuBatchUpdatePO.getBusMenuBatchUpdateInfos().forEach(busMenuBatchUpdateInfo -> {
            BusMenuInfo busMenuInfoTemp = new BusMenuInfo();
            BeanUtil.copyProperties(busMenuBatchUpdateInfo, busMenuInfoTemp);
            if (busMenuInfoTemp.getId() != null) {
                busMenuInfoTemp.setUpdaterId(userInfo.getUserId());
                busMenuInfoTemp.setUpdateTime(date);
                busMenuInfoTemp.setIsDel(0);
                busMenuInfoMapper.updateByPrimaryKey(busMenuInfoTemp);
            } else {
                OperateUtil.onSaveNew(busMenuInfoTemp, userInfo, snowflakeComponent.getInstance().nextId());
                busMenuInfoMapper.insert(busMenuInfoTemp);
            }
        });
    }

    /**
     * 查询菜谱
     * @param busMenuSelectPO           查询菜谱入参
     * @param userInfo                  token
     */
    @Override
    public List<BusMenuSelectVO> selectMenuInfo(BusMenuSelectPO busMenuSelectPO, UserInfoToken userInfo) {
        Date start = null;
        Date end = null;
        try {
            start = DateUtils.stringToDateByFormat(busMenuSelectPO.getStartTime(), "yyyy-MM-dd");
            end = DateUtils.stringToDateByFormat(busMenuSelectPO.getEndTime(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Example example = new Example(BusMenuInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        criteria.andBetween("date", start, end);
        example.orderBy("date");
        List<BusMenuInfo> busMenuInfoList = busMenuInfoMapper.selectByExample(example);
        List<BusMenuSelectVO> busMenuSelectVOS = new ArrayList<>();
        busMenuInfoList.forEach(busMenuInfo -> {
            BusMenuSelectVO busMenuSelectVO = new BusMenuSelectVO();
            BeanUtil.copyProperties(busMenuInfo, busMenuSelectVO);
            busMenuSelectVO.setDate(sdf.format(busMenuInfo.getDate()));
            Calendar date = Calendar.getInstance();
            date.setTime(busMenuInfo.getDate());
            int w = date.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            busMenuSelectVO.setDayOfWeek(weekDays[w]);
            busMenuSelectVOS.add(busMenuSelectVO);
        });

        return handleCirculationDate(start, end, busMenuSelectVOS);
    }

    /**
     * 查询菜谱
     * @param busMenuSelectPO           查询菜谱入参
     */
    @Override
    public List<BusMenuSelectVO> selectMenuInfoNoToken(BusMenuSelectPO busMenuSelectPO) {
        List<BusMenuSelectVO> busMenuSelectVOS = selectMenuInfo(busMenuSelectPO, null);
        List<String> menuList = new ArrayList<>();
        Map<String,String> stapleFoodDictMap = sysDictDataService.getDictName(DictTypeEnum.MENU_STAPLE_FOOD.getCode(), menuList);
        Map<String,String> dishesDictMap = sysDictDataService.getDictName(DictTypeEnum.MENU_DISHES.getCode(), menuList);
        busMenuSelectVOS.forEach(busMenuSelectVO -> {
            busMenuSelectVO.setBreakfastMenu(setMenuName(busMenuSelectVO.getBreakfastMenu(), dishesDictMap));
            busMenuSelectVO.setLunchMenu(setMenuName(busMenuSelectVO.getLunchMenu(), dishesDictMap));
            busMenuSelectVO.setDinnerMeun(setMenuName(busMenuSelectVO.getDinnerMeun(), dishesDictMap));
            busMenuSelectVO.setBreakfastStapleFood(setMenuName(busMenuSelectVO.getBreakfastStapleFood(), stapleFoodDictMap));
            busMenuSelectVO.setLunchStapleFood(setMenuName(busMenuSelectVO.getLunchStapleFood(), stapleFoodDictMap));
            busMenuSelectVO.setDinnerStapleFood(setMenuName(busMenuSelectVO.getDinnerStapleFood(), stapleFoodDictMap));
        });
        return busMenuSelectVOS;
    }

    private List<String> setMenuName(List<String> menuList, Map<String,String> foodMap) {
        return menuList.stream().map(menu -> foodMap.getOrDefault(menu, menu)).collect(Collectors.toList());
    }

    public List<BusMenuSelectVO> handleCirculationDate(Date startTime, Date endTime, List<BusMenuSelectVO> busMenuSelectVOS) {
        int i = 0;
        List<BusMenuSelectVO> resultList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar calStartTime = Calendar.getInstance();
            Calendar calEndTime = Calendar.getInstance();
            calStartTime.setTime(startTime);
            calEndTime.setTime(endTime);
            calEndTime.add(Calendar.DAY_OF_MONTH, 1);
            while (calStartTime.getTime().before(calEndTime.getTime())) {
                if (i < busMenuSelectVOS.size() && dateFormat.format(calStartTime.getTime()).equals(busMenuSelectVOS.get(i).getDate())) {
                    resultList.add(busMenuSelectVOS.get(i));
                    i++;
                } else {
                    BusMenuSelectVO busMenuSelectVO = new BusMenuSelectVO();
                    busMenuSelectVO.setDate(dateFormat.format(calStartTime.getTime()));
                    int w = calStartTime.get(Calendar.DAY_OF_WEEK) - 1;
                    if (w < 0)
                        w = 0;
                    busMenuSelectVO.setDayOfWeek(weekDays[w]);
                    resultList.add(busMenuSelectVO);
                }
                calStartTime.add(Calendar.DAY_OF_MONTH, 1);
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
