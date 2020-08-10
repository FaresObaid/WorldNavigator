package com.world.navigator.service.handler.general;

import com.world.navigator.domain.game.Game;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.maze.Room;
import com.world.navigator.service.handler.CommandHandler;

import java.util.HashMap;
import java.util.Map;

public class Quit extends CommandHandler {

    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        Room room = player.getCurrentRoom();
        HashMap<String, Item> items = player.getAllItems();
        Game game = request.getGame();

        for(Map.Entry<String, Item> i:items.entrySet()){
            if(i.getValue() instanceof Currency){
                game.distributeCurrency(player, (Currency) i.getValue());
            }else{
                room.putItemOnFloor(i.getValue());
                player.removeItem(i.getKey());
            }
        }

        room.getPlayers().remove(player);
    }
}
