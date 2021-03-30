package org.academiadecodigo.kotxiposix.katxupiadventures.gamecontrol;

import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.obstacles.Obstacle;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.powerup.PowerUp;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character.Character;
import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Scenario;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.Converter;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.GameFactory;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Game {


    private Scenario scenario ;
    private Character character;
    private LinkedList<Obstacle> obstacles;
    private CollisionDetector collisionDetector;
    private int delay;
    private Controls controls;
    private Picture front;
    private Picture gameOver_background;
    private Obstacle ground1;

    public Game() {
        this.front = new Picture(Scenario.getPADDING(),Scenario.getPADDING(),"public/front.png");
        this.gameOver_background = new Picture(Scenario.getPADDING(),Scenario.getPADDING(),"public/gameOverBackground.png");
        this.scenario = new Scenario();
        this.character = GameFactory.createCaracter();
        this.obstacles = new LinkedList<>();
        this.delay = 50;

    }

    public void initi(){

        drawObstacles();
        collisionDetector = new CollisionDetector(character, obstacles);
        character.setCollisionDetector(collisionDetector);

        controls = new Controls();
        controls.init();
        controls.setMainCharacter(character);

    }

    public void start()throws InterruptedException{


        this.front.draw();
        Thread.sleep(3000);
        this.front.delete();
        Sound sound = new Sound("/public/backgroundsound.wav");

        while(true){

            if(sound.getFramePosition() > sound.getFrameLength() - 1){
                sound.play(true);
            }else{
                sound.play(false);
            }
            if (!Character.isaLive()){
                break;
            }
            Thread.sleep(delay);
            character.move();
        }

        showResults();
    }

    public void drawObstacles(){
        int w = 5;
        Obstacle obs = GameFactory.createObstacle(0, Scenario.getHeight() -72, 72, 1);
        obstacles.add(obs);

        obstacles.add(GameFactory.createObstacle(8,Scenario.getHeight()-10, 10, 5));

        obstacles.add(GameFactory.createObstacle(25, Scenario.getHeight() - 6, 6, 5));
        obstacles.add(GameFactory.createObstacle(35,Scenario.getHeight()-6, 6, 5));

        obstacles.add(new PowerUp(350,265,5,5));
        obstacles.add(new PowerUp(20,10,5,5));
        obstacles.add(new PowerUp(831,586,w,w));
        obstacles.add(new PowerUp(44,457,w,w));
        obstacles.add(new PowerUp(88,457,w,w));
        obstacles.add(new PowerUp(235,586,w,w));
        obstacles.add(new PowerUp(280,586,w,w));
        obstacles.add(new PowerUp(321,586,w,w));

        obstacles.add(new PowerUp(448,437,w,w));
        obstacles.add(new PowerUp(488,437,w,w));
        obstacles.add(new PowerUp(430,330,w,w));
        obstacles.add(new PowerUp(430,205,w,w));

        obstacles.add(new PowerUp(543,265,w,w));
        obstacles.add(new PowerUp(583,265,w,w));
        obstacles.add(new PowerUp(649,168,w,w));
        obstacles.add(new PowerUp(684,168,w,w));
        obstacles.add(new PowerUp(703,265,w,w));
        obstacles.add(new PowerUp(745,265,w,w));
        obstacles.add(new PowerUp(790,265,w,w));
        obstacles.add(new PowerUp(831,265,w,w));
        obstacles.add(new PowerUp(891,200,w,w));
        obstacles.add(new PowerUp(969,153,w,w));
        obstacles.add(new PowerUp(1020,153,w,w));
        obstacles.add(new PowerUp(934,330,w,w));
        obstacles.add(new PowerUp(1007,379,w,w));
        obstacles.add(new PowerUp(1124,342,w,w));
        obstacles.add(new PowerUp(1124,341,w,w));
        obstacles.add(new PowerUp(995,591,w,w));

        //_________________________
        obstacles.add(GameFactory.createObstacle(30,509,95,20));
        obstacles.add(GameFactory.createObstacle(51,286,196,20));
        obstacles.add(GameFactory.createObstacle(231,283,20,152));
        obstacles.add(GameFactory.createObstacle(231,432,131,20));
        obstacles.add(GameFactory.createObstacle(312,131,20,199));

        obstacles.add(GameFactory.createObstacle(312,317,77,20));
        obstacles.add(GameFactory.createObstacle(312,132,79,20));
        obstacles.add(GameFactory.createObstacle(427,256,63,20));
        obstacles.add(GameFactory.createObstacle(427,382,63,28));
        obstacles.add(GameFactory.createObstacle(427,485,92,20));
        obstacles.add(GameFactory.createObstacle(376,545,51,20));
        obstacles.add(GameFactory.createObstacle(536,225,49,20));
        obstacles.add(GameFactory.createObstacle(518,317,129,20));
        obstacles.add(GameFactory.createObstacle(632,225,77,20));
        obstacles.add(GameFactory.createObstacle(697,317,179,20));
        obstacles.add(GameFactory.createObstacle(865,256,79,20));
        obstacles.add(GameFactory.createObstacle(931,205,20,62));

        obstacles.add(GameFactory.createObstacle(931,205,126,20));
        obstacles.add(GameFactory.createObstacle(911,382,59,20));
        obstacles.add(GameFactory.createObstacle(982,432,63,20));
        obstacles.add(GameFactory.createObstacle(1119,293,63,20));
        obstacles.add(GameFactory.createObstacle(1109,393,63,20));


        obstacles.add(GameFactory.createObstacle(482,593,59,20));

         ground1  =  GameFactory.createObstacle(130,650,633,30);
        obstacles.add(ground1);

        obstacles.add(GameFactory.createObstacle(969,650,140,20));
        obstacles.add(GameFactory.createObstacle(831,631,92,20));

        // tree






    }

    private void showResults(){

        this.gameOver_background.draw();
        Text text = new Text(Converter.toPixelPos(Scenario.getWidth()/2),Converter.toPixelPos((Scenario.getHeight()-1)/2),"Point : " +character.getPoints() );
        text.setColor(Color.WHITE);
        text.grow(10,10);
        text.draw();
    }
}
