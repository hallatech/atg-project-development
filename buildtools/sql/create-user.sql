create user atg profile "DEFAULT"
    identified by atg
    default tablespace "USERS"
    temporary tablespace "TEMP" account unlock;

GRANT DBA TO atg;

exit;