package com.icickes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by mickey.1cx on 17.02.2018.
 */

public class Constants {

    //world
    public static final float WORLD_SIZE = 10.0f;
    public static final Color bgColor = Color.ROYAL;

    //icicle
    public static final float ICICLE_HEIGHT = 1.0f;
    public static final float ICICLE_WIDTH = 0.5f;
    public static final Color icicleColor = Color.WHITE;
    public static final float ICICLE_ACC = 0.1f;
    public static final int ICICLE_SPAWN_PER_SEC = 2;

    //Player
    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 2.0f;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final float PLAYER_LIMB_LENGTH = 0.5f;
    public static final float PLAYER_LIMB_WIDTH = 0.2f;
    public static final Color playerColor = Color.BLACK;

    public static final float PLAYER_SPEED = 10.0f;

    //accelerometer
    public static final float ACC_SENSITIVITY = 0.5f;
    public static final float ACC_GRAVITY = 9.8f;

    //hud
    public static final float HUD_SIZE = 480.0f;
    public static final float HUD_MARGIN = 10.0f;
    public static final Color hudColor = Color.GOLD;

    //game
    public static final String LEVEL_EASY = "Cold";
    public static final String LEVEL_MEDIUM = "Colder";
    public static final String LEVEL_HARD = "Coldest";

    public static final float LEVEL_EASY_RATE = 2.0f;
    public static final float LEVEL_MEDIUM_RATE = 4.0f;
    public static final float LEVEL_HARD_RATE = 8.0f;

    // TODO: Add constants for the color of each difficulty select circle
    public static final Color easyColor = Color.LIGHT_GRAY;
    public static final Color mediumColor = Color.GRAY;
    public static final Color hardColor = Color.DARK_GRAY;
    // TODO: Add constant for the size of the difficulty world
    public static final float DIFFICULTY_WORLD_SIZE = 480.0f;

    // TODO: Add constant for the radius of the difficulty select "buttons"
    public static final float BUTTON_RADUIS =  DIFFICULTY_WORLD_SIZE / 9;

    // TODO: Add constant for the scale of the difficulty labels (1.5 works well)
    public static final float BUTTON_SCALE = 1.5f;

    // TODO: Add Vector2 constants for the centers of the difficulty select buttons
    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);

    public enum Difficulty {
        EASY(LEVEL_EASY_RATE, LEVEL_EASY),
        MEDIUM(LEVEL_MEDIUM_RATE, LEVEL_MEDIUM),
        HARD(LEVEL_HARD_RATE, LEVEL_HARD);

        float spawnRate;
        String label;

        Difficulty(float spawnRate, String label) {
            this.spawnRate = spawnRate;
            this.label = label;
        }
    }


}
