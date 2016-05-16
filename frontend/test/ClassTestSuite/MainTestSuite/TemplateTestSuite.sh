#!/usr/bin/env bash

SDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

TemplateTestSuite() {
    # call your test cases from here
    bash ${SDIR}/TemplateTestCase/TemplateTestCase.sh;
}