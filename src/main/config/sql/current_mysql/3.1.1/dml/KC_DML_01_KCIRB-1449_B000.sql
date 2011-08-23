DELIMITER /

INSERT INTO PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, FINAL_ACTION_FOR_BATCH_CORRESP, UPDATE_TIMESTAMP,UPDATE_USER, obj_id) 
VALUES ('904', 'Funding Source', 'N', 'N', 'N', now(), 'admin', uuid())
/

INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES (null)
/
insert into notification_type
(NOTIFICATION_TYPE_ID,MODULE_CODE,ACTION_CODE,DESCRIPTION,SUBJECT,MESSAGE,PROMPT_USER,SEND_NOTIFICATION,SYSTEM_GENERATED,UPDATE_USER,    
 UPDATE_TIMESTAMP,VER_NBR,OBJ_ID ) values(                             
 (SELECT MAX(ID) FROM SEQ_NOTIFICATION_TYPE_ID),7,904,'Funding Source','{MESSAGE_SUBJECT}','{MESSAGE_BODY}',  'N','Y','Y','admin',now(),1,
uuid())
/

INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES (null)
/
insert into NOTIFICATION_TYPE_RECIPIENT(
NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_name,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
values((SELECT MAX(ID) FROM SEQ_NOTIFICATION_TYPE_ID),(select NOTIFICATION_TYPE_ID from notification_type where DESCRIPTION = 'Funding Source'),'KC-UNT:Funding Source Monitor','unitNumber', 'T','admin',now(),1, 
uuid())
/


