#!/usr/bin/env bash

TDIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
source "${TDIR}/../../TestCase.sh" ${TDIR};

function TemplateTestCase {
    TestCase
}

#setup
TemplateTestCase #Uncomment to enable test