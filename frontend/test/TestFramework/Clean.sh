#!/usr/bin/env bash
#
# Clean, version 1
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
CLEANDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# clean():
#   deletes the current test build directory if
#+  one exists.
function clean {
    if [ -d ${CLEANDIR}/../TestBuild ]; then
        rm -r "${CLEANDIR}"/../TestBuild
    fi
}

