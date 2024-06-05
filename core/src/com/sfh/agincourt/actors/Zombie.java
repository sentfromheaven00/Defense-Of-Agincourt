package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sfh.agincourt.Agincourt;
import com.sfh.agincourt.Direction;
import com.sfh.agincourt.views.PlayScreen;

public class Zombie extends Actor {
    public final Sprite zombieSprite;
    public final float damage = 2.0f;
    public final float speed = 5.0f;
    public Direction direction = Direction.RIGHT;
    final JsonValue path = new JsonReader().parse(Gdx.files.internal("path.json"));

    float pathMultiplierX = Agincourt.VIEWPORT_WIDTH / 1500f;
    float pathMultiplierY = Agincourt.VIEWPORT_HEIGHT / 840f;

    public Zombie() {
        zombieSprite = new Sprite(new Texture("zombie1_left.png"));
        setOrigin(45, 0);
        zombieSprite.setOrigin(45, 0);
        zombieSprite.flip(true, false);
        setBounds(-20, path.get(0).getFloat("y") * pathMultiplierY, zombieSprite.getWidth() * .2f * pathMultiplierX, zombieSprite.getHeight() * .2f * pathMultiplierY);
        zombieSprite.setBounds(getX(), getY(), getWidth(), getHeight());
        this.setTouchable(Touchable.enabled);


        this.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
//                Gdx.app.log("Zombie", "X: " + (int) getX() + " Y: " + (int) getY());
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        zombieSprite.setBounds(getX(), getY(), getWidth(), getHeight());

        for (JsonValue point : path) {
            Vector2 v = new Vector2(point.getFloat("x") * pathMultiplierX, point.getFloat("y") * pathMultiplierY);
            if (Math.abs(v.dst(getX(), getY())) < 6) {
//                Gdx.app.log("Zombie", "X: " + (int) getX() + " Y: " + (int) getY());
                switch (point.getString("direction")) {
                    case "UP":
                        direction = Direction.UP;
                        break;
                    case "DOWN":
                        direction = Direction.DOWN;
                        break;
                    case "LEFT":
                        direction = Direction.LEFT;
                        break;
                    case "RIGHT":
                        direction = Direction.RIGHT;
                        break;
                }
            }
        }
        switch (direction) {
            case UP:
                setPosition(getX(), getY() + speed);
                break;
            case DOWN:
                setPosition(getX(), getY() - speed);
                break;
            case LEFT:
                if (zombieSprite.isFlipX()) {
                    zombieSprite.flip(true, false);
                    setOriginX(-1 * getOriginX());
                    zombieSprite.setOrigin(-1 * getOriginX(), getOriginY());
                }
                setPosition(getX() - speed, getY());
                break;
            case RIGHT:
                if (!zombieSprite.isFlipX()) {
                    zombieSprite.flip(true, false);
                    setOriginX(-1 * getOriginX());
                    zombieSprite.setOrigin(-1 * getOriginX(), getOriginY());
                }
                setPosition(getX() + speed, getY());
                break;
        }

        if (getY() < -50) {
            PlayScreen.takeDamage(damage);
            this.remove();
        }

        zombieSprite.draw(batch);
    }
}