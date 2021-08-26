package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusContractMapper;
import com.vblessings.nhs.model.entity.business.BusContract;
import com.vblessings.nhs.model.po.nurse.ContractInsertPO;
import com.vblessings.nhs.model.po.nurse.ContractUpdatePO;
import com.vblessings.nhs.model.po.nurse.RiskNotificationQueryPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.ContractQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.ContractService;
import com.vblessings.nhs.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ContractServiceImpl implements ContractService {

    @Resource
    private BusContractMapper busContractMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public boolean insertContract(ContractInsertPO contractInsertPO, UserInfoToken userInfoToken) {
        //判断住院号是否重复
        Example example = new Example(BusContract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", contractInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        int count = busContractMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已签订合同，无法新增");
        }
        //新增
        BusContract busContract = new BusContract();
        BeanUtils.copyProperties(contractInsertPO, busContract);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busContract, userInfoToken, id);
        busContract.setIsSignContract("1");
        log.info("新增合同信息,入参busContract:{}", JSON.toJSONString(busContract));
        try {
            busContractMapper.insertSelective(busContract);
        }catch (Exception e){
            log.error("新增合同信息失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增合同信息失败");
        }
        return true;
    }

    @Override
    public boolean updateContract(ContractUpdatePO contractUpdatePO, UserInfoToken userInfoToken) {
        //判断住院号是否重复
        Example example = new Example(BusContract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", contractUpdatePO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andNotEqualTo("id", contractUpdatePO.getId());
        int count = busContractMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该病人已签订合同，无法更新");
        }
        //新增
        BusContract busContract = new BusContract();
        BeanUtils.copyProperties(contractUpdatePO, busContract);
        busContract.setUpdaterId(userInfoToken.getUserId());
        busContract.setUpdateTime(new Date());
        log.info("更新合同信息,入参busContract:{}", JSON.toJSONString(busContract));
        try {
            busContractMapper.updateByPrimaryKeySelective(busContract);
        }catch (Exception e){
            log.error("更新合同信息失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新合同信息失败");
        }
        return true;
    }

    @Override
    public ContractQueryVO queryContractByBusinessNo(String businessNo) {
        Example example = new Example(BusContract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", businessNo);
        criteria.andEqualTo("isDel", 0);
        BusContract busContract = busContractMapper.selectOneByExample(example);
        if(StringUtils.isEmpty(busContract)){
            return null;
        }
        ContractQueryVO contractQueryVO = new ContractQueryVO();
        BeanUtils.copyProperties(busContract, contractQueryVO);
        String directorTime = null == busContract.getDirectorTime() ? "" :
                DateFormatUtils.format(busContract.getDirectorTime(), "yyyy-MM-dd HH:mm:ss");
        String guardianTime = null == busContract.getGuardianTime() ? "" :
                DateFormatUtils.format(busContract.getGuardianTime(), "yyyy-MM-dd HH:mm:ss");
        contractQueryVO.setDirectorTime(directorTime);
        contractQueryVO.setGuardianTime(guardianTime);
        return contractQueryVO;
    }

    @Override
    public PageVO<ContractQueryVO> queryContract(RiskNotificationQueryPO riskNotificationQueryPO) {
        Page<ContractQueryVO> result = PageHelper.startPage(riskNotificationQueryPO.getPageNum(), riskNotificationQueryPO.getPageSize());
        Example example = new Example(BusContract.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(riskNotificationQueryPO.getName())){
            criteria.andLike("name", "%" + riskNotificationQueryPO.getName() + "%");
        }
        if(!StringUtils.isEmpty(riskNotificationQueryPO.getBusinessNo())){
            criteria.andEqualTo("businessNo", riskNotificationQueryPO.getBusinessNo());
        }
        criteria.andEqualTo("isDel", 0);
        List<BusContract> busContractList = busContractMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(busContractList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        List<ContractQueryVO> contractQueryVOList = new ArrayList<>();
        busContractList.forEach(busContract -> {
            ContractQueryVO contractQueryVO = new ContractQueryVO();
            BeanUtils.copyProperties(busContract, contractQueryVO);
            String directorTime = null == busContract.getDirectorTime() ? "" :
                    DateFormatUtils.format(busContract.getDirectorTime(), "yyyy-MM-dd HH:mm:ss");
            String guardianTime = null == busContract.getGuardianTime() ? "" :
                    DateFormatUtils.format(busContract.getGuardianTime(), "yyyy-MM-dd HH:mm:ss");
            contractQueryVO.setDirectorTime(directorTime);
            contractQueryVO.setGuardianTime(guardianTime);
            contractQueryVOList.add(contractQueryVO);
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), contractQueryVOList);
    }
}
