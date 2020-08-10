package com.world.navigator.service.handler.normal.check;

import com.world.navigator.domain.maze.Door;
import com.world.navigator.domain.maze.Wall;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class CheckDoor extends CommandHandler {
    @Override
    public void handle(Request request) {
        Wall wall = request.getPlayer().getFacedWall();

        if(!(wall instanceof Door)){
            request.addMessageToResponse("there is no door in front of you");
            return;
        }
        Door door = (Door) wall;

        if(door.isOpen()){
            request.addMessageToResponse("Door is open");
        }else{
            request.addMessageToResponse("Door is locked, " + door.getKey().toString() + " is needed to unlock");
        }
    }
}
