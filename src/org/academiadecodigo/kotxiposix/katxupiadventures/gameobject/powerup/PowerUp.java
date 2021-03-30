package org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.powerup;

import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.obstacles.Obstacle;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.Converter;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class PowerUp extends Obstacle {

    private Picture image;

    public PowerUp(int x, int y, int width, int height){
        super(x,y,width,height);
        getBox().setColor(Color.BLUE);
        image = new Picture(Converter.toPixelPos(getX()), Converter.toPixel(getY()), "public/banana.png");
        image.draw();
        getBox().delete();
    }

    public void hide(){
        this.image.delete();
    }


}
