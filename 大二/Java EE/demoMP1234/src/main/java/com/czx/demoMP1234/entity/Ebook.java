package com.czx.demoMP1234.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

@Data
@TableName("book")
public class Ebook {
    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    private String author;
    private String press;
    private String status;


}
