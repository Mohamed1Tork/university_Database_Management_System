#!/bin/bash

db_owner=MO
pass=123
db=XE
direct="E:/DM44-Alex/Backups"
d=$(date +"%Y%m%d_%H%M%S")
file_name="backup_${d}.dmp"

expdp ${db_owner}/${pass}@${db} DUMPFILE=${file_name}  DIRECTORY=my_directory;

if [ $? -eq 0 ]; then
	echo " Make Backup Successfully " >> "${direct}/backupLogs.log"
else 
	echo " ERROR : Make Backup Failed " >> "${direct}/backupLogs.log"
fi

