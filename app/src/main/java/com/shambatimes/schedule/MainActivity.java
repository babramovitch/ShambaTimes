package com.shambatimes.schedule;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.Alarms.AlarmHelper;
import com.shambatimes.schedule.Util.AnimationHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.animations.MyTransitionDrawable;
import com.shambatimes.schedule.Util.SeenArtistHelper;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ArtistListLoadDoneEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.ShowHideAutoCompleteSearchClearButtonEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DatabaseLoadFinishedEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;

import com.shambatimes.schedule.events.ToggleFilterVisibility;
import com.shambatimes.schedule.events.ToggleToGridEvent;
import com.shambatimes.schedule.events.ToggleToStageEvent;
import com.shambatimes.schedule.events.ToggleToTimeEvent;
import com.shambatimes.schedule.events.UpdateScheduleByTimeEvent;
import com.shambatimes.schedule.myapplication.R;
import com.shambatimes.schedule.views.ClearableAutoCompleteTextView;

import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.shambatimes.schedule.Constants.ANIMATION_DURATION_HEARTS;

public class MainActivity extends AppCompatActivity {

    public static Shambhala shambhala = new Shambhala();

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean isDrawerOpen = false;
    private boolean isSearchExpanded = false;

    private final int FRAGMENT_TIME = 0;
    private final int FRAGMENT_STAGE = 1;
    private final int FRAGMENT_FAVORITES = 2;
    private final int FRAGMENT_ARTISTS = 3;
    private final int FRAGMENT_CALENDAR = 4;

    private final int RESULT_SETTINGS = 1010;

    //nav bar
    Spinner scheduleSpinner;
    AdapterBaseScheduleDays adapterBaseScheduleDays;

    //Items that require a saved state
    private int currentTheme;
    private int actionBarStage = 0;
    private int currentFragment = FRAGMENT_TIME;
    public int currentDay = -1;
    private int currentTimePosition;

    private String scheduleBy = "Schedule by Time";
    private SharedPreferences prefs;

    private Menu menu;
    private int[] gradientColors = {0, 0, 0};

    ArrayAdapter<Artist> searchAdapter;
    private String searchText;
    ClearableAutoCompleteTextView searchTextView;
    Handler handler = new Handler();

    String festivalYear;
    String timeFormatPreference;

    boolean genreFilteringActive = false;

    private AlertDialog welcomeDialog2017;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Declare a new thread to do a preference check
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //  Initialize SharedPreferences
//                SharedPreferences getPrefs = PreferenceManager
//                        .getDefaultSharedPreferences(getBaseContext());
//
//                //  Create a new boolean and preference and set it to true
//                //  boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
//
//                //  If the activity has never started before...
//                //if (isFirstStart) {
//
//                //  Launch app intro
//                Intent i = new Intent(MainActivity.this, IntroActivity.class);
//                startActivity(i);
//
//                //  Make a new preferences editor
//                SharedPreferences.Editor e = getPrefs.edit();
//
//                //  Edit preference to make it false because we don't want this to run again
//                //e.putBoolean("firstStart", false);
//
//                //  Apply changes
//                e.apply();
//            }
//            //}
//        });
//
//        // Start the thread
//        t.start();


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (!prefs.contains("2017_loaded")) {
            prefs.edit().putString(SettingsActivity.FESTIVAL_YEAR, "2017").apply();
        }

        timeFormatPreference = prefs.getString(SettingsActivity.TIME_FORMAT, "24");
        festivalYear = Shambhala.getFestivalYear(this);

        DateUtils.setReferenceTime(this);

        prepareAndLoadDatabase();

        if (savedInstanceState == null) {
            currentTimePosition = DateUtils.getCurrentTimePosition(this);
            ColorUtil.setCurrentThemeColor(getResources().getColor(R.color.pagoda_color));
            replaceFragment(R.id.content_frame, new TimeScheduleFragment(), Constants.FRAGMENT_TIME, false);
        } else {
            ColorUtil.setCurrentThemeColor(savedInstanceState.getInt("COLOR"));

            actionBarStage = savedInstanceState.getInt("STAGE");
            currentFragment = savedInstanceState.getInt("POSITION");
            currentDay = savedInstanceState.getInt("DAY");
            scheduleBy = savedInstanceState.getString("TITLE");
            currentTimePosition = savedInstanceState.getInt("CURRENT_TIME");
            isSearchExpanded = savedInstanceState.getBoolean("SEARCH_EXPANDED");
            searchText = savedInstanceState.getString("SEARCH_TEXT", "");
            artistDateSelected = savedInstanceState.getBoolean("artistDateSelected", false);
            genreFilteringActive = savedInstanceState.getBoolean("genreFilteringActive", false);
        }

