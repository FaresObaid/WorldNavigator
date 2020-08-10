package com.world.navigator.domain.cache;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.world.navigator.domain.item.Item;

public class ContentWithOneItem extends Content {

    @JsonCreator
    public ContentWithOneItem(@JsonProperty("item") Item item) {
        super(item);
    }
}
