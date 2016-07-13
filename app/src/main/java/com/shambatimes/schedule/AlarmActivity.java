package com.shambatimes.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.myapplication.R;

import java.io.IOException;
import java.util.ArrayList;

public class AlarmActivity extends Activity {

    int[] stageColors = ColorUtil.getStageColors();

    final int ONE_MINUTE_MILLISECONDS = 60000;
    static MediaPlayer mediaPlayer;
    static Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        long[] pattern = {0, 700, 1000};

        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ringtone = sharedPreferences.getString(SettingsActivity.ALARM_SOUND, "content://settings/system/alarm_alert");
        String alarmTime = sharedPreferences.getString(SettingsActivity.ALARM_TIMES, "30");
        final boolean vibrate = sharedPreferences.getBoolean(SettingsActivity.ALARM_VIBRATE, false);
        final int alarmLength = Integer.valueOf(sharedPreferences.getString(SettingsActivity.ALARM_LENGTH, "5"));

        Uri ringtoneUri = Uri.parse(ringtone);

        if (ringtoneUri == null || ringtoneUri.equals(Uri.EMPTY)) {
            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        if (vibrator == null) {
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        }

        if (mediaPlayer == null) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setLooping(true);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mediaPlayer.setDataSource(this, ringtoneUri);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        RelativeLayout alarmLayout = (RelativeLayout) findViewById(R.id.alarm_layout);
        TextView artistName = (TextView) findViewById(R.id.artist_name);
        TextView artistTime = (TextView) findViewById(R.id.artist_time);
        Button dismissButton = (Button) findViewById(R.id.dismiss_alarm_button);

        String[] stageNames = getResources().getStringArray(R.array.stages);

        TypedArray colors = getResources().obtainTypedArray(R.array.stage_light_colors);

        String[] query1 = {intent.getStringExtra("id")};

        ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, "id = ?", query1, null, null, null);
        if (artists.size() == 1) {

            Artist artist = artists.get(0);
            artist.setIsAlarmSet(false);
            artist.save();

            alarmLayout.setBackgroundColor(ContextCompat.getColor(this, stageColors[artist.getStage()]));
            artistName.setText(artist.getArtistName());
            artistTime.setText(getResources().getString(R.string.alarm_time, alarmTime, stageNames[artist.getStage()]));
            dismissButton.setBackgroundColor(colors.getColor(artist.getStage(), 0));
            colors.recycle();

            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopPlayback();
                    finish();
                }
            });

//                Intent notificationIntent = new Intent(getApplicationContext(), AlarmActivity.class);
//                PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

//                Notification test = new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_alarm_white_48dp)
//                        .setContentTitle("ShambaTimes Reminder")
//                        .setContentText("Justin Martin playing in 30 minutes")
//                        .setCategory("alarm")
//                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC).build();
//
//
//                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(1, test);

        }

        if (savedInstanceState == null) {
            mediaPlayer.start();
            if (vibrate) {
                vibrator.vibrate(pattern, 0);
            }

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopPlayback();
                    finish();
                }
            }, alarmLength * ONE_MINUTE_MILLISECONDS);
        }
    }

    private void stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (vibrator != null) {
            vibrator.cancel();
        }

        mediaPlayer = null;
        vibrator = null;

        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        stopPlayback();
    }
}
