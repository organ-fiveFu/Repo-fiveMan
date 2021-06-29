package com.nurse.healthy.base;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

public interface BaseRepository<T> extends Mapper<T>, IdsMapper<T>, InsertListMapper<T> {
}
