/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : online_food_order

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 21/03/2024 20:43:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `item_id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `number` int NOT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `img` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of carts
-- ----------------------------
BEGIN;
INSERT INTO `carts` (`id`, `item_id`, `name`, `unit_price`, `number`, `user_id`, `img`) VALUES ('0c745143c2824e20b3efe33e2e8668bc', '91708cd6b2c74ff49ce40d58f45eb0d5', '川味小炒肉', 18.00, 1, '53675d7f5b4f4825bc591395bb517384', 'http://localhost:8080/upload/d7d328a6-ffff-4e04-8525-ef8535a892f2');
INSERT INTO `carts` (`id`, `item_id`, `name`, `unit_price`, `number`, `user_id`, `img`) VALUES ('40c310702f8a480daabe54baa2c8c447', '510adc6d7ec047248e002d0d3658f873', '猫肉', 10.00, 1, '53675d7f5b4f4825bc591395bb517384', 'http://localhost:8080/upload/2dc3acfc-0739-4c1b-82d0-00fb44a65a7e');
INSERT INTO `carts` (`id`, `item_id`, `name`, `unit_price`, `number`, `user_id`, `img`) VALUES ('83f8b710589e4f87b169080c298be476', 'fdcc33680ea248c3a6a94d562d5b945f', '鱼香肉丝', 12.00, 1, '53675d7f5b4f4825bc591395bb517384', 'http://localhost:8080/upload/a61da90f-5cf5-4fa9-8084-c655bb4ac7f4');
COMMIT;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `contact` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `receiver` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of customers
-- ----------------------------
BEGIN;
INSERT INTO `customers` (`id`, `contact`, `receiver`, `user_id`, `address`) VALUES ('1f48af3e39b243cda5f240d1b0fc1a4b', '15729108729', '李红仪', '53675d7f5b4f4825bc591395bb517384', '河北省石家庄常山');
COMMIT;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `shop_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `img` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of items
-- ----------------------------
BEGIN;
INSERT INTO `items` (`id`, `shop_id`, `name`, `price`, `img`) VALUES ('510adc6d7ec047248e002d0d3658f873', NULL, '猫肉', 10.00, 'http://localhost:8080/upload/2dc3acfc-0739-4c1b-82d0-00fb44a65a7e');
INSERT INTO `items` (`id`, `shop_id`, `name`, `price`, `img`) VALUES ('91708cd6b2c74ff49ce40d58f45eb0d5', NULL, '川味小炒肉', 18.00, 'http://localhost:8080/upload/d7d328a6-ffff-4e04-8525-ef8535a892f2');
INSERT INTO `items` (`id`, `shop_id`, `name`, `price`, `img`) VALUES ('fdcc33680ea248c3a6a94d562d5b945f', NULL, '鱼香肉丝', 12.00, 'http://localhost:8080/upload/a61da90f-5cf5-4fa9-8084-c655bb4ac7f4');
COMMIT;

