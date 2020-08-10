package com.world.navigator.domain.maze;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.world.navigator.domain.cache.ContentWithMultipleItem;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.item.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

public class Room {
    private int roomID;
    private ArrayList<Wall> walls;
    private boolean light;
    private boolean lightStatus;
    private boolean exitRoom;
    private ArrayList<Player> players;
    private ContentWithMultipleItem floor;

    @JsonCreator
    public Room(
            @JsonProperty("id") int roomID,
            @JsonProperty("light") boolean light,
            @JsonProperty("walls") ArrayList<Wall> walls
    ){
        this.roomID = roomID;
        this.light = light;
        this.lightStatus = (roomID % 4 != 0); //to reduce "dark rooms" as much as possible
        this.walls = walls;
        this.exitRoom = (walls.size() == 0);
        players = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Wall getWall(int index){
        return walls.get(index);
    }
    public int getNumberOfWalls(){
        return walls.size();
    }

    public int getRoomID() {
        return roomID;
    }

    public boolean isExitRoom() {
        return exitRoom;
    }

    public boolean hasLight(){
        return light;
    }
    public boolean isLightOn() {return lightStatus;}

    public void switchLightOn(){
        if(this.hasLight()) lightStatus = true;
    }

    public void switchLightOff(){
        lightStatus = false;
    }

    public void putItemOnFloor(Item item){
        if(floor == null) floor = new ContentWithMultipleItem(item);
        else{
            floor.setMoreItem(item);
        }
    }

    public ContentWithMultipleItem lootFloor(){
        ContentWithMultipleItem tempFloor = floor;
        floor = null;//delete all contents after the player loot it
        return tempFloor;
    }
}
