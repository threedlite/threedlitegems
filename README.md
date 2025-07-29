# ThreeDLite Gems Live Wallpaper

A modern Android live wallpaper featuring animated 3D polyhedra.

## Features
- Beautiful 3D geometric shapes floating in space
- Touch interaction support
- Smooth 30 FPS animations
- No ads, no data collection
- Battery efficient

## Build Requirements
- Android Studio Arctic Fox or newer
- Android SDK 36 (Android 16)
- Java 17

## Building the App

1. Open the project in Android Studio
2. Sync project with Gradle files
3. Build the project:
   ```
   ./gradlew assembleDebug
   ```

For release build:
```
./gradlew assembleRelease
```

## Google Play Store Compliance

This project has been updated to meet current Play Store requirements:

- ✅ Targets Android 16 (API 36)
- ✅ Uses AndroidX libraries
- ✅ Includes data safety declarations
- ✅ Privacy-focused (no data collection)
- ✅ ProGuard configuration for release builds
- ✅ No dangerous permissions required

## Installation

1. Install the APK on your device
2. Open the app and tap "Set as Live Wallpaper"
3. Or go to Settings → Wallpaper → Live Wallpapers → ThreeDLite Gems

## Key Updates from Original

- Migrated from Eclipse project structure to modern Gradle
- Updated minimum SDK from 7 to 21 (Android 5.0)
- Updated target SDK to 36 (Android 16) 
- Added proper data extraction rules for Android 12+
- Enhanced settings activity with privacy information
- Improved touch event handling
- Added ProGuard rules for optimized release builds

## License

Licensed under the Apache License, Version 2.0