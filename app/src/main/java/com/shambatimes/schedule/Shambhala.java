package com.shambatimes.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.shambatimes.schedule.Settings.SettingsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Ben on 14/02/2015.
 */
public class Shambhala {

    public final static String CURRENT_YEAR = "2016";

    ArrayList<Artist> artists = new ArrayList<Artist>();
    private final String TAG = "Shambhala";

    public Shambhala() {
    }


    public Artist getArtistsByDayAndPositionAndStage(int day, int position, int stage) {

        for (Artist currentArtist : artists) {

            int endPosition = currentArtist.getEndPosition() == 0 ? 48 : currentArtist.getEndPosition(); //Start 11AM - 11AM = 0, so we need to manually set the last position

            if (currentArtist.getDay() == day && position >= currentArtist.getStartPosition() && position < endPosition) {
                if (stage == currentArtist.getStage()) {
                    return currentArtist;
                }
            }
        }
        return null;
    }

    public ArrayList<Artist> getArtistsByDayAndStage(int day, int stage) {

        ArrayList<Artist> artistsByDay = new ArrayList<>();

        for (Artist currentArtist : artists) {
            if (currentArtist.getDay() == day && currentArtist.getStage() == stage) {
                artistsByDay.add(currentArtist);
            }
        }

        Collections.sort(artistsByDay, new Comparator<Artist>() {
            @Override
            public int compare(Artist lhs, Artist rhs) {
                int o1 = rhs.getStartPosition();
                int o2 = lhs.getStartPosition();
                return (o1 > o2 ? -1 : (o1 == o2 ? 0 : 1));
            }
        });

        return artistsByDay;
    }

    public ArrayList<Artist> getArtistsByDay(int day) {

        ArrayList<Artist> artistsByDay = new ArrayList<>();

        for (Artist currentArtist : artists) {
            if (currentArtist.getDay() == day) {
                artistsByDay.add(currentArtist);
            }
        }

        return artistsByDay;
    }


//    public void updateArtist(long id){
//        for(Artist currentArtist : artists){
//            if(currentArtist.getId() == id){
//                currentArtist = Artist.findById(Artist.class, id);
//                Log.i(TAG, "Artist " + currentArtist.getAristName() + " updated to favorite is" + currentArtist.isFavorite());
//            }
//        }
//    }

    public boolean updateArtistById(long id) {
        for (int x = 0; x < artists.size(); x++) {
            if (artists.get(x).getId() == id) {
                artists.set(x, Artist.findById(Artist.class, id));
                return true;
            }
        }
        return false;
    }

    public void addArtistAtPosition(int position, long id) {
        artists.add(position, Artist.findById(Artist.class, id));
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<Artist> loadAllArtistsForYear(String year) {
        String[] query1 = {"" + year};
        ArrayList<Artist> artistList = (ArrayList<Artist>) Artist.find(Artist.class, "year = ?", query1, null, "lower(artist_Name) asc", null);
        return artistList;
    }

    public ArrayList<Artist> loadAllArtistsForYearAndDay(String year, String day) {
        String[] query1 = {year, day};
        ArrayList<Artist> artistList = (ArrayList<Artist>) Artist.find(Artist.class, "year = ? and day = ?", query1, null, "lower(artist_Name) asc", null);
        return artistList;
    }

    public static String getFestivalYear(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(SettingsActivity.FESTIVAL_YEAR, "2016");
    }


    public ArrayList<String> generateGenreList() {

        ArrayList<String> genreList = new ArrayList<>();

        for (Artist artist : artists) {
            String[] genres = artist.getGenres().toLowerCase().split(",");
            for (String genre : genres) {
                genre = genre.trim();
                if (!genre.equals("") && !genreList.contains(genre)) {
                    genreList.add(genre);
                }
            }
        }
        
        Collections.sort(genreList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        return genreList;
    }
}
