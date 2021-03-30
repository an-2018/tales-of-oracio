package org.academiadecodigo.kotxiposix.katxupiadventures.gamecontrol;

import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.obstacles.Obstacle;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.powerup.PowerUp;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character.Character;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.Converter;

import java.util.LinkedList;

public class CollisionDetector {

    private Character character;
    private LinkedList<Obstacle> obstacles;
    private Obstacle colisionObs;

    int cX;
    int cY;
    int oX;
    int oY;

    int chWidth;
    int chHeight;
    int oWidth;
    int oHeight;

    //      overflow || intersect
/*
1 - ((oWidth < oX || oWidth > cX) &&
2 - (oHeight < oY || oHeight > cY) &&
3 - (chWidth < cX || chWidth > oX) &&
4 - (chHeight < cY || chHeight > oY));
*/

    public CollisionDetector(Character character, LinkedList<Obstacle> obstacle) {
        this.character = character;
        this.obstacles = obstacle;
    }


    public boolean colisionUp(){

        for (int i = 0; i < obstacles.size(); i++) {
            cX = Converter.fromPixelPos(character.getPosition().getX());
            cY = Converter.fromPixelPos(character.getPosition().getY());
            oX = obstacles.get(i).getX();
            oY = obstacles.get(i).getY();

            chWidth = character.getWidth();
            chHeight = character.getHeight();
            oWidth = obstacles.get(i).getWidth();
            oHeight = obstacles.get(i).getHeight();

            // up
            if(((cX + chWidth) > oX && (cX < oX + oWidth) ) &&  ( (cY + chHeight > oY) && (cY + chHeight  < oY + oHeight)) ){
                if(obstacles.get(i) instanceof PowerUp){
                    PowerUp powerUp = (PowerUp) obstacles.get(i);
                    powerUp.hide();
                    powerUp.setX(-100);
                    powerUp.setY(-100);
                    character.addPoints();
                    return true;
                }else {
                    character.setPositionY(Converter.toPixel(cY - 1));
                    character.setCurrentVelocityY(0);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean colisionDown(){
        for (int i = 0; i < obstacles.size(); i++) {
            cX = Converter.fromPixelPos(character.getPosition().getX());
            cY = Converter.fromPixelPos(character.getPosition().getY());
            oX = obstacles.get(i).getX();
            oY = obstacles.get(i).getY();

            chWidth = character.getWidth();
            chHeight = character.getHeight();
            oWidth = obstacles.get(i).getWidth();
            oHeight = obstacles.get(i).getHeight();
// bottom
            if (((cX + chWidth) > oX && (cX < oX + oWidth)) && ((cY <= oY + oHeight) && (cY > oY))) {
                if(obstacles.get(i) instanceof PowerUp ){
                    PowerUp powerUp = (PowerUp) obstacles.get(i);
                    powerUp.hide();
                    powerUp.setX(-100);
                    powerUp.setY(-100);
                    character.addPoints();
                    return true;
                }else {
                character.setPositionY(Converter.toPixel(cY + 1));
                character.setCurrentVelocityY(0);
                return true;

                    }
            }
        }
        return false;
    }

    public boolean colisionLeft() {
            for (int i = 0; i < obstacles.size(); i++) {
                cX = Converter.fromPixelPos(character.getPosition().getX());
                cY = Converter.fromPixelPos(character.getPosition().getY());
                oX = obstacles.get(i).getX();
                oY = obstacles.get(i).getY();

                chWidth = character.getWidth();
                chHeight = character.getHeight();
                oWidth = obstacles.get(i).getWidth();
                oHeight = obstacles.get(i).getHeight();

                // left
                if((cX + chWidth >= oX && (cX + chWidth) <= oX + oWidth ) &&( cY + chHeight > oY && cY  < oY + oHeight )){
                    if(obstacles.get(i) instanceof PowerUp ){
                        PowerUp powerUp = (PowerUp) obstacles.get(i);
                        powerUp.hide();
                        powerUp.setX(-100);
                        powerUp.setY(-100);
                        character.addPoints();
                        return true;
                    }else {

                    character.setPositionX(Converter.toPixel(cX -1));
                    character.setCurrentVelocityX(0);
                    return true;

                        }
                }
            }
            return false;
        }

    public boolean colisionRight(){
        for (int i = 0; i < obstacles.size(); i++) {
            cX = Converter.fromPixelPos(character.getPosition().getX());
            cY = Converter.fromPixelPos(character.getPosition().getY());
            oX = obstacles.get(i).getX();
            oY = obstacles.get(i).getY();

            chWidth = character.getWidth();
            chHeight = character.getHeight();
            oWidth = obstacles.get(i).getWidth();
            oHeight = obstacles.get(i).getHeight();
            // right
            if( (cX <= (oX + oWidth) && (cX + chWidth) >= oX) && ( cY + chHeight > oY  && cY < oY + oHeight) ){
                if(obstacles.get(i) instanceof PowerUp ){
                    PowerUp powerUp = (PowerUp) obstacles.get(i);
                    powerUp.hide();
                    powerUp.setX(-100);
                    powerUp.setY(-100);
                    character.addPoints();
                    return true;
                }else {

                    character.setPositionX(Converter.toPixel(cX + 1));
                    character.setCurrentVelocityX(0);
                    return true;
                }
            }
        }

        return false;
    }
}
