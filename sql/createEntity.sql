CREATE TABLE if not exists users (
    userId INT AUTO_INCREMENT comment '用户id' PRIMARY KEY,
    userName VARCHAR(255) NOT NULL comment '用户姓名',
    userGender ENUM('male', 'female', 'other') NOT NULL comment '用户性别'
);
CREATE TABLE if not exists dishes (
    dishId INT AUTO_INCREMENT comment '菜品id' PRIMARY KEY,
    dishName VARCHAR(255) NOT NULL comment '菜品名称',
    dishPrice DECIMAL(10, 2) NOT NULL comment '菜品价格', -- 假设价格最多有10位数字，其中2位是小数
    dishCategory VARCHAR(255) NOT NULL comment '菜品分类',
    dishDescription TEXT comment '菜品描述',
    dishImage BLOB comment '菜品图片', -- BLOB用于存储二进制数据，如图片
    merchantId INT comment '商户名称',
    FOREIGN KEY (merchantId) REFERENCES merchants(merchantId) -- 假设商户表名为merchants，商户ID字段名为merchant_id
);
CREATE TABLE if not exists merchants (
    merchantId INT AUTO_INCREMENT comment '商户id' PRIMARY KEY,
    merchantAddr VARCHAR(255) NOT NULL comment '商户地址'
);
