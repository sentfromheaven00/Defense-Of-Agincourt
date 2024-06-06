package com.sfh.agincourt;

import com.badlogic.gdx.Gdx;

/**
 * Utility interface containing methods for repeating tasks.
 */
public interface Utils {

    /**
     * Repeats a task a specified number of times at one-second intervals.
     *
     * @param times    The number of times the task should be repeated.
     * @param runnable The task to be executed, represented as a Runnable.
     */
    static void repeat(int times, Runnable runnable) {
        new Thread(() -> {
            for (int i = 0; i < times; i++) {
                long time = System.currentTimeMillis();
                while (System.currentTimeMillis() < time + 1000){}
                Gdx.app.postRunnable(runnable);
            }
        }).start();
    }

    /**
     * Repeats a task a specified number of times at a specified interval.
     *
     * @param times    The number of times the task should be repeated.
     * @param delay    The delay between each task execution, in seconds.
     * @param runnable The task to be executed, represented as a Runnable.
     */
    static void repeat(int times, float delay, Runnable runnable) {
        new Thread(() -> {
            for (int i = 0; i < times; i++) {
                long time = System.currentTimeMillis();
                Gdx.app.postRunnable(runnable);
                while (System.currentTimeMillis() < time + (delay * 1000)){}
            }
        }).start();
    }
}