-- Initialization Data
-- ----------------------------
-- Table  xs_teamgroup 销售团队分组表
-- ----------------------------
DROP TABLE IF EXISTS `xs_teamGroup`;
CREATE TABLE `xs_teamGroup` (
  `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'Id',
  `groupName` varchar(255) NOT NULL DEFAULT '' COMMENT '分组名称',
  `projectId` int(11) NOT NULL COMMENT '项目id',
  `isProjectAdmin` BOOL NOT NULL COMMENT '是否项目经理',
  `Description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=gbk  COMMENT '销售团队分组表';



-- ----------------------------
-- Table xs_user_projectgroup 用户销售团队关联
-- ----------------------------
DROP TABLE IF EXISTS `xs_user_teamGroup`;
CREATE TABLE `xs_user_teamGroup` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  `userId` int(11) NOT NULL COMMENT '用户id',
  `teamGroupId` int(11) NOT NULL COMMENT '团队分组id',
  `userLevelId` int(11) NOT NULL COMMENT '客户等级id',      -- 1、团队经理    2、组长  3、置业顾问
  `Description` varchar(255) NOT NULL DEFAULT '' COMMENT '客户等级描述'
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT '用户销售团队关联' ;