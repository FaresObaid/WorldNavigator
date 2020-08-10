package com.world.navigator.service.handler.trade;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class TradeFinish extends CommandHandler {
    @Override
    public void handle(Request request) {
        request.getPlayer().setMode("normal");
        request.addMessageToResponse("you left the trade mode");
    }
}