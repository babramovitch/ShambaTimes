package com.shambatimes.schedule.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;

import com.shambatimes.schedule.animations.MyTransitionDrawable;
import com.shambatimes.schedule.myapplication.R;

/**
 * Created by babramovitch on 5/14/2017.
 */

public class AnimationHelper {

     public static TransitionDrawable getFavoriteTransitionDrawable(Context context, boolean initialFavorite){

         TransitionDrawable transitionDrawable;

        if (initialFavorite) {
            transitionDrawable = new MyTransitionDrawable(new Drawable[]{
                    ContextCompat.getDrawable(context, R.drawable.new_favourite),
                    ContextCompat.getDrawable(context, R.drawable.new_favourite_border)},true);

            transitionDrawable.setCrossFadeEnabled(true);
        } else {
            transitionDrawable = new MyTransitionDrawable(new Drawable[]{
                    ContextCompat.getDrawable(context, R.drawable.new_favourite_border),
                    ContextCompat.getDrawable(context, R.drawable.new_favourite)},false);
        }

        transitionDrawable.setCrossFadeEnabled(true);

        return transitionDrawable;
    }


}
