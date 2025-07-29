#!/bin/bash

echo "ThreeDLite Gems Project Check"
echo "============================="

# Check Java files
echo -n "Java source files: "
find app/src/main/java -name "*.java" | wc -l

# Check resources
echo -n "Polyhedra data files: "
find app/src/main/res/raw -name "*.txt" | wc -l

# Check key files
echo ""
echo "Key files present:"
for file in "app/src/main/AndroidManifest.xml" "app/build.gradle" "build.gradle" "settings.gradle"; do
    if [ -f "$file" ]; then
        echo "✅ $file"
    else
        echo "❌ $file (MISSING)"
    fi
done

# Check device connection
echo ""
echo "Device connection:"
if adb devices | grep -q "192.168.4.209:40999"; then
    echo "✅ Device connected at 192.168.4.209:40999"
else
    echo "❌ Device not connected"
    echo "  Run: adb connect 192.168.4.209:40999"
fi

# Check for Android SDK
echo ""
echo "Build tools:"
if [ -n "$ANDROID_HOME" ]; then
    echo "✅ ANDROID_HOME is set: $ANDROID_HOME"
else
    echo "❌ ANDROID_HOME not set"
fi

if command -v gradle &> /dev/null; then
    echo "✅ Gradle found: $(gradle --version | head -1)"
else
    echo "⚠️  Gradle not in PATH (can use ./gradlew instead)"
fi

echo ""
echo "Project appears ready for building!"
echo "Run ./build_and_deploy.sh to build and install"