-- Initialization Data
-- ----------------------------
-- Table  zs_customerlog ���̿ͻ�������־��
-- ----------------------------
DROP TABLE IF EXISTS `zs_customerlog`;
CREATE TABLE `zs_customerlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cusId` int(11) NOT NULL,
  `logType` varchar(255) NOT NULL DEFAULT '' COMMENT '��־����',
  `operDate` datetime DEFAULT NULL COMMENT '����ʱ��',
  `opercation` varchar(255) NOT NULL DEFAULT '' COMMENT '������',
  `content` varchar(255) NOT NULL COMMENT '����',
  `description` varchar(255) NOT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=gbk;

