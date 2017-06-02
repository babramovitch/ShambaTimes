package com.shambatimes.schedule.Settings;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.shambatimes.Alarms.AlarmHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.ShambaTimesApplication;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.myapplication.R;

import de.greenrobot.event.EventBus;

/**
 * Created by babramovitch on 5/31/2016.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    public final static String FESTIVAL_YEAR = "FESTIVAL_YEAR";
    public final static String ALARM_TIMES = "ALARM_TIMES";
    public final static String ALARM_SOUND = "ALARM_SOUND";
    public final static String ALARM_VIBRATE = "ALARM_VIBRATE";
    public final static String ALARM_LENGTH = "ALARM_LENGTH";
    public final static String TIME_FORMAT = "TIME_FORMAT";

    public final static String NIGHT_MODE = "NIGHT_MODE";
    public final static String NIGHT_MODE_AUTOMATIC = "NIGHT_MODE_AUTOMATIC";
    int actionBarColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupActionBar();

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        ColorUtil.setCurrentThemeColor(event.getColor());
        actionBarColor = ColorUtil.themedGray(SettingsActivity.this);
        Drawable colorDrawable = new ColorDrawable(actionBarColor);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getDarkerShade(actionBarColor));
        }
    }

    private static final float SHADE_FACTOR = 0.6f;

    private int getDarkerShade(int color) {
        return Color.rgb((int) (SHADE_FACTOR * Color.red(color)),
                (int) (SHADE_FACTOR * Color.green(color)),
                (int) (SHADE_FACTOR * Color.blue(color)));
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public static class SettingsFragment extends PreferenceFragment {

        boolean firstLoadAlarmTimes = true;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (savedInstanceState != null) {
                firstLoadAlarmTimes = savedInstanceState.getBoolean("firstLoadAlarmTimes", true);
            }

            addPreferencesFromResource(R.xml.preferences);
            bindPreferenceSummaryToValue(findPreference(FESTIVAL_YEAR));
            bindPreferenceSummaryToValue(findPreference(ALARM_TIMES));
            bindPreferenceSummaryToValue(findPreference(ALARM_LENGTH));
            bindPreferenceSummaryToValue(findPreference(TIME_FORMAT));

            final CheckBoxPreference nightMode = (CheckBoxPreference) findPreference(NIGHT_MODE);
            final CheckBoxPreference automaticNightMode = (CheckBoxPreference) findPreference(NIGHT_MODE_AUTOMATIC);

            nightMode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    automaticNightMode.setChecked(false);
                    ((ShambaTimesApplication) (getActivity().getApplication())).setNightModeAutomatic(false);

                    CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
                    ((ShambaTimesApplication) (getActivity().getApplication())).setNightMode(checkBoxPreference.isChecked());
                    return false;
                }
            });

            automaticNightMode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    ((ShambaTimesApplication) (getActivity().getApplication())).setNightMode(false);
                    nightMode.setChecked(false);

                    CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
                    ((ShambaTimesApplication) (getActivity().getApplication())).setNightModeAutomatic(checkBoxPreference.isChecked());
                    return false;
                }
            });

        }

        /**
         * Binds a preference's summary to its value. More specifically, when the
         * preference's value is changed, its summary (line of text below the
         * preference title) is updated to reflect the value. The summary is also
         * immediately updated upon calling this method. The exact display format is
         * dependent on the type of preference.
         *
         * @see #sBindPreferenceSummaryToValueListener
         */
        private void bindPreferenceSummaryToValue(Preference preference) {
            // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

            // Trigger the listener immediately with the preference's
            // current value.
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }

        private Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object value) {
                String stringValue = value.toString();

                if (preference instanceof ListPreference) {
                    // For list preferences, look up the correct display value in
                    // the preference's 'entries' list.
                    ListPreference listPreference = (ListPreference) preference;
                    int index = listPreference.findIndexOfValue(stringValue);

                    String message = "";

                    // Set the summary to reflect the new value.
                    if (preference.getKey().equals(ALARM_TIMES)) {
                        message = "The time an alarm will go off before a set.  This time is for all alarms.\n\n";
                        if (!firstLoadAlarmTimes) {
                            AlarmHelper.recalculateAllAlarmTimes(getActivity(), Integer.valueOf(stringValue));
                        }
                        firstLoadAlarmTimes = false;
                    }

                    preference.setSummary(
                            index >= 0
                                    ? message + listPreference.getEntries()[index]
                                    : null);


                } else {
                    // For all other preferences, set the summary to the value's
                    // simple string representation.
                    preference.setSummary(stringValue);
                }
                return true;
            }
        };


        @Override
        public void onSaveInstanceState(Bundle savedInstanceState) {
            savedInstanceState.putBoolean("firstLoadAlarmTimes", firstLoadAlarmTimes);
            super.onSaveInstanceState(savedInstanceState);
        }
    }
}


