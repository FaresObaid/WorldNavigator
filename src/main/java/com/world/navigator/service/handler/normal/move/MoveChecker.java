package com.world.navigator.service.handler.normal.move;

import com.world.navigator.domain.cache.ContentWithMultipleItem;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.maze.Door;
import com.world.navigator.domain.maze.Room;

import java.util.HashMap;
import java.util.Map;

class MoveChecker {
    Player player;
    Door door;
    Request request;
    Room source;
    Room destination;

    public MoveChecker(Player player, Door door, Request request){
        this.player = player;
        this.door = door;
        this.request = request;
        this.source = player.getCurrentRoom();
        this.destination = request.getMaze().getRoomAt(door.getOppositeDirection(this.source.getRoomID()));
    }


    public Room getSourceRoom(){
        return source;
    }
    public Room getDestinationRoom(){
        return destination;
    }


    public boolean preCheck(){
        if(!door.isOpen()){
            request.addMessageToResponse("the door is closed");
            return false;
        }

        if(destination.getPlayers().size() == 2) {
            request.addMessageToResponse("there is a fight in this room, wait until the fight end");
            return false;
        }
        source.getPlayers().remove(player);
        destination.getPlayers().add(player);

        player.setCurrentRoom(destination);

        //automatically, loot the contents on the floor if any
        ContentWithMultipleItem contents = destination.lootFloor();

        if(contents != null){
            while (contents.containMoreItems()){
                Item item = contents.getItem();
                player.addItem(item.toString(), item);
            }
            request.addMessageToResponse("you loot some contents, check you status");
        }

        return true;
    }

    public void postCheck(){
        //after the player moved to destination and the number of player become 2,
        // these two player should enter fight mode
        if(destination.getPlayers().size() == 2){
            Player enemy = destination.getPlayers().get(0);
            enemy.setMode("fight");
            player.setMode("fight");
            request.addMessageToResponse("you enter the fight mode");
            return;
        }

        //if the player reach exit room then the player will win and all other players in maze get lost
        if(destination.isExitRoom()){
            HashMap<String, Player> players = request.getGame().getAllPlayers();
            for(Map.Entry<String, Player> entry:players.entrySet()){
                if(entry.getValue() != player) entry.getValue().setMode("lose");
            }
            player.setMode("win");
            request.addMessageToResponse("you won the game");
        }
    }
}
