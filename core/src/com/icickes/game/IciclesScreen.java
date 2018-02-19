package com.icickes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.icickes.game.Constants.Difficulty;

/**
 * Created by mickey.1cx on 17.02.2018.
 */

public class IciclesScreen extends InputAdapter implements Screen {

    public static final String TAG = IciclesScreen.class.getName();

    ExtendViewport viewport;
    ShapeRenderer renderer;
    Icicles icicles;
    Player player;

    ScreenViewport hud;
    SpriteBatch batch;
    BitmapFont font;

    int topScore;

    Difficulty difficulty;
    IciclesGame game;

    public IciclesScreen(IciclesGame game, Difficulty difficulty) {

        this.game = game;
        this.difficulty = difficulty;

    }

    @Override
    public void show() {

        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        hud = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Constants.hudColor);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

        player = new Player(viewport);
        icicles = new Icicles(viewport, player, difficulty);

        topScore = 0;

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {

        player.update(delta);
        icicles.update(delta);

        if (player.isHit) {
            player.isHit = false;
            icicles.init(player);
        }

        viewport.apply(true);

        Gdx.gl.glClearColor(Constants.bgColor.r, Constants.bgColor.g, Constants.bgColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeType.Filled);
        icicles.render(renderer);
        player.render(renderer);
        renderer.end();

        topScore = Math.max(topScore, icicles.getIciclesDodged());
        hud.apply();
        batch.setProjectionMatrix(hud.getCamera().combined);
        batch.begin();
        font.draw(batch, "Deaths: " + player.countDeaths + "\nDifficulty: " + difficulty,
                Constants.HUD_MARGIN, hud.getWorldHeight() - Constants.HUD_MARGIN);
        font.draw(batch, "Score: " + icicles.getIciclesDodged() + "\nTop Score: " + topScore,
                hud.getWorldWidth() - Constants.HUD_MARGIN, hud.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);
        hud.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_SIZE);

        player.init();
        icicles.init(player);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

        renderer.dispose();
        batch.dispose();
        font.dispose();

    }

    @Override
    public void dispose() {

        renderer.dispose();
        batch.dispose();
        font.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        game.showDifficultyScreen();
        return true;

    }
}
