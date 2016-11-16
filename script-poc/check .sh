breaker_process="transbridge-isobreaker.jar"
processor_process="transbridge-processor.jar"

function check_process_status 
{
	
	pid=$(ps x | grep $1 | grep -v grep | cut -d ' ' -f 2)
	if [ "${pid}" ]; then
		echo -e "\e[0;32mPROCESS $1 IS RUNNING $(date)"
	else 
		echo -e "\e[1;31mPROCESS $1 NOT RUNNING $(date)"
	fi
}

for (( ; ; ))
do
   check_process_status $breaker_process
   check_process_status $processor_process
   sleep 5
done
