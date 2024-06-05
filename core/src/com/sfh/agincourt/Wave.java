package com.sfh.agincourt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.sfh.agincourt.actors.Zombie;
import com.sfh.agincourt.views.PlayScreen;

/**
 * Interface for managing waves of enemies in the game.
 * It uses a JSON file to define the characteristics of each wave.
 */
public interface Wave {
    // JSON value containing the wave definitions from the waves.json file
    JsonValue waves = new JsonReader().parse(Gdx.files.internal("waves.json"));
    Stage stage = PlayScreen.stage;
    Group zombies = PlayScreen.zombies;

    /**
     * Spawns a wave of enemies on the given stage.
     *
     * @param waveNumber The number of the wave to spawn.
     */
    static void spawnWave(int waveNumber) {
        Gdx.app.log("Wave", "Spawning wave " + waveNumber);
        JsonValue wave = waves.get(waveNumber);

        try {
            // If there is a child in the wave named "zombie", add the integer value of the child amount of zombies to the stage
            if (wave.getString("zombie") != null) {
                int amount = wave.getInt("zombie");
                Gdx.app.log("Wave", "Spawning " + amount + " zombies");

                // Repeatedly add a new Zombie actor to the stage, the number of times specified by 'amount'
                Utils.repeat(amount, () -> zombies.addActor(new Zombie()));

            }
        } catch (Exception e) {
            Gdx.app.log("Wave", "Error spawning wave " + waveNumber, e);
        }

    }
}