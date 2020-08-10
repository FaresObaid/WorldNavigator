package com.world.navigator.tempdomain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameResponse {
    private String name;
    private String player;

    public GameResponse(){
        this.name = "";
        this.player = "";
    }

    public GameResponse(String name, String player){
        this.name = name;
        this.player = player;
    }
}
