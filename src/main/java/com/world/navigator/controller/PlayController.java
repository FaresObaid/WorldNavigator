package com.world.navigator.controller;

import com.world.navigator.service.arena.Arena;
import com.world.navigator.tempdomain.MessageString;
import com.world.navigator.tempdomain.GameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PlayController {
    private Arena arena;

    @Autowired
    public PlayController(Arena arena){
        this.arena = arena;
    }

    @GetMapping(
            path="/play/{game}/{player}"
    )public String view(@PathVariable("game") String game,
                        @PathVariable("player") String player,
                        ModelMap model){
        model.put("game", new GameResponse(game, player));
        model.put("command", new MessageString());
        model.put("result", arena.getResult(game, player));

        return "CommandPage";
    }

    @GetMapping(
            path="/quit/{game}/{player}"
    )public String quit(@PathVariable("game") String game,
                        @PathVariable("player") String player){
        arena.process(game, player, "quit");
        arena.removePlayer(game, player);
        return "redirect:/";
    }

    @PostMapping(
            path="/play/{game}/{player}"
    )public String accept(@PathVariable("game") String game,
                          @PathVariable("player") String player,
                          MessageString command){
        try {
            arena.process(game, player, command.getValue());
        }catch(Exception e){
            arena.process(game, player, "there is a bug in source code");
        }
        return "redirect:/play/"+ game + "/" + player;
    }
}
