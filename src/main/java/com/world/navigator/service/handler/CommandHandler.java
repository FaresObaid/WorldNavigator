package com.world.navigator.service.handler;

import com.world.navigator.domain.game.Request;

import java.util.HashMap;

public abstract class CommandHandler {
    private HashMap nextLevel = new HashMap<String, CommandHandler>();

    public void addChild(String name, CommandHandler commandHandler){
        nextLevel.put(name, commandHandler);
    }

    public CommandHandler getChild(String name){
        return (CommandHandler) nextLevel.get(name);
    }

    public abstract void handle(Request request);
}
