package org.academiadecodigo.kotxiposix.katxupiadventures.gamecontrol;

import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character.Character;
import org.academiadecodigo.kotxiposix.katxupiadventures.gameobject.character.CharacterState;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controls implements KeyboardHandler {

    private Keyboard keyboard;
    private Character gameObject;
    private boolean keyReleaseForward;
    private final int gravityStrength = 3;

    public void init(){

        this.keyboard = new Keyboard(this);
        //KEY PRESS
        KeyboardEvent pressRight = new KeyboardEvent();
        pressRight.setKey(KeyboardEvent.KEY_RIGHT);
        pressRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent pressLeft = new KeyboardEvent();
        pressLeft.setKey(KeyboardEvent.KEY_LEFT);
        pressLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent pressDown = new KeyboardEvent();
        pressDown.setKey(KeyboardEvent.KEY_DOWN);
        pressDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent pressUp = new KeyboardEvent();
        pressUp.setKey(KeyboardEvent.KEY_UP);
        pressUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent pressSpace = new KeyboardEvent();
        pressSpace.setKey(KeyboardEvent.KEY_SPACE);
        pressSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        //KEY RELEASE
        KeyboardEvent releasedRight = new KeyboardEvent();
        releasedRight.setKey(KeyboardEvent.KEY_RIGHT);
        releasedRight.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent releasedleft = new KeyboardEvent();
        releasedleft.setKey(KeyboardEvent.KEY_LEFT);
        releasedleft.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent releasedUp = new KeyboardEvent();
        releasedUp.setKey(KeyboardEvent.KEY_UP);
        releasedUp.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent releasedDown = new KeyboardEvent();
        releasedDown.setKey(KeyboardEvent.KEY_DOWN);
        releasedDown.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        KeyboardEvent releasedSpace = new KeyboardEvent();
        releasedSpace.setKey(KeyboardEvent.KEY_SPACE);
        releasedSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);




        keyboard.addEventListener(pressRight);
        keyboard.addEventListener(pressLeft);
        keyboard.addEventListener(pressDown);
        keyboard.addEventListener(pressUp);
        keyboard.addEventListener(pressSpace);
        keyboard.addEventListener(releasedRight);
        keyboard.addEventListener(releasedleft);
        keyboard.addEventListener(releasedUp);
        keyboard.addEventListener(releasedDown);
        keyboard.addEventListener(releasedSpace);
    }

    public void setMainCharacter(Character character){
        this.gameObject = character;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()){

            case KeyboardEvent.KEY_RIGHT:

                gameObject.setVelocityGoal(50,gravityStrength);
                gameObject.setCharacterState(CharacterState.FORWARD);
                gameObject.changeSide();
                break;

            case KeyboardEvent.KEY_LEFT:

                gameObject.setVelocityGoal(-50,gravityStrength);
                gameObject.setCharacterState(CharacterState.BACKWARD);
                gameObject.changeSide();
                break;

            case KeyboardEvent.KEY_UP:
                if(gameObject.getCharacterState() == CharacterState.UP){
                    gameObject.setVelocityGoal(0,gravityStrength);

                    return;
                }
                switch (gameObject.getCharacterState()){
                    case MAXJUMP:
                        gameObject.setVelocityGoal(0, 35);
                        gameObject.setCharacterState(CharacterState.UP);
                        break;
                    case STANDING:
                        gameObject.setVelocityGoal(0,-35);
                        gameObject.setCharacterState(CharacterState.UP);
                        break;

                    default:
                        gameObject.setVelocityGoal(0,-35);
                        gameObject.setCharacterState(CharacterState.UP);
                        break;
                }
                break;

            /*case KeyboardEvent.KEY_DOWN:

                gameObject.setVelocityGoal(0,15);
                break;
*/
            case KeyboardEvent.KEY_SPACE:

                switch (gameObject.getCharacterState()){

                    case MAXJUMP:
                        gameObject.setVelocityGoal(0,35);

                        break;
                   case FORWARD:

                       gameObject.setVelocityGoal(7,-35);
                       break;

                    case BACKWARD:

                        gameObject.setVelocityGoal(-7,-35);
                        break;
                    case STANDING:
                        if(gameObject.getVelocityGoal().getVelocity_X() > 0){
                            gameObject.setVelocityGoal(7,-35);
                            break;
                        }
                        else if(gameObject.getVelocityGoal().getVelocity_X() < 0){
                            gameObject.setVelocityGoal(-7,-35);
                        }
                    }
                }

        }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()){

            case KeyboardEvent.KEY_RIGHT:

                gameObject.setVelocityGoal(0,gravityStrength);
                keyReleaseForward = false;
                break;

            case KeyboardEvent.KEY_LEFT:

                gameObject.setVelocityGoal(0,gravityStrength);
                keyReleaseForward = false;
                break;

            case KeyboardEvent.KEY_UP:

                gameObject.setVelocityGoal(0,gravityStrength);

                gameObject.setCharacterState(CharacterState.STANDING);
                break;

            case KeyboardEvent.KEY_DOWN:

                gameObject.setVelocityGoal(0,gravityStrength);
                break;

            case KeyboardEvent.KEY_SPACE:
                switch (gameObject.getCharacterState()){
                    case FORWARD:
                        gameObject.setVelocityGoal(5,gravityStrength);
                        gameObject.setCharacterState(CharacterState.STANDING);
                        break;
                    case BACKWARD:
                        gameObject.setVelocityGoal(-5,gravityStrength);
                        gameObject.setCharacterState(CharacterState.STANDING);
                        break;

                    default:
                        gameObject.setVelocityGoal(0,gravityStrength);
                        gameObject.setCharacterState(CharacterState.STANDING);
                        break;
                }

                break;
        }
    }
}
