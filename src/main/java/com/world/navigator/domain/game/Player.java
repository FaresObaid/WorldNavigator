package com.world.navigator.domain.game;

import com.world.navigator.domain.item.Item;
import com.world.navigator.domain.maze.Room;
import com.world.navigator.domain.maze.Wall;

import java.util.HashMap;

public class Player {
    private String name;
    private Room currentRoom;
    private double direction;
    private String mode;
    private HashMap<String, Item> items;

    public Player(String name, Room currentRoom, double direction){
        this.name = name;
        this.currentRoom = currentRoom;
        this.direction = direction;
        this.mode = "normal";
        items = new HashMap<>();
        currentRoom.getPlayers().add(this);
    }

    public String getName() {
        return name;
    }

    public Wall getFacedWall() {
        return currentRoom.getWall((int) (direction / 90.0));
    }

    public Wall getOppositeWall() {
        return currentRoom.getWall((int) (((direction + 180) % 360) / 90.0));
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        if(currentRoom.isExitRoom()) this.setMode("win");
        this.currentRoom = currentRoom;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public void addItem(String name, Item item){
        items.put(name, item);
    }

    public void removeItem(String name){
        items.remove(name);
    }

    public Item getItem(String name){
        return items.get(name);
    }

    public HashMap<String, Item> getAllItems(){
        return items;
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
