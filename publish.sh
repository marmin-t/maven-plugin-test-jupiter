#!/usr/bin/env bash
mvn versions:set
mvn clean install
mvn deploy -DperformRelease=true -DskipTests
mvn versions:set