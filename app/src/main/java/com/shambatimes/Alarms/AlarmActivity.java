package com.shambatimes.Alarms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.myapplication.R;
import com.shambatimes.schedule.views.MySwipeLayout;

import java.io.IOException;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class AlarmActivity extends Activity {

    public static final String ALARM_ACTION = "ALARM_ACTION";

    static MediaPlayer mediaPlayer;
    static Vibrator vibrator;

    private Artist artist;

    boolean alarmCancelled = false;

    private boolean vibrate;
    private int alarmLength;
    private int alarmNotificationId;

    private String ringtone;
    private String alarmTime;

    private BroadcastReceiver dismissAlarmReceiver = new dismissButtonListener();

    public class dismissButtonListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            cancelNotification();
            stopAlarmPlayback();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_alarm);

        registerAlarmReceiver();
        loadPreferences();
        configureAlarm();
        setupAlarmLayout();

        if (savedInstanceState == null) {
            startAlarm();
        }
    }

    private void registerAlarmReceiver() {
        IntentFilter filter = new IntentFilter("dismissAlarmActivity");
        this.registerReceiver(dismissAlarmReceiver, filter);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        alarmTime = sharedPreferences.getString(SettingsActivity.ALARM_TIMES, "30");
        vibrate = sharedPreferences.getBoolean(SettingsActivity.ALARM_VIBRATE, true);
        alarmLength = Integer.valueOf(sharedPreferences.getString(SettingsActivity.ALARM_LENGTH, "5"));
        ringtone = sharedPreferences.getString(SettingsActivity.ALARM_SOUND, "content://settings/system/alarm_alert");
    }

    private void configureAlarm() {

        Uri ringtoneUri = Uri.parse(ringtone);

        if (ringtoneUri == null || ringtoneUri.equals(Uri.EMPTY)) {
            ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
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
    }

    private void setupAlarmLayout() {
        Intent intent = getIntent();

        final MySwipeLayout swipeLayout = (MySwipeLayout) findViewById(R.id.swipe_layout);
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                swipeLayout.setSwipeEnabled(false);
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        RelativeLayout alarmLayout = (RelativeLayout) findViewById(R.id.alarm_layout);
        LinearLayout topWrapper = (LinearLayout) findViewById(R.id.top_wrapper);
        TextView artistName = (TextView) findViewById(R.id.artist_name);
        TextView artistTime = (TextView) findViewById(R.id.artist_time);
        Button dismissButton = (Button) findViewById(R.id.dismiss_alarm_button);

        String[] stageNames = getResources().getStringArray(R.array.stages);
        String[] query1 = {intent.getStringExtra("id")};

        ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, "id = ?", query1, null, null, null);
        if (artists.size() > 0) {

            artist = artists.get(0);
            artist.setIsAlarmSet(false);
            artist.save();

            //Notify the rest of the app so the artist if shown no longer shows an alarm clock
            EventBus.getDefault().post(new DataChangedEvent(true, artist.getId()));

            int[] stageColors = ColorUtil.getStageColors();
            float alpha = 0.5f;

            if (ColorUtil.nightMode) {
                alpha = 1f;
                alarmLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lighterSecondaryUISelectionGray));
            } else {
                alarmLayout.setBackgroundColor(ContextCompat.getColor(this, stageColors[artist.getStage()]));
            }
            artistName.setText(artist.getArtistName());
            artistTime.setText(getResources().getString(R.string.alarm_time, alarmTime, stageNames[artist.getStage()]));

            dismissButton.setBackgroundColor(ColorUtil.adjustAlpha(ContextCompat.getColor(this, stageColors[artist.getStage()]), alpha - 0.1f));
            topWrapper.setBackgroundColor(ColorUtil.adjustAlpha(ContextCompat.getColor(this, stageColors[artist.getStage()]), alpha));
            createAlarmNotification(getResources().getString(R.string.alarm_time, alarmTime, stageNames[artist.getStage()]));
        }

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
                stopAlarmPlayback();
                finish();
            }
        });
    }

    private void startAlarm() {
        mediaPlayer.start();
        if (vibrate) {
            long[] pattern = {0, 700, 1000};
            vibrator.vibrate(pattern, 0);
        }

        startAlarmTimeout();
    }

    private void startAlarmTimeout() {
        final int ONE_MINUTE_MILLISECONDS = 60000;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!alarmCancelled) {
                    cancelNotification();
                    createMissedAlarmNotification();
                    stopAlarmPlayback();
                    finish();
                }
            }
        }, alarmLength * ONE_MINUTE_MILLISECONDS);
    }

    private void createAlarmNotification(String alarmMessage) {

        alarmNotificationId = Integer.valueOf(String.valueOf(artist.getDay()) + String.valueOf(artist.getStage()) + String.valueOf(artist.getStartPosition()));

        Intent dismissReceive = new Intent();
        dismissReceive.setAction(ALARM_ACTION);

        PendingIntent pendingIntentDismiss = PendingIntent.getBroadcast(this, alarmNotificationId, dismissReceive, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .addAction(R.drawable.ic_alarm_black_18dp, getString(R.string.dismiss_button), pendingIntentDismiss)
                .setSmallIcon(R.drawable.ic_alarm_black_18dp)
                .setContentTitle(artist.getArtistName())
                .setContentText(alarmMessage)
                .setCategory("alarm")
                .setDeleteIntent(pendingIntentDismiss)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC).build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(alarmNotificationId, notification);
    }

    private void createMissedAlarmNotification() {
        Notification missedNotification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_alarm_black_18dp)
                .setContentTitle(getString(R.string.missed_alarm_title))
                .setContentText(getString(R.string.missed_alarm_message) + artist.getAristName())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) //must give priority to High, Max which will considered as heads-up notification
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(alarmNotificationId, missedNotification);
    }

    private void cancelNotification() {
        alarmCancelled = true;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(alarmNotificationId);
    }

    private void stopAlarmPlayback() {
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
    public void onBackPressed() {
        //Overriding the back button so an accidental in pocket press doesn't dismiss the alarm since
        //I can't tell the difference between an accidental and intentional back press.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.dismissAlarmReceiver);
    }
}



