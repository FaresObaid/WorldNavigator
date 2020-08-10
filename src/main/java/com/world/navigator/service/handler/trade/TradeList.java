package com.world.navigator.service.handler.trade;

import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.maze.Goods;
import com.world.navigator.domain.maze.Seller;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

import java.util.ArrayList;

class TradeList extends CommandHandler {
    @Override
    public void handle(Request request) {
        Seller seller = (Seller) request.getPlayer().getFacedWall();
        ArrayList<Goods> goods = seller.getAllGoods();

        for(int i = 0;i < goods.size();++i){
            Item item = goods.get(i).getItem();
            Currency currency = goods.get(i).getPrice();

            request.addMessageToResponse((i + 1) + " " + item.toString() + " " + currency.getAmount());
        }
    }
}