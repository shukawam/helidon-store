#! /bin/bash

if [ $# -ne 1 ]; then
    echo "[USAGE] ./dev.sh <service-name>"
    exit 1
fi

cd $1 && helidon dev --app-jvm-args "--enable-preview -Dconfig.profile=local"