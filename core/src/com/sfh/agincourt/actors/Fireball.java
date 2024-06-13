package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.sfh.agincourt.views.PlayScreen;

public class Fireball extends Actor {
    private Animation<TextureRegion> fireballAnimation;
    private float stateTime = 0;
    private float speed;
    private float frameDuration = 0.1f;



    public Fireball(float x, float y, float angle , float speed) {
        this.speed = speed;

        Array<TextureAtlas.AtlasRegion> fireballFrames = FireballSprites.atlas.getRegions();
        fireballAnimation = new Animation<>(frameDuration, fireballFrames, Animation.PlayMode.LOOP);
        setBounds(x, y, 32, 32);
        setRotation(angle);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        float x = (float) (getX() + Math.cos(Math.toRadians(getRotation())) * speed);
        float y = (float) (getY() + Math.sin(Math.toRadians(getRotation())) * speed);
        setPosition(x, y);

        // Check for collisions with zombies
        for (Actor actor : PlayScreen.zombies.getChildren()) {
            if (actor instanceof Zombie) {
                Zombie zombie = (Zombie) actor;
                if (getBounds().overlaps(zombie.getBounds())) {
                    // Handle collision
                    remove(); // Remove the fireball
                    zombie.remove(); // Remove the zombie
                    break;
                }
            }
        }

        // Check if the fireball is outside the screen bounds
        if (x < 0 || x > Gdx.graphics.getWidth() || y < 0 || y > Gdx.graphics.getHeight()) {
            remove(); // Remove the fireball
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion currentFrame = fireballAnimation.getKeyFrame(stateTime);
        float originX = currentFrame.getRegionWidth() / 2.0f;
        float originY = currentFrame.getRegionHeight() / 2.0f;

        float rotation = getRotation() - 180;

        batch.draw(currentFrame, getX() - originX, getY() - originY, originX, originY,
                currentFrame.getRegionWidth(), currentFrame.getRegionHeight(),
                getScaleX(), getScaleY(), rotation);
    }
}