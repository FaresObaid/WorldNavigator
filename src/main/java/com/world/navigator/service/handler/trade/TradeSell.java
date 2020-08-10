package com.world.navigator.service.handler.trade;

import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.maze.Goods;
import com.world.navigator.domain.maze.Seller;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class TradeSell extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        Seller seller = (Seller) request.getPlayer().getFacedWall();

        try {
            int indexOfItem = Integer.parseInt(request.getArgumentAt(1));
            indexOfItem--;
            Goods goods = seller.getGoodsAt(indexOfItem);
            Item item = goods.getItem();
            Currency itemPrice = goods.getPrice();
            Currency playerMoney = (Currency) player.getItem(itemPrice.toString());

            if(player.getItem(item.toString()) == null){
                request.addMessageToResponse("you don\'t have this item");
            }else{
                playerMoney.increase(itemPrice.getAmount());
                player.removeItem(item.toString());
                request.addMessageToResponse("your deal is done");
            }
        }catch(NumberFormatException e){
            request.addMessageToResponse("the index of item must be number");
            return;
        }catch (IndexOutOfBoundsException e){
            request.addMessageToResponse("there is no such item");
            return;
        }
    }
}
