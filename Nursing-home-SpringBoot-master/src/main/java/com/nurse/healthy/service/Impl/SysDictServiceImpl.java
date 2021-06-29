package com.nurse.healthy.service.Impl;

import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.mapper.SysDictDataMapper;
import com.nurse.healthy.mapper.SysDictTypeMapper;
import com.nurse.healthy.model.entity.sys.SysDictData;
import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.SysDictService;
import com.nurse.healthy.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
                throw new MyException("code已重复");
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
    public List<SysDictType> select() {
        Example example = new Example(SysDictType.class);
        example.createCriteria().andEqualTo("isDel",0);
        List<SysDictType> sysDictTypeList = sysDictTypeMapper.selectByExample(example);
        return sysDictTypeList;
    }

    @Override
    @Transactional
    public void del(List<String> typeCodes) {
        //判断大类型下有没有明细
        for (String typeCode:
        typeCodes) {
            Example example = new Example(SysDictData.class);
            example.selectProperties("dictCode");
            example.createCriteria().andEqualTo("dictTypeCode",typeCode);

          List<SysDictData> sysDictDataList = sysDictDataMapper.selectByExample(example);
          List<String> dictDataList = sysDictDataList.stream().map(SysDictData::getDictCode).collect(Collectors.toList());
          if(dictDataList!=null && !dictDataList.isEmpty()){
              throw new MyException("该字典类型下存在明细不可删除");
          }
            sysDictTypeMapper.delByTypeCode(typeCode);
        }
    }
}
