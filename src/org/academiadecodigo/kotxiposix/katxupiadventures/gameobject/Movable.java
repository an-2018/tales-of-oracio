package org.academiadecodigo.kotxiposix.katxupiadventures.gameobject;

import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.FieldDirection;

public interface Movable{

    public void moveInDirection(FieldDirection direction, int distance);

    public void moveUp(int distance);
    public void moveLeft(int distance);
    public void moveRight(int distance);
    public void moveDown(int distance);

    public void setPosition(int col, int row);

}
