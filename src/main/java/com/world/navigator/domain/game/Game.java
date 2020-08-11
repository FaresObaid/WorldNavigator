package com.world.navigator.domain.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.world.navigator.domain.item.Currency;
import com.world.navigator.domain.item.Gold;
import com.world.navigator.domain.maze.Maze;
import com.world.navigator.service.handler.GeneralHandler;
import com.world.navigator.tempdomain.MessageString;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Maze maze;
    private final HashMap<String, Player> players;
    private final HashMap<String, Request> requests;
    private final HashMap<String, GeneralHandler> handlers;
    private boolean start;
    private final Timer timer;
    private Shuffler shuffler;

    public Game(){
        players = new HashMap<>();
        requests = new HashMap<>();
        handlers = new HashMap<>();
        start = false;
        timer = new Timer();

        double totalTimeInMinutes = 20;
        timer.initializeTimeInMilliSecond((int) totalTimeInMinutes * 60 * 1000);
    }

    public HashMap<String, Player> getAllPlayers(){
        return players;
    }

    public void createMaze(){
        File file = new File("first.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            maze = objectMapper.readValue(file, Maze.class);
            shuffler = new Shuffler(maze);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @return must return error message to arena class if the function can't add player
     * */
    public String addPlayer(String playerName){

        if(!shuffler.canAddMorePlayers()){
            return "the game is full";
        }
        else if(players.get(playerName) == null){
            //each new player start in randomized room and faced north direction
            players.put(playerName, new Player(playerName, maze.getRoomAt(shuffler.getRandomRoomID()), 0));
            requests.put(playerName, new Request());
            handlers.put(playerName, new GeneralHandler());

            players.get(playerName).addItem("gold", new Gold(10));

            Request request = requests.get(playerName);

            request.setMaze(maze);
            request.setPlayer(players.get(playerName));
            request.setGame(this);
            return "";
        }else{
            return "repeated player name";
        }
    }

    public void startGame(){
        this.start = true;
        timer.startTimer();
    }

    public boolean isStart(){
        return start;
    }

    public int numberOfPlayers(){
        return players.size();
    }

    public void process(String playerName, String command){
        requests.get(playerName).clearResponse();
        requests.get(playerName).splitCommandToArguments(command);

        if(timer.isTimeFinished()) {
            requests.get(playerName).addMessageToResponse("timeout");
            return;
        }

        handlers.get(playerName).handle(requests.get(playerName));
    }

    public ArrayList<MessageString> getResult(String name){
        ArrayList<MessageString> result = new ArrayList<>();
        ArrayList<String> ret = requests.get(name).getResponse();

        for (String s : ret) {
            MessageString temp = new MessageString(s);
            result.add(temp);
        }

        return result;
    }

    public void removePlayer(String player) {
        players.remove(player);
        requests.remove(player);
        handlers.remove(player);
    }

    public void distributeCurrency(Player player, Currency currency){
        if(players.size() == 1) return;
        int newAmount = currency.getAmount() / (players.size() - 1);
        for(Map.Entry<String, Player> j:players.entrySet()){
            if(j.getValue() != player) {
                Currency playerCurrency = (Currency) j.getValue().getItem(currency.toString());
                playerCurrency.increase(newAmount);
            }
        }
        currency.setAmount(0);
    }
}
