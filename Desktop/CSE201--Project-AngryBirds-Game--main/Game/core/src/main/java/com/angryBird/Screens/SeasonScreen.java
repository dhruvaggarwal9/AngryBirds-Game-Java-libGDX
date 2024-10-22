package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Season;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SeasonScreen implements Screen {

    Button backButton;
    Season season;
    Main game;

    Sprite backButtonSprite;
    Sprite backgroundSprite;
    SpriteBatch spriteBatch;

    ArrayList<Vector2> levelIconPositions;
    ArrayList<Sprite> levelIcons;

    Vector2 touch;

    public SeasonScreen(Main game, Season season) {
        backButton = new Button("back.png");
        this.game = game;
        this.season = season;

        backButtonSprite = backButton.getButtonSprite();
        backgroundSprite = new Sprite(season.getBackground());
        spriteBatch = new SpriteBatch();

        touch = new Vector2();

        levelIconPositions = new ArrayList<Vector2>();
        levelIcons = new ArrayList<Sprite>();

        // Create level icons first
        for (int i = 0; i < 7; i++) {
            levelIcons.add(new Sprite(new Texture("back.png")));
        }

        // Calculate initial positions
        calculateLevelPositions();
        setPosition();
    }

    private void calculateLevelPositions() {
        levelIconPositions.clear();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Starting position for the zigzag pattern
        float startX = screenWidth * 0.15f;  // 15% from left edge
        float endX = screenWidth * 0.85f;    // 85% from left edge
        float startY = screenHeight * 0.15f;  // 15% from bottom
        float maxY = screenHeight * 0.85f;    // 85% from top

        // Calculate height step between levels
        float heightStep = (maxY - startY) / 6;

        for (int i = 0; i < 7; i++) {
            float x;
            if (i % 2 == 0) {
                x = startX; // Left side
            } else {
                x = endX;   // Right side
            }

            float y = startY + (heightStep * i);
            levelIconPositions.add(new Vector2(x, y));
        }
    }

    private void setPosition() {
        // Set back button position at top-left corner with padding
        backButtonSprite.setPosition(20,
                Gdx.graphics.getHeight() - backButtonSprite.getHeight() - 20);

        // Set position and size for each level icon
        float iconSize = Gdx.graphics.getHeight() * 0.1f; // Icon size 10% of screen height

        for (int i = 0; i < levelIcons.size(); i++) {
            Vector2 pos = levelIconPositions.get(i);

            // Center the icon on its position point
            levelIcons.get(i).setBounds(
                    pos.x - iconSize/2,  // Center horizontally
                    pos.y - iconSize/2,  // Center vertically
                    iconSize,
                    iconSize
            );
        }
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            if (backButtonSprite.getBoundingRectangle().contains(touch)) {
                game.setScreen(new SeasonsListScreen(game));
            } else {
                for (int i = 0; i < levelIcons.size(); i++) {
                    if (i < season.getLevels().size()) {
                        if (levelIcons.get(i).getBoundingRectangle().contains(touch)) {
                            game.setScreen(new LevelScreen(game, season.getLevels().get(i)));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();

        ScreenUtils.clear(Color.BLACK);
        spriteBatch.begin();

        // Draw background
        backgroundSprite.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundSprite.draw(spriteBatch);

        // Draw level icons
        for (int i = 0; i < levelIcons.size(); i++) {
            if (i < season.getLevels().size()) {
                Sprite icon = levelIcons.get(i);

                // Add slight hover effect
                if (icon.getBoundingRectangle().contains(
                        Gdx.input.getX(),
                        Gdx.graphics.getHeight() - Gdx.input.getY())) {
                    icon.setScale(1.1f);
                } else {
                    icon.setScale(1.0f);
                }

                icon.draw(spriteBatch);
            }
        }

        backButtonSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        calculateLevelPositions();
        setPosition();
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
        // Dispose textures here
    }
}