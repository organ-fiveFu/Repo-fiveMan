package com.vblessings.nhs.service.Impl;

import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.MyException;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.SysDictDataMapper;
import com.vblessings.nhs.mapper.SysDictTypeMapper;
import com.vblessings.nhs.model.entity.sys.SysDictData;
import com.vblessings.nhs.model.entity.sys.SysDictType;
import com.vblessings.nhs.model.vo.base.PullDownVo;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.SysDictService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Resource
    private SysDictDataMapper sysDictDataMapper;

    @Override
    @Transactional
    public void add(SysDictType sysDictType, UserInfoToken userInfo) {
     Long id = snowflakeComponent.getInstance().nextId();
        //验证code是否重复
        List<SysDictType> sysDictTypeList = sysDictTypeMapper.selectAll();
        List<String> codes = sysDictTypeList.stream().map(SysDictType::getDictTypeCode).collect(Collectors.toList());
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysDictType.getDictTypeCode())){
                throw ResponseEnum.CODE_ALREADY_EXISTS.newException("code已重复");
            }
        }
        OperateUtil.onSaveNew(sysDictType,userInfo,id);
        sysDictTypeMapper.insert(sysDictType);

    }

    @Override
    @Transactional
    public void update(SysDictType sysDictType,UserInfoToken userInfo) {
        //验证code是否重复
      /*  List<String> codes = sysDictTypeMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysDictType.getDictTypeCode())){
                throw new MyException("code已重复");
            }
        }*/
        sysDictType.setUpdateTime(new Date());
        sysDictType.setUpdaterId(userInfo.getUserId());
        sysDictTypeMapper.updateByPrimaryKeySelective(sysDictType);
    }

    @Override
    public List<SysDictType> select(String keyWord) {
        Example example = new Example(SysDictType.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("isDel",0);
        if(keyWord!=null){
            if(StringUtil.isChinese(keyWord)){
                c.andLike("dictTypeName","%"+keyWord+"%");
            }
            if(StringUtil.isNumeric(keyWord)){
                c.andEqualTo("dictTypeCode",keyWord);
            }
            if(StringUtil.isEnglish(keyWord)){
                Example.Criteria criteria1 = example.createCriteria();
                criteria1.orLike("wubiCode",keyWord)
                        .orLike("pinyinCode",keyWord);
                example.and(criteria1);
            }
        }
        List<SysDictType> sysDictTypeList = sysDictTypeMapper.selectByExample(example);
        return sysDictTypeList;
    }

    @Override
    @Transactional
    public void del(String typeCodes) {
        //判断大类型下有没有明细
        List<String> result = Arrays.asList(typeCodes.split(","));
        for (String typeCode:
                result) {
            Example example = new Example(SysDictData.class);
            example.selectProperties("dictCode");
            example.createCriteria().andEqualTo("dictTypeCode",typeCode).andEqualTo("isDel",0);

            List<SysDictData> sysDictDataList = sysDictDataMapper.selectByExample(example);
            List<String> dictDataList = sysDictDataList.stream().map(SysDictData::getDictCode).collect(Collectors.toList());
            if(dictDataList!=null && !dictDataList.isEmpty()){
                throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该字典类型下存在明细不可删除");
            }
            sysDictTypeMapper.delByTypeCode(typeCode);
        }
    }

    @Override
    public Map selectPullDown(List<String> dictTypeCodes) {
        Example example = new Example(SysDictData.class);
        example.selectProperties("dictTypeCode","dictCode","dictName");
        Example.Criteria c = example.createCriteria();
        c.andIn("dictTypeCode",dictTypeCodes).andEqualTo("isDel",0);
        List<SysDictData> sysDictDataList = sysDictDataMapper.selectByExample(example);
        Map<String, List<PullDownVo>> map = new HashMap<>();
        List<PullDownVo> list= new ArrayList<>();
        for (SysDictData s:
                sysDictDataList) {
            PullDownVo pullDownVo = new PullDownVo();
            pullDownVo.setDictTypeCode(s.getDictTypeCode());
            pullDownVo.setValue(s.getDictCode());
            pullDownVo.setLabel(s.getDictName());
            list.add(pullDownVo);
        }
        return list.stream().collect(
                Collectors.groupingBy(PullDownVo::getDictTypeCode));
    }
}
