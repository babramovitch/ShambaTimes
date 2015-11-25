package com.shambatimes.schedule.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;


import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.myapplication.R;

import java.util.ArrayList;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        new Thread(new Runnable() {
            public void run() {
                try {

                    String[] query1 = {intent.getStringExtra("id")};
                    ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, "id = ?", query1, null, null, null);
                    if (artists.size() == 1) {
                        artists.get(0).setIsAlarmSet(false);
                        artists.get(0).save();
                        Log.i("AlarmReceiver", "Alarm has been turned off for " + artists.get(0).getArtistName());
                    }

                    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.test_cbr);
                    mediaPlayer.start();

                    //Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    //long[] pattern = {0, 5000, 500, 100, 500, 100, 500, 100, 500, 100, 500};
                    //v.vibrate(pattern, 3);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}