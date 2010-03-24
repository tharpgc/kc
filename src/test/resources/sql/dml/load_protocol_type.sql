DELETE FROM PROTOCOL_VULNERABLE_SUB;
DELETE FROM PROTOCOL_ACTIONS;
DELETE FROM PROTOCOL_ATTACHMENT_PERSONNEL;
DELETE FROM PROTOCOL_PERSONS;
DELETE FROM PROTOCOL_LOCATION;
DELETE FROM PROTOCOL_SPECIAL_REVIEW;
DELETE FROM PROTOCOL; 
DELETE FROM PROTOCOL_TYPE;
Insert into PROTOCOL_TYPE
(PROTOCOL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
Values
(1, 'Standard', to_date('2007-12-20 13:25:59','YYYY-MM-DD HH24:MI:SS'), 'COEUS');
Insert into PROTOCOL_TYPE
(PROTOCOL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
Values
(2, 'Emergency Use', to_date('2007-12-20 13:25:59','YYYY-MM-DD HH24:MI:SS'), 'COEUS');
Insert into PROTOCOL_TYPE
(PROTOCOL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
Values
(3, 'Humanitarian Use Device (HUD)', to_date('2007-12-20 13:25:59','YYYY-MM-DD HH24:MI:SS'), 'COEUS');
Insert into PROTOCOL_TYPE
(PROTOCOL_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
Values
(4, 'Exempt', to_date('2007-12-20 13:25:59','YYYY-MM-DD HH24:MI:SS'), 'COEUS');
