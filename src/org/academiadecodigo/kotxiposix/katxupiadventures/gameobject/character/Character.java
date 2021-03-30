package org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character;

import org.academiadecodigo.kotxiposix.katxupiadventures.gamecontrol.CollisionDetector;
import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Position;
import org.academiadecodigo.kotxiposix.katxupiadventures.scenario.Scenario;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.Converter;
import org.academiadecodigo.kotxiposix.katxupiadventures.utils.Velocity;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Character {

    private String name = "Oracio";
    private Velocity velocityGoal;
    private Velocity currentVelocity;
    private Position position;
    private int points;
    private Shape characterForward;
    private Shape characterBackward;
    private Shape character;
    private int width;
    private int height;
    private static final int DELTA_T=3;
    private CharacterState characterState;
    private CollisionDetector collisionDetector;
    private int maxJumpHeight;
    private int currentJumpHeight;
    private Picture gameOverBackground;
    private static boolean aLive=true;


    public Character( Position position) {
        this.width = 2;
        this.height = 3;
        this.maxJumpHeight = Converter.toPixel(2* height);
        this.currentJumpHeight = position.getY();
        this.velocityGoal = new Velocity(0,0);
        this.currentVelocity = new Velocity(0,0);
        this.position = position;
       // this.character = new Rectangle(Converter.toPixelPos(position.getX()), Converter.toPixelPos(position.getY()), Converter.toPixel(this.width), Converter.toPixel(this.height));// rectangle
       this.characterForward = new Picture(Converter.toPixelPos(position.getX()),Converter.toPixelPos(position.getY()), "public/oracio40.png"); // Oracio_forward
          this.characterBackward =  new Picture(Converter.toPixelPos(position.getX()),Converter.toPixelPos(position.getY()), "public/oracio40back.png");// Oracio_backthis.character = characterForward;
         this.gameOverBackground = new Picture(Scenario.getPADDING(),Scenario.getPADDING(),"public/gameOverBackground.png");
       // ((Rectangle)character).fill();
        //character.setColor(Color.GRAY);
    }

    public Velocity getVelocityGoal() {
        return velocityGoal;
    }

    public void setVelocityGoal(int x,int y) {
        this.velocityGoal.setVelocity_X(x);
        this.velocityGoal.setVelocity_Y(y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPositionX(int x) {
       position.setX(x);
    }

    public void setPositionY(int y){
        position.setY(y);
    }

    public void setCurrentVelocityY(int vY){
        currentVelocity.setVelocity_Y(vY);
    }

    public void setCurrentVelocityX(int vX){
        currentVelocity.setVelocity_X(vX);
    }

    public int getCurrentVelocityX(){
        return currentVelocity.getVelocity_X();
    }
    public int getCurrentVelocityY(){
        return  currentVelocity.getVelocity_Y();
    }
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void move(){

        if(currentVelocity.getVelocity_Y() == 0 && currentVelocity.getVelocity_X() == 0){
            //setCharacterState(CharacterState.STANDING);
        }

        int currentX = position.getX();
        int currentY = position.getY();

        calculateVelocity();

        limitVelocity();

        int next_X =position.getX()+currentVelocity.getVelocity_X()*DELTA_T;
        int next_Y =position.getY()+currentVelocity.getVelocity_Y()*DELTA_T;

        // up limit
        if(next_X < 0 ){

            next_X = 0;
            currentVelocity.setVelocity_X(0);

        }

        else if(next_X > (Converter.toPixel(Scenario.getWidth()) - Converter.toPixel(this.width)) ){

            next_X = (Converter.toPixel(Scenario.getWidth()) - Converter.toPixel(this.width));
            currentVelocity.setVelocity_X(0);
        }

        // bottom limit
        if(next_Y > Converter.toPixel(Scenario.getHeight())-Converter.toPixel(this.height) ){
            next_Y = Converter.toPixel(Scenario.getHeight())-Converter.toPixel(this.height) ;
            currentVelocity.setVelocity_Y(0);
            currentJumpHeight = next_Y;
            setCharacterState(CharacterState.STANDING);

            this.characterBackward.delete();
            this.characterForward.delete();
            //character.delete();
          //  this.gameOverBackground.draw();
            aLive = false;
        }

        else if(next_Y <=0){

            next_Y = 0;
            currentVelocity.setVelocity_Y(0);
        }

        // collision detection

        position.setY(next_Y + DELTA_T);
        position.setX(next_X + DELTA_T);
 // collide up or dow
        if(collisionDetector.colisionUp() /*|| collisionDetector.colisionDown()*/){

            next_Y = position.getY();
            currentJumpHeight = next_Y;
            setCharacterState(CharacterState.STANDING);
        }
        else if(collisionDetector.colisionDown()){

            next_Y = position.getY();
            currentJumpHeight = next_Y;
            setCharacterState(CharacterState.STANDING);
        }
 //collide left or right
        if(collisionDetector.colisionLeft() || collisionDetector.colisionRight()){

            next_X = position.getX();
        }

        // max jump
        if(position.getY() < (this.currentJumpHeight - this.maxJumpHeight)){

            next_Y = currentY ;
            currentVelocity.setVelocity_Y(0);
            setCharacterState(CharacterState.MAXJUMP);
        }
        position.setX(next_X);
        position.setY(next_Y);

        int finalX = position.getX();
        int finalY = position.getY();

      //  ((Rectangle)character).translate((finalX-currentX),(finalY-currentY));// rectangle
        ((Picture) characterForward).translate((finalX-currentX),(finalY-currentY)); //Oracio_forwqrd
        ((Picture) characterBackward).translate((finalX-currentX),(finalY-currentY)); // Oracio_back_ward

    }

    public int approachCalculation(int goal, int current){

        int difference = goal - current;
        if(difference > DELTA_T){
            return current + DELTA_T;
        }
        if(difference < -DELTA_T){
            return current - DELTA_T;
        }
        return goal;
    }

    public CharacterState getCharacterState() {
        return characterState;
    }

    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }

    public Shape getBox(){
        return characterForward;
    }

    private void collisionDetection(){

    }

    public void calculateVelocity(){
        currentVelocity.setVelocity_X(approachCalculation(velocityGoal.getVelocity_X(),currentVelocity.getVelocity_X()));
        currentVelocity.setVelocity_Y(approachCalculation(velocityGoal.getVelocity_Y(),currentVelocity.getVelocity_Y()));
    }
    public void limitVelocity(){
        // limit max velocity


        if(getCurrentVelocityY() > 4){
            setCurrentVelocityY(4);
        }

        if(currentVelocity.getVelocity_Y() < -4){
            currentVelocity.setVelocity_Y(-4);
        }

        if(currentVelocity.getVelocity_X() >= 3){
            currentVelocity.setVelocity_X(3);
        }


        if(currentVelocity.getVelocity_X() <= -3) {
            currentVelocity.setVelocity_X(-3);
        }
    }

    public void changeSide(){
        switch (characterState){
            case FORWARD:
                this.characterForward.draw();
                this.characterBackward.delete();
                break;
            case BACKWARD:
                this.characterBackward.draw();
                this.characterForward.delete();
        }

    }

    public void setCharacterForward(String url){
        this.characterForward = new Picture(Converter.toPixelPos(position.getX()),Converter.toPixelPos(position.getY()), url);
        this.characterForward.draw();
    }

    public void addPoints(){

        this.points ++;

    }

    public int getPoints() {
        return points;
    }

    public static boolean isaLive(){
        return aLive;
    }
}

