package com.shambatimes.schedule.events;

public class ToggleFilterVisibility {

    boolean isVisible;


    public ToggleFilterVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return (isVisible);
    }
}