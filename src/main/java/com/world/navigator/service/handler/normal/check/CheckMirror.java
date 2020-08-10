package com.world.navigator.service.handler.normal.check;

import com.world.navigator.domain.frontage.MirrorWithOneKey;
import com.world.navigator.domain.item.Key;
import com.world.navigator.domain.maze.Wall;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class CheckMirror extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        Wall wall = player.getFacedWall();

        if(!(wall instanceof MirrorWithOneKey)){
            request.addMessageToResponse("there is no mirror in front of you");
            return;
        }

        MirrorWithOneKey mirror = (MirrorWithOneKey) wall;

        if(mirror.foundKey()){
            Key key = mirror.takeKey();
            player.addItem(key.getName(), key);
            request.addMessageToResponse("The " + key.getName() + " was acquired");
        }
        else request.addMessageToResponse("no item found");
    }
}
