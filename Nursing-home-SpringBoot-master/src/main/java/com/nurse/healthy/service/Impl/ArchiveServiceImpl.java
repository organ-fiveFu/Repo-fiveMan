package com.nurse.healthy.service.Impl;

import com.nurse.healthy.mapper.ArchiveMapper;
import com.nurse.healthy.model.entity.Archive;
import com.nurse.healthy.service.ArchiveService;
import com.nurse.healthy.vo.ResultBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Resource
    private ArchiveMapper archiveMapper;
    @Override
    public ResultBody<Archive> selectInfo() {
        Archive archive = Archive.builder().name("ZZ").name("测试").id(11L).build();
        archiveMapper.insert(archive);
        return null;
    }
}
