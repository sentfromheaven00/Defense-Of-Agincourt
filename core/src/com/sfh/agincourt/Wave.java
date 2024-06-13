package com.sfh.agincourt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sfh.agincourt.actors.SuperZombie;
import com.sfh.agincourt.actors.Zombie;
import com.sfh.agincourt.views.PlayScreen;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Interface for managing waves of enemies in the game.
 * It uses a JSON file to define the characteristics of each wave.
 */
public interface Wave {
    // JSON value containing the wave definitions from the waves.json file
    JsonValue waves = new JsonReader().parse(Gdx.files.internal("waves.json"));
    Group zombies = PlayScreen.zombies;

    /**
     * Spawns a wave of enemies on the given stage.
     *
     * @param waveNumber The number of the wave to spawn.
     */
    static void spawnWave(int waveNumber) {
        Gdx.app.log("Wave", "Spawning wave " + waveNumber);
        JsonValue wave = waves.get(waveNumber);
        // print out the wave JSON value to the console

        Json json = new Json();

        try {
            // If there is a child in the wave named "zombie", add the integer value of the child amount of zombies to the stage
            if (wave.getString("zombie") != null) {
                int amount = wave.getInt("zombie");

                // Repeatedly add a new Zombie actor to the stage, the number of times specified by 'amount'
                Utils.repeat(amount, () -> zombies.addActor(new Zombie()));
            }
            if (wave.getString("superzombie") != null) {
                int amount = wave.getInt("superzombie");

                Utils.repeat(amount, () -> zombies.addActor(new SuperZombie()));
            }
        } catch (Exception e) {
            Gdx.app.log("Wave", "Error spawning wave " + waveNumber, e);
        }
    }

    static void startWaves() {
        AtomicBoolean isWaveActive = new AtomicBoolean(false);
        Thread waveThread = new Thread(() -> {
            int i = 0;
            while (i < waves.size) {
                if (zombies.getChildren().size == 0)
                    isWaveActive.set(false);
                if (!isWaveActive.get()) {
                    isWaveActive.set(true);
                    spawnWave(i);
                    i++;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Thread.yield();
            }
        });
        waveThread.setDaemon(true);
        waveThread.start();
    }
}