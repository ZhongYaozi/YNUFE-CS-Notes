create table shopping_cart
(
    cart_id     bigint auto_increment comment '购物车ID'
        primary key,
    user_id     bigint                             not null comment '用户ID',
    flower_id   bigint                             not null comment '鲜花ID',
    quantity    int      default 1                 not null comment '购买数量',
    create_time datetime default CURRENT_TIMESTAMP null comment '添加时间',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint shopping_cart_ibfk_1
        foreign key (flower_id) references flower_info (flower_id)
            on delete cascade,
    constraint shopping_cart_ibfk_2
        foreign key (user_id) references sys_user (user_id)
            on delete cascade
)
    comment '购物车表' collate = utf8mb4_unicode_ci;

create index flower_id
    on shopping_cart (flower_id);

create index user_id
    on shopping_cart (user_id);

INSERT INTO flower.shopping_cart (cart_id, user_id, flower_id, quantity, create_time, update_time) VALUES (9, 1, 2, 5, '2025-06-24 15:16:07', '2025-06-24 15:16:08');
INSERT INTO flower.shopping_cart (cart_id, user_id, flower_id, quantity, create_time, update_time) VALUES (10, 1, 1, 4, '2025-06-24 15:16:09', '2025-06-24 15:16:10');