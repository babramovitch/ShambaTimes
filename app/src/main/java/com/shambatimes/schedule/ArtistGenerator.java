package com.shambatimes.schedule;

import android.content.Context;

import java.util.ArrayList;

import static com.shambatimes.schedule.Constants.AMPHITHEATER;
import static com.shambatimes.schedule.Constants.CEDAR_LOUNGE;
import static com.shambatimes.schedule.Constants.FOREST;
import static com.shambatimes.schedule.Constants.GROVE;
import static com.shambatimes.schedule.Constants.LIVINGROOM;
import static com.shambatimes.schedule.Constants.PAGODA;
import static com.shambatimes.schedule.Constants.VILLAGE;

/**
 * Created by Ben on 14/02/2015.
 */

public class ArtistGenerator {

    private Context context;

    public ArtistGenerator(Context context) {

        this.context = context;

    }

    public ArrayList<Artist> get2015Artists() {

        ArrayList<Artist> artistList = new ArrayList<>();

        //Living Room
        artistList.add(new Artist(2015, LIVINGROOM, 0, "11:11", "12:00", "JoaqoPelli (Flute Ceremony)", "house,dubstep"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "12:00", "13:00", "Select-R-Us", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "13:00", "14:00", "Shasta", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "14:00", "15:00", "Cass Rhapsody", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "15:00", "16:00", "Ginger", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "16:00", "17:30", "Mich Duvernet", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "17:30", "19:00", "Hoola", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "19:00", "20:30", "Val Kilmer & The New Coke", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "20:30", "21:00", "Hoola (Band Changeover)", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "21:00", "22:00", "Sack Grabbath", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "22:00", "23:30", "Vinyl Ritchie", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "23:30", "00:30", "Dubconscious", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "00:30", "01:30", "Sam Demoe", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "01:30", "03:00", "Spiltmilk", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "03:00", "04:30", "Leif", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 0, "04:30", "05:30", "Akimi", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, LIVINGROOM, 1, "11:30", "13:00", "Foxy Moron", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "13:00", "14:30", "The Gaff", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "14:30", "16:00", "DJ K-Tel", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "16:00", "17:00", "Slohand", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "17:00", "18:30", "Sugarbear", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "18:30", "20:00", "Koosh", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "20:00", "00:00", "DJ Harvey", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "00:00", "01:30", "Matthew1626 & Bongofide", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "01:30", "03:00", "Mr. Moe", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "03:00", "04:30", "Lorne B", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "04:30", "06:00", "Breakfluid", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 1, "06:00", "08:00", "Andrew Interchill", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, LIVINGROOM, 2, "11:00", "12:00", "Marty Carter", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "12:00", "13:00", "Mama Sa", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "13:00", "14:00", "45 Seasons", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "14:00", "15:30", "Big Toes Hi-Fi", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "15:30", "17:30", "Rob Paine", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "17:30", "19:00", "BC Dubcats", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "19:00", "20:00", "Meow Mix", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "20:00", "21:00", "Spiralynn & Dislocait", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "21:00", "22:30", "Czech", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "22:30", "00:00", "Schmaltzy", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "00:00", "02:00", "Thornato", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "02:00", "04:00", "El Buho", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "04:00", "05:00", "Bassos Rancheros", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "05:00", "06:00", "The Tailor", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 2, "06:00", "07:00", "Gemma Luna", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, LIVINGROOM, 3, "12:00", "13:00", "Erica Dee w/ Aisha Rose", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "13:00", "14:00", "S2", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "14:00", "15:30", "Emma Star", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "15:30", "16:30", "Boomtown", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "16:30", "18:30", "Adham Shaikh", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "18:30", "19:30", "Barisone", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "19:30", "20:30", "Tiger Fresh", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "20:30", "21:30", "Tipper", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "21:30", "22:30", "Coyote Kisses", "neon nature"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "22:30", "23:30", "HAANA", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "23:30", "01:30", "El Papachango", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "01:30", "03:30", "Sweet Anomaly", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "03:30", "04:30", "Natural Alchemy", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "04:30", "06:00", "Stage Freight", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "06:00", "07:30", "Sijay and Syrinx", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "07:30", "11:00", "Lion-S", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, LIVINGROOM, 3, "11:00", "12:00", "JoaqoPelli (Flute Ceremony)", "house,electro,glitch hop"));

        //Amphitheater
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "12:00", "13:30", "Bryx", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "13:30", "15:00", "Timothy Wisdom", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "15:00", "16:30", "Skiitour", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "16:30", "18:00", "jOBOT", "bass,dubstep,glitch hop,trap"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "18:00", "19:30", "MORiLLO", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "19:30", "21:00", "Space Jesus", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "21:00", "22:30", "Self Evident", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "22:30", "00:00", "Bleep Bloop", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "00:00", "01:30", "G Jones", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "01:30", "03:00", "Ana Sia", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "03:00", "04:30", "David Starfire", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 0, "04:30", "05:30", "Greazus", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, AMPHITHEATER, 1, "15:00", "15:30", "Jodie Bruce", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "15:30", "16:00", "Gemma Luna", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "16:00", "16:30", "Daniel Huscroft", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "16:30", "18:00", "Fedski", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "18:00", "19:30", "Perkulat0r", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "19:30", "21:00", "Ekali", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "21:00", "22:30", "Andrea", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "22:30", "00:00", "Ganz", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "00:00", "01:30", "Mr. Carmack", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "01:30", "03:00", "B-Ju", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "03:00", "04:30", "Yan Zombie w/ Zes Nomis", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 1, "04:30", "06:00", "Eviction", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, AMPHITHEATER, 2, "14:00", "14:30", "Sean Rodman", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "14:30", "15:00", "Kristie McCracken", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "15:00", "15:30", "Marin Patenaude", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "15:30", "16:30", "Fashion Show", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "16:30", "18:00", "Howl Sound", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "18:00", "19:30", "Cal Bass", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "19:30", "21:00", "Ryan Wells", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "21:00", "22:30", "Sabota", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "22:30", "00:00", "Worthy", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "00:00", "01:30", "Kry Wolf", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "01:30", "03:00", "Max Ulis", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "03:00", "04:30", "Petey Clicks", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 2, "04:30", "06:00", "DJ Cure", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, AMPHITHEATER, 3, "15:00", "15:30", "Jenny Lea", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "15:30", "16:00", "Max Hawk", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "16:00", "16:30", "Windmills", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "16:30", "18:00", "Eli Muro", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "18:00", "19:30", "M!nt", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "19:30", "21:00", "Sugarpill", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "21:00", "22:20", "Huglife (9-10:20PM)", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "22:20", "22:30", "Subscura (10:20-10:30PM)", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "22:30", "00:00", "Jackal", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "00:00", "01:30", "Crnkn", "bass,trap"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "01:30", "03:00", "heRobust", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "03:00", "04:30", "Longwalkshortdock", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, AMPHITHEATER, 3, "04:30", "06:00", "Metafloor", "house,electro,glitch hop"));

        //Pagoda
        artistList.add(new Artist(2015, PAGODA, 1, "15:00", "16:00", "Logan Hart", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "16:00", "17:00", "Wallis", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "17:00", "18:00", "Ardalan (Dirtybird Takeover Begins!)", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "18:00", "19:20", "Friend Within", "house,techno"));
        artistList.add(new Artist(2015, PAGODA, 1, "19:20", "20:40", "Cause & Affect", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "20:40", "22:00", "Kill Frenzy", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "22:00", "23:20", "J Phlip", "house,techno"));
        artistList.add(new Artist(2015, PAGODA, 1, "23:20", "00:40", "Shiba San", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "00:40", "02:00", "Justin Martin", "house,techno"));
        artistList.add(new Artist(2015, PAGODA, 1, "02:00", "03:20", "Claude VonStroke", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 1, "03:20", "04:40", "Dusky", "deep house,house"));
        artistList.add(new Artist(2015, PAGODA, 1, "04:40", "06:00", "Christian Martin", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, PAGODA, 2, "16:00", "17:00", "I-Sick", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "17:00", "18:00", "DJ Just-B", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "18:00", "19:00", "Freebound", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "19:00", "20:00", "Neighbour", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "20:00", "21:30", "jackLNDN", "house"));
        artistList.add(new Artist(2015, PAGODA, 2, "21:30", "22:30", "Kidnap Kid", "electronic,house"));
        artistList.add(new Artist(2015, PAGODA, 2, "22:30", "23:30", "Destructo", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "23:30", "01:00", "MK", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "01:00", "02:30", "GRiZ", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "02:30", "04:00", "Mystery Headliner", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "04:00", "05:00", "Mija", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 2, "05:00", "06:30", "Whipped Cream", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, PAGODA, 3, "16:30", "17:30", "De Block", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "17:30", "18:30", "Wax Romeo", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "18:30", "20:00", "DJ Soup", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "20:00", "21:30", "MÖWE", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "21:30", "23:00", "Bakermat", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "23:00", "00:30", "Thomas Jack", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "00:30", "02:00", "Kygo", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, PAGODA, 3, "02:00", "03:30", "Pretty Lights", "electro,hip hop,soul"));
        artistList.add(new Artist(2015, PAGODA, 3, "03:30", "04:00", "Mat The Alien & The Librarian (3:30-5am)", "space music"));
        artistList.add(new Artist(2015, PAGODA, 3, "04:00", "05:00", "Mat The Alien & The Librarian (3:30-5am)", "space music"));
        artistList.add(new Artist(2015, PAGODA, 3, "05:00", "06:30", "Capn Fuzz & Ajax", "house,electro,glitch hop"));

        //FOREST

        artistList.add(new Artist(2015, FOREST, 1, "12:00", "13:00", "Dilligent", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "13:00", "14:00", "Wildout", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "14:00", "15:00", "Lefy", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "15:00", "16:30", "JGirl & Manousos", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "16:30", "18:00", "Justin Hale", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "18:00", "19:00", "Dan Solo", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "19:00", "20:30", "Vinyl Ritchie", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "20:30", "22:00", "Slynk", "breaks,drum & bass,ghetto funk,hip hop"));
        artistList.add(new Artist(2015, FOREST, 1, "22:00", "23:30", "Fort Knox Five", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "23:30", "01:00", "The Funk Hunters", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "01:00", "02:30", "Stickybuds", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "02:30", "04:00", "Smalltown DJs", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "04:00", "05:30", "K+Lab", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "05:30", "07:00", "Freddy J", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 1, "07:00", "08:30", "Joseph Martin", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, FOREST, 2, "16:00", "18:00", "Wood N Soo", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "18:00", "19:30", "Cosmo Baker", "dance,funk,hip hop"));
        artistList.add(new Artist(2015, FOREST, 2, "19:30", "21:00", "Skratch Bastid", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "21:00", "22:30", "DJ Jazzy Jeff", "hip hop,turntablism"));
        artistList.add(new Artist(2015, FOREST, 2, "22:30", "23:30", "Mix Master Mike", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "23:30", "01:00", "A. Skillz", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "01:00", "02:30", "DJ Zinc", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "02:30", "04:00", "Jesse Rose", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "04:00", "05:30", "Dubtribe Sound System", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 2, "05:30", "07:00", "Neighbour", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, FOREST, 3, "14:00", "19:00", "Fractal Funk Jam - Hosted by Smalltown DJs", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "19:00", "20:30", "Beat Fatigue", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "20:30", "22:00", "Jillionaire of Major Lazer", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "22:00", "23:30", "Neon Steve", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "23:30", "01:00", "Jack Beats", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "01:00", "02:30", "Jauz", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "02:30", "04:00", "AC Slater", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, FOREST, 3, "04:00", "05:30", "Amtrac", "house"));
        artistList.add(new Artist(2015, FOREST, 3, "05:30", "10:00", "Rich-e-Rich", "house,electro,glitch hop"));

        //GROVE

        artistList.add(new Artist(2015, GROVE, 1, "13:00", "14:00", "Opening Ceremony w/ Soul Fire Dance", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "14:00", "15:30", "Adham Shaikh", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "15:30", "17:00", "Intersect", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "17:00", "18:00", "Janover and reSunator", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "18:00", "19:00", "Dakini", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "19:00", "20:00", "Goopsteppa", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "20:00", "21:00", "Westerley", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "21:00", "22:15", "The Librarian", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "22:15", "22:30", "Subscura", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "22:30", "23:45", "Daega Sound", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "23:45", "01:00", "Geode", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "01:00", "02:30", "Dimond Saints", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "02:30", "04:00", "Liquid Stranger", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "04:00", "05:15", "Naasko", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 1, "05:15", "06:30", "Griff", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, GROVE, 2, "16:30", "17:30", "Das Booty", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "17:30", "18:30", "J.Jonah", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "18:30", "19:30", "Shiny Things", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "19:30", "20:30", "Organic Mechanic", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "20:30", "21:30", "Octaban", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "21:30", "22:00", "Omnika", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "22:00", "23:15", "Jpod the beat chef", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "23:15", "00:30", "Big Wild", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "00:30", "01:00", "Phadroid", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "01:00", "02:00", "Tipper with Android Jones", "breaks,downtempo,trip hop"));
        artistList.add(new Artist(2015, GROVE, 2, "02:00", "03:30", "Leon Switch", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "03:30", "05:00", "Synkro", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 2, "05:00", "06:30", "AtYyA", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, GROVE, 3, "16:30", "17:30", "Willa", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "17:30", "18:30", "Mr. Diggler", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "18:30", "19:30", "S. Holmes", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "19:30", "20:45", "Footprints", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "20:45", "22:00", "Moontricks", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "22:00", "23:30", "Rising Appalachia", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "23:30", "01:30", "Bonobo (DJ Set)", "downtempo,house,trip hop"));
        artistList.add(new Artist(2015, GROVE, 3, "01:30", "03:00", "Birds Of Paradise", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "03:00", "04:30", "Biome", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "04:30", "06:00", "Jafu", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, GROVE, 3, "06:00", "07:00", "Austero", "house,electro,glitch hop"));

        //VILLAGE
        artistList.add(new Artist(2015, VILLAGE, 1, "14:00", "17:30", "Ragga Jungle Rinse Out - Hosted by Hush", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "17:30", "18:00", "Marcus Visionary", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "18:00", "19:00", "Liondub", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "19:00", "20:00", "Dua & Generic", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "20:00", "21:00", "Stylust Beats", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "21:00", "22:00", "ill.Gates", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "22:00", "23:00", "Subvert", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "23:00", "00:30", "Datsik", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "00:30", "02:00", "Skrillex", "bass,dubstep"));
        artistList.add(new Artist(2015, VILLAGE, 1, "02:00", "03:00", "Downlink", "bass,dubstep,trap"));
        artistList.add(new Artist(2015, VILLAGE, 1, "03:00", "04:30", "Drumsound & Bassline Smith", "drum & bass"));
        artistList.add(new Artist(2015, VILLAGE, 1, "04:30", "05:30", "Openend", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 1, "05:30", "06:30", "Mr. B", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, VILLAGE, 2, "13:30", "19:00", "Hip Hop Showcase ft. Grouch & Eligh", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "19:00", "20:00", "Kursa & Fat Pat", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "20:00", "21:00", "DJ Anger", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "21:00", "22:00", "Manic Focus", "electronic,soul"));
        artistList.add(new Artist(2015, VILLAGE, 2, "22:00", "23:00", "Etc!Etc!", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "23:00", "00:00", "Koan Sound", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "00:00", "01:30", "Terravita", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "01:30", "03:00", "Excision", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "03:00", "04:30", "Tantrum Desire", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 2, "04:30", "07:00", "Liquid Sunrise Jam", "house,electro,glitch hop"));

        artistList.add(new Artist(2015, VILLAGE, 3, "16:30", "17:00", "Kris Cayden (WAVO Remix Contest Winner)", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "17:00", "18:00", "Tiny Dancer & kAtO", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "18:00", "19:00", "Big B", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "19:00", "20:00", "Wakcutt", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "20:00", "21:00", "ill-esha", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "21:00", "22:00", "Haywyre", "electronic"));
        artistList.add(new Artist(2015, VILLAGE, 3, "22:00", "23:00", "Lucent Dossier Experience", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "23:00", "00:00", "Big Gigantic", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "00:00", "01:30", "Camo & Krooked", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "01:30", "03:00", "Zomboy", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "03:00", "04:30", "GTA", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "04:30", "05:30", "Spiral Architects", "house,electro,glitch hop"));
        artistList.add(new Artist(2015, VILLAGE, 3, "05:30", "06:30", "Scottie D", "house,electro,glitch hop"));

        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }

    public ArrayList<Artist> get2016Artists() {

        ArrayList<Artist> artistList = new ArrayList<>();

        //Living Room
        artistList.add(new Artist(2016, LIVINGROOM, 0, "11:00", "12:00", "Classical Hour", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "12:00", "13:30", "Yoga of Bass with FreQ Nasty & Claire Thompson", "Bass,bass yoga,meditation"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "13:30", "15:00", "Hoola", "Disco Dancing"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "15:00", "16:30", "Footprints", "vibes & stuff"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "16:30", "17:30", "WAVS (VS. + Wakcutt ft. Lyndi Lush)", "hip hop,live"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "17:30", "18:30", "Yan Zombie", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "18:30", "20:30", "Adept", "hip hop,turntablism"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "20:30", "22:00", "Morninglory", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "22:00", "23:00", "DJ Just-B", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "23:00", "00:00", "Justin Pleasure", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "00:00", "01:00", "Breakfluid", "breaks,dancehall,dub,hip hop,house,rare groove,reggae,soul"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "01:00", "02:30", "An-ten-nae", "acid crunk"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "02:30", "03:30", "Jorma", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "03:30", "04:30", "Braden Early", "House,Melodic"));
        artistList.add(new Artist(2016, LIVINGROOM, 0, "04:30", "05:30", "Kimmy K", ""));

        artistList.add(new Artist(2016, LIVINGROOM, 1, "11:00", "12:00", "SubPac Sound Healing w/ FreQ Nasty", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "12:00", "13:00", "Mooves", "footwork,future bass,jungle,soul"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "13:00", "14:30", "Foxy Moron", "45s!,hi-fi,low-tech,mid-thigh"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "14:30", "16:30", "Fort Knox Five ft. Qdup", "breaks,funk"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "16:30", "18:00", "Czech", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "18:00", "19:00", "Mandai", "reggae,dancehall"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "19:00", "20:30", "Riddim Fernandez", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "20:30", "22:00", "Only Now (aka Kush Arora)", "experimental,industrial,kuduro"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "22:00", "23:30", "Dirtwire", "Space cowboy swamptronica"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "23:30", "01:00", "Sweet Anomaly", "bass,downtempo,electro,global bass,techno"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "01:00", "02:30", "Tyler Stadius", "House,Tech House,Techno"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "02:30", "04:00", "Ryan Wells", "house,techno"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "04:00", "05:30", "Leif", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "05:30", "07:00", "Andy Soloman", "Afro,ballroom,disco,eclectic,house"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "07:00", "08:00", "Joshua James", "Polygenres"));
        artistList.add(new Artist(2016, LIVINGROOM, 1, "08:00", "09:00", "Matt Robertson", "Ambient Electronic"));

        artistList.add(new Artist(2016, LIVINGROOM, 2, "11:30", "12:30", "Hoola Hoop Workshop with FunkConscious", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "12:30", "13:30", "Marty Carter w/ Russ", "Folk Fusion,World"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "13:30", "14:30", "Russ-2-Fari", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "14:30", "15:30", "Jittabug", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "15:30", "16:30", "Kermode", "bass,downtempo,dubstep,electronic"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "16:30", "18:00", "Val Kilmer & The New Coke", "80's Party"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "18:00", "19:00", "Cass Rhapsody", "Beach,Jams,tropical"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "19:00", "20:00", "Shasta", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "20:00", "21:30", "Dubconcious", "bass,dancehall,dubstep,garage,house"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "21:30", "23:00", "Branko", "bass,carnival,tropical"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "23:00", "00:30", "The Librarian", "bass"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "00:30", "02:00", "DJ Laura Low", "bass"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "02:00", "04:00", "Lion-S", "bass,Downtempo"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "04:00", "05:30", "Leeland Riivr", "intelligent bass"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "05:30", "07:00", "Michael Red", "bass,live PA"));
        artistList.add(new Artist(2016, LIVINGROOM, 2, "07:00", "08:30", "Sijay James", "bass"));

        artistList.add(new Artist(2016, LIVINGROOM, 3, "11:00", "12:00", "Hoola Hoop Workshop with Dubconscious", "bass,dancehall,dubstep,garage,house"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "12:00", "13:00", "Infinati", "downtempo,psychedelic"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "13:00", "14:00", "Intersect", "bass,future bass"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "14:00", "16:00", "Adham Shaikh", "global bass"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "16:00", "19:00", "Nightmares on Wax", "electronic,experimental,hip hop"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "19:00", "20:00", "Sugarbear", "party music"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "20:00", "21:00", "Inncouragable", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "21:00", "22:30", "Lady V", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "22:30", "00:00", "Mich Duvernet", "old-school,electro,chunky house"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "00:00", "02:30", "Meow Mix", "bass,downtempo,house"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "02:30", "04:30", "El Papachango", "bass,electronica,hip hop,tropical"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "04:30", "08:00", "Sabo", "cumbia,disco,dub,house,latin,moombahton,regga,soul,tropical"));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "08:00", "10:00", "The Pride", ""));
        artistList.add(new Artist(2016, LIVINGROOM, 3, "10:00", "11:30", "Gemma Luna", "singer,songwriter"));

        //Amphitheater
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "11:30", "12:00", "Hosted by Def3 & ThinkTank", ""));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "12:00", "13:30", "Zeke Beats", ""));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "13:30", "14:30", "Stylust Beats", "bass,dubstep,good music,hip hop,trap"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "14:30", "15:30", "Pigeon Hole", "future bass,hip hop,trap"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "15:30", "17:00", "Skiitour", "Drum & bass,house,trap,twerk"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "17:00", "18:30", "Fort Knox Five ft. Qdup", "breaks,funk"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "18:30", "20:00", "DJ Soup", "disco,house"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "20:00", "21:00", "Tennyson", "Electronic"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "21:00", "22:30", "S2", "Emo Bap,Psy Jersey"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "22:30", "00:00", "Space Jesus", "bass"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "00:00", "01:30", "Dabow", "hip hop,trap"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "01:30", "03:00", "El Papachango", "bass,electronica,hip hop,tropical"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "03:00", "04:30", "Woodhead", "Disco,House"));
        artistList.add(new Artist(2016, AMPHITHEATER, 0, "04:30", "06:00", "Lorne B", "house,techno"));

        artistList.add(new Artist(2016, AMPHITHEATER, 1, "15:30", "17:00", "Barlee", "gangster bass,hip hop"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "17:00", "18:30", "WLYN", "Electronic"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "18:30", "20:00", "Perkulat0r", "West Coast Future Bass,bass"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "20:00", "21:00", "Ramzoid", "bass"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "21:00", "22:30", "Josh Pan", "beat scene,electronic,hybrid,trap"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "22:30", "00:00", "Sam Gellaitry", "Electronic,Experimental,Hip Hop,House,Trap"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "00:00", "01:30", "The Gaslamp Killer", "bass,eclectic"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "01:30", "03:00", "Stwo", "Electronic,R&B"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "03:00", "04:30", "Eprom", "bass,dark melodies"));
        artistList.add(new Artist(2016, AMPHITHEATER, 1, "04:30", "06:00", "Tails", "bump,trap"));

        artistList.add(new Artist(2016, AMPHITHEATER, 2, "13:00", "14:00", "Sean Rodman", "soulful mountain folk"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "14:00", "15:00", "Fashion Fiasco Fantastico w/ DJ Faiith", ""));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "15:00", "16:30", "MA/AM", "disco,house,techno"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "16:30", "18:00", "Wmnstudies", "deep house,house,nu disco"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "18:00", "19:30", "Kid Kurse", "bass"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "19:30", "21:00", "Plastician", "bass,dubstep"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "21:00", "22:30", "Greazus", "bass,drum & bass,footwork,grime,hip hop,house,jungle"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "22:30", "00:00", "Alix Perez", "electronica,soul"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "00:00", "01:30", "Flava D", "garage,grime,house"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "01:30", "03:00", "DJ Q", "electronic"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "03:00", "04:30", "Sorrow", "dubstep,future garage"));
        artistList.add(new Artist(2016, AMPHITHEATER, 2, "04:30", "06:00", "Taal Mala", "Soundbwoy Obliterating Futuristic Riddim 'N' bass flex"));

        artistList.add(new Artist(2016, AMPHITHEATER, 3, "15:00", "16:30", "Boats", "funk,vibes"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "16:30", "18:00", "BIOS", "grime,hip hop"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "18:00", "19:30", "The Originalz", "Future bass,purple music"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "19:30", "21:00", "VNDMG", "bass,electronic,hip hop"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "21:00", "22:30", "Conrank", "bass"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "22:30", "00:00", "Shades", "bass"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "00:00", "01:30", "Troyboi", "bass,trap"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "01:30", "03:00", "Bleep Bloop", "bass,electronic"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "03:00", "04:30", "Yheti", "bass,fusion"));
        artistList.add(new Artist(2016, AMPHITHEATER, 3, "04:30", "06:00", "Longwalkshortdock", "acid,death step,deathno,electronic,techno"));

        //Pagoda
        artistList.add(new Artist(2016, PAGODA, 1, "15:00", "16:00", "Logan Hart", "house"));
        artistList.add(new Artist(2016, PAGODA, 1, "16:00", "17:30", "Trotter", "Nudisco,NuFunk"));
        artistList.add(new Artist(2016, PAGODA, 1, "17:30", "18:30", "DeBlock", "house"));
        artistList.add(new Artist(2016, PAGODA, 1, "18:30", "20:00", "DJ Soup", "disco,house"));
        artistList.add(new Artist(2016, PAGODA, 1, "20:00", "21:00", "Nora En Pure", "Deep House,Indie Dance"));
        artistList.add(new Artist(2016, PAGODA, 1, "21:00", "22:00", "SNBRN", "house,indie dance"));
        artistList.add(new Artist(2016, PAGODA, 1, "22:00", "00:00", "Justin Martin", "house,techno"));
        artistList.add(new Artist(2016, PAGODA, 1, "00:00", "01:00", "AlunaGeorge", "hip pop,r&b"));
        artistList.add(new Artist(2016, PAGODA, 1, "01:00", "02:30", "Gorgon City", "house"));
        artistList.add(new Artist(2016, PAGODA, 1, "02:30", "04:00", "Boys Noize", "acid house,electro house,electronic,techno"));
        artistList.add(new Artist(2016, PAGODA, 1, "04:00", "05:30", "Chris Lorenzo", "bass,house"));
        artistList.add(new Artist(2016, PAGODA, 1, "05:30", "07:00", "Wallis", "House"));

        artistList.add(new Artist(2016, PAGODA, 2, "16:00", "17:00", "Sweet Pickle", ""));
        artistList.add(new Artist(2016, PAGODA, 2, "17:00", "18:00", "Freebound", ""));
        artistList.add(new Artist(2016, PAGODA, 2, "18:00", "19:00", "Everyman - Pumpkin Tribute", "love sauce"));
        artistList.add(new Artist(2016, PAGODA, 2, "19:00", "20:30", "2TIGHT", ""));
        artistList.add(new Artist(2016, PAGODA, 2, "20:30", "22:00", "Hotel Garuda", "house"));
        artistList.add(new Artist(2016, PAGODA, 2, "22:00", "23:20", "Autograf", "Live electronic"));
        artistList.add(new Artist(2016, PAGODA, 2, "23:20", "23:40", "Mat The Alien", "space music"));
        artistList.add(new Artist(2016, PAGODA, 2, "23:40", "01:00", "Beats Antique ft. David Satori & Tommy Sidecar", "World Electronica"));
        artistList.add(new Artist(2016, PAGODA, 2, "01:00", "02:30", "Amine Edge & DANCE", "house"));
        artistList.add(new Artist(2016, PAGODA, 2, "02:30", "04:00", "REZZ", "Electronic,Techno"));
        artistList.add(new Artist(2016, PAGODA, 2, "04:00", "05:30", "Teemid", "deep house"));

        artistList.add(new Artist(2016, PAGODA, 3, "16:00", "17:00", "Ocea Sasa", "deep house"));
        artistList.add(new Artist(2016, PAGODA, 3, "17:00", "18:00", "Cut La Whut", "booty music"));
        artistList.add(new Artist(2016, PAGODA, 3, "18:00", "19:30", "Mat The Alien", "space music"));
        artistList.add(new Artist(2016, PAGODA, 3, "19:30", "20:30", "Chuurch", "deep house,live,tech house"));
        artistList.add(new Artist(2016, PAGODA, 3, "20:30", "22:00", "The M Machine", "dubstep,electro house,indie dance"));
        artistList.add(new Artist(2016, PAGODA, 3, "22:00", "23:30", "Mija", "bass,house,trap"));
        artistList.add(new Artist(2016, PAGODA, 3, "23:30", "01:00", "Getter", "electronic"));
        artistList.add(new Artist(2016, PAGODA, 3, "01:00", "02:30", "Marshmello", "house,trap"));
        artistList.add(new Artist(2016, PAGODA, 3, "02:30", "04:00", "What So Not", "bass,dub,electronic,house"));
        artistList.add(new Artist(2016, PAGODA, 3, "04:00", "05:30", "Destructo - Sunrise Sermon", "House"));

        //FOREST

        artistList.add(new Artist(2016, FOREST, 1, "15:00", "17:00", "Freddy J", "bassline electro funk,party bass"));
        artistList.add(new Artist(2016, FOREST, 1, "17:00", "18:30", "Lefy", "drum & bass,electro funk,midtempo breaks"));
        artistList.add(new Artist(2016, FOREST, 1, "18:30", "20:00", "Father Funk", "Bass,Funk,mashup"));
        artistList.add(new Artist(2016, FOREST, 1, "20:00", "21:30", "K+Lab", "Electronic,glitch hop"));
        artistList.add(new Artist(2016, FOREST, 1, "21:30", "23:00", "Neon Steve", "disco to Drum & Bass"));
        artistList.add(new Artist(2016, FOREST, 1, "23:00", "00:30", "Opiuo", "Glitch Hop"));
        artistList.add(new Artist(2016, FOREST, 1, "00:30", "02:00", "Stickybuds", "Breaks,drum & bass,funk,ghetto funk,glitch hop,reggae"));
        artistList.add(new Artist(2016, FOREST, 1, "02:00", "03:30", "The Funk Hunters", "bass,boogie,electronic,funk,future funk"));
        artistList.add(new Artist(2016, FOREST, 1, "03:30", "05:00", "Defunk", "bass,electro funk,glitch,uptempo"));
        artistList.add(new Artist(2016, FOREST, 1, "05:00", "07:00", "Joseph Martin", "Funky Disco House"));

        artistList.add(new Artist(2016, FOREST, 2, "16:00", "17:00", "Fattycakes", ""));
        artistList.add(new Artist(2016, FOREST, 2, "17:00", "18:30", "JGirl & Manousos", "House,Techno"));
        artistList.add(new Artist(2016, FOREST, 2, "18:30", "20:00", "The Gaff ft Mat The Alien", "funk,global bass,hip hop,turntablism,world"));
        artistList.add(new Artist(2016, FOREST, 2, "20:00", "21:00", "Dj Nu Mark", "Elclectic Musical roots based in Hip HOp"));
        artistList.add(new Artist(2016, FOREST, 2, "21:00", "22:00", "Questlove", "auteur Dj,hip hop,Neo Soul"));
        artistList.add(new Artist(2016, FOREST, 2, "22:00", "23:00", "Cut Chemist", "Electronic,Hip Hop,Turntablism"));
        artistList.add(new Artist(2016, FOREST, 2, "23:00", "00:00", "Skratch Bastid", "breaks,funk,hip hop,turntablism"));
        artistList.add(new Artist(2016, FOREST, 2, "00:00", "01:30", "Z-Trip", "breaks,hip hop,turntablism"));
        artistList.add(new Artist(2016, FOREST, 2, "01:30", "03:00", "A.Skillz", "breaks,funk,hip hop,party"));
        artistList.add(new Artist(2016, FOREST, 2, "03:00", "04:30", "Featurecast", "Breaks,NuFunk"));
        artistList.add(new Artist(2016, FOREST, 2, "04:30", "06:30", "Neighbour", "disco,house"));

        artistList.add(new Artist(2016, FOREST, 3, "14:30", "19:30", "Fractal Funk Jam - Hosted by Smalltown DJs", ""));
        artistList.add(new Artist(2016, FOREST, 3, "19:30", "21:00", "SkiiTour", "Drum & bass,house,trap,twerk"));
        artistList.add(new Artist(2016, FOREST, 3, "21:00", "22:30", "Purple Disco Machine", "electronica,house,nu disco"));
        artistList.add(new Artist(2016, FOREST, 3, "22:30", "00:00", "Felix Da Housecat", "house"));
        artistList.add(new Artist(2016, FOREST, 3, "00:00", "01:30", "Green Velvet", "electronica,house"));
        artistList.add(new Artist(2016, FOREST, 3, "01:30", "03:00", "Ghastly", ""));
        artistList.add(new Artist(2016, FOREST, 3, "03:00", "04:30", "Dr Fresch", "Future Ghetto"));
        artistList.add(new Artist(2016, FOREST, 3, "04:30", "06:00", "Slynk", "breaks,drum & bass,ghetto funk,hip hop"));
        artistList.add(new Artist(2016, FOREST, 3, "06:00", "12:00", "Rich-e-Rich", "My sets are like a box of chocolates"));

        //GROVE

        artistList.add(new Artist(2016, GROVE, 1, "13:00", "14:00", "Opening Ceremony with Soul Fire Dance", "world dance production"));
        artistList.add(new Artist(2016, GROVE, 1, "14:00", "15:00", "Adham Shaikh", "global bass"));
        artistList.add(new Artist(2016, GROVE, 1, "15:00", "16:00", "Vespers", "future bass,live sax"));
        artistList.add(new Artist(2016, GROVE, 1, "16:00", "17:30", "The Russ Liquid Test", "electronic,live"));
        artistList.add(new Artist(2016, GROVE, 1, "17:30", "18:30", "Soulular", "Sexy Psychedelic Love Crunk Space Bass"));
        artistList.add(new Artist(2016, GROVE, 1, "18:30", "20:00", "Naasko", "budstep,deeptech,dub & bass"));
        artistList.add(new Artist(2016, GROVE, 1, "20:00", "22:00", "Meow Mix", "bass,downtempo,house"));
        artistList.add(new Artist(2016, GROVE, 1, "22:00", "22:30", "Omnika In Motion", "performance art,theatrics"));
        artistList.add(new Artist(2016, GROVE, 1, "22:30", "00:00", "Headphone Activist", "Beats,electronic,vibe music"));
        artistList.add(new Artist(2016, GROVE, 1, "00:00", "01:30", "Hermitude", "electronic,hip hop"));
        artistList.add(new Artist(2016, GROVE, 1, "01:30", "03:00", "Troyboi", "bass,trap"));
        artistList.add(new Artist(2016, GROVE, 1, "03:00", "04:30", "Om Unit", "bass"));
        artistList.add(new Artist(2016, GROVE, 1, "04:30", "05:45", "Michael Red", "bass,live PA"));
        artistList.add(new Artist(2016, GROVE, 1, "05:45", "07:00", "Goopsteppa", "bass"));

        artistList.add(new Artist(2016, GROVE, 2, "15:30", "17:00", "Lazy Syrup Orchestra", "freestyle,hip hop,Downtempo"));
        artistList.add(new Artist(2016, GROVE, 2, "17:00", "18:00", "Love You Miss You", ""));
        artistList.add(new Artist(2016, GROVE, 2, "18:00", "19:00", "Natural Alchemy", "deep urban tribal tech"));
        artistList.add(new Artist(2016, GROVE, 2, "19:00", "20:00", "Melo.Nade", "bass,crunk"));
        artistList.add(new Artist(2016, GROVE, 2, "20:00", "21:00", "Shiny Things", "footwork hyphy deep and such,weirdo futuristic trap"));
        artistList.add(new Artist(2016, GROVE, 2, "21:00", "22:30", "Dj Madd", "bass,dubwise"));
        artistList.add(new Artist(2016, GROVE, 2, "22:30", "23:00", "Subscura", "Performance Art"));
        artistList.add(new Artist(2016, GROVE, 2, "23:00", "00:30", "Thelem", "drum & bass,grime,hip hop,trap"));
        artistList.add(new Artist(2016, GROVE, 2, "00:30", "02:30", "Truth", "bass,dubstep"));
        artistList.add(new Artist(2016, GROVE, 2, "02:30", "04:00", "The Widdler", "dubstep"));
        artistList.add(new Artist(2016, GROVE, 2, "04:00", "05:30", "Barisone", "bass,dancehall,hip hop"));
        artistList.add(new Artist(2016, GROVE, 2, "05:30", "06:30", "Hakuu", "Downtempo Dream Beats"));

        artistList.add(new Artist(2016, GROVE, 3, "15:00", "16:00", "Theo Tzu", "dubstep,psy dub"));
        artistList.add(new Artist(2016, GROVE, 3, "16:00", "17:00", "Light Twerkerz", ""));
        artistList.add(new Artist(2016, GROVE, 3, "17:00", "18:00", "Octaban", "bass,hip hop,trap"));
        artistList.add(new Artist(2016, GROVE, 3, "18:00", "19:00", "Application", ""));
        artistList.add(new Artist(2016, GROVE, 3, "19:00", "20:00", "Moontricks", "live bass"));
        artistList.add(new Artist(2016, GROVE, 3, "20:00", "21:30", "Five Alarm Funk", "Insturmental,Rock"));
        artistList.add(new Artist(2016, GROVE, 3, "21:30", "22:45", "Jpod The Beat Chef", "melodic bass"));
        artistList.add(new Artist(2016, GROVE, 3, "22:45", "23:00", "Circus Acts Insomniacs ", "acrobatics,circus"));
        artistList.add(new Artist(2016, GROVE, 3, "23:00", "00:30", "Clozee", "Bass,chill trap,ethnofusion,future bass,glitch hop,trip hop"));
        artistList.add(new Artist(2016, GROVE, 3, "00:30", "02:00", "Emancipator", "Epic forest beats"));
        artistList.add(new Artist(2016, GROVE, 3, "02:00", "03:00", "Shigeo", "Electronic"));
        artistList.add(new Artist(2016, GROVE, 3, "03:00", "04:30", "xxyyxx", "electronic"));
        artistList.add(new Artist(2016, GROVE, 3, "04:30", "06:00", "A Sol Mechanic", "Electronic,Experimental Hip Hop,R&B"));
        artistList.add(new Artist(2016, GROVE, 3, "06:00", "07:30", "Flamingosis", "electronic,future funk,hip hop"));

        //VILLAGE
        artistList.add(new Artist(2016, VILLAGE, 1, "15:00", "19:00", "Ragga Jungle Rinse Out -  Hosted by Hush", ""));
        artistList.add(new Artist(2016, VILLAGE, 1, "19:00", "20:00", "Lost City", "Bass,Future Dancehall,jungle"));
        artistList.add(new Artist(2016, VILLAGE, 1, "20:00", "21:00", "Kennedy Jones", "Bass,Big room house,dubstep,hard dance,hip hop,trap"));
        artistList.add(new Artist(2016, VILLAGE, 1, "21:00", "22:00", "KJ Sawka", "Bass,Dance,Electronic,Live Drums"));
        artistList.add(new Artist(2016, VILLAGE, 1, "22:00", "23:00", "Ephwurd", "bass,house"));
        artistList.add(new Artist(2016, VILLAGE, 1, "23:00", "00:30", "Roni Size & Krust Present Full Cycle ft. Dynamite MC", "Drum & Bass"));
        artistList.add(new Artist(2016, VILLAGE, 1, "00:30", "02:00", "Caspa & Rusko", "bass,dubstep"));
        artistList.add(new Artist(2016, VILLAGE, 1, "02:00", "03:30", "Excision", "drumstep,dubstep"));
        artistList.add(new Artist(2016, VILLAGE, 1, "03:30", "05:00", "TC", "drum & bass"));
        artistList.add(new Artist(2016, VILLAGE, 1, "05:00", "06:30", "Liquid Stranger", "bass,downtempo,experimental"));

        artistList.add(new Artist(2016, VILLAGE, 2, "13:30", "19:00", "Hip Hop Showcase", ""));
        artistList.add(new Artist(2016, VILLAGE, 2, "19:00", "20:00", "vekked", "scratch punk"));
        artistList.add(new Artist(2016, VILLAGE, 2, "20:00", "21:00", "Stylust Beats", "bass,dubstep,good music,hip hop,trap"));
        artistList.add(new Artist(2016, VILLAGE, 2, "21:00", "22:00", "Chali 2na & The House of Vibe", "hip hop"));
        artistList.add(new Artist(2016, VILLAGE, 2, "22:00", "23:00", "ill.Gates", "bass,glitch hop"));
        artistList.add(new Artist(2016, VILLAGE, 2, "23:00", "00:00", "Subvert", "bass"));
        artistList.add(new Artist(2016, VILLAGE, 2, "00:00", "01:30", "Grandtheft", "club,house,trap"));
        artistList.add(new Artist(2016, VILLAGE, 2, "01:30", "03:00", "Sub Focus", "drum & bass,dubstep,house"));
        artistList.add(new Artist(2016, VILLAGE, 2, "03:00", "04:30", "The Upbeats", "drum & bass"));
        artistList.add(new Artist(2016, VILLAGE, 2, "04:30", "06:00", "Liquid Sunrise Jam Hosted by Spiral Architects", "Drum & bass,turntablism"));

        artistList.add(new Artist(2016, VILLAGE, 3, "17:00", "18:00", "Arlene", ""));
        artistList.add(new Artist(2016, VILLAGE, 3, "18:00", "19:00", "Two Timer", ""));
        artistList.add(new Artist(2016, VILLAGE, 3, "19:00", "20:00", "Dua & Generic", "bass,Drum & Bass,Turntablism"));
        artistList.add(new Artist(2016, VILLAGE, 3, "20:00", "21:00", "DJ Anger", "bass,hip hop,turntablism"));
        artistList.add(new Artist(2016, VILLAGE, 3, "21:00", "22:00", "Haywyre", "electronic"));
        artistList.add(new Artist(2016, VILLAGE, 3, "22:00", "23:00", "Manic Focus", "electronic,soul"));
        artistList.add(new Artist(2016, VILLAGE, 3, "23:00", "00:00", "Keys N Krates", "electronic,hip hop,trap"));
        artistList.add(new Artist(2016, VILLAGE, 3, "00:00", "01:30", "Snails", "bass"));
        artistList.add(new Artist(2016, VILLAGE, 3, "01:30", "03:00", "Andy C & Armanni Reign", "drum & bass"));
        artistList.add(new Artist(2016, VILLAGE, 3, "03:00", "04:00", "G Jones", "bass"));
        artistList.add(new Artist(2016, VILLAGE, 3, "04:00", "05:00", "Marvel Years", "electro soul,future funk,glitch hop,hip hop"));
        artistList.add(new Artist(2016, VILLAGE, 3, "05:00", "07:00", "Scottie D", "Drum & Bass"));

        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }

    public ArrayList<Artist> get2017Artists() {

        ArrayList<Artist> artistList = new ArrayList<>();

        artistList.add(new Artist(2017, VILLAGE,1,"15:00","19:00","Ragga Jungle Rinse Out hosted by Hush",""));
        artistList.add(new Artist(2017, VILLAGE,1,"19:00","21:00","Marcus Visionary B2B Liondub","jungle,drum & bass"));
        artistList.add(new Artist(2017, VILLAGE,1,"21:00","22:00","Kytami w/ Phonik Ops","violin,drum & bass,bass"));
        artistList.add(new Artist(2017, VILLAGE,1,"22:00","23:00","Chali 2na & The Funk Hunters","hip hop,bass,funk"));
        artistList.add(new Artist(2017, VILLAGE,1,"23:00","00:00","NGHTMRE",""));
        artistList.add(new Artist(2017, VILLAGE,1,"00:00","02:00","Excision Special 10th Anniversary Set","drumstep,dubstep"));
        artistList.add(new Artist(2017, VILLAGE,1,"02:00","03:30","Ganja White Night","dubstep,bass"));
        artistList.add(new Artist(2017, VILLAGE,1,"03:30","04:30","Terravita","bass"));
        artistList.add(new Artist(2017, VILLAGE,1,"04:30","05:30","Crizzly","crunkstep"));
        artistList.add(new Artist(2017, VILLAGE,1,"05:30","06:30","Mr. B B2B DJ Primitive",""));

        artistList.add(new Artist(2017, VILLAGE,2,"15:00","16:00","DJ Jetts","bass"));
        artistList.add(new Artist(2017, VILLAGE,2,"16:00","17:00","Murge / Just / Ish & Ash","hip hop,electronic,experimental,turntablist,live"));
        artistList.add(new Artist(2017, VILLAGE,2,"17:00","18:00","Emotionz feat. Fresh Kils","hip hop,bass,beat box,soul"));
        artistList.add(new Artist(2017, VILLAGE,2,"18:00","19:00","Fat Pat B2B Treblesum",""));
        artistList.add(new Artist(2017, VILLAGE,2,"19:00","20:00","Pigeon Hole","west coast bass"));
        artistList.add(new Artist(2017, VILLAGE,2,"20:00","21:00","Stylust Beats","trap,bass"));
        artistList.add(new Artist(2017, VILLAGE,2,"21:00","22:00","Dilated Peoples","hip hop"));
        artistList.add(new Artist(2017, VILLAGE,2,"22:00","23:00","Subvert","bass"));
        artistList.add(new Artist(2017, VILLAGE,2,"23:00","00:00","Kill the Noise","electronic"));
        artistList.add(new Artist(2017, VILLAGE,2,"00:00","01:30","Datsik","dubstep"));
        artistList.add(new Artist(2017, VILLAGE,2,"01:30","03:00","Pendulum (DJ Set)","drum & bass"));
        artistList.add(new Artist(2017, VILLAGE,2,"03:00","04:30","Calyx & Teebee w/ Armanni Reign","drum & bass"));
        artistList.add(new Artist(2017, VILLAGE,2,"04:30","06:00","Liquid Sunrise Jam hosted by Spiral Architects","drum & bass"));

        artistList.add(new Artist(2017, VILLAGE,3,"16:00","17:00","ANDE B2B DLB",""));
        artistList.add(new Artist(2017, VILLAGE,3,"17:00","18:00","Dua & Generic","bass"));
        artistList.add(new Artist(2017, VILLAGE,3,"18:00","19:00","Arlen B2B Big B",""));
        artistList.add(new Artist(2017, VILLAGE,3,"19:00","20:00","Sam Binga","bass,footwork,jungle"));
        artistList.add(new Artist(2017, VILLAGE,3,"20:00","21:00","ill.Gates","bass"));
        artistList.add(new Artist(2017, VILLAGE,3,"21:00","22:00","Haywyre","electronica"));
        artistList.add(new Artist(2017, VILLAGE,3,"22:15","23:15","Lucent Dossier Experience","avant garde performance art"));
        artistList.add(new Artist(2017, VILLAGE,3,"23:30","00:30","Z-Trip","breaks,hip hop,turntablism"));
        artistList.add(new Artist(2017, VILLAGE,3,"00:30","02:00","Jauz","no genre"));
        artistList.add(new Artist(2017, VILLAGE,3,"02:00","03:30","Delta Heavy","bass"));
        artistList.add(new Artist(2017, VILLAGE,3,"03:30","04:30","LTJ Bukem W/ Armanni Reign",""));
        artistList.add(new Artist(2017, VILLAGE,3,"04:30","05:30","Tha Funk Junkie B2B Twotimer","b2b,drum & bass"));
        artistList.add(new Artist(2017, VILLAGE,3,"05:30","07:00","Scottie",""));

        artistList.add(new Artist(2017, FOREST,1,"15:00","16:30","Freddy J","breaks,bass house"));
        artistList.add(new Artist(2017, FOREST,1,"16:30","17:30","DJ Lefy",""));
        artistList.add(new Artist(2017, FOREST,1,"17:30","18:30","Diligent",""));
        artistList.add(new Artist(2017, FOREST,1,"18:30","20:00","JGirl & Manousos","house"));
        artistList.add(new Artist(2017, FOREST,1,"20:00","21:00","K+Lab","glitch hop,horror funk,bass"));
        artistList.add(new Artist(2017, FOREST,1,"21:00","22:00","Neon Steve","all styles"));
        artistList.add(new Artist(2017, FOREST,1,"22:00","23:00","Slynk","breaks,ghetto funk"));
        artistList.add(new Artist(2017, FOREST,1,"23:00","00:00","Opiuo","bass"));
        artistList.add(new Artist(2017, FOREST,1,"00:00","01:00","Stickybuds","funk,breaks,hip hop,mashup"));
        artistList.add(new Artist(2017, FOREST,1,"01:00","02:00","Krafty Kuts & Dynamite MC","breaks,hip hop"));
        artistList.add(new Artist(2017, FOREST,1,"02:00","03:00","The Funk Hunters","funk"));
        artistList.add(new Artist(2017, FOREST,1,"03:00","04:00","Smalltown DJs","house,bass"));
        artistList.add(new Artist(2017, FOREST,1,"04:00","05:00","Defunk","glitch,funk,bass,hip hop"));
        artistList.add(new Artist(2017, FOREST,1,"05:00","06:30","Joseph Martin","disco house"));

        artistList.add(new Artist(2017, FOREST,2,"16:00","17:30","Wood'N'Soo","hip hop,funk,breaks,ghetto funk"));
        artistList.add(new Artist(2017, FOREST,2,"17:30","19:00","Dan Solo",""));
        artistList.add(new Artist(2017, FOREST,2,"19:00","20:00","The Gaff","funk bass,scatchin"));
        artistList.add(new Artist(2017, FOREST,2,"20:00","21:00","DJ Brace",""));
        artistList.add(new Artist(2017, FOREST,2,"21:00","22:00","Skratch Bastid","turntablism"));
        artistList.add(new Artist(2017, FOREST,2,"22:00","23:30","DJ Jazzy Jeff","hip hop"));
        artistList.add(new Artist(2017, FOREST,2,"23:30","00:30","Z-Trip","breaks,hip hop,turntablism"));
        artistList.add(new Artist(2017, FOREST,2,"00:30","01:30","A.Skillz","breaks,funk,hip hop,party"));
        artistList.add(new Artist(2017, FOREST,2,"01:30","02:30","A-Trak","eletronic,dance"));
        artistList.add(new Artist(2017, FOREST,2,"02:30","04:00","Fort Knox Five vs Qdup","funk,breaks"));
        artistList.add(new Artist(2017, FOREST,2,"04:00","05:30","Rob Garza","house"));
        artistList.add(new Artist(2017, FOREST,2,"05:30","07:00","Neighbour","disco,house"));

        artistList.add(new Artist(2017, FOREST,3,"14:00","19:00","Fractal Forest Funk Jam Hosted by Smalltown DJs","house,bass"));
        artistList.add(new Artist(2017, FOREST,3,"19:00","20:00","Beat Fatigue","blues,electronic,funk,glitch hop"));
        artistList.add(new Artist(2017, FOREST,3,"20:00","21:00","Father Funk","bass,funk,mashup"));
        artistList.add(new Artist(2017, FOREST,3,"21:00","22:30","WBBL","ghetto funk,glitch hop"));
        artistList.add(new Artist(2017, FOREST,3,"22:30","00:00","JFB","turntablism,hip hop,ghetto funk,drum & bass,dubstep"));
        artistList.add(new Artist(2017, FOREST,3,"00:00","01:30","Stanton Warriors",""));
        artistList.add(new Artist(2017, FOREST,3,"01:30","02:30","Marten Hørger","house,breaks,trap,bass"));
        artistList.add(new Artist(2017, FOREST,3,"02:30","03:30","Deekline","uk bass,breaks,drum & bass,jungle"));
        artistList.add(new Artist(2017, FOREST,3,"03:30","04:30","Ed Solo","drum & bass,breaks"));
        artistList.add(new Artist(2017, FOREST,3,"04:30","06:00","CMC & Silenta","glitch hop,breaks,mashup"));
        artistList.add(new Artist(2017, FOREST,3,"06:00","08:00","Justin Hale","forms of house"));
        artistList.add(new Artist(2017, FOREST,3,"08:00","12:00","Rich-E-Rich","house,breaks,garage,funk,hip hop,electro,rock"));

        artistList.add(new Artist(2017, LIVINGROOM,0,"10:30","11:00","Opening Ceremony with Joaqopelli",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"11:00","12:30","DJ Foxy Moron","45s! and all things funky and weird"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"12:30","13:30","Adept",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"13:30","14:30","Shasta",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"14:30","15:30","Ocea Sasa","g-house,downtempo,floorfillers,bass,world"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"15:30","16:30","Jason Whyte",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"16:30","17:30","Hoola","disco house,dub,dancehall,hip hop"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"17:30","18:30","Meowmix ft. Sweets","bass"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"18:30","19:30","Lion-S","bass,downtempo"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"19:30","20:30","Ginger",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"20:30","21:30","Emma Star",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"21:30","22:30","Morninglory",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"22:30","00:00","Soohan","global bass"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"00:00","01:00","Dubconscious","bass,dancehall,dubstep,garage,house"));
        artistList.add(new Artist(2017, LIVINGROOM,0,"01:00","02:00","Lief",""));
        artistList.add(new Artist(2017, LIVINGROOM,0,"02:00","03:00","Lorne B",""));

        artistList.add(new Artist(2017, LIVINGROOM,1,"11:00","12:00","Select-R-Us",""));
        artistList.add(new Artist(2017, LIVINGROOM,1,"12:00","13:30","Gr◯un土 a.k.a DJ GROUND","organic,experimental,ritual slow houser"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"13:30","15:30","Lion-S","bass,downtempo"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"15:30","17:00","Matthew1626","house,soul,tech,tribal,gospel"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"17:00","18:30","Sugarbear","disco,house,party"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"18:30","19:00","Hoola","disco house,dub,dancehall,hip hop"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"19:00","20:30","Val Kilmer and The New Coke","80s tight and bright dance party"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"20:30","21:00","Hoola","disco house,dub,dancehall,hip hop"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"21:00","22:00","Sack Grabbath","classic rock (black sabbath tribute)"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"22:00","22:30","Hoola","disco house,dub,dancehall,hip hop"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"22:30","00:00","Breakfluid","beats,futuresoul,dub,dancehall,reggae,hip hop,rare grooves,house,bass stylings"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"00:00","01:30","Max Ulis","house,techno"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"01:30","03:00","Taal Mala","raggamuffin style sound muderation"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"03:00","03:30","Subscura","performance art"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"03:30","05:00","Siadic","deep bass"));
        artistList.add(new Artist(2017, LIVINGROOM,1,"05:00","07:00","Matsya","bass,ambient,eclectic"));

        artistList.add(new Artist(2017, LIVINGROOM,2,"11:00","12:00","Classical Hour",""));
        artistList.add(new Artist(2017, LIVINGROOM,2,"12:00","13:30","L-Nix",""));
        artistList.add(new Artist(2017, LIVINGROOM,2,"13:30","15:00","Fluxo","bruken,bass,boogie,beats,junglist"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"15:00","16:30","Senseless Live","live vocal house"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"16:30","18:00","Craig Mullin",""));
        artistList.add(new Artist(2017, LIVINGROOM,2,"18:00","00:00","Idjut Boys","dopel"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"00:00","01:30","Pedestrian","electronica"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"01:30","03:00","Moontricks","live electronic roots"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"03:00","04:30","Random Rab","random"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"04:30","06:00","Gr◯un土 a.k.a DJ GROUND","organic,experimental,ritual slow houser"));
        artistList.add(new Artist(2017, LIVINGROOM,2,"06:00","07:30","Sijay","sleepstep,chill bass,ambient"));

        artistList.add(new Artist(2017, LIVINGROOM,3,"10:00","11:00","Marty Carter and Russ","ambient,reggae rock,jazz funk electronica,instrument,synsthesized original organic kootenay kind music"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"11:00","12:00","Erica Dee","hip hop,nusoul"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"12:00","13:00","Mandai","reggae,dancehall"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"13:00","14:00","Tank Gyal","dancehall"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"14:00","15:00","Light Twerkers",""));
        artistList.add(new Artist(2017, LIVINGROOM,3,"15:00","16:30","Handsome Tiger","bass"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"16:30","18:30","Adham Shaikh",""));
        artistList.add(new Artist(2017, LIVINGROOM,3,"18:30","20:00","DJ Olive","roof music"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"20:00","21:30","The Orb","ambient,house,techno,electronica"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"21:30","22:30","Flamingosis","its a groovy thing"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"22:30","00:30","El Papachango","bass,electronica,hip hop,tropical"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"00:30","02:30","KMLN",""));
        artistList.add(new Artist(2017, LIVINGROOM,3,"02:30","04:30","MA/AM","house,live vocal"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"04:30","06:30","Sweet Anomaly","bass,downtempo,global"));
        artistList.add(new Artist(2017, LIVINGROOM,3,"06:30","12:00","The Pride Obscuri-Teaze Ft Hoola & Guests, closing with Joaqopelli at 11:30am","disco house,dub,dancehall,hip hop"));

        artistList.add(new Artist(2017, AMPHITHEATER,0,"10:45","11:45","Hosted by ThinkTank & Def3",""));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"12:00","13:30","Mat The Alien B2B The Librarian","multi genre dj set,b2b,bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"13:30","15:00","Zeke Beats","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"15:00","16:30","Holly","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"16:30","17:30","Bryx","bass,hip hop,mashup,turntablism"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"17:30","19:00","SkiiTour","upbeat funk fun party music"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"19:00","20:00","JPOD","melodic bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"20:00","21:00","Crywolf","indie electronic"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"21:00","22:30","Shield","drum & bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"22:30","00:00","um..","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"00:00","01:30","Yheti","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,0,"01:30","03:30","Longwalkshortdock","electronic"));

        artistList.add(new Artist(2017, AMPHITHEATER,1,"11:30","12:30","Hosted by DEF3 & ThinkTank",""));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"13:00","13:30","JodieB","indie,blues,acoustic hip hop"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"13:30","14:00","Gemma Luna",""));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"14:00","14:30","Marin Patenaude","confessional folk,country,jazz,soul"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"14:30","15:30","Fashion, Fiasco, Fantastico","fashion show"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"15:30","16:30","Rippel",""));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"16:30","18:00","Whipped Cream","electronic"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"18:00","19:30","Perkulat0r","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"19:30","21:00","Herzeloyde","bass,future bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"21:00","22:30","Great Dane","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"22:30","00:00","Falcons","electronic,hip hop"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"00:00","01:30","Ekali","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"01:30","02:30","AraabMUZIK",""));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"02:30","04:00","DJ Craze","hip hop,trap,bass,turntablism"));
        artistList.add(new Artist(2017, AMPHITHEATER,1,"04:00","05:00","Abstrakt Sonance","dubstep,grime"));

        artistList.add(new Artist(2017, AMPHITHEATER,2,"12:30","13:30","Hosted by ThinkTank @ Def3",""));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"14:00","15:00","I M U R","electronic r&b"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"15:00","16:00","Blenda",""));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"16:00","17:00","DVSLY",""));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"17:00","18:00","Mooves","808,breaks,soul"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"18:00","19:30","Juelz","electronic,hip hop"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"19:30","21:00","Greazus","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"21:00","22:30","Geotheory","electronic,trap,experimental"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"22:30","23:30","Pomo","electronic,funk,dance"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"23:30","01:00","Billy Kenny","good vibes house"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"01:00","02:30","J.Phlip",""));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"02:30","04:00","Jimmy Edgar","electronic"));
        artistList.add(new Artist(2017, AMPHITHEATER,2,"04:00","05:00","Woodhead","house,deep house,garage"));

        artistList.add(new Artist(2017, AMPHITHEATER,3,"12:00","13:00","Hosted by Wet Spot",""));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"13:30","14:00","Kristie McCracken","acoustic r&b"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"14:00","15:00","Bed of Coals","folk roots"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"15:00","16:15","ESB",""));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"16:15","17:30","That African","easy listening for the discerning ear"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"17:30","18:00","Stupid Beach",""));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"18:00","19:30","Yunis","bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"19:30","21:00","Sumthin Sumthin","experimental bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"21:00","22:30","kLL sMTH","fusion of,glitch,drum & bass,hip hop,dubstep"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"22:30","00:00","Woolymammoth","trap,hip hop"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"00:00","01:30","Truth B2B Stylust Beats","dubstep,b2b,trap,bass"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"01:30","03:00","Bleep Bloop B2B Proko","slimepunk"));
        artistList.add(new Artist(2017, AMPHITHEATER,3,"03:00","04:30","Liquid Stranger B2B Space Jesus","free form bass,b2b,bass"));

        artistList.add(new Artist(2017, GROVE,1,"13:00","14:00","Opening Ceremonies w/ Soul Fire Dance",""));
        artistList.add(new Artist(2017, GROVE,1,"14:00","15:00","AtYyA","psydub,bass"));
        artistList.add(new Artist(2017, GROVE,1,"15:00","16:00","Organic Mechanic",""));
        artistList.add(new Artist(2017, GROVE,1,"16:00","16:30","Frase","analog future soul"));
        artistList.add(new Artist(2017, GROVE,1,"16:30","18:00","Moontricks","live electronic roots"));
        artistList.add(new Artist(2017, GROVE,1,"18:00","19:00","Intersect","bass"));
        artistList.add(new Artist(2017, GROVE,1,"19:00","20:00","Barisone","bass,hip hop,dancehall"));
        artistList.add(new Artist(2017, GROVE,1,"20:00","21:00","PRSN","bass,hip hop,dancehall"));
        artistList.add(new Artist(2017, GROVE,1,"21:00","22:15","SHINY THINGS","bass,hip hop,trap,footwork,deep dubstep,halftime"));
        artistList.add(new Artist(2017, GROVE,1,"22:15","22:30","Subscura","performance art"));
        artistList.add(new Artist(2017, GROVE,1,"22:30","00:00","The Librarian","bass"));
        artistList.add(new Artist(2017, GROVE,1,"00:00","01:30","An-Ten-Nae",""));
        artistList.add(new Artist(2017, GROVE,1,"01:30","03:00","Ivy Lab","beats,halftime,future bass"));
        artistList.add(new Artist(2017, GROVE,1,"03:00","04:30","Aztek","future club,techno"));
        artistList.add(new Artist(2017, GROVE,1,"04:30","05:45","Naasko","deep"));
        artistList.add(new Artist(2017, GROVE,1,"05:45","07:00","Goopsteppa","gangsta ambient,bass"));

        artistList.add(new Artist(2017, GROVE,2,"16:00","17:30","Lazy Syrup Orchestra","soul,world,hip hop"));
        artistList.add(new Artist(2017, GROVE,2,"17:30","18:30","BOUSADA","trip hop,jazz"));
        artistList.add(new Artist(2017, GROVE,2,"18:30","19:30","Melo.Nade","bass,west coast,midtempo and lush"));
        artistList.add(new Artist(2017, GROVE,2,"19:30","21:00","Vokab Kompany","hip hop,soul,electronic & funk"));
        artistList.add(new Artist(2017, GROVE,2,"21:00","22:00","Charlesthefirst","downtempo,bass,ambient"));
        artistList.add(new Artist(2017, GROVE,2,"22:00","22:30","Omnika","theatrical dance and circus"));
        artistList.add(new Artist(2017, GROVE,2,"22:30","23:30","El Papachango","bass,electronica,hip-hop,tropical"));
        artistList.add(new Artist(2017, GROVE,2,"23:30","01:00","The Polish Ambassador","electronic"));
        artistList.add(new Artist(2017, GROVE,2,"01:00","02:30","CloZee","world bass"));
        artistList.add(new Artist(2017, GROVE,2,"02:30","04:00","Fixate","bass,footwork,jungle"));
        artistList.add(new Artist(2017, GROVE,2,"04:00","05:30","Congi","dubstep,hip hop"));
        artistList.add(new Artist(2017, GROVE,2,"05:30","07:00","Leland Riiivr","intelligent bass"));

        artistList.add(new Artist(2017, GROVE,3,"17:00","18:00","Mr. Diggler",""));
        artistList.add(new Artist(2017, GROVE,3,"18:00","19:00","Willa","bass"));
        artistList.add(new Artist(2017, GROVE,3,"19:00","20:00","Application","glitch hop,mid tempo"));
        artistList.add(new Artist(2017, GROVE,3,"20:00","21:00","OCTABÄN","trap,grime,uk bass,west coast bass"));
        artistList.add(new Artist(2017, GROVE,3,"21:00","22:00","Footprints","vibes & stuff"));
        artistList.add(new Artist(2017, GROVE,3,"22:00","23:00","IHF","electronic"));
        artistList.add(new Artist(2017, GROVE,3,"23:00","23:30","Circus Acts Insomniacs",""));
        artistList.add(new Artist(2017, GROVE,3,"23:30","01:30","Maribou State (DJ Set)","deep house"));
        artistList.add(new Artist(2017, GROVE,3,"01:30","03:00","Phaeleh","dubstep,ambient,electronica"));
        artistList.add(new Artist(2017, GROVE,3,"03:00","04:30","Mystery Guest ???",""));
        artistList.add(new Artist(2017, GROVE,3,"04:30","05:30","Frameworks","downtempo"));
        artistList.add(new Artist(2017, GROVE,3,"05:30","06:30","Tor","downtempo"));
        artistList.add(new Artist(2017, GROVE,3,"06:30","08:00","JPOD","melodic bass"));

        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"14:00","15:30","Levi Bannier: Shamanic Yoga Flow",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"15:30","17:00","Momentom Collective Art of Flight: Acro Yoga",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"21:00","22:00","J Jonah",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"22:00","23:30","R@ngo",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"23:30","01:00","griff","electronic"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"01:00","02:30","Austero","midtempo electronica"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"02:30","04:00","Hakuu","downtempo"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,1,"04:00","05:00","More Like Space","dreamy experimental space bass"));

        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"12:00","13:00","Tamara Dawn: The Tantric Perspective on Surya Namaskar",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"13:00","14:30","Levi Banner: Astrology Basics and Beyond",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"14:30","15:30","Leiah Luz Engel: Movement Exploration",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"15:30","17:30","Momentom Collective Flow & Transitions: Acro Yoga",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"21:00","22:00","Puar",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"22:00","23:00","KNGSPRNGS","ratchet rap,trap,grime,booty music"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"23:00","23:30","Made In Alchemy",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"23:30","00:30","S. Holmes",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"00:30","02:00","Westerley","dubstep"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"02:00","03:30","Matsya","bass,ambient,eclectic"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,2,"03:30","05:00","Das Booty","everything,chill,lounge,daytime grooves,prime time"));

        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"12:00","13:30","Tamara Dawn Traditional Tantric Yoga:",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"13:30","15:00","Levi Banner: Shamanic Breath Work",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"15:00","16:30","James Jesson: Healing the Past",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"16:30","18:00","Momentom Collective: Thai & Fly Acro Yoga",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"21:00","22:00","Tuck in Tyler","beats"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"22:00","23:30","Mooves","808,breaks,soul"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"23:30","01:00","Lapa","progressive downtempo"));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"01:00","02:30","Bumble",""));
        artistList.add(new Artist(2017, CEDAR_LOUNGE,3,"02:30","04:00","Sideshow",""));

        artistList.add(new Artist(2017, PAGODA,1,"16:00","17:00","Logan Hart","jazzy,jackin' house"));
        artistList.add(new Artist(2017, PAGODA,1,"17:00","18:00","DeBlock","house"));
        artistList.add(new Artist(2017, PAGODA,1,"18:00","19:15","DJ Soup","disco,house"));
        artistList.add(new Artist(2017, PAGODA,1,"19:15","20:30","rrotik","bass house"));
        artistList.add(new Artist(2017, PAGODA,1,"20:30","22:00","Chris Lorenzo","house,bass"));
        artistList.add(new Artist(2017, PAGODA,1,"22:00","23:30","Justin Martin","house,techno"));
        artistList.add(new Artist(2017, PAGODA,1,"23:30","01:00","Chris Lake","house"));
        artistList.add(new Artist(2017, PAGODA,1,"01:00","02:30","Black Tiger Sex Machine","electro,dubstep"));
        artistList.add(new Artist(2017, PAGODA,1,"02:30","04:00","Ephwurd","bass house"));
        artistList.add(new Artist(2017, PAGODA,1,"04:00","08:00","Destructo Sunrise Sermon","g house"));

        artistList.add(new Artist(2017, PAGODA,2,"16:00","17:30","Kali Yuga","bass,glitch"));
        artistList.add(new Artist(2017, PAGODA,2,"17:30","19:00","S2","emo bap"));
        artistList.add(new Artist(2017, PAGODA,2,"19:00","20:30","Medasin",""));
        artistList.add(new Artist(2017, PAGODA,2,"20:30","21:45","Jai Wolf","electronica"));
        artistList.add(new Artist(2017, PAGODA,2,"21:45","23:15","Vanic","electronic"));
        artistList.add(new Artist(2017, PAGODA,2,"23:15","01:00","Illenium","melodic bass"));
        artistList.add(new Artist(2017, PAGODA,2,"01:00","02:30","Beats Antique ft David Satori & Sidecar Tommy","world electronica"));
        artistList.add(new Artist(2017, PAGODA,2,"02:30","04:00","Herobust","bass,dirty south,dubstep,trap"));
        artistList.add(new Artist(2017, PAGODA,2,"04:00","05:30","Mad Zach","bass"));

        artistList.add(new Artist(2017, PAGODA,3,"16:00","17:30","Wallis","house"));
        artistList.add(new Artist(2017, PAGODA,3,"17:30","19:00","Cut la Whut","trap,hip hop"));
        artistList.add(new Artist(2017, PAGODA,3,"19:00","20:30","Mat The Alien","multi genre dj set"));
        artistList.add(new Artist(2017, PAGODA,3,"20:30","22:00","Dimond Saints","bass"));
        artistList.add(new Artist(2017, PAGODA,3,"22:00","23:30","Shaun Frank","electronic"));
        artistList.add(new Artist(2017, PAGODA,3,"23:30","01:00","Slushii","dubstep"));
        artistList.add(new Artist(2017, PAGODA,3,"01:00","02:30","Adventure Club",""));
        artistList.add(new Artist(2017, PAGODA,3,"02:30","04:00","REZZ","electronic"));
        artistList.add(new Artist(2017, PAGODA,3,"04:00","05:30","Chuurch","lean bass"));

        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }

    public ArrayList<Artist> get2018Artists() {

        ArrayList<Artist> artistList = new ArrayList<>();

        artistList.add(new Artist(2018,VILLAGE,1,"15:00","19:00","Ragga Jungle Rinse Out hosted by Hush",""));
        artistList.add(new Artist(2018,VILLAGE,1,"19:00","20:00","CAIN.1 with Special Guest","jungle,drum & bass"));
        artistList.add(new Artist(2018,VILLAGE,1,"20:00","21:00","General Levy","reggae,drum & bass"));
        artistList.add(new Artist(2018,VILLAGE,1,"21:00","22:00","Lost City","dancehall,jungle,bass"));
        artistList.add(new Artist(2018,VILLAGE,1,"22:00","23:00","Phibes","drum & bass"));
        artistList.add(new Artist(2018,VILLAGE,1,"23:00","00:00","Woofax","bass"));
        artistList.add(new Artist(2018,VILLAGE,1,"00:00","01:30","Snails","snail music"));
        artistList.add(new Artist(2018,VILLAGE,1,"01:30","03:00","Dirt Monkey","genreless"));
        artistList.add(new Artist(2018,VILLAGE,1,"03:00","04:00","Buku","bass"));
        artistList.add(new Artist(2018,VILLAGE,1,"04:00","05:00","Truth","dubstep"));
        artistList.add(new Artist(2018,VILLAGE,1,"05:00","06:30","Mr. B",""));

        artistList.add(new Artist(2018,VILLAGE,2,"17:00","18:00","DJ Jetts","bass"));
        artistList.add(new Artist(2018,VILLAGE,2,"18:00","19:00","Big B",""));
        artistList.add(new Artist(2018,VILLAGE,2,"19:00","20:00","Dua & Generic","bass"));
        artistList.add(new Artist(2018,VILLAGE,2,"20:00","21:00","Stylust","trap,bass"));
        artistList.add(new Artist(2018,VILLAGE,2,"21:00","22:00","DJ Anger","turntablism,hip-hop,bass"));
        artistList.add(new Artist(2018,VILLAGE,2,"22:00","23:00","Big B with Secret Headliner Guest",""));
        artistList.add(new Artist(2018,VILLAGE,2,"23:00","00:00","Subvert","bass"));
        artistList.add(new Artist(2018,VILLAGE,2,"00:00","01:30","The Glitch Mob","electronic"));
        artistList.add(new Artist(2018,VILLAGE,2,"01:30","03:00","Boombox Cartel","electronic"));
        artistList.add(new Artist(2018,VILLAGE,2,"03:00","04:00","DC Breaks B2B Loadstar",""));
        artistList.add(new Artist(2018,VILLAGE,2,"04:00","07:00","Liquid Sunrise Jam hosted by Spiral Architects",""));

        artistList.add(new Artist(2018,VILLAGE,3,"17:30","18:30","Sub Theory","leftcoast bass"));
        artistList.add(new Artist(2018,VILLAGE,3,"18:30","19:30","Triple XL","drum & bass,dubstep"));
        artistList.add(new Artist(2018,VILLAGE,3,"19:30","20:30","DLB",""));
        artistList.add(new Artist(2018,VILLAGE,3,"20:30","21:30","Abstrakt Sonance","dubstep,grime"));
        artistList.add(new Artist(2018,VILLAGE,3,"21:30","22:30","Perkulat0r","bass"));
        artistList.add(new Artist(2018,VILLAGE,3,"22:30","23:30","ill.Gates","bass of all kinds"));
        artistList.add(new Artist(2018,VILLAGE,3,"23:30","00:30","Dizzee Rascal","hip-hop,pop,experimental,dance,grime,dubstep"));
        artistList.add(new Artist(2018,VILLAGE,3,"00:30","02:00","Camo & Krooked","drum & bass"));
        artistList.add(new Artist(2018,VILLAGE,3,"02:00","03:00","Delta Heavy","bass"));
        artistList.add(new Artist(2018,VILLAGE,3,"03:00","04:00","Aphrodite","jungle,drum & bass"));
        artistList.add(new Artist(2018,VILLAGE,3,"04:00","05:00","Shlump","bass"));
        artistList.add(new Artist(2018,VILLAGE,3,"05:00","06:00","Scottie",""));

        artistList.add(new Artist(2018,FOREST,1,"15:00","16:00","Freddy J","house,breaks,bass house"));
        artistList.add(new Artist(2018,FOREST,1,"16:00","17:00","Wes Please",""));
        artistList.add(new Artist(2018,FOREST,1,"17:00","18:00","M3rf","sexy bass driven ear candy"));
        artistList.add(new Artist(2018,FOREST,1,"18:00","19:00","Diligent",""));
        artistList.add(new Artist(2018,FOREST,1,"19:00","20:00","Lefy","mid tempo,house,breaks,drum & bass,happy fun stuff"));
        artistList.add(new Artist(2018,FOREST,1,"20:00","21:00","K+Lab","glitch hop,horror funk,bass"));
        artistList.add(new Artist(2018,FOREST,1,"21:00","22:00","Neon Steve","all styles"));
        artistList.add(new Artist(2018,FOREST,1,"22:00","23:00","Slynk","breaks,ghetto funk"));
        artistList.add(new Artist(2018,FOREST,1,"23:00","00:00","Opiuo","bass"));
        artistList.add(new Artist(2018,FOREST,1,"00:00","01:00","Stickybuds","funk,breaks,hip-hop,mashup"));
        artistList.add(new Artist(2018,FOREST,1,"01:00","02:00","Featurecast","hip-hop,funk,breaks,drum & bass"));
        artistList.add(new Artist(2018,FOREST,1,"02:00","03:00","The Funk Hunters","funk"));
        artistList.add(new Artist(2018,FOREST,1,"03:00","04:30","Fort Knox Five & Qdup","funk,breaks"));
        artistList.add(new Artist(2018,FOREST,1,"04:30","06:00","Joseph Martin","disco house"));

        artistList.add(new Artist(2018,FOREST,2,"16:30","18:00","JGirl & Manousos","house"));
        artistList.add(new Artist(2018,FOREST,2,"18:00","19:00","Fattycakes",""));
        artistList.add(new Artist(2018,FOREST,2,"19:00","20:00","SkiiTour","upbeat funk fun party music"));
        artistList.add(new Artist(2018,FOREST,2,"20:00","21:00","The Gaff","funk bass,scratchin"));
        artistList.add(new Artist(2018,FOREST,2,"21:00","22:00","DJ MASEO (De La Soul)","hip-hop"));
        artistList.add(new Artist(2018,FOREST,2,"22:00","23:00","Skratch Bastid","turntablism"));
        artistList.add(new Artist(2018,FOREST,2,"23:00","00:00","Z-Trip","breaks,hip-hop,turntablism"));
        artistList.add(new Artist(2018,FOREST,2,"00:00","01:00","A.Skillz","bass,breaks,funk"));
        artistList.add(new Artist(2018,FOREST,2,"01:00","02:00","Gramatik","electronic"));
        artistList.add(new Artist(2018,FOREST,2,"02:00","03:00","Stanton Warriors","breaks,house"));
        artistList.add(new Artist(2018,FOREST,2,"03:00","04:00","Lady Waks","electronic"));
        artistList.add(new Artist(2018,FOREST,2,"04:00","05:00","Father Funk","bass,funk,mashup"));
        artistList.add(new Artist(2018,FOREST,2,"05:00","07:00","Neighbour","disco,house"));

        artistList.add(new Artist(2018,FOREST,3,"14:00","19:00","Fractal Funk Jam hosted by Smalltown DJs",""));
        artistList.add(new Artist(2018,FOREST,3,"19:00","20:00","The Allergies","hip-hop"));
        artistList.add(new Artist(2018,FOREST,3,"20:00","21:00","Beardyman","dance"));
        artistList.add(new Artist(2018,FOREST,3,"21:00","22:00","JFB","turntablism,hip-hop,ghetto funk,drum & bass,dubstep"));
        artistList.add(new Artist(2018,FOREST,3,"22:00","23:00","Four Color Zack",""));
        artistList.add(new Artist(2018,FOREST,3,"23:00","00:00","DJ Craze","hip-hop,trap,bass,turntablism"));
        artistList.add(new Artist(2018,FOREST,3,"00:00","00:30","2¢ (Craze + Four Color Zack)","bass"));
        artistList.add(new Artist(2018,FOREST,3,"00:30","01:30","Mix Master Mike","scratch free form"));
        artistList.add(new Artist(2018,FOREST,3,"01:30","02:30","Krafty Kuts","breaks,hip-hop"));
        artistList.add(new Artist(2018,FOREST,3,"02:30","03:30","DJ EZ","uk garage,house n garage,bass,jackin house,2step,4 to floor,4x4,bassline from the past present and future"));
        artistList.add(new Artist(2018,FOREST,3,"03:30","06:00","Dr. Fresch","g house"));
        artistList.add(new Artist(2018,FOREST,3,"06:00","08:00","Justin Hale","forms of house"));
        artistList.add(new Artist(2018,FOREST,3,"08:00","12:00","Rich-E-Rich","my sets are like a box of chocolates"));

        artistList.add(new Artist(2018,LIVINGROOM,0,"11:00","12:00","Opening Ceremony w/ Joaqopelli",""));
        artistList.add(new Artist(2018,LIVINGROOM,0,"12:00","14:00","Everyman","electronic,hip-hop"));
        artistList.add(new Artist(2018,LIVINGROOM,0,"14:00","17:00","Smalltown DJs with Lisa Lobsinger and Shad","house"));
        artistList.add(new Artist(2018,LIVINGROOM,0,"17:00","19:00","MonkeyTwerk","sexy times,party music"));
        artistList.add(new Artist(2018,LIVINGROOM,0,"19:00","20:30","BOGL","bass"));
        artistList.add(new Artist(2018,LIVINGROOM,0,"20:30","22:00","That African","head nod,hip-hop"));
        artistList.add(new Artist(2018,LIVINGROOM,0,"22:00","23:30","NICO LUMINOUS",""));
        artistList.add(new Artist(2018,LIVINGROOM,0,"23:30","01:00","Electropical w/ Nadi","tropical bass"));
        artistList.add(new Artist(2018,LIVINGROOM,0,"01:00","03:00","Rollie Fingers",""));

        artistList.add(new Artist(2018,LIVINGROOM,1,"12:00","13:00","Marty Carter","insturmental jazz fusion electronic world music"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"13:00","16:00","DJ Foxy Moron","45s! and all things funky and weird"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"16:00","17:30","Re:Me",""));
        artistList.add(new Artist(2018,LIVINGROOM,1,"17:30","19:00","El Buho","latin american folk,cumbia deep,tropical,owlbass,andino,global,organic electronic"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"19:00","21:00","JPOD","melodic bass"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"21:00","23:00","The Librarian","bass"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"23:00","00:30","Dimond Saints Live","bass"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"00:30","02:00","Soohan","global bass"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"02:00","04:00","Barisone","bass,hip-hop,dancehall"));
        artistList.add(new Artist(2018,LIVINGROOM,1,"04:00","07:00","Gr◯un土 a.k.a DJ GROUND","experimental slow house"));

        artistList.add(new Artist(2018,LIVINGROOM,2,"12:00","13:00","Bellyfit Workshop",""));
        artistList.add(new Artist(2018,LIVINGROOM,2,"13:00","14:00","Rowan","deep house"));
        artistList.add(new Artist(2018,LIVINGROOM,2,"14:00","16:00","Electropical w/ Nadi","tropical bass"));
        artistList.add(new Artist(2018,LIVINGROOM,2,"16:00","18:00","Meowmix","bass"));
        artistList.add(new Artist(2018,LIVINGROOM,2,"18:00","20:00","Mateeson",""));
        artistList.add(new Artist(2018,LIVINGROOM,2,"20:00","22:00","DJ Shoe",""));
        artistList.add(new Artist(2018,LIVINGROOM,2,"22:00","00:00","Tyler Stadius","house"));
        artistList.add(new Artist(2018,LIVINGROOM,2,"00:00","03:00","Mark Farina","house,deep house,jackin house,mushroom jazz,edm"));
        artistList.add(new Artist(2018,LIVINGROOM,2,"03:00","06:00","Eddie C","electronic"));
        artistList.add(new Artist(2018,LIVINGROOM,2,"06:00","07:00","Sijay","sleepstep,chill bass,ambient"));

        artistList.add(new Artist(2018,LIVINGROOM,3,"12:00","13:30","Michael Fraser & the Black Dog String Quartet","electronic,dance,house"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"13:30","14:30","TBA",""));
        artistList.add(new Artist(2018,LIVINGROOM,3,"14:30","16:30","Adham Shaikh Presents MonkeyDragon","electro,dub,funk"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"16:30","18:30","S2","emo bap,psy jersy"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"18:30","20:00","El Papachango","bass,tropical,world sound"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"20:00","22:00","SaQi","live electronic,soul"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"22:00","00:00","Dirtwire","global electro acoustic"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"00:00","02:00","Jeremy Sole","multicultural global bass"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"02:00","05:00","Psilosamples","electronic"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"05:00","07:00","29 Palms (Ooah & Boreta / The Glitch Mob)",""));
        artistList.add(new Artist(2018,LIVINGROOM,3,"07:00","08:30","Random Rab","random"));
        artistList.add(new Artist(2018,LIVINGROOM,3,"08:30","11:30","The Pride w/ Lion-S, Hoola and the Gang",""));
        artistList.add(new Artist(2018,LIVINGROOM,3,"11:30","12:00","Joaqopelli","double native american flutes live on tribal bass trap"));

        artistList.add(new Artist(2018,AMPHITHEATER,0,"12:00","13:00","Def3 & Brix",""));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"13:00","14:30","Pigeon Hole","west coast bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"14:30","15:30","JLeon","west coast bass,dubstep,halftime,drum & bass,experimental"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"15:30","17:00","Jvmpkicks","bass house"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"17:00","18:30","SkiiTour","upbeat funk fun party music"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"18:30","19:30","Stupid Beach",""));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"19:30","20:30","Lion S","bass,downtempo"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"20:30","21:30","Footprints","vibes & stuff"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"21:30","23:00","Digital Ethos","bass,dubstep,hip-hop,experimental"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"23:00","00:30","TLZMN","half time"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"00:30","02:00","BORN DIRTY","house"));
        artistList.add(new Artist(2018,AMPHITHEATER,0,"02:00","03:00","Longwalkshortdock","Electronic"));

        artistList.add(new Artist(2018,AMPHITHEATER,1,"16:30","17:30","Marin Patenaude","confessional folk,country,jazz,soul"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"17:30","19:00","Onhell","leftfeild bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"19:00","20:30","Liquid Stranger","free form bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"20:30","22:00","Tsuruda","electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"22:00","23:30","Stooki Sound","electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"23:30","01:00","Barclay Crenshaw","metaphysical slow jams"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"01:00","02:00","DJ Qbert","turntablism,alternative hip hop"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"02:00","03:00","Space Jesus","bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"03:00","04:00","Whipped Cream","electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,1,"04:00","05:00","Anna Morgan","bass feels"));

        artistList.add(new Artist(2018,AMPHITHEATER,2,"15:00","16:00","JodieB","blues,hip-hop,electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"16:00","17:00","Melanie Dekker",""));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"17:00","17:30","Box of Beats","electronic,vocal looper"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"17:30","19:00","Levrige","uptempo bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"19:00","20:30","Greazus","bass,drum & bass,jungle,slow/fast,hip-hop,footwork,grime"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"20:30","21:30","1985 Label Showcase - Monty","drum & bass,halftime"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"21:30","22:30","Skeptical","drum & bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"22:30","00:00","Alix Perez","drum & bass,halftime"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"00:00","01:30","Chimpo","bass,jungle,grime"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"01:30","03:00","Roska","uk funky"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"03:00","04:30","Joker","electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,2,"04:30","05:30","Khiva","dubstep"));

        artistList.add(new Artist(2018,AMPHITHEATER,3,"15:00","15:30","Sullust","deep dubstep,halftime,drum & bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"15:30","16:00","David Pelvis","deep teach house"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"16:00","16:30","Jellynote","experimental bass,trap"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"16:30","17:00","Shylow","future bass,trap"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"17:00","17:30","Dekker","halftime,neuro bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"17:30","18:30","Konka","experimental bass,halftime,garage"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"18:30","19:30","Tripzy Leary","cyberdelic bass,extra terrestrial sound,outter space"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"19:30","21:00","Gangus","electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"21:00","22:30","Woolymammoth","experimental bass"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"22:30","00:00","Machinedrum","electronic"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"00:00","01:30","Oshi","edm"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"01:30","03:00","Mr. Carmack","electronic,hip-hop"));
        artistList.add(new Artist(2018,AMPHITHEATER,3,"03:00","05:00","Yheti B2B Toadface","b2b"));

        artistList.add(new Artist(2018,GROVE,1,"13:00","14:00","Opening Ceremonies w/ Soul Fire Dance","world dance productions and prayer performances"));
        artistList.add(new Artist(2018,GROVE,1,"14:00","15:00","Frase","dub house"));
        artistList.add(new Artist(2018,GROVE,1,"15:00","16:00","Naasko","deep"));
        artistList.add(new Artist(2018,GROVE,1,"16:00","17:00","Westerley","dubstep"));
        artistList.add(new Artist(2018,GROVE,1,"17:00","18:00","Dubconscious","bass,dancehall"));
        artistList.add(new Artist(2018,GROVE,1,"18:00","19:00","Metafloor","dubstep,footwork-jungle,bass"));
        artistList.add(new Artist(2018,GROVE,1,"19:00","20:00","Puar","phonk,lofi"));
        artistList.add(new Artist(2018,GROVE,1,"20:00","21:00","S2","emo bap,psy jersy"));
        artistList.add(new Artist(2018,GROVE,1,"21:00","22:15","SHINY THINGS","bass,rap,footwork,grime,deep dubstep"));
        artistList.add(new Artist(2018,GROVE,1,"22:15","22:30","Made In Alchemy","mystic experimental hip-hop"));
        artistList.add(new Artist(2018,GROVE,1,"22:30","00:00","Charlesthefirst","downtempo,bass,ambient"));
        artistList.add(new Artist(2018,GROVE,1,"00:00","01:00","Brasstracks","jazz-tronica"));
        artistList.add(new Artist(2018,GROVE,1,"01:00","02:30","Clams Casino","electronic,hip-hop,cloud rap"));
        artistList.add(new Artist(2018,GROVE,1,"02:30","04:00","J Kenzo","dubstep,drum & bass"));
        artistList.add(new Artist(2018,GROVE,1,"04:00","05:30","Daega Sound","bass"));
        artistList.add(new Artist(2018,GROVE,1,"05:30","07:00","Melo.Nade","bass,west coast,midtempo,lush"));

        artistList.add(new Artist(2018,GROVE,2,"16:00","17:30","Lazy Syrup Orchestra","soul,world,hip-hop"));
        artistList.add(new Artist(2018,GROVE,2,"17:30","18:30","NGHTSHFT","trap,grime"));
        artistList.add(new Artist(2018,GROVE,2,"18:30","19:30","GLYDE","nature bass"));
        artistList.add(new Artist(2018,GROVE,2,"19:30","20:30","Leland Riiivr","intelligent bass"));
        artistList.add(new Artist(2018,GROVE,2,"20:30","21:45","Max Ulis","bass"));
        artistList.add(new Artist(2018,GROVE,2,"21:45","22:00","Subscura","performance art"));
        artistList.add(new Artist(2018,GROVE,2,"22:00","23:30","Mat The Alien x Librarian",""));
        artistList.add(new Artist(2018,GROVE,2,"23:30","01:00","D Double E with Greazus","grime"));
        artistList.add(new Artist(2018,GROVE,2,"01:00","02:30","Kahn and Neek","dance,electronic"));
        artistList.add(new Artist(2018,GROVE,2,"02:30","04:00","TBA",""));
        artistList.add(new Artist(2018,GROVE,2,"04:00","05:30","Goopsteppa","gangsta ambient"));
        artistList.add(new Artist(2018,GROVE,2,"05:30","07:00","PRSN","bass,hip-hop,dancehall"));

        artistList.add(new Artist(2018,GROVE,3,"18:00","19:00","Emma Star","future garage,deep bass"));
        artistList.add(new Artist(2018,GROVE,3,"19:00","20:00","IMUR",""));
        artistList.add(new Artist(2018,GROVE,3,"20:00","21:15","Hoola","disco house,dub,dancehall,hip-hop"));
        artistList.add(new Artist(2018,GROVE,3,"21:15","22:30","Moontricks","live electronic roots"));
        artistList.add(new Artist(2018,GROVE,3,"22:30","23:00","Circus Acts Insomniacs","performance artists"));
        artistList.add(new Artist(2018,GROVE,3,"23:00","00:00","IHF","electronic"));
        artistList.add(new Artist(2018,GROVE,3,"00:00","01:30","Fakear","electronic"));
        artistList.add(new Artist(2018,GROVE,3,"01:30","03:00","Christian Löffler","electronic"));
        artistList.add(new Artist(2018,GROVE,3,"03:00","06:00","Chord Marauders with FLO","bass"));
        artistList.add(new Artist(2018,GROVE,3,"06:00","07:30","Barisone","bass,hip-hop,dancehall"));
        artistList.add(new Artist(2018,GROVE,3,"07:30","09:00","Application","glitch hop,midtempo"));

        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"21:00","22:30","Willa","bass"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"22:30","00:00","J Jonah",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"00:00","01:30","Naturalist","house,disco"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"01:30","03:00","Mr.Diggler",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"03:00","04:30","Theo Tzu","bass"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"04:30","05:30","More Like Space","downtempo,psychedelic,dreamy,experimental,space bass"));

        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"21:00","22:30","Feeals","bass"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"22:30","00:00","Lazouli","downtempo,bass"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"00:00","01:30","OBESØN","electronic"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"01:30","03:00","Edamame","float music,lofi,ambient,downtempo,chill"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"03:00","04:30","Drumspyder","dance"));

        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"21:00","22:30","Pete Moss",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"22:30","00:00","SEATHTRON","heavy chill,bass,dub"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"00:00","01:30","Siadic","deep bass"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"01:30","03:00","Matsya","deep,eclectic"));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"03:00","04:30","Commoddity","deep bass"));

        artistList.add(new Artist(2018,PAGODA,1,"15:00","16:00","Wallis","house"));
        artistList.add(new Artist(2018,PAGODA,1,"16:00","17:00","Logan Hart","jazzy,jackin house"));
        artistList.add(new Artist(2018,PAGODA,1,"17:00","18:30","DeBlock","bassline house"));
        artistList.add(new Artist(2018,PAGODA,1,"18:30","20:00","Maximono","house,tech house"));
        artistList.add(new Artist(2018,PAGODA,1,"20:00","21:00","DJ Soup","disco,house"));
        artistList.add(new Artist(2018,PAGODA,1,"21:00","22:00","FISHER","house"));
        artistList.add(new Artist(2018,PAGODA,1,"22:00","23:30","Kyle Watson","tech house"));
        artistList.add(new Artist(2018,PAGODA,1,"23:30","01:00","Claptone","house"));
        artistList.add(new Artist(2018,PAGODA,1,"01:00","03:00","Justin Martin","house,techno"));
        artistList.add(new Artist(2018,PAGODA,1,"03:00","04:30","Claude VonStroke","electronic"));
        artistList.add(new Artist(2018,PAGODA,1,"04:30","06:00","Christian Martin","tech house"));

        artistList.add(new Artist(2018,PAGODA,2,"16:00","17:00","KOLT.","deep house,future"));
        artistList.add(new Artist(2018,PAGODA,2,"17:00","18:00","KO",""));
        artistList.add(new Artist(2018,PAGODA,2,"18:00","19:00","Ryan Wells","house"));
        artistList.add(new Artist(2018,PAGODA,2,"19:00","20:30","Mat the Alien","multie genre dj set"));
        artistList.add(new Artist(2018,PAGODA,2,"20:30","21:30","Dabin","melodic,dubstep"));
        artistList.add(new Artist(2018,PAGODA,2,"21:30","23:00","Kursa",""));
        artistList.add(new Artist(2018,PAGODA,2,"23:00","00:00","Defunk","glitch,funk,bass,hip-hop"));
        artistList.add(new Artist(2018,PAGODA,2,"00:00","01:30","Adventure Club","dubstep,electronic"));
        artistList.add(new Artist(2018,PAGODA,2,"01:30","03:00","Koan Sound","electronic"));
        artistList.add(new Artist(2018,PAGODA,2,"03:00","04:00","Boogie T",""));
        artistList.add(new Artist(2018,PAGODA,2,"04:00","06:00","Destructo","g house"));

        artistList.add(new Artist(2018,PAGODA,3,"16:00","17:00","Mary Jane","drum & bass,halftime"));
        artistList.add(new Artist(2018,PAGODA,3,"17:00","18:00","Trip A",""));
        artistList.add(new Artist(2018,PAGODA,3,"18:00","19:30","Kali Yuga & The Genesa Project","bass,trap,glitch"));
        artistList.add(new Artist(2018,PAGODA,3,"19:30","21:00","Chuurch","lean bass"));
        artistList.add(new Artist(2018,PAGODA,3,"21:00","22:30","BlackGummy","electro,techno"));
        artistList.add(new Artist(2018,PAGODA,3,"22:30","00:00","Black Tiger Sex Machine","electro,dubstep"));
        artistList.add(new Artist(2018,PAGODA,3,"00:00","01:30","Feed Me","electronic"));
        artistList.add(new Artist(2018,PAGODA,3,"01:30","02:30","REZZ","electronic"));
        artistList.add(new Artist(2018,PAGODA,3,"02:30","04:00","Malaa","music"));
        artistList.add(new Artist(2018,PAGODA,3,"04:00","05:00","QUIX","bass"));
        artistList.add(new Artist(2018,PAGODA,3,"05:00","06:30","Ben Fox",""));

        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }

    public ArrayList<Artist> get2018CedarLoungeArtistsUpdate() {

        ArrayList<Artist> artistList = new ArrayList<>();

        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"15:30","17:00","Herbal Medicine For Festival Vitality - Origins Apothecary",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,1,"17:00","18:00","Intro to Permaculture - Ariel",""));

        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"11:00","12:30","New Moon Yoga - Lynnsey Stardust",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"12:30","14:00","Acro Yoga - Momentom Collective",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"14:00","15:30","Contact Improv Dance - Matsya",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"15:30","17:00","Intro To Music Production - Riddim Fernandez",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,2,"17:00","18:30","Intro To Music Production - Riddim Fernandez",""));

        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"12:00","12:30","Yoga - Momentum Collective (11:00am)",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"12:30","14:00","Breathwave - Chelsea Wolff",""));
        artistList.add(new Artist(2018,CEDAR_LOUNGE,3,"14:00","15:30","Raving 101 - Giovanni Jaft",""));

        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }

    public ArrayList<Artist> get2016CedarLoungeArtists() {

        ArrayList<Artist> artistList = new ArrayList<>();

        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "14:00", "15:00", "Soniko: The Power Of A Seed", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "15:00", "16:30", "Yoga of Bass Talk w/ FreQ Nasty", "bass,bass yoga,meditation"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "16:30", "18:00", "Dash: Live Looping", "funk,hip hop,midtempo"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "18:00", "19:30", "Acro Yoga Jam", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "20:00", "21:00", "Slohand", "deep house"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "21:00", "22:00", "J Jonah", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "22:00", "23:00", "Willa", "anthem,chill,drum & bass,dubstep,glitch hop,hip hop"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "23:00", "00:30", "Dash", "funk,hip hop,midtempo"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "00:30", "02:00", "Late Night Radio", "electronic,funk,hip hop,soul"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "02:00", "03:30", "R@ngo", "deep house,funky,house,nu disco,tech house"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "03:30", "05:00", "Mr Diggler", "deep house,funky,house,nu disco,tech house"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 1, "10:00", "11:30", "SubPac Sound Healing w/ FreQ Nasty", ""));

        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "11:00", "11:30", "SubPac Sound Healing w/ FreQ Nasty (10am-11:30am)", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "11:30", "13:00", "Breathing Ground: Yin Yoga w Ashleigh Burns & Matsya", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "13:00", "14:30", "David 'Avocado' Wolfe Unplugged", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "14:30", "15:30", "Tactical Audio w/ FreQ Nasty", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "15:30", "17:00", "Thisissami", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "21:00", "23:00", "Gnome Party: Footprints, Pete Moss, Seathtron, Dirty Beat Farmer", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "23:00", "00:00", "OQO", "cinematic,operatic,synthesizer,voice"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "00:00", "01:30", "Matsya", "ambient,bass"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "01:30", "03:00", "AtYyA", "bass,psydub"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 2, "03:00", "04:30", "Soulacybin", "psydub"));

        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "11:30", "13:30", "Church of Reggae Yoga", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "13:30", "15:00", "Neil Johnson: Permaculture - The Art (and Science) of Life", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "15:00", "16:00", "Mads Alice: Mindful Eating", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "16:00", "17:30", "Vespers: Electronic Music Production & Songwriting", ""));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "20:30", "21:30", "Mr B", "pop appeal,underground sensibility"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "21:30", "22:30", "Frase", "future soul"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "22:30", "23:30", "Hachey The MouthPEACE", "beatbox,live looping"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "23:30", "01:00", "Sam Klass", "rock loops,trippy funk"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "01:00", "02:30", "Das Booty", "bass,breaks,house"));
        artistList.add(new Artist(2016, CEDAR_LOUNGE, 3, "02:30", "04:00", "Westerley", "dubstep"));


        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }
}

