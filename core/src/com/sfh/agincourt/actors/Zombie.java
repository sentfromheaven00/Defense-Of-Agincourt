package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sfh.agincourt.Direction;

public class Zombie extends Actor {
    // TODO: Implement ZombieActor
    // Write a class for ZombieActor that extends Actor that will be used to represent a zombie in
    // the game. It will use the texture zombie1_left.png. It will need values for its speed, its HP, the damage that it does to the player's HP.

    // Write the class below using PlayScreen.java as a reference.
    Sprite zombieSprite;
    int feetX = 40;
    int feetY = 10;
    Direction direction = Direction.RIGHT;

    public Zombie() {
        zombieSprite = new Sprite(new Texture("zombie1_left.png"));
        zombieSprite.flip(true, false);
        setBounds(-20, 485, zombieSprite.getWidth() * .2f, zombieSprite.getHeight() * .2f);
        zombieSprite.setBounds(getX(), getY(), getWidth(), getHeight());
        this.setTouchable(Touchable.enabled);

        this.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
                Gdx.app.log("Zombie", "X: " + (int) getX() + " Y: " + (int) getY());
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        zombieSprite.setBounds(getX(), getY(), getWidth(), getHeight());
        zombieSprite.draw(batch);
    }



}
