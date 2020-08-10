package com.world.navigator.controller;

import com.world.navigator.service.postman.Postman;
import com.world.navigator.tempdomain.GameResponse;
import com.world.navigator.tempdomain.MessageString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    private Postman postman;

    public LoginController(Postman postman){
        this.postman = postman;
    }

    @GetMapping(
            path = "/login/new"
    )
    public String newLoginView(ModelMap model){
        model.put("game", new GameResponse());
        model.put("message", new MessageString(postman.getMessageFromMailbox("/login/new")));
        return "NewGame";
    }

    @GetMapping(
            path = "/login/existing"
    )
    public String existingLoginView(ModelMap model){
        model.put("game", new GameResponse());
        model.put("message", new MessageString(postman.getMessageFromMailbox("/login/existing")));
        return "ExistingGame";
    }


    @PostMapping(
            path = "/login/new"
    )
    public String newLogin(){

        return "redirect:/login/new";
    }

    @PostMapping(
            path = "/login/existing"
    )
    public String existingLogin(){

        return "redirect:/login/existing";
    }
}
