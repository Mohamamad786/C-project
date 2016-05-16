#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

LogoutTestSuite() {
      bash ${SDIR}/ValidLogoutTestCase/ValidLogoutTestCase.sh
      bash ${SDIR}/InvalidLogoutTestCase/InvalidLogoutTestCase.sh
}


LogoutTestSuite
