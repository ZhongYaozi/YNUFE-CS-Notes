create table flower_info
(
    flower_id        bigint auto_increment comment '鲜花ID'
        primary key,
    flower_name      varchar(100)   not null comment '鲜花名称',
    flower_image_url varchar(500)   null comment '鲜花图片URL',
    flower_price     decimal(10, 2) not null comment '鲜花单价(元)',
    remark           text           null comment '备注信息'
)
    comment '鲜花信息表' collate = utf8mb4_unicode_ci;

INSERT INTO flower.flower_info (flower_id, flower_name, flower_image_url, flower_price, remark) VALUES (1, '玫瑰花', '/profile/upload/2025/06/18/玫瑰_20250618223332A001.jpg,/profile/upload/2025/06/24/QQ图片20230930233509_20250624151502A002.jpg', 5.50, '经典红玫瑰，适合表达爱意');
INSERT INTO flower.flower_info (flower_id, flower_name, flower_image_url, flower_price, remark) VALUES (2, '康乃馨', '/profile/upload/2025/06/18/康乃馨_20250618223351A002.jpg', 3.20, '母亲节热销花卉，颜色丰富');
INSERT INTO flower.flower_info (flower_id, flower_name, flower_image_url, flower_price, remark) VALUES (3, '百合花', '/profile/upload/2025/06/18/百合花_20250618223422A003.jpg', 8.80, '纯洁优雅，婚庆常用');
INSERT INTO flower.flower_info (flower_id, flower_name, flower_image_url, flower_price, remark) VALUES (4, '向日葵', '/profile/upload/2025/06/18/向日葵_20250618223440A004.jpg', 6.00, '阳光向上，寓意美好');
INSERT INTO flower.flower_info (flower_id, flower_name, flower_image_url, flower_price, remark) VALUES (5, '郁金香', '/profile/upload/2025/06/18/郁金香_20250618223449A005.jpg', 12.50, '荷兰进口品种，高档花卉');