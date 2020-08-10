package com.world.navigator.controller;

import com.world.navigator.service.arena.Arena;
import com.world.navigator.service.postman.Postman;
import com.world.navigator.tempdomain.GameResponse;
import com.world.navigator.tempdomain.MessageString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WaitController {
    private Arena arena;
    private Postman postman;

    @Autowired
    public WaitController(Arena arena, Postman postman){
        this.arena = arena;
        this.postman = postman;
    }

    @GetMapping(
            path="/wait/new/{gameName}/{playerName}"
    )
    public String newWaitView(@PathVariable("gameName") String gameName,
                              @PathVariable("playerName") String playerName,
                              ModelMap model){
        model.put("game", new GameResponse(gameName, playerName));
        return "AdminWait";
    }
    @GetMapping(
            path="/wait/existing/{gameName}/{playerName}"
    )
    public String existingWaitView(@PathVariable("gameName") String gameName,
                                   @PathVariable("playerName") String playerName,
                                   ModelMap model){
        model.put("game", new GameResponse(gameName, playerName));
        model.put("message", new MessageString(("")));
        return "PlayerWait";
    }

    @PostMapping(
            path="/wait/new"
    )
    public String newWait(ModelMap model,GameResponse game){
        if(arena.addGameWithAdmin(game.getName(), game.getPlayer())){
            return "redirect:/wait/new/" + game.getName() + "/" + game.getPlayer();
        }else{
            postman.putMessageInMailbox("/login/new", "there is another game with this name");
            return "redirect:/login/new";
        }
    }

    @PostMapping(
            path="/wait/existing"
    )
    public String existingWait(GameResponse game) {
        String errorMessage = arena.addPlayerToGame(game.getName(), game.getPlayer());

        if (errorMessage.equals("")) {
            return "redirect:/wait/existing/" + game.getName() + "/" + game.getPlayer();
        } else {
            postman.putMessageInMailbox("/login/existing", errorMessage);
            return "redirect:/login/existing";
        }
    }
}
