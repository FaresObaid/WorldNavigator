package com.world.navigator.service.handler.normal.use;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class UseCommand extends CommandHandler {

    public UseCommand(){
        addChild("key", new UseKey());
        addChild("flashlight", new UseFlashlight());
    }

    @Override
    public void handle(Request request) {
        int argumentIndex = 1;
        CommandHandler nextHandler = getChild(request.getArgumentAt(argumentIndex));
        if(nextHandler == null){
            request.addMessageToResponse("invalid command");
        }else{
            nextHandler.handle(request);
        }
    }

}
