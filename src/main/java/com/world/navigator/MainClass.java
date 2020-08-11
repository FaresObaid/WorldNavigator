package com.world.navigator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.world.navigator.domain.game.Shuffler;
import com.world.navigator.domain.maze.Maze;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        File file = new File("first.json");
        ObjectMapper objectMapper = new ObjectMapper();

        Maze maze = objectMapper.readValue(file, Maze.class);

    }
}
