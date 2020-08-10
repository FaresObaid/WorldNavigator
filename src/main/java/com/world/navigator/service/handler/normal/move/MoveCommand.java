package com.world.navigator.service.handler.normal.move;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

public class MoveCommand extends CommandHandler {

    public MoveCommand(){
        addChild("right", new MoveRight());
        addChild("left", new MoveLeft());
        addChild("forward", new MoveForward());
        addChild("backward", new MoveBackward());
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
