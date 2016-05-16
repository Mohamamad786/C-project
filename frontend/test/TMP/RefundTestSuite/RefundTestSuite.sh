#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

RefundTestSuite() {
      bash ${SDIR}/ValidRefundTestCase/ValidRefundTestCase.sh
      bash ${SDIR}/InvalidRefundTestCaseA/InvalidRefundTestCaseA.sh
      bash ${SDIR}/InvalidRefundTestCaseB/InvalidRefundTestCaseB.sh
      bash ${SDIR}/InvalidRefundTestCaseC/InvalidRefundTestCaseC.sh
}


RefundTestSuite
