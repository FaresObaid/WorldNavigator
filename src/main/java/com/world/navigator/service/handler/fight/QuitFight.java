package com.world.navigator.service.handler.fight;

import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

import java.util.ArrayList;

public class QuitFight extends CommandHandler {
    @Override
    public void handle(Request request) {
        ArrayList<Player> players = request.getPlayer().getCurrentRoom().getPlayers();
        Player loser = request.getPlayer();
        Player winner = players.get(1);//just for this line to avoid null value exception

        for(int i = 0;i < 2;++i){
            if(loser != players.get(i)) winner = players.get(i);
        }
        FightByHand fightByHand = FightFactory.get(winner.getName(), loser.getName());

        synchronized (fightByHand){
            fightByHand.judge(winner, loser, request.getGame());
        }
        FightFactory.remove(winner.getName(), loser.getName());
    }
}
