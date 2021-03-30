package org.academiadecodigo.kotxiposix.katxupiadventures.scenario;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Scenario {

    private static final int CELLSIZE = 10;
    private static final int PADDING = 10;

    private Picture field ;
    private Picture front;
    private static final int WIDTH = 128; // 72;
    private static final int HEIGHT = 76; // 30;

    private Picture picture;

    public Scenario() {

       this.field = new Picture(PADDING,PADDING,"public/scenario_background.png");
        //this.field = new Rectangle(PADDING, PADDING, Converter.toPixel(getWidth()), Converter.toPixel(getHeight()));
        field.draw();
    }

    public static int getCELLSIZE() {
        return CELLSIZE;
    }

    public static int getPADDING() {
        return PADDING;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }
}
