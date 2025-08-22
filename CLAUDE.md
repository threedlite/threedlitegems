# ThreeDLite Gems - Android Live Wallpaper

## Project Overview
ThreeDLite Gems is an Android live wallpaper application that displays animated 3D geometric shapes. The app uses OpenGL ES for rendering and provides various customization options through Android's wallpaper settings.

## Project Structure
- `app/src/main/java/com/threedlite/` - Main Java source code
  - `app/` - Application classes (Activities, Services, Wallpapers)
  - `data/` - Data models and structures (PolyData, etc.)
  - `gems/` - Core wallpaper implementation
  - `utils/` - Utility classes
- `app/src/main/res/` - Android resources (layouts, drawables, XML configs)
- `app/build.gradle` - Android app build configuration
- `build_and_deploy.sh` - Build and deployment script

## Key Components
- **Wallpaper Services**: Live wallpaper implementations extending WallpaperService
- **OpenGL Rendering**: 3D graphics rendering using OpenGL ES
- **Settings**: Customizable wallpaper preferences (colors, speed, shapes)
- **PolyData**: Geometric shape data and transformations

## Build & Test Commands
```bash
# Build debug APK
./gradlew assembleDebug

# Build release bundle (AAB)
./gradlew bundleRelease

# Run all tests
./gradlew test

# Install on connected device
./gradlew installDebug

# Clean build
./gradlew clean
```

## Development Notes
- Minimum SDK: API 21 (Android 5.0)
- Target SDK: API 34 (Android 14)
- Uses OpenGL ES 2.0 for 3D rendering
- Wallpaper XML configuration in `app/src/main/res/xml/`
- App signing configured for Play Store release

## Testing
- Test on various screen sizes and orientations
- Verify wallpaper preview and settings work correctly
- Check performance on both high-end and low-end devices
- Ensure smooth transitions between wallpaper modes

## Deployment
The `build_and_deploy.sh` script handles the complete build and deployment process:
- Builds release AAB file
- Signs the bundle
- Prepares for Play Store upload