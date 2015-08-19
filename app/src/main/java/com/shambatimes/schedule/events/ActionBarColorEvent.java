package com.shambatimes.schedule.events;

public class ActionBarColorEvent {
    private int color;
    private int stage;

    public ActionBarColorEvent(int color, int stage) {
        this.color = color;
        this.stage = stage;
    }

    public void RequestColor() {

    }

    public int getColor() {
        return (color);
    }

    public int getStage() {
        return (stage);
    }

}