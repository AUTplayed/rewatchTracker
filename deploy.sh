#!/bin/bash
./gradlew fatJar
scp build/libs/rewatchTracker-fat-1.0.jar pi@10.0.0.77:/home/pi/rwt/rewatchTracker.jar
ssh pi@10.0.0.77 /home/pi/rwt/start.sh