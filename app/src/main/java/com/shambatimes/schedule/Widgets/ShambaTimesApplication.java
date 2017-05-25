package com.shambatimes.schedule.Widgets;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;

import com.orm.SugarContext;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Util.ColorUtil;

public class ShambaTimesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isNightMode = prefs.getBoolean(SettingsActivity.NIGHT_MODE, false);
        boolean isNightModeAutomatic = prefs.getBoolean(SettingsActivity.NIGHT_MODE_AUTOMATIC, false);

        if (isNightModeAutomatic) {
            setNightModeAutomatic(true);
        } else {
            setNightMode(isNightMode);
        }
    }

    public void setNightMode(boolean nightMode) {
        ColorUtil.nightMode = nightMode;

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void setNightModeAutomatic(boolean checked) {

        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void updateNightModeFlagIfAutomatic() {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_AUTO) {

            int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

            switch (nightModeFlags) {
                case Configuration.UI_MODE_NIGHT_YES:
                    ColorUtil.nightMode = true;
                    break;
                case Configuration.UI_MODE_NIGHT_NO:
                    ColorUtil.nightMode = false;
                    break;
                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    break;
            }
        }
    }
}
