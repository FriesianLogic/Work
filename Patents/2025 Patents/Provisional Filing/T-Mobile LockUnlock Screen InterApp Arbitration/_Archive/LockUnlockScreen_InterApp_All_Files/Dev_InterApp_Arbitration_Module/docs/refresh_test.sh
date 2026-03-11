#!/bin/bash
# refresh_test.sh
# ADB script to simulate arbitration broadcast events between two apps

APP1=com.example.metrozone
APP2=com.example.metroplay

# Simulate App1 requesting a refresh slot
echo "[ADB] App1 requesting refresh slot..."
adb shell am broadcast -a com.tmobile.REFRESH_REQUEST --es originApp $APP1
sleep 1

# Simulate App2 acknowledging App1's request
echo "[ADB] App2 acknowledging..."
adb shell am broadcast -a com.tmobile.REFRESH_ACKNOWLEDGE --es originApp $APP1
sleep 6

# Simulate App2 requesting after App1 completes
echo "[ADB] App2 requesting refresh slot..."
adb shell am broadcast -a com.tmobile.REFRESH_REQUEST --es originApp $APP2
sleep 1

# Simulate App1 acknowledging App2's request
echo "[ADB] App1 acknowledging..."
adb shell am broadcast -a com.tmobile.REFRESH_ACKNOWLEDGE --es originApp $APP2

echo "[ADB] Arbitration test cycle complete."