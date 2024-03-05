#!/bin/bash

log_file="E:/DM44-Alex/Backups/disk_logs.log"
th=55
d=$(date +"%Y-%m-%d %H:%M:%S")
used_space=$(df -h | awk 'NR==2 {print $6}' | sed 's/%//')

if [ "$used_space" -ge "$th" ]; then
    echo "Warning!! The Disk Space Exceeds $th% | Date: ${d}" >> "${log_file}"
else 
    echo "The Disk Space Is Safe | Date: ${d}" >> "${log_file}"
fi
