package com.shambatimes.schedule.Widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.shambatimes.schedule.Constants;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Shambhala;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Implementation of App Widget functionality.
 */
public class ScheduleWidget extends AppWidgetProvider {

    private final String TAG = "NewAppWIdget";
    private static final String SYNC_CLICKED_PAGODA = "pagoda";
    private static final String SYNC_CLICKED_FOREST = "forest";
    private static final String SYNC_CLICKED_GROVE_ = "grove";
    private static final String SYNC_CLICKED_LIVINGROOM = "livingroom";
    private static final String SYNC_CLICKED_VILLAGE = "village";
    private static final String SYNC_CLICKED_AMPH = "amph";
    private static final String SYNC_CLICKED_CEDAR_LOUNGE = "cedar";

    Integer[] stage_title_array = {R.id.pagoda_title, R.id.forest_title, R.id.grove_title , R.id.living_room_title, R.id.village_title, R.id.amphitheatre_title, R.id.cedar_lounge_title};
    Integer[] widget_array = {R.id.pagoda_widget, R.id.forest_widget, R.id.grove_widget, R.id.living_room_widget, R.id.village_widget, R.id.amphitheatre_widget, R.id.cedar_lounge_widget};
    Integer[] artist_array = {R.id.pagoda_artist, R.id.forest_artist, R.id.grove_artist, R.id.living_room_artist, R.id.village_artist, R.id.amphitheatre_artist, R.id.cedar_lounge_artist};
    Integer[] like_array = {R.id.pagoda_like, R.id.forest_like, R.id.grove_like, R.id.living_room_like, R.id.village_like, R.id.amphitheatre_like, R.id.cedar_lounge_like};
    Integer[] time_array = {R.id.pagoda_time, R.id.forest_time, R.id.grove_time, R.id.living_room_time, R.id.village_time, R.id.amphitheatre_time, R.id.cedar_lounge_time};
    String[] stage_array = {SYNC_CLICKED_PAGODA, SYNC_CLICKED_FOREST, SYNC_CLICKED_GROVE_, SYNC_CLICKED_LIVINGROOM, SYNC_CLICKED_VILLAGE, SYNC_CLICKED_AMPH, SYNC_CLICKED_CEDAR_LOUNGE};

//http://stackoverflow.com/questions/14798073/button-click-event-for-android-widget

//see http://stackoverflow.com/questions/8304387/android-how-do-i-force-the-update-of-all-widgets-of-a-particular-kind
//for additional way to update it as homescreen doesn't update with the onrecieve like lockscreen does.

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.schedule_widget_layout);

        //Update all the views
        views = updateArtist(views, context);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    DateTimeFormatter dateStringFormat;

    private RemoteViews updateArtist(RemoteViews views, Context context) {

        int position = DateUtils.getCurrentTimePosition(context);
        int day = DateUtils.getCurrentDay(context);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        dateStringFormat = DateUtils.getTimeFormat(preferences.getString(SettingsActivity.TIME_FORMAT, "24"));

        CharSequence widgetText;

        widgetText = DateTime.now().withZone(Constants.timeZone).toString(dateStringFormat);

        views.setTextViewText(R.id.appwidget_time, "Tap to Update - Last Updated: " + widgetText);

        //Reset the widget views to empty since the next refresh may include a new empty slot which won't get updated.
        for (int x = 0; x < 7; x++) {
            widgetText = "stage closed";
            views.setTextViewText(artist_array[x], widgetText);

            widgetText = "";
            views.setTextViewText(time_array[x], widgetText);
            views.setImageViewResource(like_array[x], R.drawable.new_favourite_border);
            views.setViewVisibility(like_array[x], View.INVISIBLE);
            views.setInt(widget_array[x], "setBackgroundColor", ContextCompat.getColor(context, ColorUtil.getLightStageColors()[x]));
            views.setTextColor(artist_array[x], ContextCompat.getColor(context, R.color.noArtistTextColorWidgetDay));
            views.setTextColor(stage_title_array[x], ContextCompat.getColor(context, R.color.noArtistTextColorWidgetDay));
        }

        if (!DateUtils.isPrePostFestival(context)) {

            ArrayList<Artist> artistList = (ArrayList) Artist.find(Artist.class, "start_Position <= ? and end_Position > ? and day = ? and year = ?", "" + position, "" + position, "" + day, Shambhala.CURRENT_YEAR);

            for (Artist artist : artistList) {


                int artistStage = artist.getStage();

                views.setInt(widget_array[artistStage], "setBackgroundColor", ContextCompat.getColor(context, ColorUtil.getStageColors()[artistStage]));
                views.setTextColor(artist_array[artistStage], ContextCompat.getColor(context, R.color.white));
                views.setTextColor(stage_title_array[artistStage], ContextCompat.getColor(context, R.color.white));
                views.setViewVisibility(like_array[artistStage], View.VISIBLE);

                widgetText = "" + artist.getAristName();
                views.setTextViewText(artist_array[artistStage], widgetText);

                widgetText = "" + DateUtils.formatTime(dateStringFormat, artist.getStartTimeString()) + " - " + DateUtils.formatTime(dateStringFormat, artist.getEndTimeString());
                views.setTextViewText(time_array[artistStage], widgetText);

                if (artist.isFavorite()) {
                    views.setImageViewResource(like_array[artistStage], R.drawable.new_favourite);
                } else {
                    views.setImageViewResource(like_array[artistStage], R.drawable.new_favourite_border);
                }

                //  Log.i(TAG, "Position:" + position);
                //  Log.i(TAG, "Artist Id:" + artist.getId());
                //  Log.i(TAG, "Artist Name:" + artist.getAristName());

                views.setOnClickPendingIntent(like_array[artistStage], getPendingSelfIntent(context, stage_array[artistStage], artist.getId()));

                //views.setOnClickPendingIntent(R.id.widget_layout, getPendingSelfIntent(context, "update", -1));

            }
        }

        views.setOnClickPendingIntent(R.id.widget_layout, getPendingSelfIntent(context, "update", -1));

        return views;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);

        RemoteViews remoteViews;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.schedule_widget_layout);
        int day = DateUtils.getCurrentDay(context);
        int artistStage = getIntentID(intent.getAction());

        if (artistStage != -1) {

            int position = DateUtils.getCurrentTimePosition(context);

            ArrayList<Artist> artist = (ArrayList) Artist.find(Artist.class, "stage = ? and start_Position <= ? and end_Position > ? and day = ? and year = ?", "" + artistStage, "" + position, "" + position, "" + day, Shambhala.CURRENT_YEAR);

            if (artist != null && artist.size() == 1) {

                EventBus.getDefault().postSticky(new DataChangedEvent(true, artist.get(0).getId()));

                if (artist.get(0).isFavorite()) {
                    artist.get(0).setFavorite(false);
                } else {
                    artist.get(0).setFavorite(true);
                }
                artist.get(0).save();
            }
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        ComponentName scheduleWidget;

        remoteViews = updateArtist(remoteViews, context);

        scheduleWidget = new ComponentName(context, ScheduleWidget.class);
        appWidgetManager.updateAppWidget(scheduleWidget, remoteViews);

    }

    private int getIntentID(String stage) {
        int id = -1;

        switch (stage) {

            case SYNC_CLICKED_PAGODA:
                id = 0;
                break;
            case SYNC_CLICKED_FOREST:
                id = 1;
                break;

            case SYNC_CLICKED_GROVE_:
                id = 2;
                break;

            case SYNC_CLICKED_LIVINGROOM:
                id = 3;
                break;

            case SYNC_CLICKED_VILLAGE:
                id = 4;
                break;

            case SYNC_CLICKED_AMPH:
                id = 5;
                break;

            case SYNC_CLICKED_CEDAR_LOUNGE:
                id = 6;
                break;
        }
        return id;
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action, long id) {

        Intent intent = new Intent(context, getClass());
        intent.setAction(action);

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}