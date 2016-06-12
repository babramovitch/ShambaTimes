package com.shambatimes.schedule.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.Receivers.AlarmReceiver;
import com.shambatimes.schedule.Shambhala;
import com.shambatimes.schedule.myapplication.R;

/**
 * Created by babramovitch on 11/24/2015.
 */
public class AlarmHelper {

    Context context;
    Artist artist;
    View layout;
    Snackbar snackbar;

    int[] stageColors = {R.color.pagoda_color,
            R.color.fractal_forest_color,
            R.color.grove_color,
            R.color.living_room_color,
            R.color.village_color,
            R.color.amphitheatre_color,
            R.color.fractal_forest_color};


    public AlarmHelper(Context context, View layout) {
        this.context = context;
        this.layout = layout;
    }

    public void showSetAlarmSnackBar(Artist artist) {

        if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

            this.artist = artist;

            final View coordinatorLayoutView = layout.findViewById(R.id.snackbarPosition);
            coordinatorLayoutView.setVisibility(View.VISIBLE);

            snackbar = Snackbar.make(coordinatorLayoutView, "Add alarm 30 minutes before " + artist.getAristName(), Snackbar.LENGTH_LONG)
                    .setAction("OK", snackBarClickListener)
                    .setDuration(Snackbar.LENGTH_LONG);

            View snackbarView = snackbar.getView();

            snackbarView.setBackgroundColor(context.getResources().getColor((stageColors[artist.getStage()])));

            TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);

            snackBarTextView.setTextColor(Color.WHITE);
            snackBarActionTextView.setTextColor(Color.WHITE);

            snackBarActionTextView.setTextSize(14);

            snackbar.show();
        }
    }

    final View.OnClickListener snackBarClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            final View coordinatorLayoutView = layout.findViewById(R.id.snackbarPosition);

            snackbar = Snackbar.make(coordinatorLayoutView, "Alarm Added for " + artist.getAristName(), Snackbar.LENGTH_LONG)
                    .setDuration(Snackbar.LENGTH_LONG);


            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(context.getResources().getColor((stageColors[artist.getStage()])));

            TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);

            snackBarTextView.setTextColor(Color.WHITE);
            snackBarTextView.setGravity(Gravity.CENTER_HORIZONTAL);

            snackbar.show();

            setAlarm(artist);

        }
    };

    public void setAlarm(Artist artist) {

        if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

            this.artist = artist;

            Log.i("AlarmHelper", "Alarm set for " + artist.getArtistName());

            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            alarmIntent.putExtra("name", artist.getArtistName());
            alarmIntent.putExtra("id", artist.getId().toString());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, artist.getId().intValue(), alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            artist.setIsAlarmSet(true);
            artist.save();


            //TODO - Set the alarm to startTime minus [value from preferences] minutes.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 15000, pendingIntent);
            } else {
                manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pendingIntent);
            }
        }
    }

    public void cancelAlarm(Artist artist) {
        if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

            Log.i("AlarmHelper", "Alarm cancelled for " + artist.getArtistName());

            Intent cancelAlarmIntent = new Intent(context, AlarmReceiver.class);
            cancelAlarmIntent.putExtra("name", artist.getArtistName());
            cancelAlarmIntent.putExtra("id", artist.getId().toString());

            PendingIntent.getBroadcast(context, artist.getId().intValue(), cancelAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
        }
    }

    public void dismissSnackbar() {
        if (snackbar != null) {
            Log.i("AlarmHelper", "Snackbar dismissed");
            snackbar.dismiss();
        }
    }
}
