#!/usr/bin/env bash

TDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source ${TDIR}/../../TestFramework/TestCase.sh;

function InvalidDeleteTestCaseB {
    TestCase ${TDIR}
}


InvalidDeleteTestCaseB
