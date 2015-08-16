package com.shambatimes.schedule;

import android.app.SearchManager;
import android.content.Context;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.crashlytics.android.Crashlytics;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ArtistListLoadDoneEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DatabaseLoadFinishedEvent;
import com.shambatimes.schedule.events.SearchTextEvent;
import com.shambatimes.schedule.events.ToggleToStageEvent;
import com.shambatimes.schedule.events.ToggleToTimeEvent;
import com.shambatimes.schedule.events.UpdateScheduleByTimeEvent;
import com.shambatimes.schedule.myapplication.R;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity {

    public static Shambhala shambhala = new Shambhala();

    private Toolbar toolbar;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean isDrawerOpen = false;


    private final int FRAGMENT_TIME = 0;
    private final int FRAGMENT_STAGE = 1;
    private final int FRAGMENT_FAVORITES = 2;
    private final int FRAGMENT_ARTISTS = 3;

    //nav bar
    Spinner scheduleSpinner;
    AdapterBaseScheduleDays adapterBaseScheduleDays;

    //Items that require a saved state
    private int actionBarColor = 0xFF666666;
    private int currentFragment = FRAGMENT_TIME;
    private int currentDay = -1;
    private int currentTimePosition;

    private TimeScheduleFragment timeScheduleFragment;

    private String scheduleBy = "Schedule by Time";
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fabric.with(this, new Crashlytics());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (DateUtils.getCurrentDay() == 3) {
            Constants.REFERENCE_TIME = Constants.SUNDAY_REFERENCE_TIME;
        } else {
            Constants.REFERENCE_TIME = Constants.GENERAL_REFERENCE_TIME;
        }

        prepareAndLoadDatabase();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        String[] left_list = {"Schedule by Time", "Schedule by Stage", "My Schedule", "Artist List"};

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.item_row, left_list));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if (savedInstanceState == null) {
            currentTimePosition = DateUtils.getCurrentTimePosition();
            actionBarColor = getResources().getColor(R.color.pagoda_color);
            replaceFragment(R.id.content_frame, new TimeScheduleFragment(), "TIME", false);
        } else {
            actionBarColor = savedInstanceState.getInt("COLOR");
            currentFragment = savedInstanceState.getInt("POSITION");
            currentDay = savedInstanceState.getInt("DAY");
            scheduleBy = savedInstanceState.getString("TITLE");
            currentTimePosition = savedInstanceState.getInt("CURRENT_TIME");
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {

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

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {

            //Setting up the spinner in the actionbar
            //See: http://stackoverflow.com/questions/15193598/actionbar-spinner-customisation

            scheduleSpinner = (Spinner) new Spinner(this);
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

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            if (currentFragment != FRAGMENT_ARTISTS) {
                toolbar.addView(scheduleSpinner);
            } else {
                toolbar.setTitle(scheduleBy);
            }

            EventBus.getDefault().postSticky(new ActionBarColorEvent(actionBarColor));

        }


    }

    private void prepareAndLoadDatabase() {

        //TODO asynchronously load / create the database.
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



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    EventBus.getDefault().post(new SearchTextEvent(query));
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    EventBus.getDefault().post(new SearchTextEvent(newText));
                    return false;
                }

            });

            MenuItemCompat.setOnActionExpandListener(searchItem,
                    new MenuItemCompat.OnActionExpandListener() {
                        @Override
                        public boolean onMenuItemActionCollapse(MenuItem item) {
                            scheduleSpinner.setVisibility(View.VISIBLE);
                            return true; // Return true to collapse action view
                        }

                        @Override
                        public boolean onMenuItemActionExpand(MenuItem item) {
                            return true; // Return true to expand action view
                        }
                    });

        }

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            searchItem.setVisible(!isDrawerOpen && currentFragment == FRAGMENT_ARTISTS);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, final int position, long id) {


            //Prevents the navigation drawer stutter when switching fragments.
            final Handler mDrawerHandler = new Handler();
            mDrawerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectItem(position);
                }
            }, 300);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        currentFragment = position;

        toolbar.setTitle("");

        //I'm removing the view as toggling between spinner/non spinner was causing some
        //weird behavior where both would sometimes appear, which wasn't making sense.
        toolbar.removeView(scheduleSpinner);

        switch (position) {

            case 0:

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

                break;

            case 1:

                fragment = fragmentManager.findFragmentByTag("STAGE");
                if (fragment == null) {
                    fragment = new StageScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, "STAGE", false);
                } else {

                }

                scheduleBy = "Schedule by Stage";
                toolbar.addView(scheduleSpinner);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                break;

            case 2:

                fragment = fragmentManager.findFragmentByTag("FAVORITE");
                if (fragment == null) {
                    fragment = new FavoriteScheduleFragment();
                    replaceFragment(R.id.content_frame, fragment, "FAVORITE", false);
                } else {

                }

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                scheduleBy = "My Schedule";
                toolbar.addView(scheduleSpinner);

                break;

            case 3:

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

                break;
        }

        adapterBaseScheduleDays.notifyDataSetChanged();
        invalidateOptionsMenu();
        mDrawerList.setItemChecked(position, true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }


    public void onEventMainThread(ActionBarColorEvent event) {
        actionBarColor = event.getColor();
        Drawable colorDrawable = new ColorDrawable(actionBarColor);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }


    public void onEventMainThread(ToggleToStageEvent event) {
        int position = event.getStage();
        String name = event.getName();

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
    }


    public void onEventMainThread(ToggleToTimeEvent event) {
        int position = event.getTime();

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
    }

    public void onEventMainThread(UpdateScheduleByTimeEvent event) {
        currentTimePosition = event.getPosition();
    }

    private void replaceFragment(int id, Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment, tag);
        fragmentTransaction.commit();
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

    @Override
    public void onBackPressed() {

        //Return to the time schedule fragment before exiting the app.
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (!(f instanceof TimeScheduleFragment)) {
            selectItem(0);
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
        savedInstanceState.putInt("POSITION", currentFragment);
        savedInstanceState.putInt("DAY", currentDay);
        savedInstanceState.putString("TITLE", scheduleBy);
        savedInstanceState.putInt("CURRENT_TIME", currentTimePosition);
        super.onSaveInstanceState(savedInstanceState);
    }

}