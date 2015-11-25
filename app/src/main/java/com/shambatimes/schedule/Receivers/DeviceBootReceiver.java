package com.shambatimes.schedule.Receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.shambatimes.schedule.Artist;

import java.util.ArrayList;

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            try {

                String[] query1 = {"1"};
                ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, "is_Alarm_Set = ?", query1, null, null, null);

                for (Artist artist : artists) {

                    Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                    alarmIntent.putExtra("name", artist.getArtistName());
                    alarmIntent.putExtra("id",artist.getId().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, artist.getId().intValue(), alarmIntent, 0);

                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                    //TODO - Set the alarm to startTime minus [value from preferences] minutes.
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000, pendingIntent);
                    }else{
                        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000, pendingIntent);
                    }

                }

                Log.i("DeviceBootReceiver", "There are " + artists.size() + " saved alarms");
            } catch (Exception e) {
                Log.e("DeviceBootReceiver", "BOOM", e);
            }

        }
    }


}