package com.world.navigator.service.handler.normal.use;

import com.world.navigator.domain.item.Flashlight;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class UseFlashlight extends CommandHandler {
    @Override
    public void handle(Request request) {
        Flashlight flashlight = (Flashlight) request.getPlayer().getItem("flashlight");

        if (flashlight != null) {
            if (flashlight.isOn()) {
                flashlight.switchOff();
                request.addMessageToResponse("flashlight is off");
            } else {
                flashlight.switchOn();
                request.addMessageToResponse("flashlight is on");
            }
        }else{
            request.addMessageToResponse("there is no flashlight to use");
        }

    }
}
