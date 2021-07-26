package com.nurse.healthy.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusSpecialNursingRecordMapper;
import com.nurse.healthy.model.entity.business.BusSpecialNursingRecord;
import com.nurse.healthy.model.po.businessVO.QuerySpecialNursingPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusSpecialNursingRecordService;
import com.nurse.healthy.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusSpecialNursingRecordServiceImpl implements BusSpecialNursingRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusSpecialNursingRecordMapper busSpecialNursingRecordMapper;

    @Override
    public void addSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busSpecialNursingRecord,userInfo,id);
        busSpecialNursingRecordMapper.insert(busSpecialNursingRecord);
    }

    @Override
    public PageVO<BusSpecialNursingRecord> pageSpecialNursing(QuerySpecialNursingPO querySpecialNursingPO) {
        Page<BusSpecialNursingRecord> result = PageHelper.startPage(querySpecialNursingPO.getPageNum(), querySpecialNursingPO.getPageSize());
        Example example = new Example(BusSpecialNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusSpecialNursingRecord> busSpecialNursingRecordList = new ArrayList<>();
        if(Strings.isNotBlank(querySpecialNursingPO.getName())){
            criteria.andLike("name","%"+querySpecialNursingPO.getName()+"%");
            busSpecialNursingRecordList = busSpecialNursingRecordMapper.selectByExample(example);
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busSpecialNursingRecordList);
        }
        busSpecialNursingRecordList = busSpecialNursingRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busSpecialNursingRecordList);

    }

    @Override
    public void updateSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo) {
        busSpecialNursingRecord.setUpdaterId(userInfo.getUserId());
        busSpecialNursingRecord.setUpdateTime(new DateTime());
        busSpecialNursingRecordMapper.updateByPrimaryKeySelective(busSpecialNursingRecord);
    }

    @Override
    public void delSpecialNursing(Long id) {
        BusSpecialNursingRecord busSpecialNursingRecord = new BusSpecialNursingRecord();
        busSpecialNursingRecord.setId(id);
        busSpecialNursingRecord.setIsDel(1);
        busSpecialNursingRecordMapper.updateByPrimaryKeySelective(busSpecialNursingRecord);
    }
}
