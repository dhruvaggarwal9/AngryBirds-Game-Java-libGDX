package com.angryBird.Screens;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.angryBird.objects.Pig;

public class MainScreen implements Screen {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Button startButton;
    private Main game;

    public MainScreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();


        backgroundTexture = new Texture("MainScreen.png");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        startButton = new Button("start.png");

        Sprite btnSprite = startButton.getButtonSprite();
        btnSprite.setSize(500, 300);
        btnSprite.setPosition(
                (Gdx.graphics.getWidth() - btnSprite.getWidth()) / 2,
                 Gdx.graphics.getHeight() / 9
        );
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);

        handleInput();

        spriteBatch.begin();
        backgroundSprite.draw(spriteBatch);
        startButton.getButtonSprite().draw(spriteBatch);
        spriteBatch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (startButton.getButtonSprite().getBoundingRectangle().contains(touchX, touchY)) {

                Pig pig = new Pig("bigPig", "p1.png", 100, 100);
                Button pauseButton = new Button("pause.png");
                Level level = new Level("scene2.png", pig, pauseButton);
                game.setScreen(new LevelScreen(level, game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {

        backgroundSprite.setSize(width, height);


        Sprite btnSprite = startButton.getButtonSprite();
        btnSprite.setPosition(
                (width - btnSprite.getWidth()) / 2,
                height / 9
        );
    }


    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        backgroundTexture.dispose();
    }
}