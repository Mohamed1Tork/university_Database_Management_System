#!/bin/bash

# Predefined thresholds (multiplying by 100 for integer comparison)
CPU_THRESHOLD=7000 # Percentage
MEMORY_THRESHOLD=7000 # Percentage
DISK_THRESHOLD=5500 # Percentage
NETWORK_THRESHOLD=50000 
DB_CONNECTION_THRESHOLD=5 # Number of failures
log_file="E:/DM44-Alex/Backups/anomalies_logs.log"
d=$(date +"%Y-%m-%d %H:%M:%S")

# Function to check CPU usage
check_cpu() {
    local cpu_usage=$(wmic cpu get loadpercentage | grep -Eo "[0-9]+")
    if [ -n "$cpu_usage" ] && (( $cpu_usage > $CPU_THRESHOLD )); then
		echo "CPU usage spiked above threshold: $((cpu_usage / 100))% | Date: ${d}" >> "${log_file}"
	else 
		echo "The CPU usage Is Safe | Date: ${d}" >> "${log_file}"
    fi
}

# Function to check memory consumption
check_memory() {
    local total_memory=$(wmic ComputerSystem get TotalPhysicalMemory | grep -Eo "[0-9]+")
    local free_memory=$(wmic OS get FreePhysicalMemory | grep -Eo "[0-9]+")
    if [ -n "$total_memory" ] && [ -n "$free_memory" ]; then
        local memory_usage=$(( (($total_memory - $free_memory) * 100) / $total_memory ))
        if (( $memory_usage > $MEMORY_THRESHOLD )); then
			echo "Memory consumption spiked above threshold: $((memory_usage / 100))% | Date: ${d}" >> "${log_file}"
		else 
			echo "The Memory consumption Is Safe | Date: ${d}" >> "${log_file}"
        fi
    fi
}

# Function to check available disk space
check_disk() {
    local disk_usage=$(df -h | awk 'NR==2 {print $6}' | sed 's/%//')
    if (( $disk_usage >= $DISK_THRESHOLD )); then
		echo "Available disk space fell above threshold: $disk_usage% | Date: ${d}" >> "${log_file}"
	else 
		echo "The Disk Space Is Safe | Date: ${d}" >> "${log_file}"
    fi
}

# Function to check network traffic
check_network() {
    local network_traffic=$(netstat -e | awk '/Bytes/{printf "%.0f", $3 / (1024 * 1024)}')
    if (( $network_traffic > $NETWORK_THRESHOLD )); then
		echo "Unusual increase in network traffic: $network_traffic Mbps | Date: ${d}" >> "${log_file}"
	else 
		echo "The Network Traffic Is Safe | Date: ${d}" >> "${log_file}"
    fi
}

# Function to check database connection failures
check_db_connections() {
    local db_connection_failures=$(grep -c "database connection failure" "C:/Users/LORD LAPTOP/Oracle/oradiag_LORD LAPTOP/diag/clients/user_LORD LAPTOP/host_2903378055_80/trace/sqlnet.log")
    if (( $db_connection_failures > $DB_CONNECTION_THRESHOLD )); then
		echo "Sudden increase in database connection failures: $db_connection_failures | Date: ${d}" >> "${log_file}"
	else 
		echo "The Database Connection Is Safe | Date: ${d}" >> "${log_file}"
    fi
}

main() {
    check_cpu
    check_memory
    check_disk
    check_network
    check_db_connections
}

# Run the main function
main
