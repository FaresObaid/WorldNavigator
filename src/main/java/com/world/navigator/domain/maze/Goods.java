package com.world.navigator.domain.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.world.navigator.domain.item.Gold;
import com.world.navigator.domain.item.Item;
import lombok.Data;

@Data
public class Goods {
    private Item item;
    private Gold price;

    @JsonCreator
    public Goods(@JsonProperty("item") Item item,@JsonProperty("amount") Integer amount){
        this.item = item;
        this.price = new Gold(amount);
    }
}
