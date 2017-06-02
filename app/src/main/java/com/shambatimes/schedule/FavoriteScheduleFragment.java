package com.shambatimes.schedule;

import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.Alarms.AlarmHelper;
import com.shambatimes.schedule.Util.AnimationHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.animations.MyTransitionDrawable;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;


//see http://stackoverflow.com/questions/26995236/cardview-inside-recyclerview-has-extra-margins

public class FavoriteScheduleFragment extends Fragment {
    private static final String TAG = "FavoriteSheduleFragment";

    private int date = 0;

    private String[] stageNames;
    int[] stageColors;
    int[] colors = {0, 0, 0};
    int scrollColor;
    ArrayList<Artist> artists;

    ArtistRecyclerAdapter adapter;
    RecyclerView recyclerView;
    View rootView;
    DateTimeFormatter dateStringFormat;

    boolean ignoreSelfEvent = false;
    AlarmHelper alarmHelper;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.recycler_schedule_favorites, container, false);

        stageColors = this.getResources().getIntArray(R.array.stage_colors);

        String[] args = {"1", "0", Shambhala.getFestivalYear(getActivity())};
        artists = (ArrayList<Artist>) Artist.find(Artist.class, "favorite = ? and day = ? and year = ?", args, null, "day ASC, start_Position ASC", null);

        stageNames = getActivity().getResources().getStringArray(R.array.stages);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dateStringFormat = DateUtils.getTimeFormat(preferences.getString(SettingsActivity.TIME_FORMAT, "24"));

        recyclerView = (RecyclerView) rootView.findViewById(R.id.listView_schedule);
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

        alarmHelper = new AlarmHelper(getActivity(), rootView);

        showNoFavouritesIfEmpty();

        return (rootView);
    }

    private void showNoFavouritesIfEmpty() {
        TextView noArtistPlaying = (TextView) rootView.findViewById(R.id.no_artists_text);
        noArtistPlaying.setVisibility(artists.size() == 0 ? View.VISIBLE : View.GONE);
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        protected TextView artistName;
        protected TextView artistTime;
        protected TextView artistStartTimePosition;
        protected TextView artistStage;
        protected TextView artistGenres;
        protected RelativeLayout artistLayout;
        protected View divider;
        protected ImageView alarm;
        protected ImageView image;

        public ArtistViewHolder(View v) {

            super(v);

            artistName = (TextView) v.findViewById(R.id.artistName);
            artistTime = (TextView) v.findViewById(R.id.artistTime);
            artistStartTimePosition = (TextView) v.findViewById(R.id.artistStartTimePosition);
            artistStage = (TextView) v.findViewById(R.id.artistStage);
            artistGenres = (TextView) v.findViewById(R.id.artistGenres);
            artistLayout = (RelativeLayout) v.findViewById(R.id.artistLayout);
            alarm = (ImageView) v.findViewById(R.id.list_alarm_set);
            image = (ImageView) v.findViewById(R.id.list_favorited);
            divider = v.findViewById(R.id.separator);


        }
    }

    Artist snackArtist;
    int snackPosition;
    boolean snackWasAlarmSet = false;

    public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

        private ArrayList<Artist> artistList;

        public ArtistRecyclerAdapter(ArrayList<Artist> artistList) {
            this.artistList = artistList;
        }

        @Override
        public int getItemCount() {
            return artistList.size();
        }

        public void removeItem(int position) {
            try {
                if (position >= 0 && position < getItemCount()) {
                    artistList.remove(position);
                    notifyItemRemoved(position);
                    showNoFavouritesIfEmpty();
                    Log.i(TAG, "Removing item " + position);
                }
            } catch (Exception e) {
                Log.e("Exception", "Error removing Item", e);
            }
        }

        public void addItem(int position, Artist artist) {
            try {
                artistList.add(position, artist);
                notifyItemInserted(position);
            } catch (Exception e) {
                Log.e("Exception", "Error Adding Item", e);
            }
        }

        @Override
        public void onBindViewHolder(final ArtistViewHolder artistViewHolder, int i) {
            final Artist artist = artistList.get(artistViewHolder.getAdapterPosition());

            String formattedGenres = artist.getGenres().replace(",", ", ").toLowerCase();
            if (formattedGenres.length() == 0 || Shambhala.getFestivalYear(getActivity()).equals("2015")) {
                artistViewHolder.artistGenres.setVisibility(View.GONE);
            } else {
                artistViewHolder.artistGenres.setVisibility(View.VISIBLE);
            }

            artistViewHolder.artistName.setText(artist.getAristName());
            artistViewHolder.artistGenres.setText(formattedGenres);

            artistViewHolder.artistTime.setText(DateUtils.formatTime(dateStringFormat, artist.getStartTimeString()) +
                    " - "
                    + DateUtils.formatTime(dateStringFormat, artist.getEndTimeString()));

            artistViewHolder.artistStartTimePosition.setText("" + artist.getStartPosition());
            artistViewHolder.artistStage.setText(stageNames[artist.getStage()]);
            artistViewHolder.divider.setBackground(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));

            if (artist.isAlarmSet()) {
                artistViewHolder.alarm.setVisibility(View.VISIBLE);
            } else {
                artistViewHolder.alarm.setVisibility(View.GONE);
            }

            artistViewHolder.image.setImageDrawable(AnimationHelper.getFavoriteTransitionDrawable(getActivity(), artist.isFavorite()));
            artistViewHolder.image.setColorFilter(ContextCompat.getColor(getActivity(), ColorUtil.getStageColors()[artist.getStage()]));

            artistViewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alarmHelper.setOnAlarmStateChangedListener(null);

                    if (artist.isFavorite()) {
                        snackWasAlarmSet = artist.isAlarmSet();
                        snackArtist = artist;
                        snackPosition = artistViewHolder.getAdapterPosition();

                        MyTransitionDrawable transitionDrawable = (MyTransitionDrawable) artistViewHolder.image.getDrawable();
                        transitionDrawable.favoriteReverse(Constants.ANIMATION_DURATION_HEARTS);

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

                    removeItem(artistViewHolder.getAdapterPosition());

                }
            });

            alarmHelper.setOnAlarmStateChangedListener(null);

            artistViewHolder.artistLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alarmHelper.dismissSnackbar();
                    if (artist.isAlarmSet()) {
                        alarmHelper.showCancelAlarmSnackbar(artist);
                    } else {
                        alarmHelper.showSetAlarmSnackBar(artist);
                    }

                    alarmHelper.setOnAlarmStateChangedListener(new AlarmHelper.OnAlarmStateChangedListener() {
                        @Override
                        public void alarmStateChanged() {
                            if (adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
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

    private void showUndoSnackBar(Artist artist) {

        final View coordinatorLayoutView = rootView.findViewById(R.id.snackbarPosition);

        Snackbar snackbar = Snackbar.make(coordinatorLayoutView, "Removing " + artist.getAristName() + " from schedule", Snackbar.LENGTH_LONG)
                .setAction("UNDO", undoClickListener)
                .setDuration(Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(alarmHelper.getSnackBarColor(artist.getStage()));

        TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);

        snackBarTextView.setTextColor(ColorUtil.snackbarTextColor(getActivity()));

        TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);
        snackBarActionTextView.setTextColor(ColorUtil.snackbarTextColor(getActivity()));
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

                if (snackWasAlarmSet) {
                    alarmHelper.setAlarm(snackArtist);
                }

                adapter.addItem(position, artist);
                showNoFavouritesIfEmpty();

                MainActivity.shambhala.updateArtistById(artist.getId());

                ignoreSelfEvent = true;
                EventBus.getDefault().postSticky(new DataChangedEvent(true, id));
            }
        }
    };

    public void onEventMainThread(DataChangedEvent event) {

        if (event.isChanged() && !ignoreSelfEvent) {

            EventBus.getDefault().removeStickyEvent(event);

            String[] args = {"1", "" + date, Shambhala.getFestivalYear(getActivity())};

            MainActivity.shambhala.updateArtistById(event.getArtistId());
            artists = (ArrayList<Artist>) Artist.find(Artist.class, "favorite = ? and day = ? and year = ?", args, null, "day ASC, start_Position ASC", null);
            adapter = new ArtistRecyclerAdapter(artists);
            recyclerView.setAdapter(adapter);
        }

        ignoreSelfEvent = false;
    }

    public void onEventMainThread(ChangeDateEvent event) {

        date = event.getPosition();

        String[] args = {"1", "" + date, Shambhala.getFestivalYear(getActivity())};

        artists = (ArrayList<Artist>) Artist.find(Artist.class, "favorite = ? and day = ? and year = ?", args, null, "day ASC, start_Position ASC", null);
        adapter = new ArtistRecyclerAdapter(artists);
        recyclerView.setAdapter(adapter);
        showNoFavouritesIfEmpty();
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        int color = ColorUtil.dividerColor(getActivity());
        colors = ColorUtil.getDividerGradientColor(color);
        scrollColor = color;
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