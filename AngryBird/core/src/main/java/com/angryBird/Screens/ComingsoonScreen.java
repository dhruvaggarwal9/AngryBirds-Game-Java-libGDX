package com.angryBird.Screens;

import com.angryBird.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.angryBird.objects.Button;

public class ComingsoonScreen implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Button backButton;
    private Game game;
    private final float hovervalue = 1.1f; // Hover scale value

    public ComingsoonScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();

        background = new Texture("comingsoon.png");

        Button.buttonWidth = 300;
        Button.buttonHeight = 150;

        backButton = new Button("backcs.png");
        backButton.getButtonSprite().setPosition(20, 20);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Vector2 touch = new Vector2(Gdx.input.getX(),
                Gdx.graphics.getHeight() - Gdx.input.getY());

        if (backButton.getButtonSprite().getBoundingRectangle().contains(touch)) {
            backButton.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainScreen((Main) game));
            }
        } else {
            backButton.getButtonSprite().setScale(1.0f);
        }

        backButton.getButtonSprite().draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }

    // Required Screen interface methods
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
