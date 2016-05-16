#!/usr/bin/env bash
#
# CleanTest.sh, version 1
#
#    <description>

# <brief description>
#
# Globals:
#       None
# Param:
#       None
# xstreambackend.Return:
#       None

# Set files location directory 
readonly CLEANTESTDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

readonly TARGETCLEAN=$1

# cleanTest():
#   cleans test backend.data and output files
function cleanTest {
    if [ -f $1/output.txt ]; then
        rm $1/output.txt
    fi

    if [ -d $1/backend.data ]; then
        rm -r $1/data
    fi
}