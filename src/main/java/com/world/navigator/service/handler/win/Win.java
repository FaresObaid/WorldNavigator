package com.world.navigator.service.handler.win;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class Win extends CommandHandler {

    @Override
    public void handle(Request request) {
        request.addMessageToResponse("you won the game");
    }
}
