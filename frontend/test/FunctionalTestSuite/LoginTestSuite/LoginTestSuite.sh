#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "${SDIR}/../../TestFramework/TestSuite.sh" ${SDIR};

LoginTestSuite() {
    TestSuite
}

LoginTestSuite