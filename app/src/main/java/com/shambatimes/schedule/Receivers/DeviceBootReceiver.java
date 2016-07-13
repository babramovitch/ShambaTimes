package com.shambatimes.schedule.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.shambatimes.schedule.Shambhala;
import com.shambatimes.schedule.Util.AlarmHelper;

public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            AlarmHelper.recalculateAllAlarmTimes(context);
        }
    }
}