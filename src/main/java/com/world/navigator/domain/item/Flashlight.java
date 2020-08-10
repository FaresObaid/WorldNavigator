package com.world.navigator.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Flashlight extends Item {
    private boolean status;
    private boolean on;
    private boolean off;

    @JsonCreator
    public Flashlight() {
        this.status = false;
        this.on = true;
        this.off = false;
    }

    public boolean isOn() {
        return this.status == this.on;
    }

    public void switchOn() {
        this.status = this.on;
    }

    public void switchOff() {
        this.status = this.off;
    }

    @Override
    public String toString() {
        return "flashlight";
    }
}
