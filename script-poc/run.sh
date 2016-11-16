breaker_path="/usr/local/ATM/iso-breaker"
processor_path="/usr/local/ATM/iso-processor"
breaker_process="transbridge-isobreaker.jar"
processor_process="transbridge-processor.jar"
function killProcess
{
	echo -e "\e[1;31mKilling porcss $1"
    #find the process ids and kill
	#get the PIDS of the process running
	PIDS=$(ps -aef | grep $1 | grep -v grep | awk '{print $2}')
	#itertate the list
	for i in "${PIDS[@]}"
	do
		if [ "${i}" ]; then
			echo -e "\e[1;31mKilling porcss $1 with PID $i"
			eval "kill ${i}" 
			echo -e "\e[1;31mKilled process $1 with PID $i"
		fi
	# do whatever on $i
	done
}

function startProcess {

	echo "startProcess : $2. Path : $1 "
	cd $1
	java -jar $2 &
	MyPID=$!                        # Record PID
	echo -e "\e[1;33mPRECESS $2 started with Process ID $MyPID"                   # Print to terminal
}

function restart_bank_omni {

	killProcess $breaker_process
	killProcess $processor_process
	startProcess $breaker_path $breaker_process
	startProcess $processor_path $processor_process
}

function restart_bank {

	killProcess $breaker_process
	startProcess $breaker_path $breaker_process
}

function restart_omni {

	killProcess $processor_process
	startProcess $processor_path $processor_process
}

function stop_bank_omni {

	killProcess $breaker_process
	killProcess $processor_process
}

function stop_bank {

	killProcess $breaker_process
}

function stop_omni {

	killProcess $processor_process
}

function start_bank_omni {

	startProcess $breaker_path
	startProcess $processor_path
}

function start_bank {

	startProcess $breaker_path
}

function start_omni {

	startProcess $processor_path
}

function readOption {
	
	option=0
	echo -e "\e[32m1. Restart Bank App and OMNI App > "
	echo -e "2. Restart Bank App > "
	echo -e "3. Restart Processor  > "
	echo -e "4. Stop Bank App and OMNI App > "
	echo -e "5. Stop Bank App  > "
	echo -e "6. Stop OMNI App  > "
	#echo -e "7. Start Bank App and OMNI app  > "
	#echo -e "8. Start Bank App  > "
	#echo -e "9. Start OMNI app  > "
	echo -e "Enter a option > "
	read option
	echo "Option is $option"
	
	if [ "$option" -eq 1 ]
	then
		restart_bank_omni
	elif [ "$option" -eq 2 ]
	then
		restart_bank
	elif [ "$option" -eq 3 ]
	then
		restart_omni
	elif [ "$option" -eq 4 ]
	then
		stop_bank_omni
	elif [ "$option" -eq 5 ]
	then
		stop_bank
	elif [ "$option" -eq 6 ]
	then
		stop_omni
	#elif [ "$option" -eq 7 ]
	#then
	#	start_bank_omni
	#elif [ "$option" -eq 8 ]
	#then
	#	start_bank
	#elif [ "$option" -eq 9 ]
	#then
	#	start_omni
	else
	  echo -e "\e[1;31mInvalid Option $option"
	fi

}

#!/bin/bash
#http://w3devops.com/force-kill-linux-unix-process-shell-script/
readOption
