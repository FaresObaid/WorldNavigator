package com.world.navigator.service.handler.normal;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;
import com.world.navigator.service.handler.general.Quit;
import com.world.navigator.service.handler.normal.check.CheckCommand;
import com.world.navigator.service.handler.normal.look.LookCommand;
import com.world.navigator.service.handler.normal.move.MoveCommand;
import com.world.navigator.service.handler.normal.use.UseCommand;

public class Normal extends CommandHandler {

    public Normal(){
        addChild("use", new UseCommand());
        addChild("check", new CheckCommand());
        addChild("look", new LookCommand());
        addChild("move", new MoveCommand());
        addChild("switchlight", new SwitchLightCommand());
        addChild("help", new NormalHelp());
        addChild("trade", new TradeCommand());
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
