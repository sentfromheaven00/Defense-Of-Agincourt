package com.sfh.agincourt.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SuperZombie extends Zombie {
    public SuperZombie() {
        damage = 10;
        speed = 200;
        zombieSprite = new Sprite(new Texture("zombie/super_zombie.png"));
    }
}
