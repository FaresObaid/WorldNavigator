package com.world.navigator.service.handler.normal;

import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class NormalHelp extends CommandHandler {
    @Override
    public void handle(Request request) {
        request.addMessageToResponse("move {left|right}: to move clockwise or counter-clockwise");
        request.addMessageToResponse("move {forward|backward}: to move between rooms");
        request.addMessageToResponse("check : take one of these arguments");
        request.addMessageToResponse(" - mirror: loot key behind mirror");
        request.addMessageToResponse(" - painting: loot key behind painting");
        request.addMessageToResponse(" - chest: loot one item (gold, flashlight, painting)");
        request.addMessageToResponse(" - door: to check if the door is open or close");
        request.addMessageToResponse("look: to see what in front of you");
        request.addMessageToResponse("trade: to enter trade mood if these is a seller in front of you");
        request.addMessageToResponse("switchlight: to turn on or turn off the room light");
        request.addMessageToResponse("use key <key name>: to open a closed chest or door");
        request.addMessageToResponse("use flashlight: turn on or turn off the flashlight");
        request.addMessageToResponse("playerstatus (this command run in any mode): to show your items, gold and direction");
    }
}
