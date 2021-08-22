package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusSpecialNursingRecordMapper;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.businessVO.QuerySpecialNursingPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusSpecialNursingRecordService;
import com.vblessings.nhs.util.OperateUtil;
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
            criteria.andLike("patientName","%"+querySpecialNursingPO.getName()+"%");
           }
        if(Strings.isNotBlank(querySpecialNursingPO.getId())){
            criteria.andEqualTo("id",Long.parseLong(querySpecialNursingPO.getId()));
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
    public void delSpecialNursing(String ids) {
     String[] id = ids.split(",");
        busSpecialNursingRecordMapper.del(id);
    }
}
