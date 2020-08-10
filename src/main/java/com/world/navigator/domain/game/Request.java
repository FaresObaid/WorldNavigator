package com.world.navigator.domain.game;

import com.world.navigator.domain.maze.Maze;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Request {
    @Setter @Getter
    private Game game;
    @Setter @Getter
    private Player player;
    @Setter @Getter
    private Maze maze;
    private String[] arguments;
    private ArrayList<String> response = new ArrayList<String>();


    public String getArgumentAt(int index) {
        if(index >= arguments.length) return null;
        return arguments[index];
    }

    public void splitCommandToArguments(String command) {
        this.arguments = command.split("\\s+");
    }

    public void addMessageToResponse(String message){
        response.add(message);
    }

    public void clearResponse(){
        response.clear();
    }

    public ArrayList<String> getResponse(){
        return response;
    }
}
