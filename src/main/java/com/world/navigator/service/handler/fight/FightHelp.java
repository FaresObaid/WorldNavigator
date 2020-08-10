package com.world.navigator.service.handler.fight;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class FightHelp extends CommandHandler {
    @Override
    public void handle(Request request) {
        request.addMessageToResponse("you can fight your enemy by enter \"hand\" command with one of its arguments");
        request.addMessageToResponse("hand {rock|paper|scissor}");
        request.addMessageToResponse("or you can write this command \"hand check\" to check the result");
    }
}
