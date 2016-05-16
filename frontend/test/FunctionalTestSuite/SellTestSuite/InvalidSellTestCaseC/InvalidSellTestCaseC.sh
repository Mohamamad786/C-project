#!/usr/bin/env bash

TDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source ${TDIR}/../../../TestFramework/TestCase.sh ${TDIR};

function InvalidSellTestCaseC {
    TestCase ${TDIR}
}


InvalidSellTestCaseC
