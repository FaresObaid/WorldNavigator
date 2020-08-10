package com.world.navigator.service.handler.loss;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class Loss extends CommandHandler {
    @Override
    public void handle(Request request) {
        request.addMessageToResponse("you lost the game");
    }
}
