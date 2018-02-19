package com.icickes.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.icickes.game.Constants.Difficulty;

/**
 * Created by mickey.1cx on 17.02.2018.
 */

public class Icicles {

    public static final String TAG = Icicles.class.getName();

    private DelayedRemovalArray<Icicle> icicles;
    private Viewport viewport;
    private Player player;

    private int iciclesDodged;

    float spawnRate;

    public Icicles(Viewport viewport, Player player, Difficulty difficulty) {

        spawnRate = difficulty.spawnRate;
        this.viewport = viewport;
        init(player);

    }

    public void init(Player player) {

        icicles = new DelayedRemovalArray<Icicle>(false, 100);
        this.player = player;
        iciclesDodged = 0;

    }

    public void update(float delta){

        if (MathUtils.random() < delta * spawnRate) {
            icicles.add(new Icicle(MathUtils.random() * viewport.getWorldWidth(), viewport.getWorldHeight()));
        }

        Vector2 playerPosition = player.getPosition();

        icicles.begin();
        for (int i = 0; i < icicles.size; i++) {
            Icicle icicle = icicles.get(i);
            icicle.update(delta);

            Vector2 iciclePosition = icicle.getPosition();

            if (iciclePosition.dst2(playerPosition) <= Constants.PLAYER_HEAD_RADIUS) {
                player.setHit();
            }

            if (iciclePosition.y < -Constants.ICICLE_HEIGHT) {
                icicles.removeIndex(i);
                iciclesDodged++;
            }
        }
        icicles.end();

        //System.out.println(icicles.size);

    }

    public void render(ShapeRenderer renderer) {

        renderer.setColor(Constants.icicleColor);

        for (Icicle icicle : icicles) {
            icicle.render(renderer);
        }

    }

    public int getIciclesDodged() {
        return iciclesDodged;
    }
}
