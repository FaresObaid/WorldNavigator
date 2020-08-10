package com.world.navigator.service.handler.normal.check;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class CheckCommand extends CommandHandler {

    public CheckCommand(){
        addChild("door", new CheckDoor());
        addChild("chest", new CheckChest());
        addChild("painting", new CheckPainting());
        addChild("mirror", new CheckMirror());
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
