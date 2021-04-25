create table customer (
    customer_id int not null comment "用户id",
    name varchar(20) not null comment "用户昵称",
    avatar char(44) not null comment "用户头像",
    login_name varchar(50) not null comment "登录名",
    passwd char(32) not null comment "密码",
    solt char(5) not null comment "加盐",
    status tinyint nut null default 1 comment "用户状态"
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (id)
) engine=innodb default charset=utf8mb4 comment "用户表"

create table customer_addr (
    customer_addr_id int unsigned AUTO_INCREMENT not null commit "自增主键",
    customer_id int not null comment "用户id",
    zip smallint not null comment "邮编",
    province smallint not null comment "省份编码",
    city smallint not null comment "城市编码",
    district smallint not null comment "地区编码",
    address varchar(200) not null comment "详细地址",
    is_default tinyint not null comment "是否默认",
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (customer_addr_id)
) engine=innodb default charset=utf8mb4 comment "地址表"

create table categroy (
    categoryid int not null,
    name varchar(50) not null,
) engine=innodb default charset=utf8mb4

create table goods (
    goodsid int not null,
) engine=innodb default charset=utf8mb4