-- ----------------------------
-- Table structure for order_items
-- ----------------------------
DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `item_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `numbers` int NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `img` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `order_id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of order_items
-- ----------------------------
BEGIN;
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('25addb7359f8428ea95d8291d03a54d5', '91708cd6b2c74ff49ce40d58f45eb0d5', 2, 18.00, 'http://localhost:8080/upload/d7d328a6-ffff-4e04-8525-ef8535a892f2', 'c3e70d2726494447a5c12ad011a7ce0d', '川味小炒肉');
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('364f492b8ff948d0be1705135894224a', 'fdcc33680ea248c3a6a94d562d5b945f', 1, 12.00, 'http://localhost:8080/upload/a61da90f-5cf5-4fa9-8084-c655bb4ac7f4', 'ec85cee299f64082b2f72bc36dd32731', '鱼香肉丝');
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('6e6fda1f9b324166b68abf9a66f6dc46', '510adc6d7ec047248e002d0d3658f873', 1, 10.00, 'http://localhost:8080/upload/2dc3acfc-0739-4c1b-82d0-00fb44a65a7e', 'ec85cee299f64082b2f72bc36dd32731', '猫肉');
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('9e46968d00834900b756d3c73c149c14', '91708cd6b2c74ff49ce40d58f45eb0d5', 1, 18.00, 'http://localhost:8080/upload/d7d328a6-ffff-4e04-8525-ef8535a892f2', 'ec85cee299f64082b2f72bc36dd32731', '川味小炒肉');
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('c8340a339a4344f99c7ece1e287ddfe0', 'fdcc33680ea248c3a6a94d562d5b945f', 1, 12.00, 'http://localhost:8080/upload/a61da90f-5cf5-4fa9-8084-c655bb4ac7f4', 'c3e70d2726494447a5c12ad011a7ce0d', '鱼香肉丝');
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('e4d8491be2554e4fb3e0cded9d985f71', '510adc6d7ec047248e002d0d3658f873', 2, 10.00, 'http://localhost:8080/upload/2dc3acfc-0739-4c1b-82d0-00fb44a65a7e', '894b7ce63a9846a4992281e259699cf9', '猫肉');
INSERT INTO `order_items` (`id`, `item_id`, `numbers`, `unit_price`, `img`, `order_id`, `name`) VALUES ('fe88016bb05342ac902df28784348eb5', '510adc6d7ec047248e002d0d3658f873', 1, 10.00, 'http://localhost:8080/upload/2dc3acfc-0739-4c1b-82d0-00fb44a65a7e', '90fcfc4871714a8283057414178e12ed', '猫肉');
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `status` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `shop_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `receiver` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `create_time` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` (`id`, `status`, `shop_id`, `user_id`, `receiver`, `phone`, `address`, `money`, `create_time`) VALUES ('894b7ce63a9846a4992281e259699cf9', '已支付', NULL, '53675d7f5b4f4825bc591395bb517384', '李红仪', '15729108729', '河北省石家庄常山', 20.00, '2024-03-12 23:06:57');
INSERT INTO `orders` (`id`, `status`, `shop_id`, `user_id`, `receiver`, `phone`, `address`, `money`, `create_time`) VALUES ('90fcfc4871714a8283057414178e12ed', '已支付', NULL, '53675d7f5b4f4825bc591395bb517384', '李红仪', '15729108729', '河北省石家庄常山', 10.00, '2024-03-19 09:07:30');
INSERT INTO `orders` (`id`, `status`, `shop_id`, `user_id`, `receiver`, `phone`, `address`, `money`, `create_time`) VALUES ('c3e70d2726494447a5c12ad011a7ce0d', '已支付', NULL, '53675d7f5b4f4825bc591395bb517384', '李红仪', '15729108729', '河北省石家庄常山', 48.00, '2024-03-13 00:09:22');
INSERT INTO `orders` (`id`, `status`, `shop_id`, `user_id`, `receiver`, `phone`, `address`, `money`, `create_time`) VALUES ('ec85cee299f64082b2f72bc36dd32731', '已支付', NULL, '868def1f49794212895ec3fe851ad462', NULL, NULL, NULL, 40.00, '2024-03-13 00:12:17');
COMMIT;

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `user_id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `manager` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of shops
-- ----------------------------
BEGIN;
INSERT INTO `shops` (`id`, `user_id`, `name`, `manager`, `phone`, `address`) VALUES ('83546b8878a24a3eab96514fdc72a920', '34c385d519984d75890ae243879ade08', '12', '张三', '16726526272', '二仙桥走成华大道');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `account` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `role` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`id`, `account`, `password`, `role`) VALUES ('34c385d519984d75890ae243879ade08', 'admin', '123456', 'admin');
INSERT INTO `users` (`id`, `account`, `password`, `role`) VALUES ('53675d7f5b4f4825bc591395bb517384', 'zhangsan', '123456', 'user');
INSERT INTO `users` (`id`, `account`, `password`, `role`) VALUES ('868def1f49794212895ec3fe851ad462', 'liis', '123456', 'user');
INSERT INTO `users` (`id`, `account`, `password`, `role`) VALUES ('c0ed1b62c97b44e48b4ef672c62bd5fa', 'admin', '123456', 'user');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
