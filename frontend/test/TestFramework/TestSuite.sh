#!/usr/bin/env bash

readonly TSDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

readonly PARENTCMD=$(ps $PPID | tail -n 1 | awk "{print \$6}")

readonly TARGET=$1
#echo ${TARGET}
readonly NAME=$(basename ${TARGET} .sh)

source "${TSDIR}"/Colours.sh
source "${TSDIR}"/Setup.sh;

function TestSuite {

	TAB=""
	if ! [ "${PARENTCMD}" = "-Xss2m" ]; then
		TAB="\t"
	fi

	if [[ ${NAME} =~ "TestSuite" ]]; then
		echo -e ${TAB}"${BBlue}"${NAME}"${NC}"
	fi

	num_tests=0
	passed=0

	check=(${TARGET}/*Test*/)

	for c in ${check[@]};
	do
		((num_tests+=1))
		name=$(basename ${c})
		if [[ "${name}"  =~ "TestSuite" ]]; then
			bash ${c}${name}.sh
			if [ $? -eq 0 ]; then
				((passed+=1))
			fi

		elif [[ "${name}"  =~ "TestCase" ]]; then
			bash ${c}${name}.sh
			if [ $? -eq 0 ]; then
				((passed+=1))
			fi
		fi

	done
	if [ ${passed} -eq ${num_tests} ]; then
		COLOR=${BGreen}
	else
		COLOR=${BRed}
	fi
	echo -e "${TAB}${BBlue}${NAME}: ${COLOR}${passed} out of ${num_tests} passed${NC}"
}

