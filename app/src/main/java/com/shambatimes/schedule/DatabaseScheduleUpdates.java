package com.shambatimes.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.shambatimes.Alarms.AlarmHelper;
import com.shambatimes.schedule.Settings.SettingsActivity;

import java.util.ArrayList;
import java.util.Iterator;

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
            } catch (Exception e) {
                Toast.makeText(context, "Error Loading 2016 Schedule", Toast.LENGTH_LONG).show();
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
            } catch (Exception e) {
                Toast.makeText(context, "Error Loading 2016 Schedule", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void scheduleUpdateOne2016(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_one_complete_2016")) {

            try {
                ArrayList<Artist> artists;

                String[] query5 = {"the noisy freaks", "3", "2016"};
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

                String[] query6 = {"isick", "2", "2016"};
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

                String[] query5 = {"justin martin", "1", "2016"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query5, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setEndTimeString("00:00");
                    artist.setEndPosition(26);

                    artist.save();
                }

                String[] query6 = {"alunageorge", "1", "2016"};
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

    public static void load2017Database(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("2017_loaded")) {
            ArtistGenerator artistGenerator = new ArtistGenerator(context);
            artistGenerator.get2017Artists();
            prefs.edit().putBoolean("2017_loaded", true).apply();
            prefs.edit().putBoolean("update_one_complete_2017", true).apply();
            prefs.edit().putBoolean("update_two_complete_2017", true).apply();
        }
    }

    public static void scheduleUpdateOne2017(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_one_complete_2017")) {


            try {
                ArrayList<Artist> artists;

                //Thursday Changes
                new Artist(2017, Constants.LIVINGROOM, 0, "16:30", "17:30", "Hoola", "disco house,dub,dancehall,hip hop").save();

                String[] query1 = {"meowmix", "0", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query1, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Meowmix ft. Sweets");
                    artist.save();
                }

                //Friday Changes

                new Artist(2017, Constants.LIVINGROOM, 1, "18:30", "19:00", "Hoola", "disco house,dub,dancehall,hip hop").save();
                new Artist(2017, Constants.GROVE, 1, "13:00", "14:00", "Opening Ceremonies w/ Soul Fire Dance", "").save();

                String[] query2 = {"val kilmer and the new coke", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query2, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setEndTimeString("20:30");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query3 = {"hoola", "1", "20:00", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and start_Time_string = ? and year = ?", query3, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("20:30");
                    artist.updateStartPosition();

                    artist.save();
                }

                String[] query4 = {"dj soup", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query4, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setEndTimeString("19:15");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query5 = {"rrotik", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query5, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("19:15");
                    artist.updateStartPosition();

                    artist.setEndTimeString("20:30");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query6 = {"chris lorenzo", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query6, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("20:30");
                    artist.updateStartPosition();

                    artist.setEndTimeString("22:00");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query7 = {"destructo", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query7, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.delete();
                }

                String[] query8 = {"justin martin", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query8, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("22:00");
                    artist.updateStartPosition();

                    artist.setEndTimeString("23:30");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query9 = {"chris lake", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query9, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("23:30");
                    artist.updateStartPosition();

                    artist.setEndTimeString("01:00");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query10 = {"black tiger sex machine", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query10, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("01:00");
                    artist.updateStartPosition();

                    artist.setEndTimeString("02:30");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query11 = {"ephwurd", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query11, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("02:30");
                    artist.updateStartPosition();

                    artist.setEndTimeString("04:00");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query12 = {"destructo sermon", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query12, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("04:00");
                    artist.updateStartPosition();

                    artist.setEndTimeString("08:00");
                    artist.updateEndPosition();

                    artist.setArtistName("Destructo");

                    artist.save();
                }

                String[] query18 = {"kytami", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query18, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Kytami w/Phonik Ops");
                    artist.save();
                }

                //Saturday Changes
                new Artist(2017, Constants.GROVE, 2, "22:00", "22:30", "Omnika", "theatrical dance and circus").save();

                String[] query19 = {"beats antique", "2", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query19, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Beats Antique ft David Satori & Sidecar Tommy");
                    artist.save();
                }

                //Sunday Changes
                new Artist(2017, Constants.CEDAR_LOUNGE, 3, "02:30", "04:00", "Sideshow", "").save();

                String[] query13 = {"footprints", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query13, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("21:00");
                    artist.updateStartPosition();

                    artist.setEndTimeString("22:00");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query14 = {"ihf", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query14, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("22:00");
                    artist.updateStartPosition();

                    artist.setEndTimeString("23:00");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query15 = {"circus acts insomniacs", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query15, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("23:00");
                    artist.updateStartPosition();

                    artist.setEndTimeString("23:30");
                    artist.updateEndPosition();

                    artist.save();
                }

                String[] query16 = {"z-trip", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query16, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    artist.setStartTimeString("23:30");
                    artist.updateStartPosition();

                    artist.save();
                }

                //Special case for Marten Hoger and Deekline which have been separated
                boolean alarm = false;
                boolean favourite = false;

                String[] query17 = {"marten hørger b2b deekline", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query17, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);

                    alarm = artist.isAlarmSet();
                    favourite = artist.isFavorite();

                    artist.delete();
                }

                Artist martenHoger = new Artist(2017, Constants.FOREST, 3, "01:30", "02:30", "Marten Hørger", "house,breaks,trap,bass");
                martenHoger.setFavorite(favourite);
                martenHoger.setIsAlarmSet(alarm);
                martenHoger.save();

                Artist deekline = new Artist(2017, Constants.FOREST, 3, "02:30", "03:30", "Deekline", "uk bass,breaks,drum & bass,jungle");
                deekline.setFavorite(favourite);
                deekline.setIsAlarmSet(alarm);
                deekline.save();

                prefs.edit().putBoolean("update_one_complete_2017", true).apply();

            } catch (Exception e) {
                Log.e("UpdateDatabase", "Error Updating", e);
            }
        }
    }

    public static void scheduleUpdateTwo2017(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.contains("update_two_complete_2017")) {

            try {
                ArrayList<Artist> artists;

                //Thursday New
                new Artist(2017, Constants.AMPHITHEATER, 0, "10:45", "11:45", "Hosted by ThinkTank & Def3", "").save();
                new Artist(2017, Constants.LIVINGROOM, 0, "10:30", "11:00", "Opening Ceremony with Joaqopelli", "").save();

                //Friday New
                new Artist(2017, Constants.AMPHITHEATER, 1, "11:30", "12:30", "Hosted by DEF3 & ThinkTank", "indie,blues,acoustic hip hop").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 1, "14:00", "15:30", "Levi Bannier Shamanic Yoga Flow", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 1, "15:30", "17:00", "Momentom Collective Art of Flight: Acro Yoga", "").save();

                //Saturday New
                new Artist(2017, Constants.AMPHITHEATER, 2, "12:30", "13:30", "Hosted by ThinkTank @ Def3", "").save();
                new Artist(2017, Constants.AMPHITHEATER, 2, "15:00", "16:00", "Blenda", "").save();
                new Artist(2017, Constants.LIVINGROOM, 2, "11:00", "12:00", "Classical Hour", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 2, "12:00", "13:00", "Tamara Dawn The Tantric Perspective on  Surya Namaskar", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 2, "13:00", "14:30", "Levi Banner Astrology Basics and Beyond", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 2, "14:30", "15:30", "Leiah Luz Engel Movement Exploration", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 2, "15:30", "17:30", "Momentom Collective Flow & Transition: Acro Yoga", "").save();

                //Sunday New
                new Artist(2017, Constants.AMPHITHEATER, 3, "12:00", "13:00", "Hosted by Wet Spot", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 3, "12:00", "13:30", "Tamara Dawn Traditional Tantric Yoga", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 3, "13:30", "15:00", "Levi Banner Shamanic Breath Work", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 3, "15:00", "16:30", "James Jesson Healing the Past", "").save();
                new Artist(2017, Constants.CEDAR_LOUNGE, 3, "16:30", "18:00", "Momentom Collective Thai & Fly Acro Yoga", "").save();
                new Artist(2017, Constants.GROVE, 3, "03:00", "04:30", "Mystery Guest ???", "").save();

                //Friday Changes

                String[] query1 = {"excision", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query1, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Excision Special 10th Anniversary Set");
                    artist.save();
                }

                String[] query2 = {"destructo", "1", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query2, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Destructo Sunrise Sermon");
                    artist.save();
                }

                //Saturday Changes

                String[] query3 = {"rhoneil", "2", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query3, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.delete();
                }

                String[] query4 = {"calyx & teebee", "2", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query4, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Calyx & Teebee w/ Armanni Reign");
                    artist.save();
                }

                String[] query8 = {"marty carter and russ", "2", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query8, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setDay(3);
                    artist.save();
                }

                //Sunday Changes

                String[] query5 = {"smalltown djs", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query5, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("Fractal Forest Funk Jam Hosted by Smalltown DJs");
                    artist.save();
                }

                String[] query6 = {"ltj bukem b2b armanni reign", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query6, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("LTJ Bukem w/ Armanni Reign");
                    artist.save();
                }

                String[] query7 = {"hoola", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query7, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setArtistName("The Pride Obscuri-Teaze Ft Hoola & Guests, closing with Joaqopelli at 11:30am");
                    artist.save();
                }

                String[] query10 = {"jfb", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query10, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setEndTimeString("00:00");
                    artist.updateEndPosition();
                    artist.save();
                }

                String[] query11 = {"stanton warriors", "3", "2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "lower(artist_Name) = ? and day = ? and year = ?", query11, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    Artist artist = artists.get(0);
                    artist.setStartTimeString("00:00");
                    artist.updateStartPosition();
                    artist.save();
                }

                //Update all the start/end positions for 2017 artists due to the shift from an 11am to 10am start.  UGH I can't wait to move to real dates.
                String[] query9 = {"2017"};
                artists = (ArrayList<Artist>) Artist.find(Artist.class, "year = ?", query9, null, "day ASC, start_Position ASC", null);
                if (artists.size() > 0) {
                    for (Artist artist : artists) {
                        artist.updateStartPosition();
                        artist.updateEndPosition();
                        artist.save();
                    }
                }

                prefs.edit().putBoolean("update_two_complete_2017", true).apply();

                AlarmHelper.recalculateAllAlarmTimes(context);

            } catch (Exception e) {
                Log.e("UpdateDatabase", "Error Updating", e);
            }
        }
    }
}
