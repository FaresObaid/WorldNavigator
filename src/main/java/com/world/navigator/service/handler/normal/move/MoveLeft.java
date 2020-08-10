package com.world.navigator.service.handler.normal.move;

import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class MoveLeft extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        int unitSize = 360 / player.getCurrentRoom().getNumberOfWalls();

        double oldDirection = player.getDirection();
        double newDirection = oldDirection - unitSize;

        if(newDirection >= 0) player.setDirection(newDirection);
        else player.setDirection(360 + newDirection);

        String message = "you moved left";
        request.addMessageToResponse(message);
    }
}
