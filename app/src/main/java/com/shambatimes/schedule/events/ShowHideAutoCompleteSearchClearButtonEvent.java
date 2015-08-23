package com.shambatimes.schedule.events;

public class ShowHideAutoCompleteSearchClearButtonEvent {
    boolean isShowClearButton;


    public ShowHideAutoCompleteSearchClearButtonEvent(boolean isShowClearButton) {
        this.isShowClearButton = isShowClearButton;
    }

    public boolean isShowClearButton(){
        return isShowClearButton;
    }
}