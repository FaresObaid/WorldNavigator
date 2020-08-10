package com.world.navigator.domain.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public abstract class Currency extends Item {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void increase(int amount){
        this.amount += amount;
    }

    public void decrease(int amount){
        this.amount -= amount;
    }
}
