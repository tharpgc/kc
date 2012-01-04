CREATE TABLE COI_DISPOSITION_STATUS ( 
    COI_DISPOSITION_CODE NUMBER(3,0) NOT NULL, 
    DESCRIPTION VARCHAR2(200) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE COI_DISPOSITION_STATUS 
ADD CONSTRAINT PK_COI_DISPOSITION 
PRIMARY KEY (COI_DISPOSITION_CODE)
/

