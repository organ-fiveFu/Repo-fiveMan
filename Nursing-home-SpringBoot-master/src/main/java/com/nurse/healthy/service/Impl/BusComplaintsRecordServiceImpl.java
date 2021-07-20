package com.nurse.healthy.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusComplaintsRecordMapper;
import com.nurse.healthy.model.entity.business.BusComplaintsRecord;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.po.businessVO.QueryComplaintVO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusComplaintsRecordService;
import com.nurse.healthy.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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
    public PageVO<BusComplaintsRecord> pageComplaint(QueryComplaintVO queryComplaintVO) {
        Page<BusComplaintsRecord> result = PageHelper.startPage(queryComplaintVO.getPageNum(), queryComplaintVO.getPageSize());
        Example example = new Example(BusComplaintsRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusComplaintsRecord> busComplaintsRecordList = new ArrayList<>();
        if(Strings.isNotBlank(queryComplaintVO.getSearch())){
            criteria.andLike("theme","%"+queryComplaintVO.getSearch()+"%");
            busComplaintsRecordList = busComplaintsRecordMapper.selectByExample(example);
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busComplaintsRecordList);
        }
        busComplaintsRecordList = busComplaintsRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busComplaintsRecordList);
    }

    @Override
    public void updateComplaint(BusComplaintsRecord busComplaintsRecord, UserInfoToken userInfo) {
        busComplaintsRecord.setUpdaterId(userInfo.getUserId());
        busComplaintsRecord.setUpdateTime(new DateTime());
        busComplaintsRecordMapper.updateByPrimaryKeySelective(busComplaintsRecord);
    }

    @Override
    public void delComplaint(Long id) {
        busComplaintsRecordMapper.delComplaint(id);
        }

}
