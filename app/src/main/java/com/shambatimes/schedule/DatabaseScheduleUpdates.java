package com.shambatimes.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.shambatimes.schedule.Util.AlarmHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by babramovitch on 8/16/2015.
 */
public class DatabaseScheduleUpdates {

    public static void scheduleUpdateOne2015(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_one_complete")) {

            try {

                String[] query0 = {"JoaqoPelli", "0"};
                ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query0, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("JoaqoPelli (Flute Ceremony)");
                    artist.save();
                }

                String[] query1 = {"Hip Hop Showcase ft. Grouch & Eligh"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ?", query1, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("13:30");
                    artist.setStartPosition(5);
                    artist.save();
                }

                String[] query2 = {"Akimi"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ?", query2, null, "day ASC, start_Position ASC", null);

                if (artists.size() == 0) {
                    new Artist(2015, Constants.LIVINGROOM, 0, "04:30", "05:30", "Akimi", "").save();
                }

                String[] query3 = {"Jodie Bruce"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ?", query3, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setEndTimeString("15:30");
                    artist.setEndPosition(9);
                    artist.save();
                }

                String[] query4 = {"Gemma Luna"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ?", query4, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("15:30");
                    artist.setStartPosition(9);
                    artist.save();
                }

                String[] query5 = {"Tipper", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query5, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Tipper with Android Jones");
                    artist.save();
                }

                String[] query6 = {"AtYyA", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query6, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("05:00");
                    artist.setEndTimeString("06:30");
                    artist.setStartPosition(36);
                    artist.setEndPosition(39);
                    artist.save();
                }

                String[] query7 = {"Jafu", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query7, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("04:30");
                    artist.setEndTimeString("06:00");
                    artist.setStartPosition(33);
                    artist.setEndPosition(36);
                    artist.setDay(3);
                    artist.save();
                }

                String[] query8 = {"Synkro", "3"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query8, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("03:30");
                    artist.setEndTimeString("05:00");
                    artist.setStartPosition(33);
                    artist.setEndPosition(36);
                    artist.setDay(2);
                    artist.save();
                }

                String[] query9 = {"TBA", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query9, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("DJ Cure");
                    artist.setStartTimeString("04:30");
                    artist.setEndTimeString("06:00");
                    artist.setStartPosition(35);
                    artist.setEndPosition(38);
                    artist.save();
                }

                String[] query10 = {"Kry Wolf", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query10, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("00:00");
                    artist.setEndTimeString("01:30");
                    artist.setStartPosition(26);
                    artist.setEndPosition(29);
                    artist.save();
                }

                String[] query11 = {"Max Ulis", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query11, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("01:30");
                    artist.setEndTimeString("03:00");
                    artist.setStartPosition(29);
                    artist.setEndPosition(32);
                    artist.save();
                }

                String[] query12 = {"Petey Clicks", "2"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "artist_Name = ? and day = ?", query12, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("03:00");
                    artist.setEndTimeString("04:30");
                    artist.setStartPosition(32);
                    artist.setEndPosition(35);
                    artist.save();
                }

                prefs.edit().putBoolean("update_one_complete", true).apply();

            } catch (Exception e) {
                Log.e("UpdateDatabase", "Error Updating", e);
            }
        }
    }

    public static void scheduleUpdateTwo2015(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_two_complete")) {

            try {
                
                Iterator<Artist> artists = Artist.findAll(Artist.class);

                while (artists.hasNext()) {
                    Artist artist = artists.next();
                    if (artist.getYear() == 0) {
                        artist.setYear(2015);
                        artist.save();
                    }
                }

                prefs.edit().putBoolean("update_two_complete", true).apply();

            } catch (Exception e) {
                Log.e("UpdateDatabase", "Error Updating", e);
            }
        }
    }

    public static void load2016Database(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("2016_loaded")) {
            try {
                ArtistGenerator artistGenerator = new ArtistGenerator(context);
                artistGenerator.get2016Artists();
                artistGenerator.get2016CedarLoungeArtists();
                prefs.edit().putBoolean("2016_loaded", true).apply();
                prefs.edit().putBoolean("cedar_lounge_loaded_2016", true).apply();
                prefs.edit().putBoolean("update_one_complete_2016", true).apply();
                prefs.edit().putBoolean("update_two_complete_2016", true).apply();
            }catch(Exception e){
                Toast.makeText(context,"Error Loading 2016 Schedule", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void load2016CedarLounge(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("cedar_lounge_loaded_2016")) {
            try {
                ArtistGenerator artistGenerator = new ArtistGenerator(context);
                artistGenerator.get2016CedarLoungeArtists();
                prefs.edit().putBoolean("cedar_lounge_loaded_2016", true).apply();
            }catch(Exception e){
                Toast.makeText(context,"Error Loading 2016 Schedule", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void scheduleUpdateOne2016(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_one_complete_2016")) {

            try {
                ArrayList<Artist> artists;

                String[] query5 = {"the noisy freaks", "3","2016"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query5, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("SkiiTour");
                    artist.setGenres("Drum & bass,house,trap,twerk");
                    artist.setIsAlarmSet(false);
                    artist.setFavorite(false);
                    artist.save();

                    AlarmHelper.cancelAlarmIntent(context, artist);
                }

                String[] query6 = {"isick", "2","2016"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query6, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Kermode");
                    artist.setGenres("bass,downtempo,dubstep,electronic");
                    artist.setIsAlarmSet(false);
                    artist.setFavorite(false);
                    artist.save();

                    AlarmHelper.cancelAlarmIntent(context, artist);
                }

                prefs.edit().putBoolean("update_one_complete_2016", true).apply();

            } catch (Exception e) {
                Log.e("UpdateDatabase", "Error Updating", e);
            }
        }
    }

    public static void scheduleUpdateTwo2016(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_two_complete_2016")) {

            try {
                ArrayList<Artist> artists;

                String[] query5 = {"justin martin", "1","2016"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query5, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setEndTimeString("00:00");
                    artist.setEndPosition(26);

                    artist.save();
                }

                String[] query6 = {"alunageorge", "1","2016"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query6, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("00:00");
                    artist.setStartPosition(26);

                    artist.save();
                }

                prefs.edit().putBoolean("update_two_complete_2016", true).apply();

            } catch (Exception e) {
                Log.e("UpdateDatabase", "Error Updating", e);
            }
        }
    }
}
