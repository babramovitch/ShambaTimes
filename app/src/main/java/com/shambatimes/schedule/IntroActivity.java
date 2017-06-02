
package com.shambatimes.schedule;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.myapplication.R;

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntro2Fragment.newInstance("Welcome to ShambaTimes!", "Lets quickly go over some of the cool features in this app", R.drawable.ic_launcher, ContextCompat.getColor(this,R.color.living_room_color)));
        addSlide(AppIntro2Fragment.newInstance("Quick Jump", "The 'By' Time, Stage and Grid schedules are interconnected.\n\n You can Quick Jump between them by tapping or long pressing on an artist.", R.drawable.action_show_favourite, ContextCompat.getColor(this,R.color.pagoda_color)));

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            addSlide(AppIntro2Fragment.newInstance("Now Playing Widget", "You can set the widget up on your home screen for a quick view of who's playing without opening the app.", R.drawable.action_show_favourite, ContextCompat.getColor(this,R.color.amphitheatre_color)));
        }else{
            addSlide(AppIntro2Fragment.newInstance("Now Playing Widget", "You can set the widget up on your lockscreen screen for a quick view of who's playing without unlocking your phone.", R.drawable.action_show_favourite, ContextCompat.getColor(this,R.color.amphitheatre_color)));
        }

        addSlide(AppIntro2Fragment.newInstance("Night Mode", "Phone too bright at night?  Check out the settings section for Night Mode.", R.drawable.action_show_favourite, ContextCompat.getColor(this,R.color.lighterSecondaryUISelectionGray)));

        setColorDoneText(Color.WHITE);

        //showBackButtonWithDone = true;
        showSkipButton(true);
        setProgressButtonEnabled(true);

        showStatusBar(false);

        setColorSkipButton(Color.WHITE);
        //skipButton.setVisibility(View.VISIBLE);

        setFadeAnimation();

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
       // setVibrate(true);
       // setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}