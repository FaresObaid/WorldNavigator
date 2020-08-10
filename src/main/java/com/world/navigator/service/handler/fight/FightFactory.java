package com.world.navigator.service.handler.fight;

import com.world.navigator.domain.game.Player;

import java.util.HashMap;

class FightFactory {

    //key for name
    private static HashMap<String, FightByHand> fights = new HashMap<>();

    public static FightByHand get(String playerName, String enemyName){
        if(fights.containsKey(playerName)) return fights.get(playerName);
        else{
            FightByHand fightByHand = new FightByHand(playerName, enemyName);
            fights.put(playerName, fightByHand);
            fights.put(enemyName, fightByHand);

            return fightByHand;
        }
    }

    public static void remove(String myName, String enemyName){
        fights.remove(myName);
        fights.remove(enemyName);
    }
}
