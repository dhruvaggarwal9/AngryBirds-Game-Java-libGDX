package com.angryBird.Screens;

import java.util.ArrayList;
import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Season;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SeasonsListScreen implements Screen {
    private ArrayList<Season> seasonsAvailable;
    private ArrayList<Sprite> seasonThemes;
    private Button backButton;
    private Sprite backButtonSprite;
    private SpriteBatch spriteBatch;
    private Main game;
    private Texture backgroundImage;

    private static final float EDGE_PADDING = 50f;
    private static final float SEASON_GAP = 20f;
    private static final float HOVER_SCALE = 1.1f;

    public SeasonsListScreen(Main game) {
        this.game = game;
        seasonsAvailable = game.getSeasons();
        seasonThemes = new ArrayList<Sprite>();
        spriteBatch = new SpriteBatch();

        // Initialize back button
        backButton = new Button("back4.png");
        backButtonSprite = backButton.getButtonSprite();
        backButtonSprite.setSize(80, 80);

        // Load background
        backgroundImage = new Texture(Gdx.files.internal("listbg.jpg"));

        // Create sprites for each season
        for (Season season : seasonsAvailable) {
            seasonThemes.add(new Sprite(season.getThemeImg()));
        }

        setPosition();
    }

    public void setPosition() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Calculate dimensions for season boxes
        float boxWidth = 315;
        float boxHeight = 500;
        float totalWidth = (seasonThemes.size() * boxWidth) + ((seasonThemes.size() - 1) * SEASON_GAP);
        float startX = (screenWidth - totalWidth) / 2;
        float y = (screenHeight - boxHeight) / 2;

        // Position each season sprite
        for (int i = 0; i < seasonThemes.size(); i++) {
            Sprite sprite = seasonThemes.get(i);
            float x = startX + (i * (boxWidth + SEASON_GAP));
            sprite.setBounds(x, y, boxWidth, boxHeight);
            sprite.setOriginCenter();
        }

        // Position back button at bottom left
        backButtonSprite.setPosition(EDGE_PADDING, EDGE_PADDING);
    }

    public void handleInput() {
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        // Handle back button
        if (backButtonSprite.getBoundingRectangle().contains(touchX, touchY)) {
            backButtonSprite.setScale(HOVER_SCALE);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainScreen(game));
            }
        } else {
            backButtonSprite.setScale(1.0f);
        }

        // Handle season selection
        if (Gdx.input.justTouched()) {
            for (int i = 0; i < seasonThemes.size(); i++) {
                if (seasonThemes.get(i).getBoundingRectangle().contains(touchX, touchY)) {
                    game.setScreen(new SeasonScreen(game, seasonsAvailable.get(i)));
                    break;
                }
            }
        }

        // Handle hover effects
        for (Sprite sprite : seasonThemes) {
            sprite.setScale(sprite.getBoundingRectangle().contains(touchX, touchY) ? HOVER_SCALE : 1.0f);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        spriteBatch.begin();
        // Draw background
        spriteBatch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw seasons
        for (Sprite sprite : seasonThemes) {
            sprite.draw(spriteBatch);
        }

        // Draw back button
        backButtonSprite.draw(spriteBatch);
        spriteBatch.end();

        handleInput();
    }

    @Override
    public void resize(int width, int height) {
        setPosition();
        spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
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
        backgroundImage.dispose();
    }
}
