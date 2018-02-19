package com.icickes.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mickey.1cx on 17.02.2018.
 */

public class Icicle {

    public static final String TAG = Icicle.class.getName();

    private Vector2 position = new Vector2();
    private float velocity = 0;

    public Icicle(float x, float y) {

        position.x = x;
        position.y = y;

    }

    public void update(float delta) {

        velocity += delta * Constants.ICICLE_ACC;

        position.y -= velocity;

//        System.out.println(position.x);

    }

    public void render(ShapeRenderer renderer) {

        renderer.set(ShapeType.Filled);
        renderer.triangle(position.x, position.y,
                position.x + Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT,
                position.x - Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT);

    }

    public Vector2 getPosition() {
        return position;
    }
}
