#!/bin/bash
source config.txt
date=$(date +"%d-%b-%Y")
backup_parent_dir=$backuppath
db_name=${databasename[@]}

# Read MySQL password from stdin if empty
if [[ -z "${mysql_password}" ]]; then
  echo -n "Enter MySQL ${mysql_user} password: "
  read -s mysql_password
  echo
fi

# Check MySQL password
echo exit | mysql --user=${mysql_user} --password=${mysql_password} -B 2>/dev/null
if [[ "$?" -gt 0 ]]; then
  echo "MySQL ${mysql_user} password incorrect"
  # exit 1
else
  echo "MySQL ${mysql_user} password correct."
fi

# Create backup directory
backup_date=$(date +%Y_%m_%d_%H_%M)
backup_dir="${backup_parent_dir}/${backup_date}"

# Backup and compress each database
for database in ${db_name[@]}
do
  if [[ "${database}" == "information_schema" ]] || [[ "${database}" == "performance_schema" ]]; then
        additional_mysqldump_params="--skip-lock-tables"
  else
        additional_mysqldump_params=""
  fi

  mysqldump ${additional_mysqldump_params} --user=${mysql_user} --password=${mysql_password} ${database} > ${backup_parent_dir}/${database}${backup_date}.sql

done
cd ${backuppath}
zip -r db_backup_${backup_date}.zip *.sql    
rm *.sql

/upload_to_dropbox.sh ${backuppath}/db_backup_${backup_date}.zip /DROPBOX-PATH

