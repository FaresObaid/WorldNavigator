package com.world.navigator.service.handler.normal.check;

import com.world.navigator.domain.cache.Chest;
import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Flashlight;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.item.Lock;
import com.world.navigator.domain.maze.Wall;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class CheckChest extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        Wall wall = player.getFacedWall();

        if(!(wall instanceof Chest)){
            request.addMessageToResponse("There is no chest in front of you");
            return;
        }

        Chest chest = (Chest) wall;

        if(chest.isOpen() && chest.isContainContent()){
            Item item = chest.lootContent().getItem();
            if((item instanceof Lock) || (item instanceof Flashlight)){
                player.addItem(item.toString(), item);
                request.addMessageToResponse(item.toString() + " is looted");
            }else if(item instanceof Currency){
                Currency currency= (Currency) player.getItem(item.toString());
                currency.increase(((Currency) item).getAmount());
                request.addMessageToResponse(((Currency) item).getAmount() + " " + ((Currency) item).toString() + " is looted");
            }
        }else if(!chest.isContainContent()){
            request.addMessageToResponse("nothing to be looted");
        }else{
            request.addMessageToResponse("Chest closed, " + chest.getLockType().toString() + " is needed to unlock");
        }
    }
}
