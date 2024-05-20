create database if not exists food_order_sys;

use food_order_sys;

create table if not exists `UMReview`
(
    `reviewId`        int  auto_increment primary key comment '评价id',
    `merchantRating`  int          not null default 0 comment '对商家的评分',
    `merchantComment` varchar(256) null comment '用户对于商家的评价',
    FOREIGN KEY (userId) REFERENCES User (userId),
    FOREIGN KEY (merchantId) REFERENCES Merchant (merchantId)
);

create table if not exists `UMFavor`
(
    foreign key (userId) references User (userId),
    foreign key (merchantId) references Merchant (merchantId)
);

create table if not exists `Order`
(
    `orderId`  int auto_increment  primary key comment '订单id',
    foreign key (userId) references User(userId),
    foreign key (merchantId) references Merchant(merchantId),
    `orderStatus` tinyint default 0 comment '订单状态，0表示未完成（默认情况），1表示已完成',
    `totalPrice` float comment '订单总价'
);
create table if not exists `UDReview`
(
    `reviewId`        int  auto_increment primary key comment '评价id',
    `dishRating`  int          not null default 0 comment '对菜品的评分',
    `dishComment` varchar(256) null comment '用户对于菜品的评价',
    FOREIGN KEY (userId) REFERENCES User (userId),
    FOREIGN KEY (dishId) REFERENCES Dish (dishId)
);

create table if not exists `UDFavor`
(
    foreign key (userId) references User (userId),
    foreign key (dishId) references Dish (dishId)
);

create table if not exists 'Sale'
(
    `isFeature` tinyint default 0 comment '表示是否主打，0为非主打，1为主打，默认为0',
    foreign key (merchantId) references Merchant (merchantId),
    foreign key (dishId) references Dish (dishId)
);
