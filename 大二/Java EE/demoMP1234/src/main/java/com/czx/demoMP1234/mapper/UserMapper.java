package com.czx.demoMP1234.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czx.demoMP1234.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}