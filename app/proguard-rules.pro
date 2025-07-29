# Add project specific ProGuard rules here.

# Keep all wallpaper service classes
-keep class com.threedlite.exotics.ThreeDLiteExoticsWallpaper { *; }
-keep class com.threedlite.exotics.BaseWallpaper { *; }
-keep class com.threedlite.exotics.BaseWallpaper$BaseEngine { *; }

# Keep model classes
-keep class com.threedlite.model.** { *; }
-keep class com.threedlite.data.** { *; }

# Keep settings activity
-keep class com.threedlite.exotics.SettingsActivity { *; }

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}