package org.academiadecodigo.kotxiposix.katxupiadventures.utils;

public class Velocity {
    private int velocity_X;
    private int velocity_Y;

    public Velocity(int velocity_X, int velocity_Y) {
        this.velocity_X = velocity_X;
        this.velocity_Y = velocity_Y;
    }

    public int getVelocity_X() {
        return velocity_X;
    }

    public void setVelocity_X(int velocity_X) {
        this.velocity_X = velocity_X;
    }

    public int getVelocity_Y() {
        return velocity_Y;
    }

    public void setVelocity_Y(int velocity_Y) {
        this.velocity_Y = velocity_Y;
    }
}
