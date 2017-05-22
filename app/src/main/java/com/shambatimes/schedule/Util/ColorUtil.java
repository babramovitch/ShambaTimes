package com.shambatimes.schedule.Util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.shambatimes.schedule.StageScheduleFragment;
import com.shambatimes.schedule.myapplication.R;

/**
 * Created by babramovitch on 5/18/2016.
 */
public class ColorUtil {

    public static boolean nightMode = true;
    static int currentThemeColor = 0xFF666666;

    public static int[] getStageColors() {
        return new int[]{R.color.pagoda_color,
                R.color.fractal_forest_color,
                R.color.grove_color,
                R.color.living_room_color,
                R.color.village_color,
                R.color.amphitheatre_color,
                R.color.cedar_lounge_color};

    }

    public static int[] getLightStageColors() {
        return new int[]{R.color.pagoda_color_light,
                R.color.fractal_forest_color_light,
                R.color.grove_color_light,
                R.color.living_room_color_light,
                R.color.village_color_light,
                R.color.amphitheatre_color_light,
                R.color.cedar_lounge_color_light};

    }

    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }


    public static void setCurrentThemeColor(int color) {
        currentThemeColor = color;
    }

    public static int getCurrentThemeColor() {
        return currentThemeColor;
    }

    //public static int getCurrentThemeColor

    public static int[] getDividerGradientColor(int color) {

        int[] colors = new int[3];

        if (nightMode) {
            colors = new int[]{color, color, color};
        } else {
            colors[1] = color;
        }

        return colors;
    }

    public static int themedGray(Context context) {
        if (nightMode) {
            return getColor(context, R.color.lighterSecondaryUISelectionGray);
        } else {
            return currentThemeColor;
        }
    }

    public static int cellBackgroundColor(Context context) {
        if (nightMode) {
            return getColor(context, R.color.lighterPrimaryUIGray);
        } else {
            return getColor(context, R.color.white);
        }
    }

    private static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);

    }

    public static int dividerColor(Context context) {
        if (nightMode) {
            return getColor(context, R.color.darkPrimaryBackgroundGray);
        } else {
            return currentThemeColor;
        }
    }


    public static int pagerBackgroundColor(Context context) {
        if (nightMode) {
            return getColor(context, R.color.darkPrimaryBackgroundGray);
        } else {
            return getColor(context, R.color.white);
        }
    }

    public static int snackbarTextColor(Context context) {
        if (nightMode) {
            return getColor(context, R.color.primaryTextColor);
        } else {
            return getColor(context, R.color.white);
        }
    }
}
