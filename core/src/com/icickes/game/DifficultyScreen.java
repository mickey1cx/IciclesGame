package com.icickes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.icickes.game.Constants.Difficulty;

/**
 * Created by mickey.1cx on 17.02.2018.
 */

public class DifficultyScreen extends InputAdapter implements Screen {

    public static final String TAG = DifficultyScreen.class.getName();

    IciclesGame game;

    ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;

    BitmapFont font;

    public DifficultyScreen(IciclesGame game) {

        this.game = game;

    }

    @Override
    public void show() {

        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new FitViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.BUTTON_SCALE);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

    }

    @Override
    public void render(float delta) {

        viewport.apply();

        Gdx.gl.glClearColor(Constants.bgColor.r, Constants.bgColor.g, Constants.bgColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeType.Filled);
        renderer.setColor(Constants.easyColor);
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.BUTTON_RADUIS);
        renderer.setColor(Constants.mediumColor);
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.BUTTON_RADUIS);
        renderer.setColor(Constants.hardColor);
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.BUTTON_RADUIS);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.LEVEL_EASY);
        font.draw(batch, Constants.LEVEL_EASY, Constants.EASY_CENTER.x, Constants.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.LEVEL_MEDIUM);
        font.draw(batch, Constants.LEVEL_MEDIUM, Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.LEVEL_HARD);
        font.draw(batch, Constants.LEVEL_HARD,
                Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

        batch.dispose();
        font.dispose();
        renderer.dispose();

    }

    @Override
    public void dispose() {

        renderer.dispose();
        batch.dispose();
        font.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constants.EASY_CENTER) < Constants.BUTTON_RADUIS) {
            game.showIciclesScreen(Difficulty.EASY);
        }

        if (worldTouch.dst(Constants.MEDIUM_CENTER) < Constants.BUTTON_RADUIS) {
            game.showIciclesScreen(Difficulty.MEDIUM);
        }

        if (worldTouch.dst(Constants.HARD_CENTER) < Constants.BUTTON_RADUIS) {
            game.showIciclesScreen(Difficulty.HARD);
        }

        return true;

    }
}