        setupNavigationDrawer();
        setupToolbar();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_SETTINGS) {
            didFestivalYearChange();
            didTimeFormatChange();
            getDelegate().applyDayNight();
        }
    }

    private void prepareAndLoadDatabase() {

        if (prefs.contains("database_loaded")) {

            if (!prefs.contains("2017_loaded")) {
                Toast.makeText(this, "Preparing 2017 Database", Toast.LENGTH_LONG).show();
            }

            new Thread(new Runnable() {
                public void run() {
                    //2015 updates
                    DatabaseScheduleUpdates.scheduleUpdateOne2015(MainActivity.this);
                    DatabaseScheduleUpdates.scheduleUpdateTwo2015(MainActivity.this);

                    //2016 updates
                    DatabaseScheduleUpdates.load2016Database(MainActivity.this);
                    DatabaseScheduleUpdates.load2016CedarLounge(MainActivity.this);
                    DatabaseScheduleUpdates.scheduleUpdateOne2016(MainActivity.this);
                    DatabaseScheduleUpdates.scheduleUpdateTwo2016(MainActivity.this);

                    //2017 updates
                    DatabaseScheduleUpdates.load2017Database(MainActivity.this);

                    fetchAllArtistsForYear(festivalYear);
                }
            }).start();

        } else if (!prefs.contains("database_load_started")) {

            //TODO make this a snackbar
            Toast.makeText(this, "Preparing Database", Toast.LENGTH_LONG).show();

            new Thread(new Runnable() {
                public void run() {

                    prefs.edit().putBoolean("database_load_started", true).apply();
                    ArtistGenerator artistGenerator = new ArtistGenerator(MainActivity.this);

                    //2017 loaded first so app is quick
                    artistGenerator.get2017Artists();
                    prefs.edit().putBoolean("2017_loaded", true).apply();
                    prefs.edit().putString(SettingsActivity.FESTIVAL_YEAR, "2017").apply();
                    fetchAllArtistsForYear(festivalYear);

                    //Historical data loaded later
                    //2015
                    artistGenerator.get2015Artists();
                    prefs.edit().putBoolean("update_one_complete", true).apply();
                    prefs.edit().putBoolean("update_two_complete", true).apply();

                    //2016
                    artistGenerator.get2016Artists();
                    artistGenerator.get2016CedarLoungeArtists();
                    prefs.edit().putBoolean("2016_loaded", true).apply();
                    prefs.edit().putBoolean("cedar_lounge_loaded_2016", true).apply();
                    prefs.edit().putBoolean("update_one_complete_2016", true).apply();
                    prefs.edit().putBoolean("update_two_complete_2016", true).apply();

                    prefs.edit().putBoolean("database_loaded", true).apply();

                }
            }).start();
        }
    }

    private void fetchArtistDataForQuickLoad() {
        new Thread(new Runnable() {
            public void run() {
                ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, null, null, null, "artist_Name asc", null);
                EventBus.getDefault().postSticky(new ArtistListLoadDoneEvent(artists));
            }
        }).start();
    }

    private void fetchAllArtistsForYear(final String year) {
        new Thread(new Runnable() {
            public void run() {
                ArrayList<Artist> artistList = shambhala.loadAllArtistsForYear(year);
                shambhala.setArtists(artistList);
                EventBus.getDefault().postSticky(new DatabaseLoadFinishedEvent());
            }
        }).start();
    }

    Menu navigationMenu;

    private void setupNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationMenu = navigationView.getMenu();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                menuItem.setChecked(true);

                //Prevents the navigation drawer stutter when switching fragments.
                final Handler mDrawerHandler = new Handler();
                mDrawerHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        selectItem(menuItem.getItemId());
                    }
                }, 500);

                drawerLayout.closeDrawers();
                return true;
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {

            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset > .55 && !isDrawerOpen) {
                    onDrawerOpened(drawerView);
                    isDrawerOpen = true;
                    invalidateOptionsMenu();
                    collapseGlobalSearchActionView();
                } else if (slideOffset < .45 && isDrawerOpen) {
                    onDrawerClosed(drawerView);
                    isDrawerOpen = false;
                    invalidateOptionsMenu();
                }
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void setupToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.inflateMenu(R.menu.menu_main);

            try {
                setSupportActionBar(toolbar);
            } catch (Throwable t) {
                // WTF SAMSUNG!
            }

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            setupGlobalSearch();
            if (currentFragment != FRAGMENT_ARTISTS) {
                setupScheduleSpinner();
            } else {
                setupArtistListSpinner();
            }

            EventBus.getDefault().postSticky(new ActionBarColorEvent(ColorUtil.getCurrentThemeColor(), actionBarStage));
        }
    }

    private void showTips() {
        if(!DateUtils.isPrePostFestival(this)) {
            if (!prefs.getBoolean("tapTargetNowSeen", false)) {

                TapTargetView.showFor(MainActivity.this,
                        TapTarget.forToolbarMenuItem(toolbar, R.id.now_playing, getString(R.string.tap_target_now_title), getString(R.string.tap_target_now_description))
                                .outerCircleColorInt(ColorUtil.themedGray(this))
                                .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                                .targetCircleColor(R.color.white)   // Specify a color for the target circle
                                .titleTextSize(20)                  // Specify the size (in sp) of the title text
                                .titleTextColor(R.color.white)      // Specify the color of the title text
                                .descriptionTextSize(16)            // Specify the size (in sp) of the description text
                                .descriptionTextColor(R.color.primaryTextColorWhite)  // Specify the color of the description text
                                .textColor(R.color.primaryTextColorWhite)            // Specify a color for both the title and description text
                                .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                                .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                                .drawShadow(true)                   // Whether to draw a drop shadow or not
                                .cancelable(true)                   // Whether tapping outside the outer circle dismisses the view
                                .tintTarget(true)                   // Whether to tint the target view's color
                                .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                                .targetRadius(60),                  // Specify the target radius (in dp)
                        new TapTargetView.Listener() {              // The listener can listen for regular clicks, long clicks or cancels

                            @Override
                            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                                prefs.edit().putBoolean("tapTargetNowSeen", true).apply();
                                super.onTargetDismissed(view, userInitiated);
                            }

                            @Override
                            public void onTargetClick(TapTargetView view) {
                                gotoNow();
                                super.onTargetClick(view);          // This call is optional
                            }
                        });
            }else if (!prefs.getBoolean("SawSeenDialog", false)){
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle).create();
                alertDialog.setTitle("Who have you seen?");
                alertDialog.setMessage("Did you know you can track which artists you've seen?\n\nSimply long press on any heart once their set has begun to mark them as seen.");
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                prefs.edit().putBoolean("SawSeenDialog", true).apply();
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
    }

    private void setupScheduleSpinner() {

        //Setting up the spinner in the actionbar
        //See: http://stackoverflow.com/questions/15193598/actionbar-spinner-customisation

        scheduleSpinner = new Spinner(this);
        scheduleSpinner.setTag("spinner_nav");

        if (scheduleSpinner != null) {

            if (currentFragment == FRAGMENT_ARTISTS) { //TODO DEAD IF STATEMENT?
                adapterBaseScheduleDays = new AdapterBaseScheduleDays(this, R.layout.schedule_spinner, getResources().getStringArray(R.array.all_and_days));
            } else {
                adapterBaseScheduleDays = new AdapterBaseScheduleDays(this, R.layout.schedule_spinner, getResources().getStringArray(R.array.days));
            }

            scheduleSpinner.setAdapter(adapterBaseScheduleDays);
            scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    currentDay = position;
                    DateUtils.setReferenceTime(MainActivity.this, currentDay);
                    EventBus.getDefault().postSticky(new ChangeDateEvent(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }

            });
        }


        if (currentDay != -1) {
            scheduleSpinner.setSelection(currentDay);
        } else {

            int day = DateUtils.getCurrentDay(this);
            if (day != -1) {
                scheduleSpinner.setSelection(day);
            }

        }

        if (currentFragment != FRAGMENT_ARTISTS) {
            toolbar.addView(scheduleSpinner);
        } else {
            toolbar.setTitle(scheduleBy);
        }

    }

    /**
     * Used to prevent the first load of the spinner from triggering which automatically happens
     * when it's created.
     */
    boolean firstSpinnerLoad = true;

    /**
     * Used to track if the date has been changed once in the artist fragment.
     * Until it's been changed once, we should always show the full artist list.
     */
    boolean artistDateSelected = false;

    /**
     * A spinner for the ArtistList fragment to handle special edge cases that would complicate
     * the one used elsewhere.
     */
    private void setupArtistListSpinner() {

        //Setting up the spinner in the actionbar
        //See: http://stackoverflow.com/questions/15193598/actionbar-spinner-customisation

        scheduleSpinner = new Spinner(this);
        scheduleSpinner.setTag("spinner_nav");

        firstSpinnerLoad = true;

        if (scheduleSpinner != null) {

            adapterBaseScheduleDays = new AdapterBaseScheduleDays(this, R.layout.schedule_spinner, getResources().getStringArray(R.array.all_and_days));

            scheduleSpinner.setAdapter(adapterBaseScheduleDays);
            scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (!firstSpinnerLoad) {
                        artistDateSelected = true;
                        currentDay = position - 1;

                        DateUtils.setReferenceTime(MainActivity.this, currentDay);

                        EventBus.getDefault().postSticky(new ChangeDateEvent(position - 1, true));
                    } else {
                        firstSpinnerLoad = false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }

            });
        }

        if (!artistDateSelected) {
            scheduleSpinner.setSelection(0);
        } else {
            scheduleSpinner.setSelection(currentDay + 1);
        }
        toolbar.addView(scheduleSpinner);
    }

    private void setupGlobalSearch() {

        final DateTimeFormatter dateStringFormat = DateUtils.getTimeFormat(prefs.getString(SettingsActivity.TIME_FORMAT, "24"));

        //TODO - Remove duplication and see if the recyclerview genreAdapter in ArtistFragment can be reused
        searchAdapter = new ArrayAdapter<Artist>(this, R.layout.artist_list_item_artists) {
            private Filter filter;

            private String[] dayOfWeek = {"Thursday", "Friday", "Saturday", "Sunday"};
            private String[] stageNames = getResources().getStringArray(R.array.stages);

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.artist_list_item_artists, parent, false);
                }

                TextView artistName = (TextView) convertView.findViewById(R.id.artistName);
                TextView artistDay = (TextView) convertView.findViewById(R.id.artistDay);
                TextView artistTime = (TextView) convertView.findViewById(R.id.artistTime);
                TextView artistStage = (TextView) convertView.findViewById(R.id.artistStage);
                TextView artistGenres = (TextView) convertView.findViewById(R.id.artistGenres);

                final ImageView artistAlarmSet = (ImageView) convertView.findViewById(R.id.list_alarm_set);
                final ImageView artistSeenArtist = (ImageView) convertView.findViewById(R.id.list_seen_set);

                final ImageView artistFavorite = (ImageView) convertView.findViewById(R.id.list_favorited);
                View artistDivider = (View) convertView.findViewById(R.id.separator);
                RelativeLayout artistLayout = (RelativeLayout) convertView.findViewById(R.id.artistLayout);

                final Artist artist = this.getItem(position);

                /*
                This is to solve an issue where a record is getting cut off (but can scroll) if the text wraps.
                It only happens if there's one record though, so I'm adding extra space when that's the case.
                */
                if (artist.getGenres() != null) {
                    if (getCount() == 1) {
                        if (artist.getGenres().length() > 45) {
                            artistGenres.setMinLines(3);
                        } else {
                            artistGenres.setMinLines(2);
                        }
                    } else {
                        artistGenres.setMinLines(1);
                    }
                }

                artistFavorite.setImageDrawable(AnimationHelper.getFavoriteTransitionDrawable(MainActivity.this, artist.isFavorite()));
                artistFavorite.setColorFilter(ContextCompat.getColor(MainActivity.this, ColorUtil.getStageColors()[artist.getStage()]));

                artistSeenArtist.setVisibility(artist.isSeenArtist() ? View.VISIBLE : View.GONE);
                SeenArtistHelper.setSeenImageColor(MainActivity.this, artist, artistSeenArtist);

                artistAlarmSet.setVisibility(artist.isAlarmSet() ? View.VISIBLE : View.GONE);
                artistAlarmSet.setColorFilter(ColorUtil.imageInHeart(MainActivity.this));


                String formattedGenres = artist.getGenres().replace(",", ", ").toLowerCase();
                if (formattedGenres.length() == 0 || Shambhala.getFestivalYear(MainActivity.this).equals("2015")) {
                    artistGenres.setVisibility(View.GONE);
                } else {
                    artistGenres.setVisibility(View.VISIBLE);
                }

                artistGenres.setText(formattedGenres);

                artistFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (artist.isFavorite()) {
                            MyTransitionDrawable transitionDrawable = (MyTransitionDrawable) artistFavorite.getDrawable();
                            // adding 200 as something is causing the animation to appear faster in this scenario, and otherwise be unnoticed.
                            transitionDrawable.favoriteReverse(ANIMATION_DURATION_HEARTS + 200);

                            if (artist.isAlarmSet()) {
                                artistAlarmSet.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (artistAlarmSet != null) {
                                            artistAlarmSet.setVisibility(View.GONE);
                                        }
                                    }
                                }, ANIMATION_DURATION_HEARTS + 200);
                            }

                            AlarmHelper alarmHelper = new AlarmHelper(MainActivity.this, null);
                            alarmHelper.cancelAlarm(artist);
                            artist.setFavorite(false);
                            artist.setIsAlarmSet(false);
                            artist.save();
                        } else {
                            MyTransitionDrawable transitionDrawable = (MyTransitionDrawable) artistFavorite.getDrawable();
                            transitionDrawable.favoriteStart(ANIMATION_DURATION_HEARTS + 200);
                            artist.setFavorite(true);
                            artist.save();
                        }

                        SeenArtistHelper.setSeenImageColor(MainActivity.this, artist, artistSeenArtist);
                        EventBus.getDefault().post(new DataChangedEvent(true, artist.getId()));
                    }
                });

                artistFavorite.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        SeenArtistHelper.updateSeenState(MainActivity.this, artist, artistSeenArtist, false);
                        EventBus.getDefault().post(new DataChangedEvent(true, artist.getId()));
                        return true;
                    }
                });

                convertView.setTag(artist);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                        if (!(currentFragment instanceof ArtistsFragment)) {
                            currentDay = artist.getDay();
                            scheduleSpinner.setSelection(artist.getDay());
                            currentDay = artist.getDay();
                        } else if (scheduleSpinner.getSelectedItemPosition() != 0) {
                            currentDay = artist.getDay();
                            scheduleSpinner.setSelection(artist.getDay() + 1);
                            currentDay = artist.getDay();
                        }

                        collapseGlobalSearchActionView();
                        EventBus.getDefault().post(new SearchSelectedEvent(artist));

                    }
                });

                artistName.setText(artist.getAristName());
                artistDay.setText(dayOfWeek[artist.getDay()]);
                artistStage.setText(stageNames[artist.getStage()]);
                artistTime.setText(DateUtils.formatTime(dateStringFormat, artist.getStartTimeString()) +
                        " - "
                        + DateUtils.formatTime(dateStringFormat, artist.getEndTimeString()));

                artistDivider.setBackground(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, gradientColors));

                if (ColorUtil.nightMode) {
                    artistLayout.setBackgroundResource(R.drawable.list_night_selector);
                } else {
                    artistLayout.setBackgroundResource(getBackgroundSelector());
                }

                return convertView;

            }

            @Override
            public Filter getFilter() {
                if (filter == null) {
                    filter = new artistFilter();
                }
                return filter;
            }
        };

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.actionbar_search, null);

        ClearableAutoCompleteTextView globalSearch = (ClearableAutoCompleteTextView) v.findViewById(R.id.search_box);
        globalSearch.setAdapter(searchAdapter);

    }

    public int getBackgroundSelector() {

        int color = R.drawable.list_pagoda_selector;

        switch (actionBarStage) {

            case 0:
                color = R.drawable.list_pagoda_selector;
                break;
            case 1:
                color = R.drawable.list_forest_selector;
                break;
            case 2:
                color = R.drawable.list_grove_selector;
                break;
            case 3:
                color = R.drawable.list_living_room_selector;
                break;
            case 4:
                color = R.drawable.list_village_selector;
                break;
            case 5:
                color = R.drawable.list_amphitheatre_selector;
                break;
            case 6:
                color = R.drawable.cedar_lounge_selector;
                break;

        }

        return color;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        this.menu = menu;

        getMenuInflater().inflate(R.menu.menu_main, menu);

        setupFilterItemAnimation(menu);

        MenuItem globalSearchMenuItem = menu.findItem(R.id.global_search);

        MenuItemCompat.setOnActionExpandListener(globalSearchMenuItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        ClearableAutoCompleteTextView searchTextView = (ClearableAutoCompleteTextView) item.getActionView().findViewById(R.id.search_box);
                        searchTextView.hideClearButton();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(searchTextView.getWindowToken(), 0);
                        isSearchExpanded = false;
                        EventBus.getDefault().post(new ToggleFilterVisibility(true));
                        invalidateOptionsMenu();
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        isSearchExpanded = true;
                        if (genreFilteringActive && filterImage != null) {
                            filterImage.callOnClick();
                        }
                        EventBus.getDefault().post(new ToggleFilterVisibility(false));
                        MenuItem nowPlaying = menu.findItem(R.id.now_playing);
                        if (nowPlaying != null) {
                            nowPlaying.setVisible(false);
                        }

                        MenuItem gridFavourites = menu.findItem(R.id.grid_favourites);
                        if (gridFavourites != null) {
                            gridFavourites.setVisible(false);
                        }

                        return true; // Return true to expand action view
                    }
                });

        View actionView = menu.findItem(R.id.global_search).getActionView();

        searchTextView = (ClearableAutoCompleteTextView) actionView.findViewById(R.id.search_box);
        searchTextView.setAdapter(searchAdapter);
        searchTextView.setThreshold(1);

        if (isSearchExpanded && searchTextView != null) {
            globalSearchMenuItem.expandActionView();
            searchTextView.setSelection(searchTextView.length());
            searchTextView.setText(searchText);
            searchTextView.requestFocus();
            //Keyboard isn't raising unless I delay the command, even with the requestFocus above.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(searchTextView, InputMethodManager.SHOW_IMPLICIT);
                }
            }, 300);

        }

        return true;
    }

    ImageView filterImage;

    private void setupFilterItemAnimation(Menu menu) {
        filterImage = (ImageView) menu.findItem(R.id.filter).getActionView();
        if (filterImage != null) {
            //http://www.andronotes.org/uncategorized/animation-of-menuitem-in-actionbar/
            filterImage.setImageResource(R.drawable.ic_filter_list_white_36dp);
            filterImage.setColorFilter(ContextCompat.getColor(this, R.color.primaryTextColorWhite));

            if (genreFilteringActive) {
                setFilterToDownPosition();
            }

            filterImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation rotation;
                    if (!genreFilteringActive) {
                        rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.rotation_down);
                    } else {
                        rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.rotation_up);
                    }

                    rotation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            if (genreFilteringActive) {
                                genreFilteringActive = false;
                            } else {
                                genreFilteringActive = true;
                            }
                        }
                    });

                    view.startAnimation(rotation);

                    ArtistsFragment artistsFragment = (ArtistsFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_ARTISTS);
                    if (artistsFragment != null) {
                        artistsFragment.showGenres(true);
                    }

                }
            });
        }
    }

    private void setFilterToDownPosition() {
        Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotation_down);
        rotation.setDuration(0);
        if (filterImage != null) {
            filterImage.startAnimation(rotation);
        }
    }

    public void onEventMainThread(ToggleFilterVisibility event) {
        if (currentFragment == FRAGMENT_ARTISTS) {
            if (menu != null && !Shambhala.getFestivalYear(this).equals("2015")) {
                MenuItem filter = menu.findItem(R.id.filter);
                if (filter != null) {
                    filter.setVisible(event.isVisible());
                }
            }
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem globalSearchItem = menu.findItem(R.id.global_search);
        MenuItem filterListItem = menu.findItem(R.id.filter);
        MenuItem nowPlaying = menu.findItem(R.id.now_playing);
        MenuItem gridFavourites = menu.findItem(R.id.grid_favourites);

        if (globalSearchItem != null) {
            DrawableCompat.setTint(globalSearchItem.getIcon(), ContextCompat.getColor(this, R.color.primaryTextColorWhite));
            globalSearchItem.setVisible(!isDrawerOpen);
        }

        if (nowPlaying != null) {
            DrawableCompat.setTint(nowPlaying.getIcon(), ContextCompat.getColor(this, R.color.primaryTextColorWhite));
            nowPlaying.setVisible(!isDrawerOpen
                    && (currentFragment == FRAGMENT_TIME || currentFragment == FRAGMENT_CALENDAR)
                    && !DateUtils.isPrePostFestival(this)
                    && !isSearchExpanded);
        }

        if (gridFavourites != null) {

            WeekScheduleFragment weekScheduleFragment = (WeekScheduleFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_CALENDAR);

            boolean initialFavourite = false;
            if (weekScheduleFragment != null) {
                initialFavourite = weekScheduleFragment.isFavoritesOnly();
            }

            Drawable drawable = AnimationHelper.getFavoriteTransitionDrawable(this, initialFavourite);
            gridFavourites.setIcon(drawable);

            DrawableCompat.setTint(gridFavourites.getIcon(), ContextCompat.getColor(this, R.color.primaryTextColorWhite));
            gridFavourites.setVisible(!isDrawerOpen && currentFragment == FRAGMENT_CALENDAR && !isSearchExpanded);

        }

        if (filterListItem != null) {
            DrawableCompat.setTint(filterListItem.getIcon(), ContextCompat.getColor(this, R.color.primaryTextColorWhite));
            if (!Shambhala.getFestivalYear(this).equals("2015") && !isSearchExpanded) {
                filterListItem.setVisible(!isDrawerOpen && currentFragment == FRAGMENT_ARTISTS);
            } else {
                filterListItem.setVisible(false);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (id) {
            case R.id.now_playing:
                gotoNow();
                break;

            case R.id.grid_favourites:
                toggleGridFavourites(item);

                break;

            case R.id.global_search:
                startSearch();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoNow() {
        if (currentFragment == FRAGMENT_TIME) {
            TimeScheduleFragment timeScheduleFragment = (TimeScheduleFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_TIME);
            if (timeScheduleFragment != null) {
                timeScheduleFragment.setPagerToNow();
                scheduleSpinner.setSelection(DateUtils.getCurrentDay(this));
            }
        } else if (currentFragment == FRAGMENT_CALENDAR) {
            WeekScheduleFragment weekScheduleFragment = (WeekScheduleFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_CALENDAR);
            if (weekScheduleFragment != null) {
                double position = ((double) DateUtils.getCurrentTimePosition(this)) / 2;
                weekScheduleFragment.setShowOnlyNow(!weekScheduleFragment.isShowOnlyNow());
                weekScheduleFragment.gotoHour(position);
                scheduleSpinner.setSelection(DateUtils.getCurrentDay(this));
            }
        }
    }

    private void toggleGridFavourites(MenuItem item) {
        WeekScheduleFragment weekScheduleFragment = (WeekScheduleFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_CALENDAR);
        if (weekScheduleFragment != null) {
            weekScheduleFragment.toggleFavourites();

            MyTransitionDrawable drawable = (MyTransitionDrawable) item.getIcon();
            if (weekScheduleFragment.isFavoritesOnly()) {
                drawable.favoriteStart(300);
            } else {
                drawable.favoriteReverse(300);
            }
        }
    }

    private void startSearch() {
        if (menu != null) {
            View actionView = menu.findItem(R.id.global_search).getActionView();
            if (actionView != null) {
                final ClearableAutoCompleteTextView searchTextView = (ClearableAutoCompleteTextView) actionView.findViewById(R.id.search_box);
                searchTextView.setText("");
                searchTextView.requestFocus();

                //Keyboard isn't raising unless I delay the command, even with the requestFocus above.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(searchTextView, InputMethodManager.SHOW_IMPLICIT);
                    }
                }, 200);

                WeekScheduleFragment weekScheduleFragmentTwo = (WeekScheduleFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_CALENDAR);
                if (weekScheduleFragmentTwo != null) {
                    if (weekScheduleFragmentTwo.isFavoritesOnly()) {
                        weekScheduleFragmentTwo.toggleFavourites();
                    }

                    if (weekScheduleFragmentTwo.isShowOnlyNow()) {
                        weekScheduleFragmentTwo.toggleNow();
                    }
                }
            }
        }
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int drawerId) {

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        toolbar.setTitle("");

        genreFilteringActive = false;

        //Reset the screen orientation as the calendar view forces landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        //I'm removing the view as toggling between spinner/non spinner was causing some
        //weird behavior where both would sometimes appear, which wasn't making sense.

        switch (drawerId) {

            case R.id.drawer_time:

                fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_TIME);
                if (fragment == null) {
                    fragment = new TimeScheduleFragment();
                    Bundle args = new Bundle();
                    args.putInt(Constants.FRAGMENT_TIME, currentTimePosition);
                    fragment.setArguments(args);
                    replaceFragment(R.id.content_frame, fragment, Constants.FRAGMENT_TIME, false);
                } else {

                }

                scheduleBy = "Schedule by Time";

                //toolbar.addView(scheduleSpinner);
                currentFragment = FRAGMENT_TIME;
                toolbar.removeView(scheduleSpinner);
                setupScheduleSpinner();
                break;

            case R.id.drawer_stage:

                fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_STAGE);
                if (fragment == null) {
                    fragment = new StageScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, Constants.FRAGMENT_STAGE, false);
                } else {

                }

                scheduleBy = "Schedule by Stage";

                //toolbar.addView(scheduleSpinner);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                currentFragment = FRAGMENT_STAGE;
                toolbar.removeView(scheduleSpinner);
                setupScheduleSpinner();
                break;

            case R.id.drawer_my_schedule:

                fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_FAVOURITE);
                if (fragment == null) {
                    fragment = new FavoriteScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, Constants.FRAGMENT_FAVOURITE, false);
                } else {

                }

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                scheduleBy = "My Schedule";
                //toolbar.addView(scheduleSpinner);
                currentFragment = FRAGMENT_FAVORITES;
                toolbar.removeView(scheduleSpinner);
                setupScheduleSpinner();
                break;

            case R.id.drawer_artist_list:

                //fetchArtistDataForQuickLoad();

                fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_ARTISTS);
                if (fragment == null) {
                    fragment = new ArtistsFragment();
                    replaceFragment(R.id.content_frame, fragment, Constants.FRAGMENT_ARTISTS, false);
                } else {

                }

                scheduleBy = "Artist List";
                currentFragment = FRAGMENT_ARTISTS;
                toolbar.removeView(scheduleSpinner);
                artistDateSelected = false;
                setupArtistListSpinner();
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;

            case R.id.drawer_calendar_schedule:

                /**
                 * NOTE I am not altering the spinner here as the forced rotation will re-create it
                 * Leaving it in would cause an animatinon flicker
                 **/

                fragment = fragmentManager.findFragmentByTag(Constants.FRAGMENT_CALENDAR);
                if (fragment == null) {
                    fragment = new WeekScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, Constants.FRAGMENT_CALENDAR, false);
                } else {

                }

                scheduleBy = "Grid";

                if (currentFragment == FRAGMENT_ARTISTS) {
                    currentFragment = FRAGMENT_CALENDAR;
                    toolbar.removeView(scheduleSpinner);
                    setupScheduleSpinner();
                } else {
                    currentFragment = FRAGMENT_CALENDAR;
                }


                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;

            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, RESULT_SETTINGS);
                break;
        }

        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    protected void onResume() {

        showTips();

        //This is in a handler to avoid an exception
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentNightMode = AppCompatDelegate.getDefaultNightMode();

                ((ShambaTimesApplication) getApplication()).updateNightModeFlagIfAutomatic();

                if (currentNightMode != AppCompatDelegate.getDefaultNightMode()) {
                    getDelegate().applyDayNight();
                }
            }

        }, 1);


        super.onResume();
    }

    private void didTimeFormatChange() {
        String newTimeFormat = prefs.getString(SettingsActivity.TIME_FORMAT, "24");
        if (!timeFormatPreference.equals(newTimeFormat)) {
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
    }

    private void didFestivalYearChange() {
        String newFestivalYear = Shambhala.getFestivalYear(this);
        if (!newFestivalYear.equals(festivalYear)) {

            //I don't want to show the cedar lounge if it's 2015, but even though I'm restarting the activity
            //if I don't notify it of change in size, it crashes the app, so I'm notifying it.
            StageScheduleFragment stageScheduleFragment = (StageScheduleFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_STAGE);
            if (stageScheduleFragment != null) {
                stageScheduleFragment.notifyStageAdapter();
            }

            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
    }

    public void onEventMainThread(UpdateScheduleByTimeEvent event) {
        currentTimePosition = event.getPosition();
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        ColorUtil.setCurrentThemeColor(event.getColor());

        actionBarStage = event.getStage();

        int color = ColorUtil.dividerColor(MainActivity.this);
        gradientColors = ColorUtil.getDividerGradientColor(color);

        Drawable colorDrawable = new ColorDrawable(ColorUtil.themedGray(this));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }

    public void onEventMainThread(ToggleToStageEvent event) {
        checkNavigationItem(R.id.drawer_stage);
        int position = event.getStage();
        String name = event.getName();

        collapseGlobalSearchActionView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        StageScheduleFragment stageSchedule = (StageScheduleFragment) fragmentManager.findFragmentByTag(Constants.FRAGMENT_STAGE);

        if (stageSchedule == null) {
            stageSchedule = new StageScheduleFragment();
            Bundle args = new Bundle();
            args.putInt(Constants.FRAGMENT_STAGE, position);
            args.putString("NAME", name);
            stageSchedule.setArguments(args);
        }

        scheduleBy = "Schedule by Stage";
        currentFragment = 1;

        replaceFragment(R.id.content_frame, stageSchedule, Constants.FRAGMENT_STAGE, false);

        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    public void onEventMainThread(ToggleToTimeEvent event) {
        checkNavigationItem(R.id.drawer_time);
        int position = event.getTime();

        collapseGlobalSearchActionView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        TimeScheduleFragment timeSchedule = (TimeScheduleFragment) fragmentManager.findFragmentByTag(Constants.FRAGMENT_TIME);

        if (timeSchedule == null) {
            timeSchedule = new TimeScheduleFragment();
            Bundle args = new Bundle();
            args.putInt(Constants.FRAGMENT_TIME, position);
            currentTimePosition = position;
            timeSchedule.setArguments(args);
            replaceFragment(R.id.content_frame, timeSchedule, Constants.FRAGMENT_TIME, false);
        }

        scheduleBy = "Schedule by Time";
        currentFragment = 0;


        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    public void onEventMainThread(ToggleToGridEvent event) {
        checkNavigationItem(R.id.drawer_stage);
        int position = event.getTime();

        collapseGlobalSearchActionView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        WeekScheduleFragment weekScheduleFragment = (WeekScheduleFragment) fragmentManager.findFragmentByTag(Constants.FRAGMENT_CALENDAR);

        if (weekScheduleFragment == null) {
            weekScheduleFragment = new WeekScheduleFragment();
            Bundle args = new Bundle();
            args.putInt(Constants.FRAGMENT_TIME, position + 1);
            args.putInt("date", currentDay);
            weekScheduleFragment.setArguments(args);
        }

        scheduleBy = "Grid";
        currentFragment = FRAGMENT_CALENDAR;

        replaceFragment(R.id.content_frame, weekScheduleFragment, Constants.FRAGMENT_CALENDAR, false);

        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    private void collapseGlobalSearchActionView() {

        View actionView = (View) menu.findItem(R.id.global_search).getActionView();

        if (actionView != null) {
            MenuItem item = menu.findItem(R.id.global_search);
            item.collapseActionView();
            isSearchExpanded = false;
        }

    }


    private void replaceFragment(int id, Fragment fragment, String tag, boolean addToBackStack) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Log.e("MainActivity", "Exception replacing fragment", e);
        }
    }

    public class AdapterBaseScheduleDays extends BaseAdapter {

        Context context;
        int layoutResourceId;
        String[] data;
        LayoutInflater inflater;

        public AdapterBaseScheduleDays(Context context, int textViewResourceId,
                                       String[] data) {
            this.data = data;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.context = context;
            this.layoutResourceId = textViewResourceId;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View actionBarView = inflater.inflate(R.layout.ab_main, null);

            TextView title = (TextView) actionBarView
                    .findViewById(R.id.ab_basemaps_title);

            TextView subtitle = (TextView) actionBarView
                    .findViewById(R.id.ab_basemaps_subtitle);

            title.setText(scheduleBy);

            if (!Shambhala.getFestivalYear(MainActivity.this).equals(Shambhala.CURRENT_YEAR)) {
                subtitle.setText(data[position] + " - " + Shambhala.getFestivalYear(MainActivity.this));
            } else {
                subtitle.setText(data[position]);
            }
            return actionBarView;

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View actionBarDropDownView = inflater.inflate(
                    R.layout.ab_dropdown, null);
            TextView dropDownTitle = (TextView) actionBarDropDownView
                    .findViewById(R.id.ab_basemaps_dropdown_title);

            dropDownTitle.setText(data[position]);

            return actionBarDropDownView;

        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    private void checkNavigationItem(int id) {
        if (navigationMenu != null) {
            MenuItem item = navigationMenu.findItem(id);
            if (item != null) {
                item.setChecked(true);
            }
        }
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout != null) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return;
            }
        }

        if (isSearchExpanded) {
            super.onBackPressed();
            return;
        }
        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment instanceof ArtistsFragment) {
            if (genreFilteringActive) {
                filterImage.callOnClick();
                return;
            }
        }

        //Return to the time schedule fragment before exiting the app.
        fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (!(fragment instanceof TimeScheduleFragment)) {
            selectItem(R.id.drawer_time);
            checkNavigationItem(R.id.drawer_time);
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("COLOR", ColorUtil.getCurrentThemeColor());
        savedInstanceState.putInt("STAGE", actionBarStage);
        savedInstanceState.putInt("POSITION", currentFragment);
        savedInstanceState.putInt("DAY", currentDay);
        savedInstanceState.putString("TITLE", scheduleBy);
        savedInstanceState.putInt("CURRENT_TIME", currentTimePosition);
        savedInstanceState.putBoolean("SEARCH_EXPANDED", isSearchExpanded);
        savedInstanceState.putString("SEARCH_TEXT", searchText);
        savedInstanceState.putBoolean("artistDateSelected", artistDateSelected);
        savedInstanceState.putBoolean("genreFilteringActive", genreFilteringActive);


        super.onSaveInstanceState(savedInstanceState);
    }

    private class artistFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Artist> list = shambhala.getArtists();
            FilterResults result = new FilterResults();

            try {
                searchText = constraint.toString().toLowerCase();
            } catch (Exception e) {
                searchText = "";
            }

            EventBus.getDefault().post(new ShowHideAutoCompleteSearchClearButtonEvent(searchText.length() > 0));

            if (searchText.length() > 1) {
                // iterate over the list of venues and find if the venue matches the constraint. if it does, add to the result list
                final ArrayList<Artist> retList = new ArrayList<Artist>();
                for (Artist artist : list) {
                    if (artist.getAristName().toLowerCase().contains(searchText)) {
                        retList.add(artist);
                    }
                }
                result.values = retList;
                result.count = retList.size();
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            searchAdapter.clear();
            if (results.count > 0) {
                for (Artist artist : (ArrayList<Artist>) results.values) {
                    searchAdapter.add(artist);
                }
            }
        }

    }

    public void onEventMainThread(ShowHideAutoCompleteSearchClearButtonEvent event) {
        if (searchTextView != null) {
            if (event.isShowClearButton()) {
                searchTextView.showClearButton();
            } else {
                searchTextView.hideClearButton();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}