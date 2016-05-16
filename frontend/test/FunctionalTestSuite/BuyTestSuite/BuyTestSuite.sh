#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "${SDIR}/../../TestFramework/TestSuite.sh" ${SDIR};

BuyTestSuite() {
      TestSuite
}

BuyTestSuite

#      bash ${SDIR}/ValidBuyTestCase/ValidBuyTestCase.sh
#      bash ${SDIR}/InvalidBuyTestCaseA/InvalidBuyTestCaseA.sh
#      bash ${SDIR}/InvalidBuyTestCaseB/InvalidBuyTestCaseB.sh
#      bash ${SDIR}/InvalidBuyTestCaseC/InvalidBuyTestCaseC.sh
#      bash ${SDIR}/InvalidBuyTestCaseD/InvalidBuyTestCaseD.sh
#      bash ${SDIR}/InvalidBuyTestCaseE/InvalidBuyTestCaseE.sh
#}



