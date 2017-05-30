package com.shambatimes.schedule;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.orm.SugarContext;

import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.myapplication.BuildConfig;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;

import io.fabric.sdk.android.Fabric;

public class ShambaTimesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }

        SugarContext.init(this);

        // Initialize the preferences with default settings if
        // this is the first time the application is ever opened
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

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
            updateNightModeFlagIfAutomatic();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void updateNightModeFlagIfAutomatic() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (prefs.getBoolean(SettingsActivity.NIGHT_MODE_AUTOMATIC, false)) {

            DateTime nightStart = new DateTime();
            nightStart = nightStart.withHourOfDay(20);
            nightStart = nightStart.withMinuteOfHour(0);
            nightStart = nightStart.withSecondOfMinute(0);
            nightStart = nightStart.withMillisOfSecond(0);

            DateTime nightEnd = new DateTime();
            nightEnd = nightEnd.withHourOfDay(6);
            nightEnd = nightEnd.withMinuteOfHour(0);
            nightEnd = nightEnd.withSecondOfMinute(0);
            nightEnd = nightEnd.withMillisOfSecond(0);

            DateTime now = new DateTime();

            if (now.isAfter(nightStart) || now.isBefore(nightEnd)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                ColorUtil.nightMode = true;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                ColorUtil.nightMode = false;
            }
        }
    }
}
