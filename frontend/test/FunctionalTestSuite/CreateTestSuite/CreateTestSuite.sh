#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "${SDIR}/../../TestFramework/TestSuite.sh" ${SDIR};

CreateTestSuite() {
    TestSuite
}

CreateTestSuite

#      bash ${SDIR}/ValidCreateTestCase/ValidCreateTestCase.sh
#      bash ${SDIR}/InvalidCreateTestCaseA/InvalidCreateTestCaseA.sh
#      bash ${SDIR}/InvalidCreateTestCaseB/InvalidCreateTestCaseB.sh
#      bash ${SDIR}/InvalidCreateTestCaseC/InvalidCreateTestCaseC.sh
#      bash ${SDIR}/InvalidCreateTestCaseD/InvalidCreateTestCaseD.sh