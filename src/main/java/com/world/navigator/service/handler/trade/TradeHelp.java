package com.world.navigator.service.handler.trade;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class TradeHelp extends CommandHandler {
    @Override
    public void handle(Request request) {
        request.addMessageToResponse("you are in the trade mode");
        request.addMessageToResponse("you can use one of these commands");
        request.addMessageToResponse("list: to list items that seller have");
        request.addMessageToResponse("buy <item number>: to buy an item you can use its number in the list command");
        request.addMessageToResponse("sell <item number>");
        request.addMessageToResponse("finish");
    }
}
