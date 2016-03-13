package com.shambatimes.schedule;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;


import com.shambatimes.schedule.Receivers.AlarmReceiver;
import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.events.ActionBarColorEvent;

import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.events.FilterEvent;
import com.shambatimes.schedule.myapplication.R;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;


public class ArtistsFragment extends Fragment {
    String TAG = "ListSheduleFragment";


    private ArtistRecyclerAdapter adapter;
    private VerticalRecyclerViewFastScroller fastScroller;
    private int[] colors = {0, 0, 0};
    private int scrollColor;

    private ArrayList<Artist> artists;
    private RecyclerView recyclerView;
    private PendingIntent pendingIntent;
    AlarmHelper alarmHelper;

    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        layout = inflater.inflate(R.layout.recycler_schedule_artists, container, false);
        alarmHelper = new AlarmHelper(getActivity(),layout);

        recyclerView = (RecyclerView) layout.findViewById(R.id.listView_schedule);
        fastScroller = (VerticalRecyclerViewFastScroller) layout.findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);
        fastScroller.setBarColor(getResources().getColor(R.color.background_material_light));
        recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new FlipInBottomXAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recycler, int newState) {
                super.onScrollStateChanged(recycler, newState);
                EdgeChanger.setEdgeGlowColor(recyclerView, scrollColor);
            }
        });

        artists = (ArrayList<Artist>) Artist.find(Artist.class, null, null, null, "lower(artist_Name) asc", null);

        adapter = new ArtistRecyclerAdapter(artists);
        recyclerView.setAdapter(adapter);

        return (layout);
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        protected TextView artistDay;
        protected TextView genres;
        protected TextView artistName;
        protected TextView artistTime;
        protected TextView artistStartTimePosition;
        protected TextView artistStage;
        protected RelativeLayout artistLayout;
        protected View divider;


        protected ImageView image;

        public ArtistViewHolder(View v) {
            super(v);

            artistName = (TextView) v.findViewById(R.id.artistName);
            artistTime = (TextView) v.findViewById(R.id.artistTime);
            artistStartTimePosition = (TextView) v.findViewById(R.id.artistStartTimePosition);
            artistStage = (TextView) v.findViewById(R.id.artistStage);
            artistDay = (TextView) v.findViewById(R.id.artistDay);
            genres = (TextView) v.findViewById(R.id.artistGenres);
            artistLayout = (RelativeLayout) v.findViewById(R.id.artistLayout);
            image = (ImageView) v.findViewById(R.id.list_favorited);
            divider = v.findViewById(R.id.separator);

        }
    }

    Artist snackArtist;

    public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

        private ArrayList<Artist> artistList;
        String[] dayOfWeek = {"Thursday", "Friday", "Saturday", "Sunday"};
        private String[] stageNames = getActivity().getResources().getStringArray(R.array.stages);

        //ItemFilter mFilter = new ItemFilter();

        int[] favoriteDrawables = {R.drawable.favorite_pagoda,
                R.drawable.favorite_forest,
                R.drawable.favorite_grove,
                R.drawable.favorite_living_room,
                R.drawable.favorite_village,
                R.drawable.favorite_amphitheatre,
                R.drawable.favorite_amphitheatre};

        int[] favoriteOutlineDrawables = {R.drawable.favorite_outline_pagoda,
                R.drawable.favorite_outline_forest,
                R.drawable.favorite_outline_grove,
                R.drawable.favorite_outline_living_room,
                R.drawable.favorite_outline_village,
                R.drawable.favorite_outline_amphitheatre,
                R.drawable.favorite_outline_amphitheatre};

        int[] stageColors = {R.color.pagoda_color,
                R.color.fractal_forest_color,
                R.color.grove_color,
                R.color.living_room_color,
                R.color.village_color,
                R.color.amphitheatre_color,
                R.color.amphitheatre_color};

        public ArtistRecyclerAdapter(ArrayList<Artist> artistList) {
            this.artistList = artistList;
        }

        @Override
        public int getItemCount() {

            if (artistList != null) {
                return artistList.size();
            } else {
                return 0;
            }
        }

        public int findArtist(String name) {

            int artistPosition = 0;

            for (Artist artist : artistList) {
                if (artist.getAristName().equals(name)) {
                    return artistPosition;
                }
                artistPosition++;
            }
            return -1;
        }

//        public Filter getFilter() {
//            return mFilter;
//        }

        @Override
        public void onBindViewHolder(final ArtistViewHolder artistViewHolder, final int i) {
            final Artist artist = artistList.get(i);

            artistViewHolder.artistName.setText(artist.getAristName());
            artistViewHolder.artistDay.setText(dayOfWeek[artist.getDay()]);
            String formattedGenres = artist.getGenres().replace(",", ", ");
            artistViewHolder.genres.setText(formattedGenres);
            artistViewHolder.artistTime.setText(artist.getStartTimeString() + " to " + artist.getEndTimeString());
            artistViewHolder.artistStartTimePosition.setText("" + artist.getStartPosition());
            artistViewHolder.artistStage.setText(stageNames[artist.getStage()]);
            artistViewHolder.divider.setBackground(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));

            if (artist.isFavorite()) {
                artistViewHolder.image.setImageResource(favoriteDrawables[artist.getStage()]);
            } else {
                artistViewHolder.image.setImageResource(favoriteOutlineDrawables[artist.getStage()]);
            }

            artistViewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (artist.isFavorite()) {
                        artistViewHolder.image.setImageResource(favoriteOutlineDrawables[artist.getStage()]);
                        artist.setFavorite(false);
                        artist.save();

                        alarmHelper.dismissSnackbar();

                    } else {
                        artistViewHolder.image.setImageResource(favoriteDrawables[artist.getStage()]);
                        artist.setFavorite(true);
                        artist.save();


                        alarmHelper.showSetAlarmSnackBar(artist);
                    }

                    MainActivity.shambhala.updateArtistById(artist.getId());

                }
            });
        }

        @Override
        public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.artist_list_item_artists, viewGroup, false);

            return new ArtistViewHolder(itemView);
        }

        private void applyGenreFilter(ArrayList<String> filteredGenres) {

            final ArrayList<Artist> originalArtistList = artists;

            int count = originalArtistList.size();

            final ArrayList<Artist> newArtistList = new ArrayList<Artist>(count);


            String artistGenreString;

            for (int i = 0; i < count; i++) {

                artistGenreString = originalArtistList.get(i).getGenres();
                String[] artistGenreArray = artistGenreString.split(",");

                if (filteredGenres.isEmpty()) {
                    newArtistList.add(originalArtistList.get(i));
                } else {

                    boolean matchFound = false;

                    for (String genre : artistGenreArray) {

                        if (filteredGenres.contains(genre.toLowerCase())) {
                            newArtistList.add(originalArtistList.get(i));
                            matchFound = true;
                        }

                        if (matchFound) {
                            break;
                        }
                    }
                }

            }

            artistList = newArtistList;
            notifyDataSetChanged();

        }

    }

    public void onEventMainThread(FilterEvent event) {
        if (event.getGenreFilterList().isEmpty()) {
            recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());
        } else {
            recyclerView.removeOnScrollListener(fastScroller.getOnScrollListener());
        }

        adapter.applyGenreFilter(event.getGenreFilterList());
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        colors[1] = event.getColor();
        scrollColor = event.getColor();
        fastScroller.setHandleColor(event.getColor());
    }

    public void onEventMainThread(SearchSelectedEvent event) {
        int artistPosition = adapter.findArtist(event.getArtist().getAristName());
        if (artistPosition != -1) {
            LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
            llm.scrollToPositionWithOffset(artistPosition, 20);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}