package com.angryBird.Screens;

import com.angryBird.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.angryBird.objects.Button;

public class ProfileScreen implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Button Rewards;
    private Button Friends;
    private Button Settings;
    private Button backButton;
    private Sprite backButtonSprite;
    private Game game;
    private final float hovervalue = 1.1f; // Hover scale value
    private static float EDGE_PADDING = 50f;

    public ProfileScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("profileback.png");

        Button.buttonWidth = 400;
        Button.buttonHeight = 80;

        Rewards = new Button("rewardsbutton.png");
        Rewards.getButtonSprite().setPosition(250, 200);

        Friends = new Button("friendsbutton.png");
        Friends.getButtonSprite().setPosition(250, 290);

        Settings = new Button("settingsbutton.png");
        Settings.getButtonSprite().setPosition(250, 380);

        backButton = new Button("back4.png");
        backButtonSprite = backButton.getButtonSprite();
        backButtonSprite.setSize(80, 80);
        backButtonSprite.setPosition(20, Gdx.graphics.getHeight() - 100); // Top corner positioning
    }


    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Vector2 touch = new Vector2(Gdx.input.getX(),
                Gdx.graphics.getHeight() - Gdx.input.getY());

        if (Rewards.getButtonSprite().getBoundingRectangle().contains(touch)) {
            Rewards.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new RewardsScreen((Main) game));
            }
        } else {
            Rewards.getButtonSprite().setScale(1.0f);
        }

        if (Friends.getButtonSprite().getBoundingRectangle().contains(touch)) {
            Friends.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new ComingsoonScreen((Main) game));
            }
        } else {
            Friends.getButtonSprite().setScale(1.0f);
        }

        if (Settings.getButtonSprite().getBoundingRectangle().contains(touch)) {
            Settings.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new SettingsScreen((Main) game , new MainScreen((Main) game)));
            }
        } else {
            Settings.getButtonSprite().setScale(1.0f);
        }

        if (backButtonSprite.getBoundingRectangle().contains(touch)) {
            backButtonSprite.setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainScreen((Main) game));
            }
        } else {
            backButtonSprite.setScale(1.0f);
        }

        Rewards.getButtonSprite().draw(batch);
        Friends.getButtonSprite().draw(batch);
        Settings.getButtonSprite().draw(batch);
        backButtonSprite.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        Rewards.getButtonSprite().getTexture().dispose();
        Friends.getButtonSprite().getTexture().dispose();
        Settings.getButtonSprite().getTexture().dispose();
        backButton.getButtonSprite().getTexture().dispose();
    }

    // Required Screen interface methods
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}