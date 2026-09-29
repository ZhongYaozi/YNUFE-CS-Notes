package com.czx.demoMP1234;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.czx.demoMP1234.entity.Ebook;
import com.czx.demoMP1234.mapper.BookMapper;
import com.czx.demoMP1234.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoMp1234ApplicationTests {
    @Test
    void contextLoads() {

    }

    @Autowired(required = false)//add required
    private BookMapper bookMapper;
    @Test
    public  void findAll(){
        bookMapper.selectList(null).forEach(System.out::println);
        //bookMapper.insert(new Ebook());
    }
    @Autowired
    private BookService bookService;
    @Test
    public void findBook(){
        bookService.list().forEach(System.out::println);
        QueryWrapper<Ebook> wrapper=new QueryWrapper<>();//where 子句
        bookService.list(wrapper.like("name","辞").eq("status","0")).forEach(System.out::println);;
    }

}
