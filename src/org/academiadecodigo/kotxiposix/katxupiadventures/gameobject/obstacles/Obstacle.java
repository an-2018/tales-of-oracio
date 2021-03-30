package org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.obstacles;

import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Position;
import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Scenario;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Obstacle {

    private int width;
    private int height;

    private Position position;
    private Rectangle boundBox;

    public Obstacle(int x, int y, int width, int height) {
        this.width = width/10;
        this.height = height/10;
        this.position = new Position(x/10, y/10);
        this.boundBox = new Rectangle((position.getX()) + Scenario.getPADDING(),(position.getY()) + Scenario.getPADDING(),
                                      (width), (height));
       // this.boundBox.fill();
    }

    public Rectangle getBox() {
        return boundBox;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x){
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y){
        position.setY(y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void hide(){
        this.boundBox.delete();
    }
}
