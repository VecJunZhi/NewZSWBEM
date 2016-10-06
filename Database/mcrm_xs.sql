-- Initialization Data
-- ----------------------------
-- Table  xs_teamgroup �����Ŷӷ����
-- ----------------------------
DROP TABLE IF EXISTS `xs_teamGroup`;
CREATE TABLE `xs_teamGroup` (
  `Id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'Id',
  `groupName` varchar(255) NOT NULL DEFAULT '' COMMENT '��������',
  `projectId` int(11) NOT NULL COMMENT '��Ŀid',
  `isProjectAdmin` BOOL NOT NULL COMMENT '�Ƿ���Ŀ����',
  `Description` varchar(255) NOT NULL DEFAULT '' COMMENT '����'
) ENGINE=InnoDB DEFAULT CHARSET=gbk  COMMENT '�����Ŷӷ����';



-- ----------------------------
-- Table xs_user_projectgroup �û������Ŷӹ���
-- ----------------------------
DROP TABLE IF EXISTS `xs_user_teamGroup`;
CREATE TABLE `xs_user_teamGroup` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  `userId` int(11) NOT NULL COMMENT '�û�id',
  `teamGroupId` int(11) NOT NULL COMMENT '�Ŷӷ���id',
  `userLevelId` int(11) NOT NULL COMMENT '�ͻ��ȼ�id',      -- 1���ŶӾ���    2���鳤  3����ҵ����
  `Description` varchar(255) NOT NULL DEFAULT '' COMMENT '�ͻ��ȼ�����'
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT '�û������Ŷӹ���' ;