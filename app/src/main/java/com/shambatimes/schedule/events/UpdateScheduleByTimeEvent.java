package com.shambatimes.schedule.events;

public class UpdateScheduleByTimeEvent {
    private int position;

    public UpdateScheduleByTimeEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return (position);
    }
}