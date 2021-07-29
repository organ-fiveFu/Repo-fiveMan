package com.vblessings.nhs.service;

import com.vblessings.nhs.model.entity.sys.SysDictData;
import com.vblessings.nhs.model.po.QueryDictPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.result.UserInfoToken;

import java.util.List;
import java.util.Map;

public interface SysDictDataService {

    void add(SysDictData sysDictData, UserInfoToken userInfo);

    void update(SysDictData sysDictData, UserInfoToken userInfo);

    PageVO<SysDictData> select(QueryDictPO queryDictPO);

    void del(String dictCodes);

    /**
     * 根据code获取name
     * @param dictTypeCode
     * @param dictCodeList
     * @return
     */
    Map<String,String> getDictName(String dictTypeCode,List<String> dictCodeList);

    Map<String,List<String>> selectPullDown(String code);
}
