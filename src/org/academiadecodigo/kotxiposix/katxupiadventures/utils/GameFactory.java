package org.academiadecodigo.kotxiposix.katxupiadventures.utils;

import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.obstacles.Obstacle;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character.Character;
import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Position;

public class GameFactory {
    public static Character createCaracter(){
        return new Character(new Position(0, 0));
    }

    public static Obstacle createObstacle(int x, int y, int width, int height){
        return new Obstacle(x, y, width, height);
    }
}
