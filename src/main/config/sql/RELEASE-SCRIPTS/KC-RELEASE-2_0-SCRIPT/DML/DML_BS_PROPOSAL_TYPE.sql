UPDATE PROPOSAL_TYPE 
SET DESCRIPTION='Resubmission' 
WHERE PROPOSAL_TYPE_CODE = 2;

UPDATE PROPOSAL_TYPE 
SET DESCRIPTION='Continuation' 
WHERE PROPOSAL_TYPE_CODE = 4;

COMMIT;