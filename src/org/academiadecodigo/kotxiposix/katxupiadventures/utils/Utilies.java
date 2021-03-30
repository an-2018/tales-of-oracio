package org.academiadecodigo.kotxiposix.katxupiadventures.utils;

import org.academiadecodigo.kotxiposix.katxupiadventures.gamecontrol.CollisionType;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.obstacles.Obstacle;
import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Position;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character.Character;

public class Utilies {

    // Some usefull codes


    private Character character;
    private Obstacle obstacle;

    int chWidth;
    int chHeight;
    int oWidth;
    int oHeight;

    int cX;
    int cY;
    int oX;
    int oY;

    //      overflow || intersect
/*
1 - ((oWidth < oX || oWidth > cX) &&
2 - (oHeight < oY || oHeight > cY) &&
3 - (chWidth < cX || chWidth > oX) &&
4 - (chHeight < cY || chHeight > oY));
*/

    public Position intersects(boolean boo){
        cX = Converter.fromPixelPos(character.getPosition().getX());
        cY = Converter.fromPixelPos(character.getPosition().getY());
        oX = obstacle.getX();
        oY = obstacle.getY();

        chWidth = character.getWidth();
        chHeight = character.getHeight();
        oWidth = obstacle.getWidth();
        oHeight = obstacle.getHeight();

        oWidth += oX;
        oHeight += oY;
        chWidth += cX;
        chHeight += cY;
        if        ((oWidth <= oX || oWidth >= cX) &&
                (oHeight <= oY || oHeight >= cY) &&
                (chWidth <= cX || chWidth >= oX) &&
                (chHeight <= cY || chHeight >= oY)){
            return new Position(cX, cY);
        }
        return null;
    }

    public CollisionType intersects(){
        cX = Converter.fromPixelPos(character.getPosition().getX());
        cY = Converter.fromPixelPos(character.getPosition().getY());
        oX = obstacle.getX();
        oY = obstacle.getY();

        chWidth = character.getWidth();
        chHeight = character.getHeight();
        oWidth = obstacle.getWidth();
        oHeight = obstacle.getHeight();

        oWidth += oX;
        oHeight += oY;
        chWidth += cX;
        chHeight += cY;

        if(!hasSize()){
            return CollisionType.NONE;
        }

        if(isUp() && isTouchingUp()){
            return CollisionType.UP;
        }

        if(isDown() && isTouchingDown()){
            return CollisionType.BOTTOM;
        }

        return CollisionType.NONE;
    }

    public boolean hasSize() {

        if (oWidth <= 0 || oHeight <= 0 || chWidth <= 0 || chHeight <= 0) {
            return false;
        }
        return true;
    }

    public boolean isTouchingUp(){// 1 3 4
        return ((oWidth <= oX || oWidth >= cX) &&
                (chWidth <= cX || chWidth >= oX)
                && (chHeight <= cY || chHeight >= (oY + 2)));
    }

    public boolean isUp(){
        return !(chHeight <= cY || chHeight >= oY);
    }

    public boolean isTouchingDown(){
        return (oWidth < oX || oWidth > cX) &&
                (oHeight < oY || oHeight > cY) &&
                (chWidth < cX || chWidth > oX);

    }

    public boolean isDown(){
        return  !(oHeight < oY || oHeight > cY);
    }
}
/**
 * Corrent de ar obstacle
 *      public boolean isTouchingUp(){// 1 3 4
 *         return (oWidth <= oX || oWidth >= cX) &&
 *                 (chWidth <= cX || chWidth >= oX);
 *                 //&& (chHeight <= cY || chHeight >= oY));
 *     }
 *
 *     public boolean isUp(){
 *         return !(chHeight <= cY || chHeight >= oY);
 *     }
 */
