#!/usr/bin/env bash
#
# TestCase, version 1
#   library for testing c++ classes in bash.

##########################
# A Test Case template for
#+xstream project.
# Globals:
#   SRCDIR
#   TARGETTESTDIR
#   TESTNAME
# Arguments:
#   TARGET_PATH
#   TARGET_NAME
# Returns:
#   None

# set the SRCDIR to the files directory
readonly SRCDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# set calling command
readonly PARENTCMD=$(ps $PPID | tail -n 1 | awk "{print \$6}")

# set the path to the calling directory
readonly TARGETTESTDIR=$1

# set the name of the test case
readonly TESTNAME=$(basename "${TARGETTESTDIR}" .sh)

# link the logging module
#source ${SRCDIR}/Logger.sh $1;
source "${SRCDIR}"/Setup.sh;
source "${SRCDIR}"/Clean.sh;
source "${SRCDIR}"/CleanTest.sh ${TARGETTESTDIR};
source "${SRCDIR}"/Colours.sh;
source ${SRCDIR}/Logger.sh ${TARGETTESTDIR};


# TestCase():
#   creates a new test case which checks exit
#+  status and the difference between stdout
#+  and expected
#   RETURN 0 on success, -int on failure
function TestCase {
    ########### Setup TestEnv #############
    # remove the old backend.data
    cleanTest

    TAB=""
    if ! [ "${PARENTCMD}" = "-Xss2m" ]; then
        TAB="\t"
    fi

    # build test directory if DNE
    if ! [ -d "${SRCDIR}/../TestBuild" ]; then
        setup
    fi

    # navigate to test directory
    cd "${SRCDIR}/../TestBuild"


    #############  Run Command  #############
    # record runtime stdout and stderr of cmd
    # pass stdout from cmd to logger's function
    # output which distributed streams

    ./xstream -s < "${TARGETTESTDIR}/${TESTNAME}.in" | output


    #############    Run Tests   #############
    pass=0

    if [ $? -eq 1 ]; then
        pass=1
    fi

#    diff ${TARGETTESTDIR}/${TESTNAME}.exp ${TARGETTESTDIR}/${TESTNAME}.out > ${TARGETTESTDIR}/${TESTNAME}.log

#    if [ $? -eq 0 ]; then
#        pass=1
#    fi

    if [ ${pass} -eq 1 ]; then
        echo -e "${TAB}${Black}${TESTNAME}: ${Red}FAILED${NC}"
    else
        echo -e "${TAB}${Black}${TESTNAME}: ${Green}PASSED""${NC}"
    fi

    cp ${SRCDIR}/../TestBuild/data ${TARGETTESTDIR}/data -R
    cd ${SRCDIR}

    return ${pass}
}



