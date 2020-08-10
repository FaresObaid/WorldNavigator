package com.world.navigator.domain.cache;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.world.navigator.domain.item.Item;

import java.util.LinkedList;
import java.util.Queue;

public class ContentWithMultipleItem extends Content {
    Queue<Item> items;

    public ContentWithMultipleItem(Item item) {
        super(item);
        items = new LinkedList<>();
        this.items.add(item);
    }

    public void setMoreItem(Item item){
        if(items.size() == 0) super.setItem(item);
        items.add(item);
    }

    public boolean containMoreItems(){
        return super.getItem() != null;
    }

    @Override
    public Item getItem() {
        //the first element in the queue must be in the "item" variable in super class
        Item item = super.getItem();
        super.setItem(items.poll());
        return item;
    }
}
