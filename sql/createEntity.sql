use food_order_sys;
CREATE TABLE if not exists User (
    `userId` INT AUTO_INCREMENT comment '用户id' PRIMARY KEY,
    `userName` VARCHAR(255) NOT NULL comment '用户姓名',
    `userGender` ENUM('male', 'female', 'other') NOT NULL comment '用户性别',
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除'
);


use food_order_sys;
CREATE TABLE if not exists merchant (
    `merchantId` INT AUTO_INCREMENT comment '商户id' PRIMARY KEY,
    `merchantAddr` VARCHAR(255) NOT NULL comment '商户地址',
    `merchantName` VARCHAR(255) NOT NULL comment '商户名称',
    `featureDish`  VARCHAR(255) comment '主打菜品',
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除'
);

use food_order_sys;
CREATE TABLE if not exists Dish (
     `dishId` INT AUTO_INCREMENT comment '菜品id' PRIMARY KEY,
     `dishName` VARCHAR(255) NOT NULL comment '菜品名称',
     `dishPrice` float NOT NULL comment '菜品价格', -- 假设价格最多有10位数字，其中2位是小数
     `dishCategory` VARCHAR(255) NOT NULL comment '菜品分类',
     `dishDescription` varchar(255) comment '菜品描述',
     `dishFavourNumber` int(20) default 0 comment '菜品收藏量',
     `dishImage` BLOB comment '菜品图片', -- BLOB用于存储二进制数据，如图片
     `dishAllergens` varchar(255) comment  '菜品过敏源',
     `dishIngredients` varchar(255) comment '菜品成分',
     `dishNutrition` varchar(255) comment '菜品营养信息',
     `merchantId` INT comment '商户名称',
     `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除',
     FOREIGN KEY (merchantId) REFERENCES Merchant(merchantId) -- 假设商户表名为merchants，商户ID字段名为merchantId
);
CREATE TABLE if not exists Login (
     `password` VARCHAR(255) NOT NULL comment '登录密码',
     `name` VARCHAR(255) NOT NULL comment '登录用户名',
     `correspondingID` INT NOT NULL comment '对应库表的id',
     `role` ENUM('user', 'merchant', 'admin') NOT NULL,
     PRIMARY KEY (password, name),
    `isDelete`  tinyint default 0 comment '是否删除，0表示已经删除了，1表示已经删除'
);
