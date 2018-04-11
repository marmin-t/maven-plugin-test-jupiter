#!/usr/bin/env bash

USAGE="$0 <release_version>"
if [ $# -lt 1 ]; then echo -e "ERROR: release_version required. \n$USAGE" >&2; exit 1; fi
if [ $# -gt 1 ]; then echo -e "ERROR: One argument maximum.\n$USAGE" >&2; exit 1; fi

RELEASE_VERSION=${1:?"RELEASE_VERSION Parameter is missing!"}

mvn clean install
mvn deploy -DperformRelease=true -DskipTest