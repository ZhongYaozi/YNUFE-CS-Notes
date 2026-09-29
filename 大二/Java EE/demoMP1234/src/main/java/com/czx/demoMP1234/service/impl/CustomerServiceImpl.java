package com.czx.demoMP1234.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.demoMP1234.entity.Customer;
import com.czx.demoMP1234.entity.User;
import com.czx.demoMP1234.mapper.CustomerMapper;
import com.czx.demoMP1234.mapper.UserMapper;
import com.czx.demoMP1234.service.CustomerService;
import com.czx.demoMP1234.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
   
}