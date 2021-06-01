package com.nurse.healthy.service;

import com.nurse.healthy.model.entity.Archive;
import com.nurse.healthy.vo.ResultBody;

public interface ArchiveService {
    ResultBody<Archive> selectInfo();
}
