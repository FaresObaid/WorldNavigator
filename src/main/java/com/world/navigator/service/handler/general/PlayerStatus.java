package com.world.navigator.service.handler.general;

import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

import java.util.HashMap;
import java.util.Map;

public class PlayerStatus extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();

        request.addMessageToResponse("mode = " + player.getMode());
        request.addMessageToResponse("direction = " + player.getDirection());
        request.addMessageToResponse("room = " + player.getCurrentRoom().getRoomID());

        HashMap<String, Item> items = player.getAllItems();

        for(Map.Entry<String, Item> entry : items.entrySet()){
            if(entry.getValue() instanceof Currency)
                request.addMessageToResponse(entry.getKey() + " = " + ((Currency) entry.getValue()).getAmount());
        }

        for(Map.Entry<String, Item> entry : items.entrySet()){
            if(!(entry.getValue() instanceof Currency))
                request.addMessageToResponse(entry.getKey());
        }
    }
}
