package com.icickes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by mickey.1cx on 17.02.2018.
 */

public class Player {

    public static final String TAG = Player.class.getName();

    private Vector2 position;
    private Viewport viewport;

    public boolean isHit;
    public int countDeaths = 0;

    public Player(Viewport viewport) {

        this.viewport = viewport;
        init();

    }

    public void init(){

        position = new Vector2(viewport.getWorldWidth() / 2, 0);
        isHit = false;

    }

    public void render(ShapeRenderer renderer) {

        renderer.setColor(Constants.playerColor);
        renderer.set(ShapeType.Filled);

        renderer.circle(position.x, position.y + Constants.PLAYER_HEAD_HEIGHT, Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_HEAD_SEGMENTS);
        renderer.rectLine(position.x, position.y + Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS,
                position.x - Constants.PLAYER_LIMB_LENGTH, position.y + Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_LIMB_LENGTH - Constants.PLAYER_HEAD_RADIUS,
                Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(position.x, position.y + Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS,
                position.x + Constants.PLAYER_LIMB_LENGTH, position.y + Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_LIMB_LENGTH - Constants.PLAYER_HEAD_RADIUS,
                Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(position.x, position.y + Constants.PLAYER_LIMB_LENGTH,
                position.x - Constants.PLAYER_LIMB_LENGTH, position.y,
                Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(position.x, position.y + Constants.PLAYER_LIMB_LENGTH,
                position.x + Constants.PLAYER_LIMB_LENGTH, position.y,
                Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(position.x, position.y + Constants.PLAYER_HEAD_HEIGHT,
                position.x, position.y + Constants.PLAYER_LIMB_LENGTH,
                Constants.PLAYER_LIMB_WIDTH);

    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_SPEED;
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_SPEED;
        }

        float accInput = -Gdx.input.getAccelerometerY() / (Constants.ACC_SENSITIVITY * Constants.ACC_GRAVITY);
        position.x += delta * accInput * Constants.PLAYER_SPEED;

        checkBounds();

    }

    private void checkBounds() {

        if (position.x <= 0) position.x = 0;
        if (position.x >= viewport.getWorldWidth()) position.x = viewport.getWorldWidth();

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setHit() {
        isHit = true;
        countDeaths++;
    }
}
