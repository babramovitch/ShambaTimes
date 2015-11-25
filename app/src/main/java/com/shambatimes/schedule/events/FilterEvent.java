package com.shambatimes.schedule.events;

import java.util.ArrayList;

public class FilterEvent {
    ArrayList<String> filterList;

    public FilterEvent(ArrayList<String> filterList) {
        this.filterList = filterList;
    }

    public ArrayList<String> getGenreFilterList(){
        return filterList;
    }
}