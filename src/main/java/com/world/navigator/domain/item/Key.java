package com.world.navigator.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Key extends Lock {
    private String name;

    @JsonCreator
    public Key(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Key)) {return false;}

        if(obj == this) {return true;}

        Key k = (Key) obj;

        return this.name.equals(k.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
