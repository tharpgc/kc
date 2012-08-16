CREATE TABLE AWARD_APPROVED_EQUIPMENT (
   AWARD_APPROVED_EQUIPMENT_ID NUMBER(22,0) NOT NULL,
   AWARD_ID NUMBER(22,0) NOT NULL,
   AWARD_NUMBER varchar2(10) NOT NULL,
   SEQUENCE_NUMBER NUMBER(4) NOT NULL,
   ITEM varchar2(100) NOT NULL,
   VENDOR varchar2(50),
   MODEL varchar2(50),
   AMOUNT NUMBER(12,2) NOT NULL,
   UPDATE_TIMESTAMP date NOT NULL,
   UPDATE_USER varchar2(60) NOT NULL,
   VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
   OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL
);
ALTER TABLE AWARD_APPROVED_EQUIPMENT ADD CONSTRAINT PK_AWARD_APPRV_EQUIP PRIMARY KEY (AWARD_APPROVED_EQUIPMENT_ID);
ALTER TABLE AWARD_APPROVED_EQUIPMENT ADD CONSTRAINT FK_AWARD_AWARD_APPROVED_EQUIP FOREIGN KEY (AWARD_ID) REFERENCES AWARD(AWARD_ID);
INSERT INTO SH_PARM_T 
	(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND) 
	VALUES 
	('KC-AWARD','D','federalCapitalizationMinimum',sys_guid(),1,'CONFG','0.00','Federal Capitalization Minimum','A','WorkflowAdmin','Y');
INSERT INTO SH_PARM_T 
	(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND) 
	VALUES 
	('KC-AWARD','D','institutionCapitalizationMinimum',sys_guid(),1,'CONFG','0.00','Institution Capitalization Minimum','A','WorkflowAdmin','Y');
commit;