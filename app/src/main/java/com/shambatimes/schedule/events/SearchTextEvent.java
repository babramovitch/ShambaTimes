package com.shambatimes.schedule.events;

public class SearchTextEvent {
    String searchText;


    public SearchTextEvent(String searchText) {
        this.searchText = searchText;
    }



    public String getSearchText(){
        return searchText;
    }
}