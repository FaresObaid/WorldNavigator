package com.world.navigator.domain.cache;

import com.world.navigator.domain.item.Item;

public abstract class Content {
    private Item item;

    public Content(Item item) {
        this.item = item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
