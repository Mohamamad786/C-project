#!/usr/bin/env bash

TDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
TEST=$(basename ${TDIR}/)

source ${TDIR}/../../../TestFramework/TestCase.sh ${TDIR} ${TEST};

function MainTestCase {
     TestCase
}

MainTestCase