package com.shambatimes.schedule;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.events.ToggleToTimeEvent;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class StageListScheduleFragment extends Fragment {
    String TAG = "ListSheduleFragment";

    private static final String STAGE_POSITION = "STAGE";
    private static final String DATE_POSITION = "DATE";
    private static final String SEARCH_NAME = "NAME";
    private static final String DIVIDER_COLOR = "COLOR";

    private ListView listView;
    private ArtistAdapter adapter;
    private int date = 0;
    private int stage = 0;
    private String searchName;

    ArrayList<Artist> artists;

    static StageListScheduleFragment newInstance(int position, int date, String name, int dividerColor) {
        StageListScheduleFragment frag = new StageListScheduleFragment();
        Bundle args = new Bundle();

        args.putInt(STAGE_POSITION, position);
        args.putInt(DATE_POSITION, date);
        args.putInt(DIVIDER_COLOR, dividerColor);
        args.putString(SEARCH_NAME, name);

        frag.setArguments(args);

        return (frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View result = inflater.inflate(R.layout.list_schedule, container, false);

        date = getArguments().getInt(DATE_POSITION, 0);
        stage = getArguments().getInt(STAGE_POSITION, 0);
        searchName = getArguments().getString(SEARCH_NAME, "");
        artists = MainActivity.shambhala.getArtistsByDayAndStage(date, stage);

        //ArrayList<Artist> artists =
        //        (ArrayList<Artist>) Artist.find(Artist.class, "day = ? and stage = ?", "" + date, "" + stage);


        listView = (ListView) result.findViewById(R.id.listView_schedule);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.artistStartTimePosition);
                DateTime startTime = convertStringTimeToPosition(textView.getText().toString());

                DateTime baseTime = convertStringTimeToPosition("11:00");
//                if(date != 3) {
//                     baseTime = convertStringTimeToPosition("11:00");
//                }else{
//                     baseTime = convertStringTimeToPosition("12:00");
//                }

                Minutes minutesToStart = Minutes.minutesBetween(baseTime, startTime);

                int startPosition = minutesToStart.getMinutes() / 30;

                if (startPosition < 0) startPosition += 48;

                EventBus.getDefault().post(new ToggleToTimeEvent(startPosition));
            }
        });

        adapter = new ArtistAdapter(getActivity(), artists);

        listView.setAdapter(adapter);

        updateSelectedListViewItems(false);

        return (result);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    private void updateSelectedListViewItems(boolean resetSearchName) {

        //TODO Currently hardcoded to 1 (friday) for testing.
        Artist currentlyPlayingArtist = MainActivity.shambhala.getArtistsByDayAndPositionAndStage(1, DateUtils.getCurrentTimePosition(), stage);
        View view;
        TextView artistNameTextView;

        listView.clearChoices();

        for (int i = 0; i < listView.getCount(); i++) {

            view = listView.getAdapter().getView(i, null, null);
            artistNameTextView = (TextView) view.findViewById(R.id.artistName);

            if (!searchName.equals("") && artistNameTextView.getText().toString().equals(searchName)) {
                EventBus.getDefault().removeStickyEvent(SearchSelectedEvent.class);
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                listView.setSelection(i);
                listView.setItemChecked(i, true);
                listView.smoothScrollToPosition(i);

                if (resetSearchName) {
                    searchName = "";
                }

                if (currentlyPlayingArtist == null) {
                    break; //exit loop as there's nothing left to select
                }

            } else if (currentlyPlayingArtist != null && artistNameTextView.getText().toString().equals(currentlyPlayingArtist.getAristName())) {
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                listView.setItemChecked(i, true);
            }
        }
    }


    private DateTime convertStringTimeToPosition(String stringTime) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        DateTime dateTime = formatter.parseDateTime(stringTime);

        return dateTime;
    }

    public class ArtistAdapter extends BaseAdapter {

        ArrayList<Artist> artistList = new ArrayList<>();
        LayoutInflater inflater;
        Context context;

        int[] favoriteDrawables = {R.drawable.favorite_pagoda,
                R.drawable.favorite_forest,
                R.drawable.favorite_grove,
                R.drawable.favorite_living_room,
                R.drawable.favorite_village,
                R.drawable.favorite_amphitheatre};

        int[] favoriteOutlineDrawables = {R.drawable.favorite_outline_pagoda,
                R.drawable.favorite_outline_forest,
                R.drawable.favorite_outline_grove,
                R.drawable.favorite_outline_living_room,
                R.drawable.favorite_outline_village,
                R.drawable.favorite_outline_amphitheatre};


        public ArtistAdapter(Context context, ArrayList<Artist> artistList) {
            this.artistList = artistList;
            this.context = context;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getCount() {
            return artistList.size();
        }

        @Override
        public Artist getItem(int position) {
            return artistList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyViewHolder mViewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.artist_list_item_stage, null);
                mViewHolder = new MyViewHolder();
                convertView.setTag(mViewHolder);
                mViewHolder.artistName = (TextView) convertView.findViewById(R.id.artistName);
                mViewHolder.artistTime = (TextView) convertView.findViewById(R.id.artistTime);
                mViewHolder.artistStartTimePosition = (TextView) convertView.findViewById(R.id.artistStartTimePosition);
                mViewHolder.artistLayout = (LinearLayout) convertView.findViewById(R.id.artistLayout);
                mViewHolder.artistLayout.setBackgroundResource(getPressedColor(stage));
            } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }


            mViewHolder.artistName.setText(getItem(position).getAristName());
            mViewHolder.artistTime.setText(getItem(position).getStartTimeString() + " to " + getItem(position).getEndTimeString());
            mViewHolder.artistStartTimePosition.setText(getItem(position).getStartTimeString());

            final ImageView image = (ImageView) convertView.findViewById(R.id.list_favorited);

            if (getItem(position).isFavorite()) {
                image.setImageResource(favoriteDrawables[getItem(position).getStage()]);
            } else {
                image.setImageResource(favoriteOutlineDrawables[getItem(position).getStage()]);
            }

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getItem(position).isFavorite()) {
                        image.setImageResource(favoriteOutlineDrawables[getItem(position).getStage()]);
                        getItem(position).setFavorite(false);
                        getItem(position).save();
                    } else {
                        image.setImageResource(favoriteDrawables[getItem(position).getStage()]);
                        getItem(position).setFavorite(true);
                        getItem(position).save();
                    }
                }
            });

            return convertView;
        }

        private class MyViewHolder {
            TextView artistName, artistTime, artistStartTimePosition;
            LinearLayout artistLayout;
        }

    }


    private int getPressedColor(int stage) {

        int color = R.drawable.list_pagoda_selector;

        switch (stage) {

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

    public void onEventMainThread(ActionBarColorEvent event) {

        int color = event.getColor();
        int[] colors = {0, color, 0};
        listView.setDivider(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));
        listView.setDividerHeight(1);

        EdgeChanger.setEdgeGlowColor(listView, event.getColor());

    }

    public void onEventMainThread(ChangeDateEvent event) {
        date = event.getPosition();
        rebuildAdapter();
        updateSelectedListViewItems(true);
    }

    public void onEventMainThread(SearchSelectedEvent event) {
        searchName=event.getArtist().getAristName();
        updateSelectedListViewItems(true);
    }

    public void onEventMainThread(DataChangedEvent event) {

        EventBus.getDefault().removeStickyEvent(event);

        if (event.isChanged()) {
            MainActivity.shambhala.updateArtistById(event.getArtistId());

            //Get the current position, and how far it's scrolled.  After rebuilding adapter
            //set the selection back to these values and it won't move.
            long currentPosition = listView.getFirstVisiblePosition();
            View v = listView.getChildAt(0);
            int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());

            rebuildAdapter();

            listView.setSelectionFromTop((int) currentPosition,top);
            updateSelectedListViewItems(false);

        }
    }

    private void rebuildAdapter(){
        artists = MainActivity.shambhala.getArtistsByDayAndStage(date, stage);
        adapter = new ArtistAdapter(getActivity(), artists);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}