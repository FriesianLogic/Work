#!/bin/bash
echo "Simulating lock/unlock and checking Widget Arbitration Logs..."

# Wake and unlock screen
adb shell input keyevent KEYCODE_POWER
adb shell input keyevent KEYCODE_MENU
sleep 2

# Force Doze Mode
adb shell dumpsys deviceidle force-idle
echo "Forced Doze Mode"
sleep 2

# Exit Doze Mode
adb shell dumpsys deviceidle unforce
echo "Exited Doze Mode"
sleep 2

# Simulate memory pressure
adb shell am kill-all
adb shell dumpsys meminfo | grep "Used RAM"

# Dump arbitration logs
adb logcat -d | grep WidgetArbitration

echo "QA Test Script Complete"
