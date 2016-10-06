-- Initialization Data
-- ----------------------------
-- Table  zs_customerlog 招商客户分配日志表
-- ----------------------------
DROP TABLE IF EXISTS `zs_customerlog`;
CREATE TABLE `zs_customerlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cusId` int(11) NOT NULL,
  `logType` varchar(255) NOT NULL DEFAULT '' COMMENT '日志类型',
  `operDate` datetime DEFAULT NULL COMMENT '操作时间',
  `opercation` varchar(255) NOT NULL DEFAULT '' COMMENT '操作人',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `description` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=gbk;

