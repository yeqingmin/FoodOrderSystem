create database if not exists food_order_sys;

use food_order_sys;

create table if not exists `umreview`
(
    `reviewId`        int  auto_increment primary key comment '评价id',
    `merchantRating`  int          not null default 0 comment '对商家的评分',
    `merchantComment` varchar(256) null comment '用户对于商家的评价',
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    `userId` INT NOT NULL comment '用户' ,
    `merchantId` INT NOT NULL comment '商家' ,
    FOREIGN KEY (userId) REFERENCES user (userId),
    FOREIGN KEY (merchantId) REFERENCES merchant (merchantId)
);

create table if not exists `umfavor`
(
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    `userId` INT NOT NULL comment '用户' ,
    `merchantId` INT NOT NULL comment '商家' ,
    foreign key (userId) references User (userId),
    foreign key (merchantId) references Merchant (merchantId)
);


create table if not exists `order`
(
    `orderId`  int auto_increment  primary key comment '订单id',
    `userId` INT NOT NULL comment '用户' ,
    `merchantId` INT NOT NULL comment '商家' ,
    `orderStatus` tinyint default 0 comment '订单状态，0表示已确认未完成（默认情况），1表示已完成',
    `isOnline` tinyint default 0 comment '是否线上点单 ， 0表示线上 ，1 表示线下',
    `orderTime` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '订单时间',
    foreign key (userId) references user(userId),
    foreign key (merchantId) references merchant(merchantId)
);
use food_order_sys;
create table if not exists `udreview`
(
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    `reviewId`        int  auto_increment primary key comment '评价id',
    `dishRating`  int          not null default 0 comment '对菜品的评分',
    `dishComment` varchar(256) null comment '用户对于菜品的评价',
    `dishId` INT NOT NULL comment '菜品id',
    `userId` INT NOT NULL comment '用户' ,
    FOREIGN KEY (userId) REFERENCES user (userId),
    FOREIGN KEY (dishId) REFERENCES dish (dishId)
);
use food_order_sys;
create table if not exists `udfavor`
(
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    `dishId` INT NOT NULL comment '菜品id',
    `userId` INT NOT NULL comment '用户' ,
    foreign key (userId) references User (userId),
    foreign key (dishId) references Dish (dishId)
);

# create table if not exists `Sale`
# (
#     `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
#     `isFeature` tinyint default 0 comment '表示是否主打，0为非主打，1为主打，默认为0',
#     `type` varchar(50) comment '菜品分类',
#     `dishId` INT NOT NULL comment '菜品id',
#     `merchantId` INT NOT NULL comment '商家' ,
#     foreign key (merchantId) references Merchant (merchantId),
#     foreign key (dishId) references Dish (dishId)
# );

CREATE TABLE if not exists `DishPrice` (
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    `id` INT AUTO_INCREMENT comment '历史价格id' PRIMARY KEY,
    `dishId` INT NOT NULL comment '菜品id',
    `price` float NOT NULL comment '价格',
    `validTime` DATETIME DEFAULT CURRENT_TIMESTAMP comment '当前价格结束时间',
    FOREIGN KEY (dishId) REFERENCES Dish(dishId) -- 假设菜品表名为Dish，且主键为id
);

CREATE TABLE if not exists `book` (
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    `bookId` INT AUTO_INCREMENT comment '预定单号' PRIMARY KEY,
    `userId` INT NOT NULL comment '下单用户' ,
    `merchantId` INT NOT NULL comment '预定商家' ,
    `bookStartTime` DATETIME NOT NULL comment '预定开始时间' ,
    `bookEndTime` DATETIME NOT NULL comment '预定结束时间' ,
    `bookStatus` ENUM('valid', 'invalid') NOT NULL comment '订单状态' ,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (merchantId) REFERENCES Merchant(merchantId),
    CHECK (bookStartTime <= bookEndTime)
);

create table if not exists `orderDetail`(
      `id` INT AUTO_INCREMENT comment '订单细节表的主键id' PRIMARY KEY,
      `dishId` INT NOT NULL comment '菜品id',
      `orderId` INT NOT NULL comment '订单id',
      FOREIGN KEY (dishId) REFERENCES dish(dishId),
      FOREIGN KEY (orderId)REFERENCES `order`(orderId)
);

use food_order_sys;
CREATE TABLE if not exists `bookmessage`
(
    `isDelete`         tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    messageId          INT PRIMARY KEY AUTO_INCREMENT,      -- 消息ID，设为主键并自动增长
    userId             INT NOT NULL comment '用户id',       -- 用户ID，外键关联用户表
    bookStatusMessage  VARCHAR(255) comment '预定确认信息', -- 预订确认消息，假设最大长度为255字符
    bookId            INT NOT NULL comment '订单id',
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '消息发送时间',
    FOREIGN KEY (userId) REFERENCES User (userId) ,          -- 假设用户表名为User，用户ID字段名为userId
    FOREIGN KEY (bookId) REFERENCES Book (bookId)
);

CREATE TABLE if not exists `ordermessage`
(
    `isDelete`         tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
    messageId          INT PRIMARY KEY AUTO_INCREMENT,      -- 消息ID，设为主键并自动增长
    userId             INT NOT NULL comment '用户id',       -- 用户ID，外键关联用户表
    orderStatusMessage VARCHAR(255) comment '订单状态信息', -- 订单状态消息，假设最大长度为255字符
    orderId            INT NOT NULL comment '订单id',
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '消息发送时间',
    FOREIGN KEY (userId) REFERENCES User (userId),         -- 假设用户表名为User，用户ID字段名为userId
    FOREIGN KEY (orderId) REFERENCES `Order` (orderId)
);