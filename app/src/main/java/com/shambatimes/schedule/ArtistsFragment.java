package com.shambatimes.schedule;

import android.content.Context;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.Util.Util;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
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
    private ListView listView;

    AlarmHelper alarmHelper;

    CardView genreCardView;

    LinearLayoutManager linearLayoutManager;
    private View layout;

    ArrayList<String> selectedGenres = new ArrayList<>();
    GenreAdapter genreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.recycler_schedule_artists, container, false);
        alarmHelper = new AlarmHelper(getActivity(), layout);


        setupRecyclerView();
        setupGenres();

        if (savedInstanceState != null) {
            boolean genresVisible = savedInstanceState.getBoolean("genresVisible", false);

            if (genreAdapter != null) {
                selectedGenres = savedInstanceState.getStringArrayList("selectedGenres");

                boolean[] itemChecked = savedInstanceState.getBooleanArray("itemChecked");
                genreAdapter.setItemChecked(itemChecked);
                EventBus.getDefault().postSticky(new FilterEvent(selectedGenres));
            }

            if (genresVisible) {
                adapter.animateHeartsInwards = true;
                showGenres(false);
            }
        }

        return (layout);
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) layout.findViewById(R.id.listView_schedule);

        fastScroller = (VerticalRecyclerViewFastScroller) layout.findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new FlipInBottomXAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recycler, int newState) {
                super.onScrollStateChanged(recycler, newState);
                EdgeChanger.setEdgeGlowColor(recyclerView, scrollColor);
            }
        });

        artists = MainActivity.shambhala.loadAllArtistsForYear(Shambhala.getFestivalYear(getActivity()));

        adapter = new ArtistRecyclerAdapter(artists);
        recyclerView.setAdapter(adapter);
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

    public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

        private ArrayList<Artist> artistList;
        String[] dayOfWeek = getActivity().getResources().getStringArray(R.array.daysOfWeek);
        private String[] stageNames = getActivity().getResources().getStringArray(R.array.stages);
        private boolean animateHeartsInwards = false;
        private boolean skipAnimations = true;
        int yAxisAnimationDistance;
        //ItemFilter mFilter = new ItemFilter();

        int editTextWidth;

        int[] favoriteDrawables = ColorUtil.getStageFavoriteDrawables();
        int[] favoriteOutlineDrawables = ColorUtil.getStageFavoriteOutlineDrawables();
        int screenWidth;

        public ArtistRecyclerAdapter(ArrayList<Artist> artistList) {
            this.artistList = artistList;
        }

        public void setArtistList(ArrayList<Artist> artistList) {
            this.artistList = artistList;
            notifyDataSetChanged();
        }

        public void animateHeartsToLeft() {
            skipAnimations = false;
            animateHeartsInwards = true;
            screenWidth = getResources().getDisplayMetrics().widthPixels;
            notifyDataSetChanged();
        }

        public void animateHeartsToRight() {
            skipAnimations = false;
            animateHeartsInwards = false;
            screenWidth = getResources().getDisplayMetrics().widthPixels;
            notifyDataSetChanged();
        }

        public void skipAnimations(boolean skip) {
            skipAnimations = skip;
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

            String formattedGenres = artist.getGenres().replace(",", ", ").toLowerCase();
            if (formattedGenres.length() == 0 || Shambhala.getFestivalYear(getActivity()).equals("2015")) {
                artistViewHolder.genres.setVisibility(View.GONE);
                yAxisAnimationDistance = 20;
            } else {
                artistViewHolder.genres.setVisibility(View.VISIBLE);
                yAxisAnimationDistance = 30;
            }

            artistViewHolder.artistName.setText(artist.getAristName());
            artistViewHolder.artistDay.setText(dayOfWeek[artist.getDay()]);
            artistViewHolder.genres.setText(formattedGenres);
            artistViewHolder.artistTime.setText(artist.getStartTimeString() + " to " + artist.getEndTimeString());
            artistViewHolder.artistStartTimePosition.setText("" + artist.getStartPosition());
            artistViewHolder.artistStage.setText(stageNames[artist.getStage()]);
            artistViewHolder.divider.setBackground(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));

            setupFavorites(artistViewHolder, artist);
            setupGenreAnimations(artistViewHolder, i);

        }

        private void setupFavorites(final ArtistViewHolder artistViewHolder, final Artist artist) {
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

                                                                  Log.i("TAG", "Year: " + artist.getYear());

                                                                  alarmHelper.dismissSnackbar();
                                                              } else {
                                                                  artistViewHolder.image.setImageResource(favoriteDrawables[artist.getStage()]);
                                                                  artist.setFavorite(true);
                                                                  artist.save();

                                                                  alarmHelper.showSetAlarmSnackBar(artist);
                                                              }
                                                              MainActivity.shambhala.updateArtistById(artist.getId());
                                                          }
                                                      }
            );
        }

        private void setupGenreAnimations(ArtistViewHolder artistViewHolder, int position) {

            int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition() - 1;
            int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition() + 1;

            if (editTextWidth == 0 && artistViewHolder.artistStage.getWidth() != 0) {
                editTextWidth = artistViewHolder.artistStage.getWidth();
            }

            if (animateHeartsInwards) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(editTextWidth - ((int) Util.convertDpToPixel(40, getActivity())), RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.BELOW, R.id.separator);
                artistViewHolder.artistName.setLayoutParams(params);
                artistViewHolder.artistName.setPadding((int) Util.convertDpToPixel(15, getActivity()), 5, 0, 0);

                RelativeLayout.LayoutParams paramsGenres = new RelativeLayout.LayoutParams(editTextWidth - ((int) Util.convertDpToPixel(55, getActivity())), RelativeLayout.LayoutParams.WRAP_CONTENT);
                paramsGenres.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                paramsGenres.addRule(RelativeLayout.BELOW, R.id.artistName);
                paramsGenres.setMargins((int) Util.convertDpToPixel(15, getActivity()), 0, 0, 0);
                artistViewHolder.genres.setLayoutParams(paramsGenres);


                if (!skipAnimations && (position >= firstVisiblePosition || position <= lastVisiblePosition)) {
                    artistViewHolder.image.animate()
                            .setDuration(Constants.ANIMATION_DURATION)
                            .translationX(Util.convertDpToPixel(-115, getActivity()))
                            .translationY(Util.convertDpToPixel(yAxisAnimationDistance, getActivity()));
                } else {
                    artistViewHolder.image.setTranslationX(Util.convertDpToPixel(-115, getActivity()));
                    artistViewHolder.image.setTranslationY(Util.convertDpToPixel(yAxisAnimationDistance, getActivity()));
                }

            } else {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.LEFT_OF, R.id.list_favorited);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.BELOW, R.id.separator);
                artistViewHolder.artistName.setLayoutParams(params);
                artistViewHolder.artistName.setPadding((int) Util.convertDpToPixel(15, getActivity()), 5, 0, 0);

                RelativeLayout.LayoutParams paramsGenres = (RelativeLayout.LayoutParams) artistViewHolder.genres.getLayoutParams();
                paramsGenres.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                paramsGenres.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                paramsGenres.addRule(RelativeLayout.LEFT_OF, R.id.list_favorited);
                artistViewHolder.genres.setLayoutParams(paramsGenres);

                if (!skipAnimations && (linearLayoutManager.findLastVisibleItemPosition() <= position || linearLayoutManager.findFirstCompletelyVisibleItemPosition() <= position)) {
                    artistViewHolder.image.animate()
                            .setDuration(Constants.ANIMATION_DURATION)
                            .translationX(Util.convertDpToPixel(0, getActivity()))
                            .translationY(Util.convertDpToPixel(0, getActivity()));
                } else {
                    artistViewHolder.image.setTranslationX(Util.convertDpToPixel(0, getActivity()));
                    artistViewHolder.image.setTranslationY(Util.convertDpToPixel(0, getActivity()));
                }
            }
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
        EventBus.getDefault().removeStickyEvent(event);
        adapter.applyGenreFilter(event.getGenreFilterList());
    }

    int stage;

    public void onEventMainThread(ActionBarColorEvent event) {
        stage = event.getStage();
        colors[1] = event.getColor();
        scrollColor = event.getColor();
        if (fastScroller != null) {
            fastScroller.setHandleColor(event.getColor());
            fastScroller.setBarColor(event.getColor());
        }
        if (listView != null) {
            EdgeChanger.setEdgeGlowColor(listView, event.getColor());
        }
    }

    public void onEventMainThread(final SearchSelectedEvent event) {
        int artistPosition = adapter.findArtist(event.getArtist().getAristName());
        if (artistPosition != -1) {
            LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
            llm.scrollToPositionWithOffset(artistPosition, 20);
        } else {
            artists = MainActivity.shambhala.loadAllArtistsForYearAndDay(Shambhala.getFestivalYear(getActivity()), "" + event.getArtist().getDay());
            adapter.notifyDataSetChanged();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int artistPosition = adapter.findArtist(event.getArtist().getAristName());
                    if (artistPosition != -1) {
                        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                        llm.scrollToPositionWithOffset(artistPosition, 20);
                    }
                }
            }, 50);
        }
    }

    public void setupGenres() {

        //Immediately move the genreCardView off the screen, so we can animate it into space when clicked.
        genreCardView = (CardView) layout.findViewById(R.id.genreCard);
        genreCardView.setTranslationX(Util.convertDpToPixel(120, getActivity()));

        generateFilteredGenreList();

        if (genreAdapter == null) {

            ArrayList<String> genres = new ArrayList<>();
            genres.add("Bass Music");
            genres.add("Breakbeats");
            genres.add("Dance");
            genres.add("Deep House");
            genres.add("Downtempo");
            genres.add("Dubstep");
            genres.add("Electro");
            genres.add("Electronic");
            genres.add("Funk");
            genres.add("Ghetto Funk");
            genres.add("Glitch Hop");
            genres.add("Hip Hop");
            genres.add("House");
            genres.add("Neon Nature");
            genres.add("Soul");
            genres.add("Space Music");
            genres.add("Techno");
            genres.add("Trap");
            genres.add("Trip Hop");
            genres.add("Turntablism");

            genreAdapter = new GenreAdapter(getActivity(), genres);
            listView = (ListView) layout.findViewById(R.id.genrelist);
            listView.setAdapter(genreAdapter);
        }
    }

    ArrayList<String> availableGenresByDate = new ArrayList<>();

    public void generateFilteredGenreList() {

        availableGenresByDate.clear();

        for (Artist artist : artists) {
            String[] artistGenres = artist.getGenres().split(",");
            for (String genre : artistGenres) {
                if (!availableGenresByDate.contains(genre)) {
                    availableGenresByDate.add(genre);
                }
            }
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

    public class GenreAdapter extends BaseAdapter {

        ArrayList<String> genreList = new ArrayList<>();
        LayoutInflater inflater;
        Context context;
        boolean[] itemChecked;


        public GenreAdapter(Context context, ArrayList<String> genreList) {
            this.genreList = genreList;
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            itemChecked = new boolean[genreList.size()];
        }

        public boolean[] getItemChecked() {
            return itemChecked;
        }

        public void setItemChecked(boolean[] itemChecked) {
            this.itemChecked = itemChecked;
        }

        @Override
        public int getCount() {
            return genreList.size();
        }

        @Override
        public String getItem(int position) {
            return genreList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final MyViewHolder mViewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.genre_list_item, null);
                mViewHolder = new MyViewHolder();
                convertView.setTag(mViewHolder);
                //Styling material themed checkboxes dynamically is hard (impossible?) SO LETS HAVE ALL THE BOXES!
                //If anyone sees this... and uh knows a way to do this that's theme based and can be done programatically
                //please let me know. Pretty please.
                mViewHolder.genreboxPagoda = (CheckBox) convertView.findViewById(R.id.genreboxPagoda);
                mViewHolder.genreboxForest = (CheckBox) convertView.findViewById(R.id.genreboxForest);
                mViewHolder.genreboxGrove = (CheckBox) convertView.findViewById(R.id.genreboxGrove);
                mViewHolder.genreboxLivingRoom = (CheckBox) convertView.findViewById(R.id.genreboxLivingRoomm);
                mViewHolder.genreboxVillage = (CheckBox) convertView.findViewById(R.id.genreboxVillage);
                mViewHolder.genreboxAmphitheatre = (CheckBox) convertView.findViewById(R.id.genreboxAmph);
                mViewHolder.genreboxBioDome = (CheckBox) convertView.findViewById(R.id.genreboxBioDome);
                mViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            mViewHolder.textView.setText(genreList.get(position));

            showThemedCheckBox(mViewHolder);

            setupCheckboxEnabledState(mViewHolder, position);

            setupItemChecked(mViewHolder.genreboxPagoda, position);
            setupItemChecked(mViewHolder.genreboxForest, position);
            setupItemChecked(mViewHolder.genreboxGrove, position);
            setupItemChecked(mViewHolder.genreboxLivingRoom, position);
            setupItemChecked(mViewHolder.genreboxVillage, position);
            setupItemChecked(mViewHolder.genreboxAmphitheatre, position);
            setupItemChecked(mViewHolder.genreboxBioDome, position);

            mViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    simpleCheckboxClicker(mViewHolder);
                }
            });

            return convertView;
        }

        private void setupCheckboxEnabledState(MyViewHolder viewHolder, int position) {
            if (availableGenresByDate.contains(genreList.get(position).toLowerCase())) {
                setCheckBoxAlpha(viewHolder, 1f);
            } else {
                setCheckBoxAlpha(viewHolder, 0.1f);
            }
        }

        private void showThemedCheckBox(MyViewHolder viewHolder) {

            switch (stage) {
                case 0:
                    viewHolder.genreboxPagoda.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    viewHolder.genreboxForest.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    viewHolder.genreboxGrove.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    viewHolder.genreboxLivingRoom.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    viewHolder.genreboxVillage.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    viewHolder.genreboxAmphitheatre.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    viewHolder.genreboxBioDome.setVisibility(View.VISIBLE);
                    break;
            }
        }

        private void setCheckBoxAlpha(MyViewHolder mViewHolder, float alpha) {
            mViewHolder.genreboxPagoda.setAlpha(alpha);
            mViewHolder.genreboxForest.setAlpha(alpha);
            mViewHolder.genreboxGrove.setAlpha(alpha);
            mViewHolder.genreboxLivingRoom.setAlpha(alpha);
            mViewHolder.genreboxVillage.setAlpha(alpha);
            mViewHolder.genreboxAmphitheatre.setAlpha(alpha);
            mViewHolder.genreboxBioDome.setAlpha(alpha);
        }

        private void setupItemChecked(CheckBox view, final int position) {
            if (itemChecked[position]) {
                view.setChecked(true);
            } else {
                view.setChecked(false);
            }

            view.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {
                                            CheckBox view = (CheckBox) v;
                                            genresClicked(view, position);
                                        }
                                    }

            );
        }

        private void genresClicked(CheckBox view, int position) {

            if (view.isChecked()) {
                itemChecked[position] = true;
                Log.i("TAG", "Click CHECK" + genreList.get(position).toLowerCase());
                selectedGenres.add(genreList.get(position).toLowerCase());
                EventBus.getDefault().postSticky(new FilterEvent(selectedGenres));
            } else {
                itemChecked[position] = false;
                Log.i("TAG", "Click UNCHECKED" + genreList.get(position).toLowerCase());
                selectedGenres.remove(genreList.get(position).toLowerCase());
                EventBus.getDefault().postSticky(new FilterEvent(selectedGenres));
            }
        }

        private void simpleCheckboxClicker(MyViewHolder viewHolder) {

            switch (stage) {
                case 0:
                    checkBoxClick(viewHolder.genreboxPagoda);
                    break;
                case 1:
                    checkBoxClick(viewHolder.genreboxForest);
                    break;
                case 2:
                    checkBoxClick(viewHolder.genreboxGrove);
                    break;
                case 3:
                    checkBoxClick(viewHolder.genreboxLivingRoom);
                    break;
                case 4:
                    checkBoxClick(viewHolder.genreboxVillage);
                    break;
                case 5:
                    checkBoxClick(viewHolder.genreboxAmphitheatre);
                    break;
                case 6:
                    checkBoxClick(viewHolder.genreboxBioDome);
                    break;
            }
        }


        private void checkBoxClick(CheckBox view) {
            if (view.isChecked()) {
                view.setChecked(false);
            } else {
                view.setChecked(true);
            }
            view.callOnClick();
        }

        private class MyViewHolder {
            CheckBox genreboxPagoda;
            CheckBox genreboxForest;
            CheckBox genreboxGrove;
            CheckBox genreboxLivingRoom;
            CheckBox genreboxVillage;
            CheckBox genreboxAmphitheatre;
            CheckBox genreboxBioDome;

            TextView textView;
            boolean isChecked;
        }

        public ArrayList<String> getSelectedGenres() {
            return selectedGenres;
        }

        public void setSelectedGenres(ArrayList<String> genres) {
            selectedGenres = genres;
        }

        public void clearSelectedGenres() {
            itemChecked = new boolean[genreList.size()];
        }

    }

    public void onEventMainThread(ChangeDateEvent event) {
        if (event.isArtistEvent()) {

            if (event.getPosition() == -1) {
                artists = MainActivity.shambhala.loadAllArtistsForYear(Shambhala.getFestivalYear(getActivity()));
            } else {
                artists = MainActivity.shambhala.loadAllArtistsForYearAndDay(Shambhala.getFestivalYear(getActivity()), "" + event.getPosition());
            }

            adapter.setArtistList(artists);

            generateFilteredGenreList();
            genreAdapter.notifyDataSetChanged();
            EventBus.getDefault().postSticky(new FilterEvent(selectedGenres));

        }
    }

    public void showGenres(boolean animate) {

        if (genreCardView.getVisibility() == View.GONE) {
            genreCardView.setVisibility(View.VISIBLE);

            if (animate) {
                genreCardView.animate().setDuration(Constants.ANIMATION_DURATION).translationX(Util.convertDpToPixel(0, getActivity()));
                adapter.animateHeartsToLeft();
            } else {
                genreCardView.setTranslationX(Util.convertDpToPixel(0, getActivity()));
            }

        } else {
            if (animate) {
                adapter.animateHeartsToRight();
                genreCardView.animate().setDuration(Constants.ANIMATION_DURATION).translationX(Util.convertDpToPixel(120, getActivity())).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        //After the animation duration delete the stock
                        genreCardView.setVisibility(View.GONE);
                    }
                });
            } else {
                genreCardView.setTranslationX(Util.convertDpToPixel(120, getActivity()));
                genreCardView.setVisibility(View.GONE);
            }

            genreAdapter.clearSelectedGenres();
            selectedGenres.clear();
            genreAdapter.notifyDataSetChanged();
            adapter.applyGenreFilter(new ArrayList<String>());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (adapter != null) {
            savedInstanceState.putBoolean("animateHeartsInwards", adapter.animateHeartsInwards);
        }

        if (genreCardView != null) {
            savedInstanceState.putBoolean("genresVisible", genreCardView.getVisibility() == View.VISIBLE);
        }

        if (genreAdapter != null) {
            boolean[] itemChecked = genreAdapter.getItemChecked();
            savedInstanceState.putBooleanArray("itemChecked", itemChecked);
        }

        savedInstanceState.putStringArrayList("selectedGenres", selectedGenres);
    }
}