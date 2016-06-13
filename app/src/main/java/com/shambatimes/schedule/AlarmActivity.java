package com.shambatimes.schedule;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.myapplication.R;

import java.util.ArrayList;

public class AlarmActivity extends Activity {

    int[] stageColors = ColorUtil.getStageColors();

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();

        mediaPlayer = MediaPlayer.create(this, R.raw.test_cbr);

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

            alarmLayout.setBackgroundColor(getResources().getColor((stageColors[artist.getStage()])));
            artistName.setText(artist.getArtistName());
            artistTime.setText(getResources().getString(R.string.alarm_time, "30", stageNames[artist.getStage()]));
            dismissButton.setBackgroundColor(colors.getColor(artist.getStage(), 0));

            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
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

        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }
}
