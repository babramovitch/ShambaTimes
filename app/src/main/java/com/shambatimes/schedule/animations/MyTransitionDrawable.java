package com.shambatimes.schedule.animations;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

/**
 * Created by babramovitch on 5/14/2017.
 */

public class MyTransitionDrawable extends TransitionDrawable{

    private boolean initialFavorite = false;

    public MyTransitionDrawable(Drawable[] layers) {
        super(layers);
    }

    public MyTransitionDrawable(Drawable[] layers, boolean initialFavorite) {
        super(layers);
        this.initialFavorite = initialFavorite;
    }

    public boolean isInitialFavorite() {
        return initialFavorite;
    }

    public void favoriteStart(int durationMillis) {
       if(!initialFavorite){
           super.startTransition(durationMillis);
       }else{
           super.reverseTransition(durationMillis);
       }
    }

    public void favoriteReverse(int durationMillis) {
        if(!initialFavorite){
            super.reverseTransition(durationMillis);
        }else{
            super.startTransition(durationMillis);
        }
    }
}
