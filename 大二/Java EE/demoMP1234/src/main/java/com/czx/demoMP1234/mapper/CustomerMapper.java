package com.czx.demoMP1234.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czx.demoMP1234.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}