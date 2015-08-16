package com.shambatimes.schedule.events;

public class ToggleToTimeEvent {
    private int time = 0;

    public ToggleToTimeEvent(int time) {
        this.time = time;
    }

    public int getTime() {
        return (time);
    }
}