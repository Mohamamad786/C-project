#!/usr/bin/env bash
#
# XStream Complete Test Suite, version 1
#   script to run the complete collection of test suites.

##########################################
#
#
#
#
#
#

readonly XDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

source "${XDIR}/TestFramework/TestSuite.sh" ${XDIR};

function XStreamTestSuite {
    TestSuite
}

bash ./TestFramework/TestCase.sh clean
bash ./TestFramework/TestCase.sh cleanTest
bash ./TestFramework/TestCase.sh setup
XStreamTestSuite