#!/usr/bin/env bash

readonly SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "${SDIR}/../TestFramework/TestSuite.sh" ${SDIR};

function FunctionalTestSuite {
    TestSuite
}

FunctionalTestSuite
