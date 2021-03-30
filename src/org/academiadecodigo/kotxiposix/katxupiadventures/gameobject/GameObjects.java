package org.academiadecodigo.kotxiposix.katxupiadventures.gameobject;

import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Position;

public class GameObjects {

    private Position position;

    public GameObjects(Position position) {
        this.position = position;
    }

    public GameObjects(){
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }


}
