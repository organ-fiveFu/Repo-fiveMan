package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusNursingRecordMapper;
import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.po.business.BusNursingRecordInsertPO;
import com.vblessings.nhs.model.po.business.QueryNursingRecordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusNursingRecordService;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusNursingRecordServiceImpl implements BusNursingRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusNursingRecordMapper busNursingRecordMapper;

    @Override
    public void addNursingRecord(BusNursingRecordInsertPO busNursingRecordInsertPO, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        BusNursingRecord busNursingRecord = new BusNursingRecord();
        BeanUtil.copyProperties(busNursingRecordInsertPO, busNursingRecord);
        OperateUtil.onSaveNew(busNursingRecord, userInfo, id);
        busNursingRecordMapper.insert(busNursingRecord);
    }

    @Override
    public PageVO<BusNursingRecord> pageNursingRecord(QueryNursingRecordPO queryNursingRecordPO) {
        Page<BusNursingRecord> result = PageHelper.startPage(queryNursingRecordPO.getPageNum(), queryNursingRecordPO.getPageSize());
        Example example = new Example(BusNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusNursingRecord> busNursingRecords;
        if(Strings.isNotBlank(queryNursingRecordPO.getName())){
            criteria.andLike("name", "%" + queryNursingRecordPO.getName() + "%");
            busNursingRecords = busNursingRecordMapper.selectByExample(example);
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busNursingRecords);
        }
        busNursingRecords = busNursingRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busNursingRecords);

    }

    @Override
    public void updateNursingRecord(BusNursingRecord busNursingRecord, UserInfoToken userInfo) {
        busNursingRecord.setUpdaterId(userInfo.getUserId());
        busNursingRecord.setUpdateTime(new DateTime());
        busNursingRecordMapper.updateByPrimaryKeySelective(busNursingRecord);
    }

    @Override
    public void delNursingRecord(String ids) {
        String[] id = ids.split(",");
        busNursingRecordMapper.batchDel(id);
    }
}
