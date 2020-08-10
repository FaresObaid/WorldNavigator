package com.world.navigator.service.handler.normal.look;

import com.world.navigator.domain.item.Flashlight;
import com.world.navigator.domain.maze.Room;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class LookCommand extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        Flashlight flashlight = (Flashlight) player.getItem("flashlight");

        Room room = player.getCurrentRoom();

        if((flashlight != null && flashlight.isOn()) || room.isLightOn())
            request.addMessageToResponse(player.getFacedWall().toString());
        else request.addMessageToResponse("Dark");
    }
}
