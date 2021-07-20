package com.nurse.healthy.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.mapper.BusDonationRecordMapper;
import com.nurse.healthy.model.entity.business.BusDonationRecord;
import com.nurse.healthy.model.po.businessVO.QueryDonationPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.BusDonationRecordService;
import com.nurse.healthy.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusDonationRecordServiceImpl implements BusDonationRecordService {
    @Resource
    private BusDonationRecordMapper busDonationRecordMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public void donation(BusDonationRecord busDonationRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busDonationRecord,userInfo,id);
        busDonationRecordMapper.insert(busDonationRecord);
    }

    @Override
    public PageVO<BusDonationRecord> pageDonation( QueryDonationPO queryDonationPO) {
        Page<BusDonationRecord> result = PageHelper.startPage(queryDonationPO.getPageNum(), queryDonationPO.getPageSize());
        Example example = new Example(BusDonationRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusDonationRecord> busDonationRecordList = new ArrayList<>();
        if(Strings.isNotBlank(queryDonationPO.getSearch())){
            criteria.andLike("donor","%"+queryDonationPO.getSearch()+"%");
            busDonationRecordList = busDonationRecordMapper.selectByExample(example);
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busDonationRecordList);
        }
        busDonationRecordList = busDonationRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busDonationRecordList);
    }

    /**
     * 根据主键更新捐款记录
     * @param busDonationRecord
     */
    @Override
    public void updateDonation(BusDonationRecord busDonationRecord,UserInfoToken userInfo) {
        busDonationRecord.setUpdaterId(userInfo.getUserId());
        busDonationRecord.setUpdateTime(new DateTime());
        busDonationRecordMapper.updateByPrimaryKeySelective(busDonationRecord);
    }

    /**
     * 删除捐赠记录
     */
    @Override
    public void delDonation(Long id) {
        busDonationRecordMapper.delDonation(id);
    }

}
