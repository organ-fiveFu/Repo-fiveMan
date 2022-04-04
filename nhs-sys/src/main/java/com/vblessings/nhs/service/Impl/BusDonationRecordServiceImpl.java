package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusDonationRecordMapper;
import com.vblessings.nhs.model.entity.business.BusComplaintsRecord;
import com.vblessings.nhs.model.entity.business.BusDonationRecord;
import com.vblessings.nhs.model.po.businessVO.QueryDonationPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusComplaintsRecordVO;
import com.vblessings.nhs.model.vo.business.BusDonationRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusDonationRecordService;
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
    public PageVO<BusDonationRecordVO> pageDonation(QueryDonationPO queryDonationPO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<BusDonationRecordVO> result = PageHelper.startPage(queryDonationPO.getPageNum(), queryDonationPO.getPageSize());
        Example example = new Example(BusDonationRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusDonationRecord> busDonationRecordList = new ArrayList<>();
        if(Strings.isNotBlank(queryDonationPO.getSearch())){
            criteria.andLike("donor","%"+queryDonationPO.getSearch()+"%");
        }
        busDonationRecordList = busDonationRecordMapper.selectByExample(example);
        List<BusDonationRecordVO> busDonationRecordVOList = new ArrayList<>();
        for (BusDonationRecord busDonationRecord:
                busDonationRecordList) {
            BusDonationRecordVO busDonationRecordVO = new BusDonationRecordVO();
            BeanUtils.copyProperties(busDonationRecord, busDonationRecordVO);
            busDonationRecordVO.setDonationDate(sdf.format(busDonationRecord.getDonationDate()));
            busDonationRecordVOList.add(busDonationRecordVO);
        }
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busDonationRecordVOList);
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
    public void delDonation(String ids) {
        String[] id = ids.split(",");
        busDonationRecordMapper.delDonation(id);
    }

    @Override
    public List<BusDonationRecordVO> queryInterestGroupNoToken(QueryDonationPO queryDonationPO) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Example example = new Example(BusDonationRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusDonationRecord> busDonationRecordList = new ArrayList<>();
        if(Strings.isNotBlank(queryDonationPO.getSearch())){
            criteria.andLike("donor","%"+queryDonationPO.getSearch()+"%");
        }
        busDonationRecordList = busDonationRecordMapper.selectByExample(example);
        List<BusDonationRecordVO> busDonationRecordVOList = new ArrayList<>();
        for (BusDonationRecord busDonationRecord:
                busDonationRecordList) {
            BusDonationRecordVO busDonationRecordVO = new BusDonationRecordVO();
            BeanUtils.copyProperties(busDonationRecord, busDonationRecordVO);
            busDonationRecordVO.setDonationDate(sdf.format(busDonationRecord.getDonationDate()));
            busDonationRecordVOList.add(busDonationRecordVO);
        }
        return busDonationRecordVOList;
    }

}
