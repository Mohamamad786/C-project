#!/usr/bin/env bash

TESTDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "${TESTDIR}/./../../../TestFramework/TestCase.sh" ${TESTDIR};

function ValidLoginTestCase {
     TestCase
}

ValidLoginTestCase