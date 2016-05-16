#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

DeleteTestSuite() {
      bash ${SDIR}/ValidDeleteTestCase/ValidDeleteTestCase.sh
      bash ${SDIR}/InvalidDeleteTestCaseA/InvalidDeleteTestCaseA.sh
      bash ${SDIR}/InvalidDeleteTestCaseB/InvalidDeleteTestCaseB.sh
}


DeleteTestSuite
