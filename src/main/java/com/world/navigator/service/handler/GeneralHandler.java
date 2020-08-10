package com.world.navigator.service.handler;

import com.world.navigator.domain.game.Request;
import com.world.navigator.domain.item.Currency;
import com.world.navigator.service.handler.fight.Fight;
import com.world.navigator.service.handler.general.PlayerStatus;
import com.world.navigator.service.handler.loss.Loss;
import com.world.navigator.service.handler.normal.Normal;
import com.world.navigator.service.handler.trade.Trade;
import com.world.navigator.service.handler.win.Win;

import java.util.HashMap;

public class GeneralHandler extends CommandHandler{
    private HashMap modes = new HashMap<String, CommandHandler>();

    public GeneralHandler(){
        this.addChild("normal", new Normal());
        this.addChild("fight", new Fight());
        this.addChild("trade", new Trade());
        this.addChild("loss", new Loss());
        this.addChild("win", new Win());
        super.addChild("playerstatus", new PlayerStatus());
    }

    @Override
    public void addChild(String name, CommandHandler commandHandler){
        modes.put(name, commandHandler);
    }

    @Override
    public CommandHandler getChild(String name){
        return (CommandHandler) modes.get(name);
    }

    @Override
    public void handle(Request request) {

        if(super.getChild(request.getArgumentAt(0)) != null){
            super.getChild(request.getArgumentAt(0)).handle(request);
        }
        else this.getChild(request.getPlayer().getMode()).handle(request);
    }
}
