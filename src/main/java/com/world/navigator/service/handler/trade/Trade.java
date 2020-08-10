package com.world.navigator.service.handler.trade;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;
import com.world.navigator.service.handler.general.Quit;

public class Trade extends CommandHandler {
    public Trade(){
        addChild("buy", new TradeBuy());
        addChild("sell", new TradeSell());
        addChild("list", new TradeList());
        addChild("finish", new TradeFinish());
        addChild("help", new TradeHelp());
        addChild("quit", new Quit());
    }
    @Override
    public void handle(Request request) {

        int argumentIndex = 0;
        CommandHandler nextHandler = getChild(request.getArgumentAt(argumentIndex));
        if(nextHandler == null){
            request.addMessageToResponse("invalid command");
        }else{
            nextHandler.handle(request);
        }
    }
}
