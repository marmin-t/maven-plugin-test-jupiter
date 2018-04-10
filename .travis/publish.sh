#!/usr/bin/env bash
if [ $TRAVIS_BRANCH = 'master' ] && [ $TRAVIS_PULL_REQUEST == 'false' ]; then

    openssl aes-256-cbc -pass pass:$ENCRYPTION_PASSWORD -in $TRAVIS_DIR/pubring.gpg.enc -out $TRAVIS_DIR/pubring.gpg -d
    openssl aes-256-cbc -pass pass:$ENCRYPTION_PASSWORD -in $TRAVIS_DIR/secring.gpg.enc -out $TRAVIS_DIR/secring.gpg -d

    mvn deploy --settings $TRAVIS_DIR/settings.xml \
     -Drevision=$BUILD_REVISION \
     -DperformRelease=true -DskipTests=true \
     -Dgpg.executable=gpg \
     -Dgpg.keyname=$GPG_KEYNAME \
     -Dgpg.passphrase=$GPG_PASSPHRASE \
     -Dgpg.defaultKeyring=false \
     -Dgpg.publicKeyring=$TRAVIS_DIR/pubring.gpg
     -Dgpg.secretKeyring=$TRAVIS_DIR/secring.gpg
    exit $?
fi