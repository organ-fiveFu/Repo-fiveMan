package com.nurse.healthy.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nurse.healthy.component.SnowflakeComponent;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.mapper.CareMapper;
import com.nurse.healthy.model.entity.sys.SysCarerInfo;
import com.nurse.healthy.model.entity.sys.SysDictType;
import com.nurse.healthy.model.entity.sys.SysEmployeeInfo;
import com.nurse.healthy.model.po.KeyWordPO;
import com.nurse.healthy.model.vo.PageVO;
import com.nurse.healthy.result.UserInfoToken;
import com.nurse.healthy.service.CareService;
import com.nurse.healthy.util.OperateUtil;
import com.nurse.healthy.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service

public class CareServiceImpl implements CareService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private CareMapper careMapper;
    @Override
    public void add(SysCarerInfo sysCarerInfo, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        //验证code是否重复
        List<String> codes = careMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysCarerInfo.getCareCode())){
                throw new MyException("code已重复,不可增加此护工");
            }
        }
        OperateUtil.onSaveNew(sysCarerInfo,userInfo,id);
        careMapper.insert(sysCarerInfo);

    }

    @Override
    @Transactional
    public void update(SysCarerInfo sysCarerInfo, UserInfoToken userInfo) {
        //验证code是否重复
      /*  List<String> codes = careMapper.selectAllCode();
        if(codes!=null && !codes.isEmpty()){
            if(codes.contains(sysCarerInfo.getCareCode())){
                throw new MyException("code已重复,不可使用相同code");
            }
        }*/
        sysCarerInfo.setUpdateTime(new Date());
        sysCarerInfo.setUpdaterId(userInfo.getUserId());
        careMapper.updateByPrimaryKeySelective(sysCarerInfo);

    }

    @Override
    public PageVO<SysCarerInfo> select(KeyWordPO keyWordPO) {
        Page<SysCarerInfo> result = PageHelper.startPage(keyWordPO.getPageNum(), keyWordPO.getPageSize());
        Example example = new Example(SysCarerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        if(keyWordPO.getKeyWord()!=null){
            if(StringUtil.isChinese(keyWordPO.getKeyWord())){
                criteria.andLike("name","%"+keyWordPO.getKeyWord()+"%");
            }
            //如果是数字说明查code
            if(StringUtil.isNumeric(keyWordPO.getKeyWord())){
                criteria.andEqualTo("careCode",keyWordPO.getKeyWord());
            }
        }
        List<SysCarerInfo> sysCarerInfoList = careMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), sysCarerInfoList);

    }

    @Override
    @Transactional
    public void del(String ids) {
        String[] id = ids.split(",");
        careMapper.del(id);
    }
}
