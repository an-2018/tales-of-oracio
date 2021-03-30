package org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum CharacterType {
    RANDOM(4,8, Color.ORANGE),
    ;

    private int width;
    private int height;
    private Color color;

    CharacterType(int width, int height, Color color){
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
