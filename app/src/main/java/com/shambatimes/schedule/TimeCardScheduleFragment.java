package com.shambatimes.schedule;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.ShowHideAlarmSnackbarEvent;
import com.shambatimes.schedule.events.ToggleToStageEvent;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.greenrobot.event.EventBus;


public class TimeCardScheduleFragment extends Fragment {
    private static final String TIME_POSITION = "position";
    private static final String DATE_POSITION = "date";

    private int timePosition;

    private View rootView;

    private int date = 0;
    private BaseAdapter scheduleAdapter;
    private GridView gridView;

    AlarmHelper alarmHelper;

    static TimeCardScheduleFragment newInstance(int position, int currentDate) {
        TimeCardScheduleFragment frag = new TimeCardScheduleFragment();
        Bundle args = new Bundle();

        args.putInt(TIME_POSITION, position);
        args.putInt(DATE_POSITION, currentDate);

        frag.setArguments(args);

        return (frag);
    }


    static String getTitle(Context ctxt, int position) {
        return ("");
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.schedule_by_time_grid, container, false);
        alarmHelper = new AlarmHelper(getActivity(),rootView);

        timePosition = getArguments().getInt(TIME_POSITION, 0);
        date = getArguments().getInt(DATE_POSITION, 0);

        setHeaderTimes();
        setupGridView();

        return (rootView);
    }

    private void setHeaderTimes() {

        TextView editor = (TextView) rootView.findViewById(R.id.headline_time);

        DateTime startTime = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay().plusMinutes(Constants.REFERENCE_TIME);
        DateTime endTime;

        startTime = startTime.plusMinutes(30 * timePosition);
        endTime = startTime.plusMinutes(30);

        DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("HH:mm aa");

        editor.setText(dateStringFormat.print(startTime) + " to " + dateStringFormat.print(endTime));

    }

    private void setupGridView() {
        gridView = (GridView) rootView.findViewById(R.id.gridView);

        scheduleAdapter = new scheduleAdapter(getActivity());
        gridView.setAdapter(scheduleAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                //v.vibrate(10);

                LinearLayout childView = (LinearLayout) parent.getChildAt(position);
                CardView cardView = (CardView) childView.getChildAt(0);
                RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
                TextView artistNameTextView = (TextView) relativeLayout.getChildAt(0);

                EventBus.getDefault().post(new ToggleToStageEvent(position, artistNameTextView.getText().toString()));

                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout childView = (LinearLayout) parent.getChildAt(position);
                CardView cardView = (CardView) childView.getChildAt(0);
                RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
                TextView artistNameTextView = (TextView) relativeLayout.getChildAt(0);

                EventBus.getDefault().post(new ToggleToStageEvent(position, artistNameTextView.getText().toString()));

            }
        });
    }


    public class scheduleAdapter extends BaseAdapter {
        private Context mContext;
        private String[] stageNames = getActivity().getResources().getStringArray(R.array.stages);

        int[] stageColors = {R.color.pagoda_color,
                R.color.fractal_forest_color,
                R.color.grove_color,
                R.color.living_room_color,
                R.color.village_color,
                R.color.amphitheatre_color,
                R.color.fractal_forest_color};


        public scheduleAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return 7;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }


        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View gridView;

            gridView = inflater.inflate(R.layout.schedule_by_time_card, null);

            CardView card = (CardView) gridView.findViewById(R.id.time_card);
            TextView stageName = (TextView) gridView.findViewById(R.id.stage_text);
            TextView artistName = (TextView) gridView.findViewById(R.id.artist_text);
            TextView artistTime = (TextView) gridView.findViewById(R.id.time_text);
            //TextView artistGenres = (TextView) gridView.findViewById(R.id.genres_text);


            stageName.setText(stageNames[position]);
            card.setCardBackgroundColor(getResources().getColor(stageColors[position]));

            final ImageView image = (ImageView) gridView.findViewById(R.id.card_favorited);
            final Artist artist = MainActivity.shambhala.getArtistsByDayAndPositionAndStage(date, timePosition, position);



            if (artist != null) {
                artistName.setText(artist.getAristName());
                artistTime.setText(artist.getStartTimeString() + " - " + artist.getEndTimeString());
                //artistGenres.setText(artist.getGenres());

                if (artist.isFavorite()) {
                    image.setImageResource(R.drawable.favorite_white);
                } else {
                    image.setImageResource(R.drawable.favorite_outline_white);
                }

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (artist != null) {
                            if (artist.isFavorite()) {
                                image.setImageResource(R.drawable.favorite_outline_white);
                                artist.setFavorite(false);
                                artist.setIsAlarmSet(false);
                                artist.save();

                                alarmHelper.cancelAlarm(artist);
                                EventBus.getDefault().post(new ShowHideAlarmSnackbarEvent(null));

                            } else {
                                image.setImageResource(R.drawable.favorite_white);
                                artist.setFavorite(true);
                                artist.save();

                                EventBus.getDefault().post(new ShowHideAlarmSnackbarEvent(artist));

                            }
                        }
                        EventBus.getDefault().postSticky(new DataChangedEvent(true, artist.getId()));
                    }
                });
            } else {
                image.setImageResource(R.drawable.favorite_outline_white);
            }

            return gridView;
        }

    }

    public void onEventMainThread(ChangeDateEvent event) {

        if (event.getPosition() != date) {

            //Log.i("TAG", "Change Date Position WAS " + timePosition);

            if (date == 3 && event.getPosition() != 3) {
                timePosition = timePosition + 2;
            } else if (date != 3 && event.getPosition() == 3) {
                timePosition = timePosition - 2;
            }

            if (timePosition < 0) {
                timePosition = 0;
            }

           // Log.i("TAG", "Change Date Position Now " + timePosition);

            setHeaderTimes();

            date = event.getPosition();

            scheduleAdapter.notifyDataSetChanged();

        }

    }

    public void onEventMainThread(DataChangedEvent event) {

        EventBus.getDefault().removeStickyEvent(event);

        if (event.isChanged()) {
            MainActivity.shambhala.updateArtistById(event.getArtistId());
            scheduleAdapter = new scheduleAdapter(getActivity());
            gridView.setAdapter(scheduleAdapter);
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
