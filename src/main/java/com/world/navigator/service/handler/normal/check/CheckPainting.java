package com.world.navigator.service.handler.normal.check;

import com.world.navigator.domain.frontage.PaintingWithOneKey;
import com.world.navigator.domain.item.Key;
import com.world.navigator.domain.maze.Wall;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class CheckPainting extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        Wall wall = player.getFacedWall();

        if(!(wall instanceof PaintingWithOneKey)){
            request.addMessageToResponse("there is no painting in front of you");
            return;
        }

        PaintingWithOneKey painting = (PaintingWithOneKey) wall;

        if(painting.foundKey()){
            Key key = painting.takeKey();
            player.addItem(key.getName(), key);
            request.addMessageToResponse("The " + key.getName() + " was acquired");
        }
        else request.addMessageToResponse("no item found");
    }
}
