package com.shambatimes.Alarms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.shambatimes.Alarms.AlarmHelper;

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AlarmHelper.recalculateAllAlarmTimes(context);
        }
    }
}