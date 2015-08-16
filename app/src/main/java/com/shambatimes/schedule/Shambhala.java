package com.shambatimes.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Ben on 14/02/2015.
 */
 public class  Shambhala {

    ArrayList<Artist> artists = new ArrayList<Artist>();
    private final String TAG = "Shambhala";

    public Shambhala() {
    }


    public Artist getArtistsByDayAndPositionAndStage(int day, int position, int stage){

        for(Artist currentArtist : artists){

            int endPosition = currentArtist.getEndPosition() == 0 ? 48 : currentArtist.getEndPosition(); //Start 11AM - 11AM = 0, so we need to manually set the last position

            if(currentArtist.getDay() == day && position >= currentArtist.getStartPosition() && position < endPosition){
                if(stage == currentArtist.getStage()) {
                    return currentArtist;
                }
            }
        }
        return null;
    }

    public ArrayList<Artist> getArtistsByDayAndStage(int day, int stage){

        ArrayList<Artist> artistsByDay = new ArrayList<>();

        for(Artist currentArtist : artists){
            if(currentArtist.getDay() == day && currentArtist.getStage() == stage){
                artistsByDay.add(currentArtist);
            }
        }


        Collections.sort(artistsByDay, new Comparator<Artist>() {
            @Override
            public int compare(Artist lhs, Artist rhs) {
                int o1 = rhs.getStartPosition();
                int o2  = lhs.getStartPosition();
                return (o1>o2 ? -1 : (o1==o2 ? 0 : 1));
            }
        });

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

    public boolean updateArtistById(long id){
        for(int x = 0; x < artists.size(); x ++){
            if(artists.get(x).getId() == id){
                artists.set(x,Artist.findById(Artist.class, id));
                return true;
            }
        }
        return false;
    }

    public void addArtistAtPosition(int position, long id){
        artists.add(position, Artist.findById(Artist.class, id));
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }


}
