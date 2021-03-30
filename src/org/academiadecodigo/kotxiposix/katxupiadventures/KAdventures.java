package org.academiadecodigo.kotxiposix.katxupiadventures;

import org.academiadecodigo.kotxiposix.katxupiadventures.gamecontrol.Game;

public class KAdventures {

    public static void main(String[] args) {
        try{
            Game game = new Game();

            game.initi();
            game.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
