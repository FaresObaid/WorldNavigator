package com.world.navigator.service.handler.fight;

import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Fight extends CommandHandler {

    public Fight(){
        addChild("hand", new Hand());
        addChild("help", new FightHelp());
        addChild("quit", new QuitFight());
    }

    @Override
    public void handle(Request request) {
        request.addMessageToResponse("you are in the fight mode");

        //this part to get the player in the room and his enemy
        ArrayList<Player> players = request.getPlayer().getCurrentRoom().getPlayers();
        Player player = request.getPlayer();
        Player enemy = players.get(0);//just for this line to avoid null value exception

        for(int i = 0;i < 2;++i){
            if(player != players.get(i)) enemy = players.get(i);
        }

        //**************

        FightByHand fightByHand= FightFactory.get(player.getName(), enemy.getName());
        Player winner = null;
        Player loser = null;

        fightByHand.compare(player, enemy);
        //System.out.println(fightByHand.getCompareResult(player.getName()));
        if(fightByHand.getCompareResult(player.getName()).equals("win")){
            winner = player;
            loser = enemy;
            request.addMessageToResponse("you win in this fight");
        }else if(fightByHand.getCompareResult(player.getName()).equals("loss")){
            winner = enemy;
            loser = player;
            request.addMessageToResponse("you lost in this fight");
        }else{
            int argumentIndex = 0;
            CommandHandler nextHandler = getChild(request.getArgumentAt(argumentIndex));
            if(nextHandler == null){
                request.addMessageToResponse("invalid command");
            }else{
                if(nextHandler instanceof Hand){
                    ((Hand) nextHandler).setFighters(player, enemy);
                }

                nextHandler.handle(request);

                if(nextHandler instanceof Hand){
                    winner = ((Hand) nextHandler).getWinner();
                    loser = ((Hand) nextHandler).getLoser();
                }
            }
        }

        if(winner == null || loser == null){
            return;
        }

        synchronized (fightByHand){
            fightByHand.judge(winner, loser, request.getGame());
        }
        FightFactory.remove(winner.getName(), loser.getName());
    }
}
