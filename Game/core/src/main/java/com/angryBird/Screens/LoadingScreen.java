package com.angryBird.Screens;

import com.angryBird.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadingScreen implements Screen {

    private SpriteBatch spriteBatch;
    private Texture background;
    private Texture loadingBarTexture;
    private float progress;
    private Main game;

    private static final float LOADING_BAR_WIDTH = 1300f;


    public LoadingScreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        background = new Texture("loadingbg.png");
        loadingBarTexture = new Texture("loadingbar.png");
        progress = 0;
    }

    @Override
    public void show() {
        // This could be used to load resources asynchronously, if needed
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (progress < 1) {
            progress += delta / 3f;
            if (progress > 1) progress = 1;
        } else {
            game.setScreen(new MainScreen(game));
            return;
        }

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float screenHeight = Gdx.graphics.getHeight();
        float barX = (Gdx.graphics.getWidth() - LOADING_BAR_WIDTH) / 2;
        float barY = 173f;
        spriteBatch.draw(loadingBarTexture, barX, barY, LOADING_BAR_WIDTH * progress, 33f);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        background.dispose();
        loadingBarTexture.dispose(); 
    }
}