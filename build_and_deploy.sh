#!/bin/bash

# Build and deploy script for ThreeDLite Gems

echo "Building ThreeDLite Gems Live Wallpaper..."

# Check if gradle wrapper exists
if [ ! -f "./gradlew" ]; then
    echo "Error: gradlew not found. Please run this from the project root."
    exit 1
fi

# Clean previous builds
echo "Cleaning previous builds..."
/home/user/.gradle/wrapper/dists/gradle-8.13-bin/5xuhj0ry160q40clulazy9h7d/gradle-8.13/bin/gradle clean

# Build debug APK
echo "Building debug APK..."
/home/user/.gradle/wrapper/dists/gradle-8.13-bin/5xuhj0ry160q40clulazy9h7d/gradle-8.13/bin/gradle assembleDebug

# Check if build was successful
if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

# Find the APK
APK_PATH="app/build/outputs/apk/debug/app-debug.apk"
if [ ! -f "$APK_PATH" ]; then
    echo "APK not found at $APK_PATH"
    exit 1
fi

echo "APK built successfully at: $APK_PATH"

# Deploy to device
DEVICE_IP="192.168.4.209:40999"
echo "Connecting to device at $DEVICE_IP..."
adb connect $DEVICE_IP

# Wait a moment for connection
sleep 2

# Check if device is connected
if ! adb devices | grep -q "$DEVICE_IP"; then
    echo "Failed to connect to device"
    exit 1
fi

# Uninstall previous version if exists
echo "Uninstalling previous version (if any)..."
adb -s $DEVICE_IP uninstall com.threedlite.app 2>/dev/null

# Install the APK
echo "Installing APK..."
adb -s $DEVICE_IP install -r "$APK_PATH"

if [ $? -eq 0 ]; then
    echo "Installation successful!"
    
    # Launch the settings activity
    echo "Launching app..."
    adb -s $DEVICE_IP shell am start -n com.threedlite.app/.SettingsActivity
    
    echo ""
    echo "✅ Deployment complete!"
    echo "The app should now be running on your device."
    echo "You can set it as your wallpaper from the app."
else
    echo "Installation failed!"
    exit 1
fi