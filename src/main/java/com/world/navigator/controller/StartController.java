package com.world.navigator.controller;

import com.world.navigator.service.arena.Arena;
import com.world.navigator.tempdomain.GameResponse;
import com.world.navigator.tempdomain.MessageString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {
    private Arena arena;

    public StartController(Arena arena){
        this.arena = arena;
    }

    @GetMapping(
            path="/start/admin/{gameName}/{playerName}"
    )
    public String newAdminStart(@PathVariable("gameName") String gameName,
                                @PathVariable("playerName") String playerName){
        arena.startGame(gameName);
        return "redirect:/play/" + gameName + "/" + playerName + "/";
    }

    @GetMapping(
            path="/start/player/{gameName}/{playerName}"
    )
    public String newPlayerStart(@PathVariable("gameName") String gameName,
                                 @PathVariable("playerName") String playerName,
                                 ModelMap model){
        if(arena.didThisGameStart(gameName)) return "redirect:/play/" + gameName + "/" + playerName + "/";
        model.put("game", new GameResponse(gameName, playerName));
        model.put("message", new MessageString("wait until the admin start the game"));
        return "PlayerWait";
    }

}
