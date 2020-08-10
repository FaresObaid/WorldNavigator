package com.world.navigator.service.handler.normal;

import com.world.navigator.domain.maze.Seller;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class TradeCommand extends CommandHandler {

    @Override
    public void handle(Request request) {
        if(request.getPlayer().getFacedWall() instanceof Seller) {
            request.getPlayer().setMode("trade");
            request.addMessageToResponse("you are in the trade mode");
        }else{
            request.addMessageToResponse("there is no seller in front of you");
        }
    }
}
