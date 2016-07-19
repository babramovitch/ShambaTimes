package com.shambatimes.schedule.Util;

import com.shambatimes.schedule.myapplication.R;

/**
 * Created by babramovitch on 5/18/2016.
 */
public class ColorUtil {

    public static int[] getStageFavoriteDrawables(){
        return new int[]{R.drawable.favorite_pagoda,
                R.drawable.favorite_forest,
                R.drawable.favorite_grove,
                R.drawable.favorite_living_room,
                R.drawable.favorite_village,
                R.drawable.favorite_amphitheatre,
                R.drawable.favorite_cedar_lounge};
    }

    public static int[] getStageFavoriteOutlineDrawables(){
        return new int[] {R.drawable.favorite_outline_pagoda,
                R.drawable.favorite_outline_forest,
                R.drawable.favorite_outline_grove,
                R.drawable.favorite_outline_living_room,
                R.drawable.favorite_outline_village,
                R.drawable.favorite_outline_amphitheatre,
                R.drawable.favorite_outline_cedar_lounge};

    }

    public static int[] getStageColors(){
        return new int[] {R.color.pagoda_color,
                R.color.fractal_forest_color,
                R.color.grove_color,
                R.color.living_room_color,
                R.color.village_color,
                R.color.amphitheatre_color,
                R.color.cedar_lounge_color};

    }
}
