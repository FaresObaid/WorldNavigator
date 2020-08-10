package com.world.navigator.domain.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.world.navigator.domain.item.Gold;
import com.world.navigator.domain.item.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends Wall {
    private ArrayList<Goods> goods;

    @JsonCreator
    public Seller(@JsonProperty("goods") ArrayList<Goods> goods){
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "seller";
    }

    public ArrayList<Goods> getAllGoods(){
        return goods;
    }

    public Goods getGoodsAt(int index){
        return goods.get(index);
    }
}
