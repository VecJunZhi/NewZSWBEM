/**用户权限控制心中数据结构*通用**/


/*==============================================================*/
/* Table: user       用户表                                                                           */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User;
CREATE TABLE RBAC_User(
   UserID            INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'用户ID',
   UserName          VARCHAR(512)  NOT NULL COMMENT'用户名称',
   RealName          VARCHAR(512)  NOT NULL COMMENT'真实姓名',
   Password          VARCHAR(512)  NOT NULL COMMENT'用户密码',
   Mobile            VARCHAR(512)  NOT NULL COMMENT'手机号码',
   EMail             VARCHAR(512)  NOT NULL COMMENT'邮件地址',
   LoginType         VARCHAR(512)  NOT NULL COMMENT'登录方式',       --UserName:Mobile:EMail   以:号分隔
   UStatus           INT           NOT NULL COMMENT'用户状态',     --状态 0禁用  1启用
   Locked            BOOL          NOT NULL COMMENT'是否锁定',   
   CreateTime        DATETIME      NOT NULL COMMENT'创建时间',
   LastTime          DATETIME      NOT NULL COMMENT'最后修改时间',
   Description       VARCHAR(1024) NOT NULL COMMENT'描述'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户信息表';


/*==============================================================*/
/* Table: Role       角色表                                                                           */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Role;
CREATE TABLE RBAC_Role(
   RoleID      INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'角色ID',
   RoleName    VARCHAR(512)  NOT NULL COMMENT'角色名称',
   Available   BOOL          NOT NULL COMMENT'是否可用',
   Priority    VARCHAR(512)  NOT NULL COMMENT'角色排序',
   CreateTime  DATETIME      NOT NULL COMMENT'创建时间',
   LastTime    DATETIME      NOT NULL COMMENT'最后修改时间',
   Description VARCHAR(1024) NOT NULL COMMENT'描述'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='角色表';

/*==============================================================*/
/* Table: User_Role   用户角色映射表                                                          */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User_Role;
CREATE TABLE RBAC_User_Role(
   URID         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'URID',
   UserID       VARCHAR(512) NOT NULL COMMENT'用户ID',
   RoleID       VARCHAR(512) NOT NULL COMMENT'角色ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户角色映射表';


/*==============================================================*/
/* Table: Role_Permission 角色权限映射表                                                  */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Role_Permission;
CREATE TABLE RBAC_Role_Permission(
   RPID                 INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'RPID',
   RoleID               INT NOT NULL COMMENT'角色ID',
   PermissionID         INT NOT NULL COMMENT'权限ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='角色权限映射表';


/*==============================================================*/
/* Table: Permission       权限表                                                               */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Permission;
CREATE TABLE RBAC_Permission(
   PermissionID                   INT             NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'权限ID',
   PermissionName                 VARCHAR(512)     NOT NULL COMMENT'权限名称',
   PermissionMark                 VARCHAR(512)     NOT NULL COMMENT'权限标识',
   PermissionType                 VARCHAR(512)     NOT NULL COMMENT'权限类型',
   ParentID                       INT              NOT NULL COMMENT'上级ID'  ,
   URL                       	  VARCHAR(512)     NOT NULL COMMENT'权限地址',
   Priority                       INT              NOT NULL COMMENT'显示顺序',
   Available                      BOOL             NOT NULL COMMENT'是否可用',
   Description                    VARCHAR(1024)    NOT NULL COMMENT'描述',
   CreateTime                     DATETIME         NOT NULL COMMENT'创建时间',
   LastTime                       DATETIME         NOT NULL COMMENT'最后修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='权限表';

/*==============================================================*/
/* Table: UserGroup  用户组表                                                                       */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_UserGroup;
CREATE TABLE RBAC_UserGroup(
   GroupID         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'用户组ID',
   GroupName       VARCHAR(512)  NOT NULL COMMENT'用户组名称',
   Available       BOOL          NOT NULL COMMENT'是否可用',
   ParentGroupID   INT           NOT NULL COMMENT'上级分组ID',
   Priority        VARCHAR(512)  NOT NULL COMMENT'分组排序',
   Description     VARCHAR(1024) NOT NULL COMMENT'描述',
   CreateTime      DATETIME      NOT NULL COMMENT'创建时间',
   LastTime        DATETIME      NOT NULL COMMENT'最后修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户组表';


/*==============================================================*/
/* Table: User_UserGroup    用户_用户组映射表                                        */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User_UserGroup;
CREATE TABLE RBAC_User_UserGroup(
   UUGID               INT     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'UUGID',
   UserID			   INT     NOT NULL COMMENT'用户ID',
   GroupID             INT     NOT NULL COMMENT'用户组ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户用户组映射表';


/*==============================================================*/
/* Table: Role_UserGroup    角色_用户组映射表                                        */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Role_UserGroup;
CREATE TABLE RBAC_Role_UserGroup(
   RUGID               INT     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'RUGID',
   RoleID			   INT     NOT NULL COMMENT'角色ID',
   GroupID             INT     NOT NULL COMMENT'用户组ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='角色用户组映射表';



/*==============================================================*/
/* Table: Organization        组织机构表                               		    */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Organization;
CREATE TABLE RBAC_Organization(
	ORGID         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'组织ID', 	
	ORGName       VARCHAR(128)  NOT NULL  COMMENT'组织名称',   	    
    ORGType       INT           NOT NULL  COMMENT'组织类型',               --1集团 2公司 3部门 4团队 5小组     
	Description   VARCHAR(1024) NOT NULL  COMMENT'组织描述',    	    
	ParentID      INT  NOT NULL  COMMENT'上级组织ID',               			
    Priority      VARCHAR(512)  NOT NULL  COMMENT'同级组织排序',    
    Available     BOOL          NOT NULL  COMMENT'是否可用',     		  --状态 TRUE有效 FALSE无效
	CreateTime    DATETIME      NOT NULL  COMMENT'创建时间',     		
    LastTime      DATETIME      NOT NULL  COMMENT'最后修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='组织机构表';


/*==============================================================*/
/* Table:RBAC_User_Organization       用户组织映射表                 		    */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User_Organization;
CREATE TABLE RBAC_User_Organization(
	ID                INT  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'ID',                     
	UserID            INT  NOT NULL COMMENT'用户ID',                      /*员工编号*/
	ORGID             INT  NOT NULL COMMENT'组织ID'                       /*部门编号*/
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='部门员工映射表';


-- ----------------------------
-- Table structure for rbac_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_ext`;
CREATE TABLE `rbac_user_ext` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `property` varchar(255) NOT NULL COMMENT '属性',
  `value` varchar(255) NOT NULL DEFAULT '' COMMENT '属性值',
  `description` varchar(255) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
