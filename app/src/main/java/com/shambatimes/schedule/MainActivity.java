package com.shambatimes.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
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
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ArtistListLoadDoneEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.FilterEvent;
import com.shambatimes.schedule.events.ShowHideAutoCompleteSearchClearButtonEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DatabaseLoadFinishedEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.events.ToggleFilterVisibility;
import com.shambatimes.schedule.events.ToggleToStageEvent;
import com.shambatimes.schedule.events.ToggleToTimeEvent;
import com.shambatimes.schedule.events.UpdateScheduleByTimeEvent;
import com.shambatimes.schedule.myapplication.BuildConfig;
import com.shambatimes.schedule.myapplication.R;
import com.shambatimes.schedule.views.ClearableAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import io.fabric.sdk.android.Fabric;

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

    //nav bar
    Spinner scheduleSpinner;
    AdapterBaseScheduleDays adapterBaseScheduleDays;

    //Items that require a saved state
    private int actionBarColor = 0xFF666666;
    private int actionBarStage = 0;
    private int currentFragment = FRAGMENT_TIME;
    private int currentDay = -1;
    private int currentTimePosition;

    private String scheduleBy = "Schedule by Time";
    private SharedPreferences prefs;

    private Menu menu;
    private int[] gradientColors = {0, 0, 0};

    ArrayAdapter<Artist> searchAdapter;
    private String searchText;
    ClearableAutoCompleteTextView searchTextView;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }


        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (DateUtils.getCurrentDay() == 3) {
            Constants.REFERENCE_TIME = Constants.SUNDAY_REFERENCE_TIME;
        } else {
            Constants.REFERENCE_TIME = Constants.GENERAL_REFERENCE_TIME;
        }

        prepareAndLoadDatabase();

        if (savedInstanceState == null) {
            currentTimePosition = DateUtils.getCurrentTimePosition();
            actionBarColor = getResources().getColor(R.color.pagoda_color);
            replaceFragment(R.id.content_frame, new TimeScheduleFragment(), "TIME", false);
        } else {
            actionBarColor = savedInstanceState.getInt("COLOR");
            actionBarStage = savedInstanceState.getInt("STAGE");
            currentFragment = savedInstanceState.getInt("POSITION");
            currentDay = savedInstanceState.getInt("DAY");
            scheduleBy = savedInstanceState.getString("TITLE");
            currentTimePosition = savedInstanceState.getInt("CURRENT_TIME");
            isSearchExpanded = savedInstanceState.getBoolean("SEARCH_EXPANDED");
            searchText = savedInstanceState.getString("SEARCH_TEXT", "");
        }

        setupNavigationDrawer();
        setupToolbar();

    }

    private void prepareAndLoadDatabase() {

        if (prefs.contains("database_loaded")) {
            new Thread(new Runnable() {
                public void run() {
                    DatabaseScheduleUpdates.scheduleUpdate1(MainActivity.this);
                    fetchAllArtists();
                    EventBus.getDefault().postSticky(new DatabaseLoadFinishedEvent());
                }
            }).start();

        } else if (!prefs.contains("database_load_started")) {

            Toast.makeText(this, "Preparing Database", Toast.LENGTH_LONG).show();

            new Thread(new Runnable() {
                public void run() {

                    prefs.edit().putBoolean("database_load_started", true).apply();
                    ArtistGenerator artistList = new ArtistGenerator(MainActivity.this);
                    shambhala.setArtists(artistList.getArtists());
                    prefs.edit().putBoolean("database_loaded", true).apply();
                    prefs.edit().putBoolean("update_one_complete", true).apply(); //Original sources have been corrected, only existing users need this.
                    EventBus.getDefault().postSticky(new DatabaseLoadFinishedEvent());

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

    private void fetchAllArtists() {
        new Thread(new Runnable() {
            public void run() {
                ArrayList<Artist> artistList = (ArrayList) Artist.listAll(Artist.class);
                shambhala.setArtists(artistList);
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
                }, 300);

                drawerLayout.closeDrawers();
                return true;
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {

            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset > .55 && !isDrawerOpen) {
                    onDrawerOpened(drawerView);
                    isDrawerOpen = true;
                } else if (slideOffset < .45 && isDrawerOpen) {
                    onDrawerClosed(drawerView);
                    isDrawerOpen = false;
                }
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void setupToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            setupGlobalSearch();
            setupScheduleSpinner();
            EventBus.getDefault().postSticky(new ActionBarColorEvent(actionBarColor, actionBarStage));
        }
    }

    private void setupScheduleSpinner() {

        //Setting up the spinner in the actionbar
        //See: http://stackoverflow.com/questions/15193598/actionbar-spinner-customisation

        scheduleSpinner = new Spinner(this);
        scheduleSpinner.setTag("spinner_nav");

        if (scheduleSpinner != null) {

            adapterBaseScheduleDays = new AdapterBaseScheduleDays(this, R.layout.schedule_spinner, getResources().getStringArray(R.array.days));
            scheduleSpinner.setAdapter(adapterBaseScheduleDays);
            scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    currentDay = position;

                    if (currentDay == 3) {
                        Constants.REFERENCE_TIME = Constants.SUNDAY_REFERENCE_TIME;
                    } else {
                        Constants.REFERENCE_TIME = Constants.GENERAL_REFERENCE_TIME;
                    }

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

            int day = DateUtils.getCurrentDay();
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

    private void setupGlobalSearch() {

        //TODO - Remove duplication and see if the recyclerview genreAdapter in ArtistFragment can be reused
        searchAdapter = new ArrayAdapter<Artist>(this, R.layout.artist_list_item_artists) {
            private Filter filter;

            private String[] dayOfWeek = {"Thursday", "Friday", "Saturday", "Sunday"};
            private String[] stageNames = getResources().getStringArray(R.array.stages);


            int[] favoriteDrawables = ColorUtil.getStageFavoriteDrawables();
            int[] favoriteOutlineDrawables = ColorUtil.getStageFavoriteOutlineDrawables();

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.artist_list_item_artists, parent, false);
                }

                TextView artistName = (TextView) convertView.findViewById(R.id.artistName);
                TextView artistDay = (TextView) convertView.findViewById(R.id.artistDay);
                TextView artistTime = (TextView) convertView.findViewById(R.id.artistTime);
                TextView artistStage = (TextView) convertView.findViewById(R.id.artistStage);
                final ImageView artistFavorite = (ImageView) convertView.findViewById(R.id.list_favorited);
                View artistDivider = (View) convertView.findViewById(R.id.separator);
                RelativeLayout artistLayout = (RelativeLayout) convertView.findViewById(R.id.artistLayout);

                final Artist artist = this.getItem(position);

                if (artist.isFavorite()) {
                    artistFavorite.setImageResource(favoriteDrawables[artist.getStage()]);
                } else {
                    artistFavorite.setImageResource(favoriteOutlineDrawables[artist.getStage()]);
                }

                artistFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (artist.isFavorite()) {
                            artistFavorite.setImageResource(favoriteOutlineDrawables[artist.getStage()]);
                            artist.setFavorite(false);
                            artist.save();

                        } else {
                            artistFavorite.setImageResource(favoriteDrawables[artist.getStage()]);
                            artist.setFavorite(true);
                            artist.save();
                        }

                        EventBus.getDefault().post(new DataChangedEvent(true, artist.getId()));

                    }
                });

                convertView.setTag(artist);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        currentDay = artist.getDay();
                        scheduleSpinner.setSelection(artist.getDay());
                        currentDay = artist.getDay();

                        collapseGlobalSearchActionView();

                        EventBus.getDefault().postSticky(new SearchSelectedEvent(artist));

                    }
                });

                artistName.setText(artist.getAristName());
                artistDay.setText(dayOfWeek[artist.getDay()]);
                artistStage.setText(stageNames[artist.getStage()]);
                artistTime.setText(artist.getStartTimeString() + " to " + artist.getEndTimeString());
                artistDivider.setBackground(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, gradientColors));
                artistLayout.setBackgroundResource(getBackgroundSelector());

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
        }

        return color;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        isSearchExpanded = true;
                        EventBus.getDefault().post(new ToggleFilterVisibility(false));
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

    ImageView test;
    private void setupFilterItemAnimation(Menu menu){
         test = (ImageView) menu.findItem(R.id.filter).getActionView();
        if (test != null) {
            //http://www.andronotes.org/uncategorized/animation-of-menuitem-in-actionbar/
            test.setImageResource(R.drawable.ic_filter_list_white_36dp);

            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation rotation;
                    if(!flipped) {
                        rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.rotation_down);
                    }else{
                        rotation = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.rotation_up);
                    }

                    rotation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            if(flipped) {
                                flipped = false;
                            }else{
                                flipped = true;
                            }
                        }
                    });

                    view.startAnimation(rotation);

                    ArtistsFragment artistsFragment = (ArtistsFragment) getSupportFragmentManager().findFragmentByTag("ARTISTS");
                    if (artistsFragment != null) {
                        artistsFragment.showGenres();
                    }

                }
            });
        }
    }

    boolean flipped = false;

    public void onEventMainThread(ToggleFilterVisibility event) {

        if (menu != null) {
            MenuItem filter = menu.findItem(R.id.filter);
            if (filter != null) {
                filter.setVisible(event.isVisible());
            }
        }

    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem globalSearchItem = menu.findItem(R.id.global_search);
        MenuItem filterListItem = menu.findItem(R.id.filter);

        MenuItem filterListSelectedItem = menu.findItem(R.id.filter_active);

        if (filterListItem.isVisible()) {
            filterListSelectedItem.setVisible(false);
        } else {
            filterListSelectedItem.setVisible(true);
        }

        if (globalSearchItem != null) {
            globalSearchItem.setVisible(!isDrawerOpen);
        }

        filterListItem.setVisible(!isDrawerOpen && currentFragment == FRAGMENT_ARTISTS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.global_search) {
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
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private int measureContentWidth(ListAdapter listAdapter) {
        ViewGroup mMeasureParent = null;
        int maxWidth = 0;
        View itemView = null;
        int itemType = 0;

        final ListAdapter adapter = listAdapter;
        final int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            final int positionType = adapter.getItemViewType(i);
            if (positionType != itemType) {
                itemType = positionType;
                itemView = null;
            }

            if (mMeasureParent == null) {
                mMeasureParent = new FrameLayout(this);
            }

            itemView = adapter.getView(i, itemView, mMeasureParent);
            itemView.measure(widthMeasureSpec, heightMeasureSpec);

            final int itemWidth = itemView.getMeasuredWidth();

            if (itemWidth > maxWidth) {
                maxWidth = itemWidth;
            }
        }

        return maxWidth;
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int drawerId) {

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        toolbar.setTitle("");

        //I'm removing the view as toggling between spinner/non spinner was causing some
        //weird behavior where both would sometimes appear, which wasn't making sense.
        toolbar.removeView(scheduleSpinner);

        switch (drawerId) {

            case R.id.drawer_time:

                fragment = fragmentManager.findFragmentByTag("TIME");
                if (fragment == null) {
                    fragment = new TimeScheduleFragment();
                    Bundle args = new Bundle();
                    args.putInt("TIME", currentTimePosition);
                    fragment.setArguments(args);
                    replaceFragment(R.id.content_frame, fragment, "TIME", false);
                } else {

                }

                scheduleBy = "Schedule by Time";
                toolbar.addView(scheduleSpinner);
                currentFragment = FRAGMENT_TIME;

                break;

            case R.id.drawer_stage:

                fragment = fragmentManager.findFragmentByTag("STAGE");
                if (fragment == null) {
                    fragment = new StageScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, "STAGE", false);
                } else {

                }

                scheduleBy = "Schedule by Stage";
                toolbar.addView(scheduleSpinner);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                currentFragment = FRAGMENT_STAGE;

                break;

            case R.id.drawer_my_schedule:

                fragment = fragmentManager.findFragmentByTag("FAVORITE");
                if (fragment == null) {
                    fragment = new FavoriteScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, "FAVORITE", false);
                } else {

                }

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                scheduleBy = "My Schedule";
                toolbar.addView(scheduleSpinner);
                currentFragment = FRAGMENT_FAVORITES;

                break;

            case R.id.drawer_artist_list:

                fetchArtistDataForQuickLoad();

                fragment = fragmentManager.findFragmentByTag("ARTISTS");
                if (fragment == null) {
                    fragment = new ArtistsFragment();
                    replaceFragment(R.id.content_frame, fragment, "ARTISTS", false);
                } else {

                }

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setTitle("Artist List");
                scheduleBy = "Artist List";
                toolbar.removeView(scheduleSpinner);
                currentFragment = FRAGMENT_ARTISTS;

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

    public void onEventMainThread(UpdateScheduleByTimeEvent event) {
        currentTimePosition = event.getPosition();
    }

    public void onEventMainThread(DatabaseLoadFinishedEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        searchAdapter.addAll(shambhala.getArtists());
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        actionBarColor = event.getColor();
        actionBarStage = event.getStage();
        gradientColors[1] = event.getColor();

        Drawable colorDrawable = new ColorDrawable(actionBarColor);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }

    public void onEventMainThread(ToggleToStageEvent event) {
        checkNavigationItem(R.id.drawer_stage);
        int position = event.getStage();
        String name = event.getName();

        collapseGlobalSearchActionView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        StageScheduleFragment stageSchedule = (StageScheduleFragment) fragmentManager.findFragmentByTag("STAGE");

        if (stageSchedule == null) {
            stageSchedule = new StageScheduleFragment();
            Bundle args = new Bundle();
            args.putInt("STAGE", position);
            args.putString("NAME", name);
            stageSchedule.setArguments(args);
        }

        scheduleBy = "Schedule by Stage";
        currentFragment = 1;

        replaceFragment(R.id.content_frame, stageSchedule, "STAGE", false);

        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    public void onEventMainThread(ToggleToTimeEvent event) {
        checkNavigationItem(R.id.drawer_time);
        int position = event.getTime();

        collapseGlobalSearchActionView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        TimeScheduleFragment timeSchedule = (TimeScheduleFragment) fragmentManager.findFragmentByTag("TIME");

        if (timeSchedule == null) {
            timeSchedule = new TimeScheduleFragment();
            Bundle args = new Bundle();
            args.putInt("TIME", position);
            currentTimePosition = position;
            timeSchedule.setArguments(args);
            replaceFragment(R.id.content_frame, timeSchedule, "TIME", false);
        }

        scheduleBy = "Schedule by Time";
        currentFragment = 0;


        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    private void collapseGlobalSearchActionView() {

        View actionView = (View) menu.findItem(R.id.global_search).getActionView();

        if (actionView != null) {
            MenuItem item = menu.findItem(R.id.global_search);
            item.collapseActionView();
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

            subtitle.setText(data[position]);
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
            // TODO Auto-generated method stub
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

        if (isSearchExpanded) {
            super.onBackPressed();
            return;
        }
        Fragment f;
        f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (f instanceof ArtistsFragment){
            if(flipped){
                test.callOnClick();
                return;
            }
        }

        //Return to the time schedule fragment before exiting the app.
        f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (!(f instanceof TimeScheduleFragment)) {
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
        savedInstanceState.putInt("COLOR", actionBarColor);
        savedInstanceState.putInt("STAGE", actionBarStage);
        savedInstanceState.putInt("POSITION", currentFragment);
        savedInstanceState.putInt("DAY", currentDay);
        savedInstanceState.putString("TITLE", scheduleBy);
        savedInstanceState.putInt("CURRENT_TIME", currentTimePosition);
        savedInstanceState.putBoolean("SEARCH_EXPANDED", isSearchExpanded);
        savedInstanceState.putString("SEARCH_TEXT", searchText);

//        if(genreAdapter != null) {
//            setStringArrayPref(this, "GENRE_FILTERS", genreAdapter.getSelectedGenres());
//        }

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


}