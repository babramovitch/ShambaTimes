package com.shambatimes.Alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (intent.getAction() != null && intent.getAction().equals(AlarmActivity.ALARM_ACTION)) {
            Intent in = new Intent("dismissAlarmActivity");
            context.sendBroadcast(in);
        } else {
            Intent alarmIntent = new Intent(context, AlarmActivity.class);
            alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            alarmIntent.putExtras(intent.getExtras());
            context.startActivity(alarmIntent);
        }
    }
}