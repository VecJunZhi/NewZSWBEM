/**�û�Ȩ�޿����������ݽṹ*ͨ��**/


/*==============================================================*/
/* Table: user       �û���                                                                           */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User;
CREATE TABLE RBAC_User(
   UserID            INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'�û�ID',
   UserName          VARCHAR(512)  NOT NULL COMMENT'�û�����',
   RealName          VARCHAR(512)  NOT NULL COMMENT'��ʵ����',
   Password          VARCHAR(512)  NOT NULL COMMENT'�û�����',
   Mobile            VARCHAR(512)  NOT NULL COMMENT'�ֻ�����',
   EMail             VARCHAR(512)  NOT NULL COMMENT'�ʼ���ַ',
   LoginType         VARCHAR(512)  NOT NULL COMMENT'��¼��ʽ',       --UserName:Mobile:EMail   ��:�ŷָ�
   UStatus           INT           NOT NULL COMMENT'�û�״̬',     --״̬ 0����  1����
   Locked            BOOL          NOT NULL COMMENT'�Ƿ�����',   
   CreateTime        DATETIME      NOT NULL COMMENT'����ʱ��',
   LastTime          DATETIME      NOT NULL COMMENT'����޸�ʱ��',
   Description       VARCHAR(1024) NOT NULL COMMENT'����'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='�û���Ϣ��';


/*==============================================================*/
/* Table: Role       ��ɫ��                                                                           */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Role;
CREATE TABLE RBAC_Role(
   RoleID      INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'��ɫID',
   RoleName    VARCHAR(512)  NOT NULL COMMENT'��ɫ����',
   Available   BOOL          NOT NULL COMMENT'�Ƿ����',
   Priority    VARCHAR(512)  NOT NULL COMMENT'��ɫ����',
   CreateTime  DATETIME      NOT NULL COMMENT'����ʱ��',
   LastTime    DATETIME      NOT NULL COMMENT'����޸�ʱ��',
   Description VARCHAR(1024) NOT NULL COMMENT'����'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='��ɫ��';

/*==============================================================*/
/* Table: User_Role   �û���ɫӳ���                                                          */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User_Role;
CREATE TABLE RBAC_User_Role(
   URID         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'URID',
   UserID       VARCHAR(512) NOT NULL COMMENT'�û�ID',
   RoleID       VARCHAR(512) NOT NULL COMMENT'��ɫID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='�û���ɫӳ���';


/*==============================================================*/
/* Table: Role_Permission ��ɫȨ��ӳ���                                                  */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Role_Permission;
CREATE TABLE RBAC_Role_Permission(
   RPID                 INT          NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'RPID',
   RoleID               INT NOT NULL COMMENT'��ɫID',
   PermissionID         INT NOT NULL COMMENT'Ȩ��ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='��ɫȨ��ӳ���';


/*==============================================================*/
/* Table: Permission       Ȩ�ޱ�                                                               */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Permission;
CREATE TABLE RBAC_Permission(
   PermissionID                   INT             NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'Ȩ��ID',
   PermissionName                 VARCHAR(512)     NOT NULL COMMENT'Ȩ������',
   PermissionMark                 VARCHAR(512)     NOT NULL COMMENT'Ȩ�ޱ�ʶ',
   PermissionType                 VARCHAR(512)     NOT NULL COMMENT'Ȩ������',
   ParentID                       INT              NOT NULL COMMENT'�ϼ�ID'  ,
   URL                       	  VARCHAR(512)     NOT NULL COMMENT'Ȩ�޵�ַ',
   Priority                       INT              NOT NULL COMMENT'��ʾ˳��',
   Available                      BOOL             NOT NULL COMMENT'�Ƿ����',
   Description                    VARCHAR(1024)    NOT NULL COMMENT'����',
   CreateTime                     DATETIME         NOT NULL COMMENT'����ʱ��',
   LastTime                       DATETIME         NOT NULL COMMENT'����޸�ʱ��'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='Ȩ�ޱ�';

/*==============================================================*/
/* Table: UserGroup  �û����                                                                       */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_UserGroup;
CREATE TABLE RBAC_UserGroup(
   GroupID         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'�û���ID',
   GroupName       VARCHAR(512)  NOT NULL COMMENT'�û�������',
   Available       BOOL          NOT NULL COMMENT'�Ƿ����',
   ParentGroupID   INT           NOT NULL COMMENT'�ϼ�����ID',
   Priority        VARCHAR(512)  NOT NULL COMMENT'��������',
   Description     VARCHAR(1024) NOT NULL COMMENT'����',
   CreateTime      DATETIME      NOT NULL COMMENT'����ʱ��',
   LastTime        DATETIME      NOT NULL COMMENT'����޸�ʱ��'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='�û����';


/*==============================================================*/
/* Table: User_UserGroup    �û�_�û���ӳ���                                        */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User_UserGroup;
CREATE TABLE RBAC_User_UserGroup(
   UUGID               INT     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'UUGID',
   UserID			   INT     NOT NULL COMMENT'�û�ID',
   GroupID             INT     NOT NULL COMMENT'�û���ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='�û��û���ӳ���';


/*==============================================================*/
/* Table: Role_UserGroup    ��ɫ_�û���ӳ���                                        */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Role_UserGroup;
CREATE TABLE RBAC_Role_UserGroup(
   RUGID               INT     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'RUGID',
   RoleID			   INT     NOT NULL COMMENT'��ɫID',
   GroupID             INT     NOT NULL COMMENT'�û���ID'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='��ɫ�û���ӳ���';



/*==============================================================*/
/* Table: Organization        ��֯������                               		    */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_Organization;
CREATE TABLE RBAC_Organization(
	ORGID         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'��֯ID', 	
	ORGName       VARCHAR(128)  NOT NULL  COMMENT'��֯����',   	    
    ORGType       INT           NOT NULL  COMMENT'��֯����',               --1���� 2��˾ 3���� 4�Ŷ� 5С��     
	Description   VARCHAR(1024) NOT NULL  COMMENT'��֯����',    	    
	ParentID      INT  NOT NULL  COMMENT'�ϼ���֯ID',               			
    Priority      VARCHAR(512)  NOT NULL  COMMENT'ͬ����֯����',    
    Available     BOOL          NOT NULL  COMMENT'�Ƿ����',     		  --״̬ TRUE��Ч FALSE��Ч
	CreateTime    DATETIME      NOT NULL  COMMENT'����ʱ��',     		
    LastTime      DATETIME      NOT NULL  COMMENT'����޸�ʱ��'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='��֯������';


/*==============================================================*/
/* Table:RBAC_User_Organization       �û���֯ӳ���                 		    */
/*==============================================================*/
DROP TABLE IF EXISTS RBAC_User_Organization;
CREATE TABLE RBAC_User_Organization(
	ID                INT  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'ID',                     
	UserID            INT  NOT NULL COMMENT'�û�ID',                      /*Ա�����*/
	ORGID             INT  NOT NULL COMMENT'��֯ID'                       /*���ű��*/
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='����Ա��ӳ���';


-- ----------------------------
-- Table structure for rbac_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_ext`;
CREATE TABLE `rbac_user_ext` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `property` varchar(255) NOT NULL COMMENT '����',
  `value` varchar(255) NOT NULL DEFAULT '' COMMENT '����ֵ',
  `description` varchar(255) NOT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
