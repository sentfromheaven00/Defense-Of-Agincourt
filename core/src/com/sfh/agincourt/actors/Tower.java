package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sfh.agincourt.actors.TowerSprites;
import com.sfh.agincourt.views.PlayScreen;

public class Tower extends Actor {
    public Sprite towerSprite = new Sprite(TowerSprites.tower1);
    public int level = 1;
    public int cost = 100;
    public float fireballSpeed = 5;
    public static Tower selectedTower = null;

    public Group zombies;

    private float fireballSpawnDelay = 2.0f; // Delay in seconds
    private float timeSinceLastFireball = 0.0f;

    public Tower(float x, float y) {
        zombies = PlayScreen.zombies;
        towerSprite.setBounds(x, y, 106, 162);
        setBounds(x, y, 106, 162);

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Tower.selectedTower = Tower.this;
            }
        });
    }

    private Zombie findZombieInRange(float range) {
        for (Actor actor : zombies.getChildren()) {
            if (actor instanceof Zombie) {
                Zombie zombie = (Zombie) actor;
                float distance = Vector2.dst(getX(), getY(), zombie.getX(), zombie.getY());
                Gdx.app.log("Tower", "Distance to zombie: " + distance);
                if (distance <= range) {
                    return zombie;
                }
            }
        }
        return null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        timeSinceLastFireball += delta;
        if (timeSinceLastFireball >= fireballSpawnDelay) {
            Zombie zombie = findZombieInRange(200);
            if (zombie != null) {
                float angle = (float) Math.toDegrees(Math.atan2(zombie.getY() - getY(), zombie.getX() - getX()));

                float centerX = getX() + getWidth() / 2;
                float centerY = getY() + getHeight() / 1.33f;

                Fireball fireball = new Fireball(centerX, centerY, angle, fireballSpeed);
                getStage().addActor(fireball);
                Gdx.app.log("Tower", "Fireball spawned");
                timeSinceLastFireball = 0.0f; // Reset the timer
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        switch (level) {
            case 2:
                towerSprite.setRegion(TowerSprites.tower2);
                fireballSpeed = 7;
                fireballSpawnDelay = 1.64f;
                break;
            case 3:
                towerSprite.setRegion(TowerSprites.tower3);
                fireballSpeed = 9;
                fireballSpawnDelay = 1.28f;
                break;
            case 4:
                towerSprite.setRegion(TowerSprites.tower4);
                fireballSpeed = 11;
                fireballSpawnDelay = 0.92f;
                break;
            case 5:
                towerSprite.setRegion(TowerSprites.tower5);
                fireballSpeed = 13;
                fireballSpawnDelay = 0.56f;
                break;
            case 6:
                towerSprite.setRegion(TowerSprites.tower6);
                fireballSpeed = 15;
                fireballSpawnDelay = 0.2f;
                break;
            default:
                towerSprite.setRegion(TowerSprites.tower1);
                fireballSpeed = 5;
                fireballSpawnDelay = 2.0f;
                break;
        }
        towerSprite.draw(batch);
    }
}
