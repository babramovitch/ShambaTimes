package com.shambatimes.schedule;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Ben on 14/02/2015.
 */


public class ArtistGenerator {

    private Context context;


    public ArtistGenerator(Context context) {

        this.context = context;

    }

    public ArrayList<Artist> getArtists() {

        ArrayList<Artist> artistList = new ArrayList<>();


        //Living Room
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "11:11", "12:00", "JoaqoPelli (Flute Ceremony)"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "12:00", "13:00", "Select-R-Us"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "13:00", "14:00", "Shasta"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "14:00", "15:00", "Cass Rhapsody"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "15:00", "16:00", "Ginger"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "16:00", "17:30", "Mich Duvernet"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "17:30", "19:00", "Hoola"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "19:00", "20:30", "Val Kilmer & The New Coke"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "20:30", "21:00", "Hoola (Band Changeover)"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "21:00", "22:00", "Sack Grabbath"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "22:00", "23:30", "Vinyl Ritchie"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "23:30", "00:30", "Dubconscious"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "00:30", "01:30", "Sam Demoe"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "01:30", "03:00", "Spiltmilk"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "03:00", "04:30", "Leif"));
        artistList.add(new Artist(Constants.LIVINGROOM, 0, "04:30", "05:30", "Akimi"));

        artistList.add(new Artist(Constants.LIVINGROOM, 1, "11:30", "13:00", "Foxy Moron"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "13:00", "14:30", "The Gaff"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "14:30", "16:00", "DJ K-Tel"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "16:00", "17:00", "Slohand"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "17:00", "18:30", "Sugarbear"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "18:30", "20:00", "Koosh"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "20:00", "00:00", "DJ Harvey"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "00:00", "01:30", "Matthew1626 & Bongofide"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "01:30", "03:00", "Mr. Moe"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "03:00", "04:30", "Lorne B"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "04:30", "06:00", "Breakfluid"));
        artistList.add(new Artist(Constants.LIVINGROOM, 1, "06:00", "08:00", "Andrew Interchill"));

        artistList.add(new Artist(Constants.LIVINGROOM, 2, "11:00", "12:00", "Marty Carter"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "12:00", "13:00", "Mama Sa"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "13:00", "14:00", "45 Seasons"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "14:00", "15:30", "Big Toes Hi-Fi"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "15:30", "17:30", "Rob Paine"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "17:30", "19:00", "BC Dubcats"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "19:00", "20:00", "Meow Mix"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "20:00", "21:00", "Spiralynn & Dislocait"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "21:00", "22:30", "Czech"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "22:30", "00:00", "Schmaltzy"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "00:00", "02:00", "Thornato"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "02:00", "04:00", "El Buho"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "04:00", "05:00", "Bassos Rancheros"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "05:00", "06:00", "The Tailor"));
        artistList.add(new Artist(Constants.LIVINGROOM, 2, "06:00", "07:00", "Gemma Luna"));

        artistList.add(new Artist(Constants.LIVINGROOM, 3, "12:00", "13:00", "Erica Dee w/ Aisha Rose"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "13:00", "14:00", "S2"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "14:00", "15:30", "Emma Star"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "15:30", "16:30", "Boomtown"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "16:30", "18:30", "Adham Shaikh"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "18:30", "19:30", "Barisone"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "19:30", "20:30", "Tiger Fresh"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "20:30", "21:30", "Tipper"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "21:30", "22:30", "Coyote Kisses"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "22:30", "23:30", "HAANA"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "23:30", "01:30", "El Papachango"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "01:30", "03:30", "Sweet Anomaly"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "03:30", "04:30", "Natural Alchemy"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "04:30", "06:00", "Stage Freight"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "06:00", "07:30", "Sijay and Syrinx"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "07:30", "11:00", "Lion-S"));
        artistList.add(new Artist(Constants.LIVINGROOM, 3, "11:00", "12:00", "JoaqoPelli (Flute Ceremony)"));

        //Amphitheater
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "12:00", "13:30", "Bryx"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "13:30", "15:00", "Timothy Wisdom"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "15:00", "16:30", "Skiitour"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "16:30", "18:00", "jOBOT"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "18:00", "19:30", "MORiLLO"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "19:30", "21:00", "Space Jesus"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "21:00", "22:30", "Self Evident"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "22:30", "00:00", "Bleep Bloop"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "00:00", "01:30", "G Jones"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "01:30", "03:00", "Ana Sia"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "03:00", "04:30", "David Starfire"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 0, "04:30", "05:30", "Greazus"));

        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "15:00", "15:30", "Jodie Bruce"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "15:30", "16:00", "Gemma Luna"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "16:00", "16:30", "Daniel Huscroft"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "16:30", "18:00", "Fedski"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "18:00", "19:30", "Perkulat0r"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "19:30", "21:00", "Ekali"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "21:00", "22:30", "Andrea"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "22:30", "00:00", "Ganz"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "00:00", "01:30", "Mr. Carmack"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "01:30", "03:00", "B-Ju"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "03:00", "04:30", "Yan Zombie w/ Zes Nomis"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 1, "04:30", "06:00", "Eviction"));

        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "14:00", "14:30", "Sean Rodman"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "14:30", "15:00", "Kristie McCracken"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "15:00", "15:30", "Marin Patenaude"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "15:30", "16:30", "Fashion Show"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "16:30", "18:00", "Howl Sound"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "18:00", "19:30", "Cal Bass"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "19:30", "21:00", "Ryan Wells"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "21:00", "22:30", "Sabota"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "22:30", "00:00", "Worthy"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "00:00", "01:30", "Kry Wolf"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "01:30", "03:00", "Max Ulis"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "03:00", "04:30", "Petey Clicks"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 2, "04:30", "06:00", "DJ Cure"));

        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "15:00", "15:30", "Jenny Lea"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "15:30", "16:00", "Max Hawk"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "16:00", "16:30", "Windmills"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "16:30", "18:00", "Eli Muro"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "18:00", "19:30", "M!nt"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "19:30", "21:00", "Sugarpill"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "21:00", "22:20", "Huglife (9-10:20PM)"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "22:20", "22:30", "Subscura (10:20-10:30PM)"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "22:30", "00:00", "Jackal"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "00:00", "01:30", "Crnkn"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "01:30", "03:00", "heRobust"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "03:00", "04:30", "Longwalkshortdock"));
        artistList.add(new Artist(Constants.AMPHITHEATER, 3, "04:30", "06:00", "Metafloor"));

        //Pagoda
        artistList.add(new Artist(Constants.PAGODA, 1, "15:00", "16:00", "Logan Hart"));
        artistList.add(new Artist(Constants.PAGODA, 1, "16:00", "17:00", "Wallis"));
        artistList.add(new Artist(Constants.PAGODA, 1, "17:00", "18:00", "Ardalan (Dirtybird Takeover Begins!)"));
        artistList.add(new Artist(Constants.PAGODA, 1, "18:00", "19:20", "Friend Within"));
        artistList.add(new Artist(Constants.PAGODA, 1, "19:20", "20:40", "Cause & Affect"));
        artistList.add(new Artist(Constants.PAGODA, 1, "20:40", "22:00", "Kill Frenzy"));
        artistList.add(new Artist(Constants.PAGODA, 1, "22:00", "23:20", "J Phlip"));
        artistList.add(new Artist(Constants.PAGODA, 1, "23:20", "00:40", "Shiba San"));
        artistList.add(new Artist(Constants.PAGODA, 1, "00:40", "02:00", "Justin Martin"));
        artistList.add(new Artist(Constants.PAGODA, 1, "02:00", "03:20", "Claude VonStroke"));
        artistList.add(new Artist(Constants.PAGODA, 1, "03:20", "04:40", "Dusky"));
        artistList.add(new Artist(Constants.PAGODA, 1, "04:40", "06:00", "Christian Martin"));

        artistList.add(new Artist(Constants.PAGODA, 2, "16:00", "17:00", "I-Sick"));
        artistList.add(new Artist(Constants.PAGODA, 2, "17:00", "18:00", "DJ Just-B"));
        artistList.add(new Artist(Constants.PAGODA, 2, "18:00", "19:00", "Freebound"));
        artistList.add(new Artist(Constants.PAGODA, 2, "19:00", "20:00", "Neighbour"));
        artistList.add(new Artist(Constants.PAGODA, 2, "20:00", "21:30", "jackLNDN"));
        artistList.add(new Artist(Constants.PAGODA, 2, "21:30", "22:30", "Kidnap Kid"));
        artistList.add(new Artist(Constants.PAGODA, 2, "22:30", "23:30", "Destructo"));
        artistList.add(new Artist(Constants.PAGODA, 2, "23:30", "01:00", "MK"));
        artistList.add(new Artist(Constants.PAGODA, 2, "01:00", "02:30", "GRiZ"));
        artistList.add(new Artist(Constants.PAGODA, 2, "02:30", "04:00", "Mystery Headliner"));
        artistList.add(new Artist(Constants.PAGODA, 2, "04:00", "05:00", "Mija"));
        artistList.add(new Artist(Constants.PAGODA, 2, "05:00", "06:30", "Whipped Cream"));

        artistList.add(new Artist(Constants.PAGODA, 3, "16:30", "17:30", "De Block"));
        artistList.add(new Artist(Constants.PAGODA, 3, "17:30", "18:30", "Wax Romeo"));
        artistList.add(new Artist(Constants.PAGODA, 3, "18:30", "20:00", "DJ Soup"));
        artistList.add(new Artist(Constants.PAGODA, 3, "20:00", "21:30", "MÖWE"));
        artistList.add(new Artist(Constants.PAGODA, 3, "21:30", "23:00", "Bakermat"));
        artistList.add(new Artist(Constants.PAGODA, 3, "23:00", "00:30", "Thomas Jack"));
        artistList.add(new Artist(Constants.PAGODA, 3, "00:30", "02:00", "Kygo"));
        artistList.add(new Artist(Constants.PAGODA, 3, "02:00", "03:30", "Pretty Lights"));
        artistList.add(new Artist(Constants.PAGODA, 3, "03:30", "04:00", "Mat The Alien & The Librarian (3:30-5am)"));
        artistList.add(new Artist(Constants.PAGODA, 3, "04:00", "05:00", "Mat The Alien & The Librarian (3:30-5am)"));
        artistList.add(new Artist(Constants.PAGODA, 3, "05:00", "06:30", "Capn Fuzz & Ajax"));

        //FOREST

        artistList.add(new Artist(Constants.FOREST, 1, "12:00", "13:00", "Dilligent"));
        artistList.add(new Artist(Constants.FOREST, 1, "13:00", "14:00", "Wildout"));
        artistList.add(new Artist(Constants.FOREST, 1, "14:00", "15:00", "Lefy"));
        artistList.add(new Artist(Constants.FOREST, 1, "15:00", "16:30", "JGirl & Manousos"));
        artistList.add(new Artist(Constants.FOREST, 1, "16:30", "18:00", "Justin Hale"));
        artistList.add(new Artist(Constants.FOREST, 1, "18:00", "19:00", "Dan Solo"));
        artistList.add(new Artist(Constants.FOREST, 1, "19:00", "20:30", "Vinyl Ritchie"));
        artistList.add(new Artist(Constants.FOREST, 1, "20:30", "22:00", "Slynk"));
        artistList.add(new Artist(Constants.FOREST, 1, "22:00", "23:30", "Fort Knox Five"));
        artistList.add(new Artist(Constants.FOREST, 1, "23:30", "01:00", "The Funk Hunters"));
        artistList.add(new Artist(Constants.FOREST, 1, "01:00", "02:30", "Stickybuds"));
        artistList.add(new Artist(Constants.FOREST, 1, "02:30", "04:00", "Smalltown DJs"));
        artistList.add(new Artist(Constants.FOREST, 1, "04:00", "05:30", "K+Lab"));
        artistList.add(new Artist(Constants.FOREST, 1, "05:30", "07:00", "Freddy J"));
        artistList.add(new Artist(Constants.FOREST, 1, "07:00", "08:30", "Joseph Martin"));

        artistList.add(new Artist(Constants.FOREST, 2, "16:00", "18:00", "Wood N Soo"));
        artistList.add(new Artist(Constants.FOREST, 2, "18:00", "19:30", "Cosmo Baker"));
        artistList.add(new Artist(Constants.FOREST, 2, "19:30", "21:00", "Skratch Bastid"));
        artistList.add(new Artist(Constants.FOREST, 2, "21:00", "22:30", "DJ Jazzy Jeff"));
        artistList.add(new Artist(Constants.FOREST, 2, "22:30", "23:30", "Mix Master Mike"));
        artistList.add(new Artist(Constants.FOREST, 2, "23:30", "01:00", "A. Skillz"));
        artistList.add(new Artist(Constants.FOREST, 2, "01:00", "02:30", "DJ Zinc"));
        artistList.add(new Artist(Constants.FOREST, 2, "02:30", "04:00", "Jesse Rose"));
        artistList.add(new Artist(Constants.FOREST, 2, "04:00", "05:30", "Dubtribe Sound System"));
        artistList.add(new Artist(Constants.FOREST, 2, "05:30", "07:00", "Neighbour"));

        artistList.add(new Artist(Constants.FOREST, 3, "14:00", "19:00", "Fractal Funk Jam - Hosted by Smalltown DJs"));
        artistList.add(new Artist(Constants.FOREST, 3, "19:00", "20:30", "Beat Fatigue"));
        artistList.add(new Artist(Constants.FOREST, 3, "20:30", "22:00", "Jillionaire of Major Lazer"));
        artistList.add(new Artist(Constants.FOREST, 3, "22:00", "23:30", "Neon Steve"));
        artistList.add(new Artist(Constants.FOREST, 3, "23:30", "01:00", "Jack Beats"));
        artistList.add(new Artist(Constants.FOREST, 3, "01:00", "02:30", "Jauz"));
        artistList.add(new Artist(Constants.FOREST, 3, "02:30", "04:00", "AC Slater"));
        artistList.add(new Artist(Constants.FOREST, 3, "04:00", "05:30", "Amtrac"));
        artistList.add(new Artist(Constants.FOREST, 3, "05:30", "10:00", "Rich-e-Rich"));

        //GROVE

        artistList.add(new Artist(Constants.GROVE, 1, "13:00", "14:00", "Opening Ceremony w/ Soul Fire Dance"));
        artistList.add(new Artist(Constants.GROVE, 1, "14:00", "15:30", "Adham Shaikh"));
        artistList.add(new Artist(Constants.GROVE, 1, "15:30", "17:00", "Intersect"));
        artistList.add(new Artist(Constants.GROVE, 1, "17:00", "18:00", "Janover and reSunator"));
        artistList.add(new Artist(Constants.GROVE, 1, "18:00", "19:00", "Dakini"));
        artistList.add(new Artist(Constants.GROVE, 1, "19:00", "20:00", "Goopsteppa"));
        artistList.add(new Artist(Constants.GROVE, 1, "20:00", "21:00", "Westerley"));
        artistList.add(new Artist(Constants.GROVE, 1, "21:00", "22:15", "The Librarian"));
        artistList.add(new Artist(Constants.GROVE, 1, "22:15", "22:30", "Subscura"));
        artistList.add(new Artist(Constants.GROVE, 1, "22:30", "23:45", "Daega Sound"));
        artistList.add(new Artist(Constants.GROVE, 1, "23:45", "01:00", "Geode"));
        artistList.add(new Artist(Constants.GROVE, 1, "01:00", "02:30", "Dimond Saints"));
        artistList.add(new Artist(Constants.GROVE, 1, "02:30", "04:00", "Liquid Stranger"));
        artistList.add(new Artist(Constants.GROVE, 1, "04:00", "05:15", "Naasko"));
        artistList.add(new Artist(Constants.GROVE, 1, "05:15", "06:30", "Griff"));

        artistList.add(new Artist(Constants.GROVE, 2, "16:30", "17:30", "Das Booty"));
        artistList.add(new Artist(Constants.GROVE, 2, "17:30", "18:30", "J.Jonah"));
        artistList.add(new Artist(Constants.GROVE, 2, "18:30", "19:30", "Shiny Things"));
        artistList.add(new Artist(Constants.GROVE, 2, "19:30", "20:30", "Organic Mechanic"));
        artistList.add(new Artist(Constants.GROVE, 2, "20:30", "21:30", "Octaban"));
        artistList.add(new Artist(Constants.GROVE, 2, "21:30", "22:00", "Omnika"));
        artistList.add(new Artist(Constants.GROVE, 2, "22:00", "23:15", "Jpod the beat chef"));
        artistList.add(new Artist(Constants.GROVE, 2, "23:15", "00:30", "Big Wild"));
        artistList.add(new Artist(Constants.GROVE, 2, "00:30", "01:00", "Phadroid"));
        artistList.add(new Artist(Constants.GROVE, 2, "01:00", "02:00", "Tipper with Android Jones"));
        artistList.add(new Artist(Constants.GROVE, 2, "02:00", "03:30", "Leon Switch"));
        artistList.add(new Artist(Constants.GROVE, 2, "03:30", "05:00", "Synkro"));
        artistList.add(new Artist(Constants.GROVE, 2, "05:00", "06:30", "AtYyA"));

        artistList.add(new Artist(Constants.GROVE, 3, "16:30", "17:30", "Willa"));
        artistList.add(new Artist(Constants.GROVE, 3, "17:30", "18:30", "Mr. Diggler"));
        artistList.add(new Artist(Constants.GROVE, 3, "18:30", "19:30", "S. Holmes"));
        artistList.add(new Artist(Constants.GROVE, 3, "19:30", "20:45", "Footprints"));
        artistList.add(new Artist(Constants.GROVE, 3, "20:45", "22:00", "Moontricks"));
        artistList.add(new Artist(Constants.GROVE, 3, "22:00", "23:30", "Rising Appalachia"));
        artistList.add(new Artist(Constants.GROVE, 3, "23:30", "01:30", "Bonobo (DJ Set)"));
        artistList.add(new Artist(Constants.GROVE, 3, "01:30", "03:00", "Birds Of Paradise"));
        artistList.add(new Artist(Constants.GROVE, 3, "03:00", "04:30", "Biome"));
        artistList.add(new Artist(Constants.GROVE, 3, "04:30", "06:00", "Jafu"));
        artistList.add(new Artist(Constants.GROVE, 3, "06:00", "07:00", "Austero"));

        //VILLAGE
        artistList.add(new Artist(Constants.VILLAGE, 1, "14:00", "17:30", "Ragga Jungle Rinse Out - Hosted by Hush"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "17:30", "18:00", "Marcus Visionary"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "18:00", "19:00", "Liondub"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "19:00", "20:00", "Dua & Generic"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "20:00", "21:00", "Stylust Beats"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "21:00", "22:00", "ill.Gates"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "22:00", "23:00", "Subvert"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "23:00", "00:30", "Datsik"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "00:30", "02:00", "Skrillex"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "02:00", "03:00", "Downlink"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "03:00", "04:30", "Drumsound & Bassline Smith"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "04:30", "05:30", "Openend"));
        artistList.add(new Artist(Constants.VILLAGE, 1, "05:30", "06:30", "Mr. B"));

        artistList.add(new Artist(Constants.VILLAGE, 2, "13:30", "19:00", "Hip Hop Showcase ft. Grouch & Eligh"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "19:00", "20:00", "Kursa & Fat Pat"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "20:00", "21:00", "DJ Anger"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "21:00", "22:00", "Manic Focus"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "22:00", "23:00", "Etc!Etc!"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "23:00", "00:00", "Koan Sound"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "00:00", "01:30", "Terravita"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "01:30", "03:00", "Excision"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "03:00", "04:30", "Tantrum Desire"));
        artistList.add(new Artist(Constants.VILLAGE, 2, "04:30", "07:00", "Liquid Sunrise Jam"));

        artistList.add(new Artist(Constants.VILLAGE, 3, "16:30", "17:00", "Kris Cayden (WAVO Remix Contest Winner)"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "17:00", "18:00", "Tiny Dancer & kAtO"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "18:00", "19:00", "Big B"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "19:00", "20:00", "Wakcutt"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "20:00", "21:00", "ill-esha"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "21:00", "22:00", "Haywyre"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "22:00", "23:00", "Lucent Dossier Experience"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "23:00", "00:00", "Big Gigantic"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "00:00", "01:30", "Camo & Krooked"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "01:30", "03:00", "Zomboy"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "03:00", "04:30", "GTA"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "04:30", "05:30", "Spiral Architects"));
        artistList.add(new Artist(Constants.VILLAGE, 3, "05:30", "06:30", "Scottie D"));


        for (Artist artist : artistList) {
            artist.save();
        }

        return artistList;
    }


}
