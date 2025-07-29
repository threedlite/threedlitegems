#!/bin/bash
# Gradle wrapper script

GRADLE_HOME="${GRADLE_HOME:-$HOME/.gradle}"
GRADLE_VERSION="8.2"
GRADLE_ZIP="gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_URL="https://services.gradle.org/distributions/${GRADLE_ZIP}"
GRADLE_DIR="${GRADLE_HOME}/wrapper/dists/gradle-${GRADLE_VERSION}-bin"

if [ ! -d "${GRADLE_DIR}" ]; then
    echo "Downloading Gradle ${GRADLE_VERSION}..."
    mkdir -p "${GRADLE_DIR}"
    curl -L "${GRADLE_URL}" -o "${GRADLE_HOME}/${GRADLE_ZIP}"
    unzip -q "${GRADLE_HOME}/${GRADLE_ZIP}" -d "${GRADLE_DIR}"
    rm "${GRADLE_HOME}/${GRADLE_ZIP}"
fi

"${GRADLE_DIR}/gradle-${GRADLE_VERSION}/bin/gradle" "$@"