package com.world.navigator.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gold extends Currency {

    @JsonCreator
    public Gold(@JsonProperty("amount") int amount){
        super(amount);
    }
    @Override
    public String toString() {
        return "gold";
    }
}
