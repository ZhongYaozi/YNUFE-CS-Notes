package com.czx.demoMP1234.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czx.demoMP1234.entity.Ebook;
import com.czx.demoMP1234.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Ebook> implements BookService {
}
