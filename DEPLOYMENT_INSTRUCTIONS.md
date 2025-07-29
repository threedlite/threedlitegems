# Deployment Instructions for ThreeDLite Gems

## Quick Deploy (if you have Android Studio installed)

1. Open Android Studio
2. Open the project: File → Open → Select `/home/user/threedlite/threedlite-modern`
3. Wait for Gradle sync to complete
4. Connect your device (already connected at 192.168.4.209:40999)
5. Click "Run" button (green play icon) or press Shift+F10
6. Select your device from the list

## Command Line Deploy

If you have Android SDK installed with proper environment variables:

```bash
cd /home/user/threedlite/threedlite-modern
./gradlew assembleDebug
adb -s 192.168.4.209:40999 install app/build/outputs/apk/debug/app-debug.apk
adb -s 192.168.4.209:40999 shell am start -n com.threedlite.exotics/.SettingsActivity
```

## Manual Build Steps

1. **Install Android SDK** (if not installed):
   ```bash
   # Download command line tools from:
   # https://developer.android.com/studio#command-tools
   ```

2. **Set up environment**:
   ```bash
   export ANDROID_HOME=$HOME/Android/Sdk
   export PATH=$PATH:$ANDROID_HOME/platform-tools
   ```

3. **Run the build script**:
   ```bash
   cd /home/user/threedlite/threedlite-modern
   ./build_and_deploy.sh
   ```

## Testing the Wallpaper

Once installed:
1. Open "ThreeDLite Gems" from your app drawer
2. Tap "Set as Live Wallpaper"
3. Preview the wallpaper and tap "Set Wallpaper"

## Troubleshooting

- If build fails: Make sure you have Android SDK 34 installed
- If deployment fails: Check device is still connected with `adb devices`
- If app crashes: Check logcat with `adb logcat | grep threedlite`

## Current Status

✅ Project is ready to build
✅ Device is connected (192.168.4.209:40999)
✅ All source files are in place
✅ Resources and assets are configured

You just need to run the build process with Android SDK tools.