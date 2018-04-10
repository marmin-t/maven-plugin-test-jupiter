#!/usr/bin/env bash
if [ $TRAVIS_BRANCH = 'master' ] && [ $TRAVIS_PULL_REQUEST == 'false' ]; then

    mvn deploy --settings $TRAVIS_DIR/settings.xml \
     -Drevision=$BUILD_REVISION \
     -DperformRelease=true -DskipTests=true \
     -Dgpg.executable=gpg \
     -Dgpg.keyname=$GPG_KEYNAME \
     -Dgpg.passphrase=$GPG_PASSPHRASE \
     -Dgpg.defaultKeyring=false \
     -Dgpg.publicKeyring=$TRAVIS_DIR/pubring.kbx
     -Dgpg.secretKeyring=$TRAVIS_DIR/trustdb.gpg
    exit $?

fi