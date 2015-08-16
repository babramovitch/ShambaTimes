package com.shambatimes.schedule.events;

public class ToggleToStageEvent {
    private int stage = 0;
    private String name;
    public ToggleToStageEvent(int position, String name) {
        this.stage = position;
        this.name = name;
    }

    public int getStage() {
        return (stage);
    }

    public String getName(){
        return (name);
    }
}