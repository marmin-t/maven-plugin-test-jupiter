#!/usr/bin/env bash

USAGE="$0 <release_version> $1 <next_iteration_version>"
if [ $# -lt 2 ]; then echo -e "ERROR: release_version and next_iteration_version required. \n$USAGE" >&2; exit 1; fi
if [ $# -gt 2 ]; then echo -e "ERROR: Two argument maximum.\n$USAGE" >&2; exit 1; fi

RELEASE_VERSION=${1:?"RELEASE_VERSION Parameter is missing!"}.RELEASE
NEXT_ITERATION_VERSION=${2:?"NEXT_RELEASE_VERSION Parameter is missing!"}.BUILD-SNAPSHOT
RELEASE_TAG="v$RELEASE_VERSION"

#mvn clean install

(echo "Preparing release of version: $RELEASE_VERSION" && git stash\
        && mvn -o -q versions:set -DgenerateBackupPoms=false -DnewVersion=${RELEASE_VERSION}\
 #       && mvn deploy -DperformRelease=true -DskipTests\
       \ && git remote set-url origin git@github.com:devbhuwan/maven-plugin-test-jupiter.git\
        && (git commit -am "Release of version -> $RELEASE_VERSION" || git push)\
        && git push\
        && git tag -l | xargs git tag -d && git fetch -t\
        && git tag -a ${RELEASE_TAG} -m "Release of version: $RELEASE_VERSION"\
        && git push origin ${RELEASE_TAG}:refs/tags/stable\
        && mvn -o -q versions:set -DgenerateBackupPoms=false -DnewVersion=${NEXT_ITERATION_VERSION}\
        && git commit -am "Iteration of version -> $NEXT_ITERATION_VERSION"\
        && echo "Reapplying potentially stashed changes... " && git stash pop\
        && git push
        )\
|| echo "Something went wrong!"