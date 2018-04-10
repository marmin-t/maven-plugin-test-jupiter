#!/usr/bin/env bash
if [ $TRAVIS_BRANCH = 'master' ] && [ $TRAVIS_PULL_REQUEST == 'false' ]; then

    mvn deploy --settings $TRAVIS_DIR/settings.xml -Drevision=$BUILD_REVISION -DperformRelease=true -DskipTests=true
    exit $?

fi