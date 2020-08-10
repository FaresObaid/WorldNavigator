package com.world.navigator.service.handler.normal;

import com.world.navigator.domain.maze.Room;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class SwitchLightCommand extends CommandHandler {
    @Override
    public void handle(Request request) {
        Room room = request.getPlayer().getCurrentRoom();

        if(room.hasLight()){
            if(!room.isLightOn()) {
                room.switchLightOn();
                request.addMessageToResponse("light switched on");
            }
            else {
                room.switchLightOff();
                request.addMessageToResponse("light switched off");
            }
        }else{
            request.addMessageToResponse("there is no light to switch on");
        }
    }
}
