package com.world.navigator.domain.cache;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.world.navigator.domain.item.LockFactory;

public class ChestNoKeyOneItem extends Chest {

    @JsonCreator
    public ChestNoKeyOneItem(
            @JsonProperty("contents") ContentWithOneItem contents
    ) {
        super(LockFactory.getLock("NoKey"), new OpenWithKey(), contents, true);
    }

    @Override
    public String toString() {
        return "chest";
    }
}
