package com.shambatimes.schedule.events;

public class ToggleToGridEvent {
    private int time = 0;

    public ToggleToGridEvent(int time) {
        this.time = time;
    }

    public int getTime() {
        return (time);
    }
}