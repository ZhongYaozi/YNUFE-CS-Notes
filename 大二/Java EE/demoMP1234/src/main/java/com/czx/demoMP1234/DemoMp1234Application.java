package com.czx.demoMP1234;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.czx.demoMP1234.mapper")
public class DemoMp1234Application {
    public static void main(String[] args) {
        SpringApplication.run(DemoMp1234Application.class, args);
    }

}


