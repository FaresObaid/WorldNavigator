package com.world.navigator.service.handler.normal.move;

import com.world.navigator.domain.maze.Door;
import com.world.navigator.domain.maze.Room;
import com.world.navigator.domain.maze.Wall;
import com.world.navigator.domain.game.Player;
import com.world.navigator.domain.game.Request;
import com.world.navigator.service.handler.CommandHandler;

class MoveForward extends CommandHandler {
    @Override
    public void handle(Request request) {
        Player player = request.getPlayer();
        try{
            Door door = (Door) player.getFacedWall();

            MoveChecker moveChecker = new MoveChecker(player, door, request);

            if(!moveChecker.preCheck()) return;

            /*
             * this part to know the direction in new room
             * by calculate the new degree
             */
            Room source = moveChecker.getSourceRoom();
            Room destination = moveChecker.getDestinationRoom();

            for (int i = 0; i < destination.getNumberOfWalls(); ++i) {
                try{
                    Wall wall = destination.getWall(i);
                    Door door2 = (Door) wall;
                    if (door2.getOppositeDirection(destination.getRoomID()) == source.getRoomID()) {
                        int numberOfWalls = destination.getNumberOfWalls();
                        player.setDirection(((Integer) (i * 360 / numberOfWalls + 180)) % 360);
                    }
                }catch(ClassCastException e){
                    continue;
                }
            }

            request.addMessageToResponse("you moved forward");

            moveChecker.postCheck();
        }catch(ClassCastException e){
            request.addMessageToResponse("you must face a door to move forward");
        }
    }
}
