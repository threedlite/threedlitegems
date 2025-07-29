package com.threedlite.gems;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SettingsActivity extends Activity {

    public static final String PREFS_NAME = "ThreeDLitePrefs";
    public static final String DISPLAY_MODE_KEY = "display_mode";
    public static final String MODE_CLASSIC = "classic";
    public static final String MODE_GEMS = "gems";

    private RadioGroup displayModeGroup;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        
        TextView titleView = new TextView(this);
        titleView.setText("ThreeDLite Live Wallpaper");
        titleView.setTextSize(24);
        titleView.setPadding(0, 0, 0, 20);
        layout.addView(titleView);
        
        TextView descView = new TextView(this);
        descView.setText("Beautiful animated 3D polyhedra floating in space.\n\nChoose your preferred display mode:");
        descView.setTextSize(16);
        descView.setPadding(0, 0, 0, 20);
        layout.addView(descView);
        
        // Display mode selection
        TextView modeLabel = new TextView(this);
        modeLabel.setText("Display Mode:");
        modeLabel.setTextSize(18);
        modeLabel.setPadding(0, 0, 0, 10);
        layout.addView(modeLabel);
        
        displayModeGroup = new RadioGroup(this);
        displayModeGroup.setOrientation(RadioGroup.VERTICAL);
        
        RadioButton classicButton = new RadioButton(this);
        classicButton.setText("Classic (9 Traditional Polyhedra)");
        classicButton.setId(1);
        classicButton.setPadding(20, 10, 0, 10);
        displayModeGroup.addView(classicButton);
        
        RadioButton gemsButton = new RadioButton(this);
        gemsButton.setText("Gems (142 Complex Polyhedra)");
        gemsButton.setId(2);
        gemsButton.setPadding(20, 10, 0, 10);
        displayModeGroup.addView(gemsButton);
        
        // Set current selection
        String currentMode = prefs.getString(DISPLAY_MODE_KEY, MODE_GEMS);
        if (MODE_CLASSIC.equals(currentMode)) {
            classicButton.setChecked(true);
        } else {
            gemsButton.setChecked(true);
        }
        
        displayModeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String mode = (checkedId == 1) ? MODE_CLASSIC : MODE_GEMS;
                prefs.edit().putString(DISPLAY_MODE_KEY, mode).apply();
                Toast.makeText(SettingsActivity.this, "Display mode changed to " + 
                    (mode.equals(MODE_CLASSIC) ? "Classic" : "Gems"), Toast.LENGTH_SHORT).show();
            }
        });
        
        layout.addView(displayModeGroup);
        
        Button setWallpaperButton = new Button(this);
        setWallpaperButton.setText("Set as Live Wallpaper");
        setWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });
        setWallpaperButton.setPadding(0, 40, 0, 0);
        layout.addView(setWallpaperButton);
        
        TextView infoView = new TextView(this);
        infoView.setText("\nTouch the screen to interact with the polyhedra!\n\nThis wallpaper does not collect any personal data.\nNo internet connection required.");
        infoView.setTextSize(12);
        infoView.setPadding(0, 40, 0, 0);
        layout.addView(infoView);
        
        // Add build version and timestamp
        TextView buildInfoView = new TextView(this);
        String buildInfo = getBuildInfo();
        buildInfoView.setText(buildInfo);
        buildInfoView.setTextSize(10);
        buildInfoView.setPadding(0, 20, 0, 0);
        buildInfoView.setTextColor(0xFF666666); // Gray color
        layout.addView(buildInfoView);
        
        setContentView(layout);
    }
    
    private void setWallpaper() {
        try {
            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                new ComponentName(this, ThreeDLiteExoticsWallpaper.class));
            startActivity(intent);
        } catch (Exception e) {
            try {
                Intent intent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
                startActivity(intent);
            } catch (Exception e2) {
                Toast.makeText(this, "Error: Unable to set wallpaper", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private String getBuildInfo() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            int versionCode = packageInfo.versionCode;
            
            // Get build timestamp (APK installation time as proxy)
            long buildTime = packageInfo.firstInstallTime;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            String buildTimestamp = sdf.format(new Date(buildTime));
            
            return "\nVersion: " + versionName + " (" + versionCode + ")\nBuild: " + buildTimestamp;
        } catch (PackageManager.NameNotFoundException e) {
            return "\nVersion: Unknown";
        }
    }
}