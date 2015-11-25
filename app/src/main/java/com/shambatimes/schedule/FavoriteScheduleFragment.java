package com.shambatimes.schedule;


import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.shambatimes.schedule.Receivers.AlarmReceiver;
import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.myapplication.R;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;


//see http://stackoverflow.com/questions/26995236/cardview-inside-recyclerview-has-extra-margins

public class FavoriteScheduleFragment extends Fragment {
    private static final String TAG = "FavoriteSheduleFragment";

    private static final String STAGE_POSITION = "STAGE";
    private static final String DATE_POSITION = "DATE";
    private static final String SEARCH_NAME = "NAME";
    private static final String DIVIDER_COLOR = "COLOR";

    private int date = 0;


    private String[] stageNames;
    int[] stageColors;
    int[] colors = {0, 0, 0};
    int scrollColor;
    ArrayList<Artist> artists;

    ArtistRecyclerAdapter adapter;
    RecyclerView recyclerView;
    View result;

    boolean ignoreSelfEvent = true;
    AlarmHelper alarmHelper;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        result = inflater.inflate(R.layout.recycler_schedule_favorites, container, false);
        alarmHelper = new AlarmHelper(getActivity(),result);

        stageColors = this.getResources().getIntArray(R.array.stage_colors);

        String[] args = {"1", "0"};
        artists = (ArrayList<Artist>) Artist.find(Artist.class, "favorite = ? and day = ?", args, null, "day ASC, start_Position ASC", null);

        stageNames = getActivity().getResources().getStringArray(R.array.stages);

        recyclerView = (RecyclerView) result.findViewById(R.id.listView_schedule);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        adapter = new ArtistRecyclerAdapter(artists);

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new FlipInBottomXAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recycler, int newState) {
                super.onScrollStateChanged(recycler, newState);
                EdgeChanger.setEdgeGlowColor(recyclerView, scrollColor);
            }
        });


        return (result);
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

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
            artistLayout = (RelativeLayout) v.findViewById(R.id.artistLayout);
            image = (ImageView) v.findViewById(R.id.list_favorited);
            divider = v.findViewById(R.id.separator);

        }

    }


    Artist snackArtist;
    int snackPosition;
    boolean snackWasAlarmSet = false;

    public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

        private ArrayList<Artist> artistList;
        private int lastPosition = -1;

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

        public ArtistRecyclerAdapter(ArrayList<Artist> artistList) {
            this.artistList = artistList;
        }

        @Override
        public int getItemCount() {
            return artistList.size();
        }

        public void removeItem(int position) {
            artistList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, artistList.size());
            Log.i(TAG, "Removing item " + position);
        }

        public void addItem(int position, Artist artist) {
            try {
                artistList.add(position, artist);
                notifyItemInserted(position);
                notifyItemRangeChanged(position, artistList.size());
            } catch (Exception e) {
                Log.e("Exception", "Error Adding Item", e);
            }
        }


        @Override
        public void onBindViewHolder(final ArtistViewHolder artistViewHolder, final int i) {
            final Artist artist = artistList.get(i);


            artistViewHolder.artistName.setText(artist.getAristName());
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

                        snackWasAlarmSet = artist.isAlarmSet();
                        snackArtist = artist;
                        snackPosition = i;

                        artistViewHolder.image.setImageResource(favoriteOutlineDrawables[artist.getStage()]);
                        artist.setFavorite(false);
                        artist.setIsAlarmSet(false);
                        artist.save();

                        MainActivity.shambhala.updateArtistById(artist.getId());
                    }

                    Animation animUndo = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                    animUndo.setDuration(1000);

                    showUndoSnackBar(artist);

                    alarmHelper.cancelAlarm(artist);

                    ignoreSelfEvent = true;

                    EventBus.getDefault().postSticky(new DataChangedEvent(true, artist.getId()));

                    removeItem(i);

                }
            });

        }

        @Override
        public ArtistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.artist_list_item_favorite, viewGroup, false);

            return new ArtistViewHolder(itemView);
        }

    }

    private void showUndoSnackBar(Artist artist){

        final View coordinatorLayoutView = result.findViewById(R.id.snackbarPosition);

        Snackbar snackbar = Snackbar.make(coordinatorLayoutView, "Removing " + artist.getAristName() + " from schedule", Snackbar.LENGTH_LONG)
                .setAction("UNDO", undoClickListener)
                .setDuration(Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.pagoda_color));
        TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);

        snackBarTextView.setTextColor(Color.WHITE);
        snackBarActionTextView.setTextColor(Color.WHITE);

        snackBarActionTextView.setTextSize(14);

        snackbar.show();

    }

    final View.OnClickListener undoClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            if (snackArtist != null) {

                long id = snackArtist.getId();
                int position = snackPosition;

                Artist artist = Artist.findById(Artist.class, id);
                artist.setFavorite(true);
                artist.setIsAlarmSet(snackWasAlarmSet);
                artist.save();

                if(snackWasAlarmSet){

                    alarmHelper.setAlarm(snackArtist);
                }

                adapter.addItem(position, artist);

                ignoreSelfEvent = true;
                EventBus.getDefault().postSticky(new DataChangedEvent(true, id));

            }
        }
    };

    public void onEventMainThread(DataChangedEvent event) {

        if (event.isChanged() && ignoreSelfEvent != true) {

            EventBus.getDefault().removeStickyEvent(event);

            String[] args = {"1", "" + date};

            MainActivity.shambhala.updateArtistById(event.getArtistId());
            artists = (ArrayList<Artist>) Artist.find(Artist.class, "favorite = ? and day = ?", args, null, "day ASC, start_Position ASC", null);
            adapter = new ArtistRecyclerAdapter(artists);
            recyclerView.setAdapter(adapter);
        }

        ignoreSelfEvent = false;
    }

    public void onEventMainThread(ChangeDateEvent event) {

        date = event.getPosition();

        String[] args = {"1", "" + date};

        artists = (ArrayList<Artist>) Artist.find(Artist.class, "favorite = ? and day = ?", args, null, "day ASC, start_Position ASC", null);
        adapter = new ArtistRecyclerAdapter(artists);
        recyclerView.setAdapter(adapter);

    }

    public void onEventMainThread(ActionBarColorEvent event) {
        colors[1] = event.getColor();
        scrollColor = event.getColor();
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