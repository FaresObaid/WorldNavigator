package com.world.navigator.domain.game;

import com.world.navigator.domain.maze.Maze;

import java.util.Collections;
import java.util.LinkedList;

public class Shuffler {
    private final Maze maze;
    private final LinkedList<Integer> stockRoom; // to store the shuffled room ID's

    public Shuffler(Maze maze){
        this.maze = maze;
        stockRoom = new LinkedList<Integer>();
        shuffleStartingRooms();
    }

    private void shuffleStartingRooms() {
        for(int i = 0;i < maze.getNumOfRooms();++i){
            if(maze.getRoomAt(i).isExitRoom()) continue;
            stockRoom.add(i);
        }
        Collections.shuffle(stockRoom);
    }

    public boolean canAddMorePlayers(){
        return stockRoom.size() != 0;
    }

    public int getRandomRoomID(){
        return stockRoom.removeLast();
    }


}
