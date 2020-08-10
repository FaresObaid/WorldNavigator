package com.world.navigator.domain.frontage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.world.navigator.domain.item.Key;
import com.world.navigator.domain.item.LockFactory;

public class MirrorWithOneKey extends Frontage{
    private boolean hidden;
    private Key key;

    @JsonCreator
    public MirrorWithOneKey(
            @JsonProperty("hidden") boolean hidden,
            @JsonProperty("key") String keyName
    ){
        this.hidden = hidden;
        this.key = (Key) LockFactory.getLock(keyName);
    }

    public MirrorWithOneKey(){
        this.hidden = false;
    }

    public void setKey(Key key) {
        this.key = key;
        this.hidden = true;
    }

    public boolean foundKey(){
        return hidden == true;
    }

    public Key takeKey(){
        if(this.hidden == false) return null;
        this.hidden = false;
        return this.key;
    }

    @Override
    public String toString() {
        return "You See a silhouette of you";
    }
}
