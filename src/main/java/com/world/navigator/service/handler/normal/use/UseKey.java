package com.world.navigator.service.handler.normal.use;

import com.world.navigator.domain.cache.Chest;
import com.world.navigator.domain.item.Key;
import com.world.navigator.domain.maze.Door;
import com.world.navigator.domain.maze.Wall;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class UseKey extends CommandHandler {
    @Override
    public void handle(Request request) {
        String keyName = request.getArgumentAt(2);
        Player player = request.getPlayer();
        Key key = (Key) player.getItem(keyName);

        if(key != null){
            Wall wall = player.getFacedWall();
            if(wall instanceof Chest){
                if(((Chest) wall).isOpen()) request.addMessageToResponse("the chest is already open");
                else if(((Chest) wall).openCashe(key)){
                    request.addMessageToResponse("the chest is now open");
                }else request.addMessageToResponse("can not open this chest using this key");
            }else if(wall instanceof Door){
                if(((Door) wall).isOpen()) request.addMessageToResponse("the door is already open");
                else if(((Door) wall).openDoor(key)){
                    request.addMessageToResponse("the door is now open");
                }else request.addMessageToResponse("can not open this door using this key");
            }else{
                request.addMessageToResponse("there is no door or chest in front of you");
            }
        }else{
            request.addMessageToResponse("there is no such key with you");
        }
    }
}
