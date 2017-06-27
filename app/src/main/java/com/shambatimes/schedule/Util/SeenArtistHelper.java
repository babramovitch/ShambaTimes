package com.shambatimes.schedule.Util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.MainActivity;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;

/**
 * Created by benjaminabramovitch on 2017-06-08.
 * <p>
 * Due to the poor use of duplication of adapters, I've moved this newer code into a helper class
 * so I'm not duplicating this as well.
 */

public class SeenArtistHelper {

    public static void updateSeenState(Context context, Artist artist, ImageView seenImage, boolean reverseColor) {

        DateTime now = new DateTime();

        DateTime artistStartTime = DateUtils.getFullDateTimeForArtist(artist);

        if (now.isAfter(artistStartTime)) {
            if (reverseColor) {
                SeenArtistHelper.setReverseSeenImageColor(context, artist, seenImage);
            } else {
                SeenArtistHelper.setSeenImageColor(context, artist, seenImage);
            }
            SeenArtistHelper.animateVisibility(seenImage, !artist.isSeenArtist());
            artist.setSeenArtist(!artist.isSeenArtist());
            artist.save();
            MainActivity.shambhala.updateArtistById(artist.getId());
        }
    }

    static public void animateVisibility(final ImageView image, boolean visible) {
        if (visible) {
            image.setAlpha(0f);
            image.setVisibility(View.VISIBLE);
            image.animate().setDuration(500).alpha(1.0f);
        } else {
            image.animate().setDuration(500).alpha(0.0f);
            image.postDelayed(new Runnable() {
                @Override
                public void run() {
                    image.setVisibility(View.GONE);
                }
            }, 500);
        }
    }

    public static void setSeenImageColor(Context context, Artist artist, ImageView seenImage) {
        if (artist.isFavorite()) {
            seenImage.setColorFilter(ColorUtil.imageInHeart(context));
        } else {
            seenImage.setColorFilter(ContextCompat.getColor(context, ColorUtil.getStageColors()[artist.getStage()]));
        }
    }

    public static void setReverseSeenImageColor(Context context, Artist artist, ImageView seenImage) {
        if (artist.isFavorite()) {
            seenImage.setColorFilter(ColorUtil.themedBackground(context, artist.getStage()));
        } else {
            if (ColorUtil.nightMode) {
                seenImage.setColorFilter(ContextCompat.getColor(context, ColorUtil.getStageColors()[artist.getStage()]));
            } else {
                seenImage.setColorFilter(ContextCompat.getColor(context, R.color.white));
            }
        }
    }
}
