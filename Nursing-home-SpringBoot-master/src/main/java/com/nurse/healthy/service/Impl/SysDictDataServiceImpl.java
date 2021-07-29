package com.nurse.healthy.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.mapper.SysDictDataMapper;
import com.nurse.healthy.model.entity.business.BusComplaintsRecord;
import com.nurse.healthy.model.entity.sys.SysDictData;
import com.nurse.healthy.model.po.QueryDictPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.SysDictDataService;
import com.nurse.healthy.util.OperateUtil;
import com.nurse.healthy.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SysDictDataServiceImpl implements SysDictDataService {
    @Resource
    private SysDictDataMapper sysDictDataMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    @Transactional
    public void add(SysDictData sysDictData, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        //找寻此类别下的所有code
        List<String> dictCodeList = sysDictDataMapper.selectByTypeCode(sysDictData.getDictTypeCode());
        if(dictCodeList!=null && !dictCodeList.isEmpty()){
            if(dictCodeList.contains(sysDictData.getDictCode())){
                throw new MyException("该字典明细code已存在");
            }
        }
        OperateUtil.onSaveNew(sysDictData,userInfo,id);
        sysDictDataMapper.insert(sysDictData);
    }

    @Override
    @Transactional
    public void update(SysDictData sysDictData, UserInfoToken userInfo) {
        sysDictData.setUpdaterId(userInfo.getUserId());
        sysDictData.setUpdateTime(new Date());
        sysDictDataMapper.updateByPrimaryKeySelective(sysDictData);
    }

    @Override
    public PageVO<SysDictData> select(QueryDictPO queryDictPO) {
        Page<SysDictData> result = PageHelper.startPage(queryDictPO.getPageNum(), queryDictPO.getPageSize());

        Example example = new Example(SysDictData.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("isDel",0);
        if(queryDictPO.getTypeCode()!=null){
        criteria.andEqualTo("dictTypeCode",queryDictPO.getTypeCode());}
        if(queryDictPO.getSearch()!=null){
            //如果是中文 查字典name
            if(StringUtil.isChinese(queryDictPO.getSearch())){
                criteria.andEqualTo("dictName",queryDictPO.getSearch());
            }
            //如果是数字说明查code
            if(StringUtil.isNumeric(queryDictPO.getSearch())){
                criteria.andEqualTo("dictCode",queryDictPO.getSearch());
            }
            //如果是英文说明为五笔码或者拼音码
            if(StringUtil.isEnglish(queryDictPO.getSearch())){
                Example.Criteria criteria1 = example.createCriteria();
                criteria1.orLike("wubiCode",queryDictPO.getSearch())
                        .orLike("pinyinCode",queryDictPO.getSearch());
                example.and(criteria1);
            }
        }
        List<SysDictData>  sysDictDataList = sysDictDataMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysDictDataList);

    }

    @Override
    public void del(String dictCodes) {
        String[] code = dictCodes.split(",");
        sysDictDataMapper.updateIsDel(code);
    }

    @Override
    public Map<String, String> getDictName(String code,List<String> dictCodeList) {
        Example example = new Example(SysDictData.class);
        Example.Criteria criteria = example.createCriteria();
        example.selectProperties("dictCode", "dictName");
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("dictTypeCode", code);

        if (!CollectionUtils.isEmpty(dictCodeList)) {
            criteria.andIn("dictCode", dictCodeList);
        }

        List<SysDictData> sysDictDataList = sysDictDataMapper.selectByExample(example);

        // 查询结果为空，返回空列表
        if (CollectionUtils.isEmpty(sysDictDataList)) {
            log.info("对应查询字典详情列表结果为空");
            return new HashMap();
        }

        // 将获得的参数拼成 Map<dictCode, dictName>的形式
        Map<String, String> result = new HashMap();
        sysDictDataList.forEach(sysDictData -> {
            result.put(sysDictData.getDictCode(), sysDictData.getDictName());
        });
        return result;
    }

    @Override
    public Map<String, List<String>> selectPullDown(String code) {
        return null;
    }

}
