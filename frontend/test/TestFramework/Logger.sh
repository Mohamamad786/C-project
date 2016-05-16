#!/usr/bin/env bash
#
# Logger, version 1.4
#   unit.logs stdin, stdout and stderr to their
#   repective files.
#

#######################################
# redirects stdin, stdout, and stderr
# to .in, .out, and .err files
# Globals:
#   BLACK   *color code for console
#   RED     *
#   BLUE    *
#   BLACK   *
# Param:
#       None
# xstreambackend.Return
#       None


LDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

source "${LDIR}"/Colours.sh;

TARGET=$1


# set stdin, stdout, and stderr.
STDOUT="${TARGET}/$(basename ${TARGET} .sh).out"
STDERR="${TARGET}/$(basename ${TARGET} .sh).err"
STDLOG="${TARGET}/$(basename ${TARGET} .sh).log"


output() {
	{
		while read line; do
			echo ${line} >> ${STDOUT} 2>&1
		done  #| tee 2>${STDERR}
		# 1>${STDOUT} | tee 2>${STDERR}
	}
}


#error() {
#	NAME="$(basename $(1) .sh)"
#	{
#		echo "[$(date +'%Y-%m-%dT%H:%M:%S%z')]: $@"
#	} >> ${STDERR}
#}
#
#
#log() {
#	MSG=${1:-"$@"}
#	VALUE=${2:-${@}}
#	{
#		echo "$@"
#	} &> ${STDLOG}
#}



























#$(awk'
#{
#    if (NF != 4) {
#        error("Expected 4 fields");
#    } else {
#        print;
#    }
#}')
#
#$(awk'
#function error ( message ) {
#    if (FILENAME != "-") {
#        printf("%s: ", FILENAME) > '${STDLOG}';
#    }
#    printf("line # %d, %s, line: %s\n", NR, message, $0) > "$(${STDLOG})";
#}')

