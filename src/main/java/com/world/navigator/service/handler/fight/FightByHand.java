package com.world.navigator.service.handler.fight;

import com.world.navigator.domain.game.Game;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.item.Key;

import java.util.HashMap;
import java.util.Map;

class FightByHand {
    //the key in all three maps is for name of players
    private HashMap<String, String> fighters = new HashMap<>(); //the value should be equal (rock, paper, scissor)
    private HashMap<String, String> compareResult = new HashMap<>(); // the value should be equal (win, loss, tie)
    private HashMap<String, String> finalResult = new HashMap<>();// the value should be equal (play, wait, win, loss, tie)

    public FightByHand(String player1, String player2){
        fighters.put(player1, "");
        fighters.put(player2, "");
        compareResult.put(player1, "");
        compareResult.put(player2, "");
        finalResult.put(player1, "");
        finalResult.put(player2, "");
    }

    public void putHand(String playerName, String hand){
        fighters.put(playerName, hand);
    }

    public String getHand(String playerName){
        return fighters.get(playerName);
    }

    public void compare(Player player, Player enemy){
        if(!getCompareResult(player.getName()).equals("")) return;

        HashMap<String, Item> items1 = player.getAllItems();
        HashMap<String, Item> items2 = enemy.getAllItems();

        int result1 = 0, result2 = 0;
        for(Map.Entry<String, Item> entry: items1.entrySet()){
            if(entry.getValue() instanceof Currency) result1 += ((Currency) entry.getValue()).getAmount();
            else if(entry.getValue() instanceof Key) result1 += 10;
            else result1 += 5;
        }

        for(Map.Entry<String, Item> entry: items2.entrySet()){
            if(entry.getValue() instanceof Currency) result2 += ((Currency) entry.getValue()).getAmount();
            else if(entry.getValue() instanceof Key) result2 += 10;
            else result2 += 5;
        }

        if (result1 > result2) {
            setCompareResult(player.getName(), "win");
            setCompareResult(enemy.getName(), "loss");
        } else if (result1 < result2) {
            setCompareResult(player.getName(), "loss");
            setCompareResult(enemy.getName(), "win");
        } else {
            setCompareResult(player.getName(), "tie");
            setCompareResult(enemy.getName(), "tie");
        }
    }

    public void setCompareResult(String playerName, String result){
        compareResult.put(playerName, result);
    }

    public String getCompareResult(String playerName){
        return compareResult.get(playerName);
    }

    public String checkResult(String playerName,String enemyName){
        String enemy = fighters.get(enemyName);
        String player = fighters.get(playerName);

        if(player.equals("")) setFinalResult(playerName, "play");
        else if(enemy.equals("")) setFinalResult(playerName,"wait");
        else if(enemy.equals(player)) setFinalResult(playerName, "tie");
        else if(enemy.equals("rock") && player.equals("paper")) setFinalResult(playerName, "win");
        else if(enemy.equals("paper") && player.equals("scissor")) setFinalResult(playerName, "win");
        else if(enemy.equals("scissor") && player.equals("rock")) setFinalResult(playerName, "win");
        else setFinalResult(playerName, "loss");

        return getFinalResult(playerName);
    }

    public void setFinalResult(String playerName, String result){
        finalResult.put(playerName, result);
    }

    public String getFinalResult(String playerName){
        return finalResult.get(playerName);
    }


    public void judge(Player winner, Player loser, Game game){
        if(winner.getMode() != "fight") return;

        winner.setMode("normal");
        loser.setMode("loss");

        distributeLegacy(winner, loser, game);
    }

    private void distributeLegacy(Player winner, Player loser, Game game){
        HashMap<String, Item> items = loser.getAllItems();

        for(Map.Entry<String, Item> i:items.entrySet()){
            if(i.getValue() instanceof Currency){
                game.distributeCurrency(loser, (Currency) i.getValue());
            }else{
                winner.addItem(i.getKey(), i.getValue());
                loser.removeItem(i.getKey());
            }
        }
        loser.getCurrentRoom().getPlayers().remove(loser);
    }
}
