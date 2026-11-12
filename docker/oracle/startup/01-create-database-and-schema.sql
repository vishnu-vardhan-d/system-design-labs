DECLARE
  n NUMBER;
BEGIN
  SELECT COUNT(*) INTO n FROM v$pdbs WHERE name = 'SYSTEMDESIGNLABS';
  IF n = 0 THEN
    EXECUTE IMMEDIATE q'[CREATE PLUGGABLE DATABASE systemdesignlabs
      ADMIN USER pdbadmin IDENTIFIED BY PdbAdminPwd1
      FILE_NAME_CONVERT = ('/opt/oracle/oradata/FREE/pdbseed/', '/opt/oracle/oradata/FREE/systemdesignlabs/')]';
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE systemdesignlabs OPEN';
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE systemdesignlabs SAVE STATE';
  END IF;
END;
/

ALTER SESSION SET CONTAINER = systemdesignlabs;

DECLARE
  n NUMBER;
BEGIN
  SELECT COUNT(*) INTO n FROM dba_tablespaces WHERE tablespace_name = 'LABS_DATA';
  IF n = 0 THEN
    EXECUTE IMMEDIATE q'[CREATE TABLESPACE labs_data
      DATAFILE '/opt/oracle/oradata/FREE/systemdesignlabs/labs_data01.dbf' SIZE 200M AUTOEXTEND ON]';
  END IF;

  SELECT COUNT(*) INTO n FROM dba_users WHERE username = 'SYSTEMDESIGNLABS';
  IF n = 0 THEN
    EXECUTE IMMEDIATE 'CREATE USER systemdesignlabs IDENTIFIED BY systemdesignlabs DEFAULT TABLESPACE labs_data QUOTA UNLIMITED ON labs_data';
    EXECUTE IMMEDIATE 'GRANT DB_DEVELOPER_ROLE, SELECT_CATALOG_ROLE TO systemdesignlabs';
  END IF;
END;
/
