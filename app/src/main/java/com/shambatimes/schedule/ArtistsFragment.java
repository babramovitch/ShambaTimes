package com.shambatimes.schedule;

import android.app.PendingIntent;
import android.content.Context;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
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
    private ListView listview;
    private PendingIntent pendingIntent;
    AlarmHelper alarmHelper;

    CardView genreCardView;

    LinearLayoutManager llm;
    private View layout;

    static ArrayList<String> selectedGenres = new ArrayList<>();
    GenreAdapterA genreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        layout = inflater.inflate(R.layout.recycler_schedule_artists, container, false);
        alarmHelper = new AlarmHelper(getActivity(), layout);



        setupGenres();
        setupRecyclerView();

        return (layout);
    }

    private void setupRecyclerView(){
        recyclerView = (RecyclerView) layout.findViewById(R.id.listView_schedule);

        fastScroller = (VerticalRecyclerViewFastScroller) layout.findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);
        fastScroller.setBarColor(getResources().getColor(R.color.pagoda_color));
        recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());

        recyclerView.setHasFixedSize(true);

        llm = new LinearLayoutManager(getActivity());
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
        //ItemFilter mFilter = new ItemFilter();

        int editTextWidth;

        int[] favoriteDrawables = ColorUtil.getStageFavoriteDrawables();
        int[] favoriteOutlineDrawables = ColorUtil.getStageFavoriteOutlineDrawables();
        int screenWidth;

        public ArtistRecyclerAdapter(ArrayList<Artist> artistList) {
            this.artistList = artistList;
        }

        public void animateHeartsToLeft() {
            animateHeartsInwards = true;
            screenWidth = getResources().getDisplayMetrics().widthPixels;
            notifyDataSetChanged();
        }

        public void animateHeartsToRight() {
            animateHeartsInwards = false;
            screenWidth = getResources().getDisplayMetrics().widthPixels;
            notifyDataSetChanged();
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

            String formattedGenres = artist.getGenres().replace(",", ", ");

            artistViewHolder.artistName.setText(artist.getAristName());
            artistViewHolder.artistDay.setText(dayOfWeek[artist.getDay()]);
            artistViewHolder.genres.setText(formattedGenres);
            artistViewHolder.artistTime.setText(artist.getStartTimeString() + " to " + artist.getEndTimeString());
            artistViewHolder.artistStartTimePosition.setText("" + artist.getStartPosition());
            artistViewHolder.artistStage.setText(stageNames[artist.getStage()]);
            artistViewHolder.divider.setBackground(new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors));

            setupFavorites(artistViewHolder,artist);
            setupGenreAnimations(artistViewHolder, i);

        }

        private void setupFavorites(final ArtistViewHolder artistViewHolder, final Artist artist){
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
                                                      }
            );
        }

        private void setupGenreAnimations( ArtistViewHolder artistViewHolder, int position){

            int lastVisiblePosition = llm.findLastVisibleItemPosition() - 1;
            int firstVisiblePosition = llm.findFirstVisibleItemPosition() + 1;

            if (editTextWidth == 0 && artistViewHolder.artistStage.getWidth() != 0) {
                editTextWidth = artistViewHolder.artistStage.getWidth();
            }

            if (animateHeartsInwards) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(editTextWidth - 120, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.LEFT_OF, R.id.list_favorited);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.BELOW, R.id.separator);
                artistViewHolder.artistName.setLayoutParams(params);
                artistViewHolder.artistName.setPadding((int) Util.convertDpToPixel(15, getActivity()), 5, 0, 0);

                if (position >= firstVisiblePosition || position <= lastVisiblePosition) {
                    artistViewHolder.image.animate()
                            .setDuration(Constants.ANIMATION_DURATION)
                            .translationX(Util.convertDpToPixel(-115, getActivity()))
                            .translationY(Util.convertDpToPixel(30, getActivity()));
                } else {
                    artistViewHolder.image.setTranslationX(Util.convertDpToPixel(-115, getActivity()));
                    artistViewHolder.image.setTranslationY(Util.convertDpToPixel(40, getActivity()));
                }

            } else {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.LEFT_OF, R.id.list_favorited);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.BELOW, R.id.separator);
                artistViewHolder.artistName.setLayoutParams(params);
                artistViewHolder.artistName.setPadding((int) Util.convertDpToPixel(15, getActivity()), 5, 0, 0);

                if (llm.findLastVisibleItemPosition() <= position || llm.findFirstCompletelyVisibleItemPosition() <= position) {
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

    public void setupGenres() {

        //Immediately move the genreCardView off the screen, so we can animate it into space when clicked.
        genreCardView = (CardView) layout.findViewById(R.id.genreCard);
        genreCardView.setTranslationX(Util.convertDpToPixel(120, getActivity()));

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

            genreAdapter = new GenreAdapterA(getActivity(), genres);

            listview = (ListView) layout.findViewById(R.id.genrelist);
            listview.setAdapter(genreAdapter);
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

    public class GenreAdapterA extends BaseAdapter {

        ArrayList<String> genreList = new ArrayList<>();
        LayoutInflater inflater;
        Context context;
        boolean[] itemChecked;


        public GenreAdapterA(Context context, ArrayList<String> genreList) {
            this.genreList = genreList;
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            itemChecked = new boolean[genreList.size()];
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
            MyViewHolder mViewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.genre_list_item, null);
                mViewHolder = new MyViewHolder();
                convertView.setTag(mViewHolder);
                mViewHolder.genrebox = (CheckBox) convertView.findViewById(R.id.genrebox);
                mViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
            }

            mViewHolder.genrebox.setHighlightColor(colors[1]);
            mViewHolder.textView.setText(genreList.get(position));

            if (itemChecked[position]) {
                mViewHolder.genrebox.setChecked(true);
            } else {
                mViewHolder.genrebox.setChecked(false);
            }

            mViewHolder.genrebox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox view = (CheckBox) v;
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
            });

            return convertView;
        }


        private class MyViewHolder {
            CheckBox genrebox;
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

    public void showGenres() {
        if (genreCardView.getVisibility() == View.GONE) {
            genreCardView.setVisibility(View.VISIBLE);
            genreCardView.animate().setDuration(Constants.ANIMATION_DURATION).translationX(Util.convertDpToPixel(0, getActivity()));
            adapter.animateHeartsToLeft();

        } else {
            adapter.animateHeartsToRight();
            genreCardView.animate().setDuration(400).translationX(Util.convertDpToPixel(120, getActivity())).withEndAction(new Runnable() {
                @Override
                public void run() {
                    //After the animation duration delete the stock
                    genreCardView.setVisibility(View.GONE);
                }
            });

            genreAdapter.clearSelectedGenres();
            selectedGenres.clear();
            genreAdapter.notifyDataSetChanged();
            adapter.applyGenreFilter(new ArrayList<String>());
        }
    }
}