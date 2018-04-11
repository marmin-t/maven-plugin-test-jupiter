#!/usr/bin/env bash
#
# This script creates a new local issue branch for the given issue id.
# Previous changes are stashed before the issue branch is created and are reapplied after branch creation.
#
# executing new-issue-branch.sh ISSUE-42
# will create a new (local) branch issue/ISSUE-42
USAGE="$0 <issue_id>"
if [ $# -lt 1 ]; then echo -e "ERROR: issue_id required. \n$USAGE" >&2; exit 1; fi
if [ $# -gt 1 ]; then echo -e "ERROR: One argument maximum.\n$USAGE" >&2; exit 1; fi

ISSUE_ID=${1:?"ISSUE_ID Parameter is missing!"}

ISSUE_BRANCH="issue/$ISSUE_ID"

echo "Creating feature branch $ISSUE_ID"

echo "Stashing potential intermediate changes..." && git stash\
&& git checkout -b ${ISSUE_BRANCH}\
&& git commit -am "$ISSUE_ID - Prepare branch"\
&& echo "Created feature branch: $ISSUE_BRANCH"\
&& echo "Reapplying potentially stashed changes... " && git stash pop\
&& echo "DONE!"