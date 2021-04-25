create table customer (
    customer_id int not null comment "用户id",
    name varchar(20) not null comment "用户昵称",
    avatar char(44) not null comment "用户头像",
    login_name varchar(50) not null comment "登录名",
    passwd char(32) not null comment "密码",
    solt char(5) not null comment "加盐",
    status tinyint nut null default 1 comment "用户状态 1 正常 0 注销"
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (id)
) engine=innodb default charset=utf8mb4 comment "用户表";

create table customer_addr (
    customer_addr_id int unsigned not null commit "主键",
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
) engine=innodb default charset=utf8mb4 comment "地址表";

create table brand (
    brand_id int AUTO_INCREMENT not null comment "品牌ID",
    brand_name varchar(50) not null comment "名称",
    logo varchar(100) not null comment "品牌logo",
    status tinyint not null default 1 comment "品牌状态 1 正常 0 禁用"
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY （brand_id）
) engine=innodb default charset=utf8mb4 comment "品牌表";


create table category (
    category_id int AUTO_INCREMENT not null comment "分类ID",
    category_name varchar(15) not null  comment "分类名称",
    pid int not null default 0 comment "父分类id",
    category_level tinyint not null default 1 "分类层级",
    status tinyint not null default 1 comment "分类状态 1 正常 0 禁用",
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (category_id)
) engine=innodb default charset=utf8mb4 comment "分类表";


create table prduct (
    product_id int not null comment "商品ID",
    product_name varchar(20) not null comment "商品名称",
    brand_id int not null comment "品牌ID",
    category_id int not null comment "分类ID",
    price decimal(8, 2) not null comment "商品价格",
    product_pic varchar(100) not null comment "商品图片",
    description varchar(100) not null comment "商品描述",
    count int not null default 0 comment "库存",
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (product_id)
) engine=innodb default charset=utf8mb4 comment "商品表";

create table order (
    order_id int not null comment "订单ID",
    customer_id int not null comment "用户id",
    brand_id int not null comment "品牌ID",
    province smallint not null comment "省份编码",
    city smallint not null comment "城市编码",
    district smallint not null comment "地区编码",
    address varchar(200) not null comment "详细地址",
    price decimal(8, 2) not null comment "商品价格",
    pay_money decimal(8, 2) not null comment "支付金额",
    pay_time TIMESTAMP  COMMENT '支付时间',
    shipping_name varchar(15) not null comment "快递名字",
    shipping_sn varchar(50) not null comment "快递单号",
    shipping_time TIMESTAMP  COMMENT '发货时间',
    receive_time TIMESTAMP  COMMENT '收货时间',
    status tinyint not null default 0 comment "订单状态"
    invoice varchar(30) comment "发票抬头",
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (order_id)
) engine=innodb default charset=utf8mb4 comment "订单表";

create table order_product (
    order_id int not null comment "订单ID",
    product_id int not null comment "用户id",
    product_name varchar(20) not null comment "商品名称",
    brand_id int not null comment "品牌ID",
    category_id int not null comment "分类ID",
    price decimal(8, 2) not null comment "商品价格",
    count int not null default 1 comment "购买数量",
    createtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modifytime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (order_id)
) engine=innodb default charset=utf8mb4 comment "订单商品快照";

create table shopping_cart (
    cart_id int AUTO_INCREMENT not null comment "订单ID",
    customer_id int not null comment "用户id",
    product_id int not null comment "用户id",
    count  int not null default 1 comment "购买数量",
    price decimal(8, 2) not null comment "商品价格",
    add_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (cart_id)
) engine=innodb default charset=utf8mb4 comment "购物车";
