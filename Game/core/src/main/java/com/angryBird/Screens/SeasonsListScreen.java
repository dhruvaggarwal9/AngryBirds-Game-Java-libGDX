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
    private Vector2 touch;
    private SpriteBatch spriteBatch;
    private Main game;

    private int hoveredIndex = -1;
    private Texture backgroundImage;

    private static float EDGE_PADDING = 50f; //padding from screen edges
    private static final float BOX_GAP = 20f;     //Gap between boxes
    private static final float hovervalue = 1.1f; //Scale factor when hovering

    public SeasonsListScreen(Main game) {
        this.game = game;
        seasonsAvailable = game.getSeasons();
        seasonThemes = new ArrayList<Sprite>();
        spriteBatch = new SpriteBatch();
        touch = new Vector2();
        Button.buttonWidth = 100;
        Button.buttonHeight = 50;
        backButton = new Button("back4.png");
        backButtonSprite = backButton.getButtonSprite();
        backgroundImage = new Texture(Gdx.files.internal("listbg1.jpg"));

        for (Season season : seasonsAvailable) {
            seasonThemes.add(new Sprite(season.getThemeImg()));
        }

        setPosition();
    }

    public void setPosition() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        int topbox = 3;
        int bottombox = 2;

        float totalGapWidth = BOX_GAP * (topbox - 1);
        float boxWidth = (screenWidth - (2 * EDGE_PADDING) - totalGapWidth) / topbox;
        float boxHeight = boxWidth * 0.75f;

        float topRowY = screenHeight - boxHeight - EDGE_PADDING;
        float bottomRowY = topRowY - boxHeight - BOX_GAP;

        for (int i = 0; i < topbox && i < seasonThemes.size(); i++) {
            Sprite sprite = seasonThemes.get(i);
            float xPos = EDGE_PADDING + (i * (boxWidth + BOX_GAP));
            sprite.setBounds(xPos, topRowY, boxWidth, boxHeight);
            sprite.setOriginCenter();
        }

        for (int i = 0; i < bottombox && i < seasonThemes.size() - topbox; i++) {
            Sprite sprite = seasonThemes.get(i + topbox);
            float xPos = EDGE_PADDING + ((i + 0.5f) * (boxWidth + BOX_GAP));
            sprite.setBounds(xPos, bottomRowY, boxWidth, boxHeight);
            sprite.setOriginCenter();
        }
        backButtonSprite.setSize(80, 80);
        backButtonSprite.setPosition(20, EDGE_PADDING);
    }

    public void handleInput() {

        touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        hoveredIndex = -1;

        for (int i = 0; i < seasonThemes.size(); i++) {
            Sprite sprite = seasonThemes.get(i);
            if (sprite.getBoundingRectangle().contains(touch)) {
                hoveredIndex = i;
                sprite.setScale(hovervalue);
            } else {
                sprite.setScale(1.0f);
            }
        }

        if (backButtonSprite.getBoundingRectangle().contains(touch)) {
            backButtonSprite.setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainScreen(game));
            }
        } else {
            backButtonSprite.setScale(1.0f);

            if (Gdx.input.justTouched()) {
                for (int i = 0; i < seasonThemes.size(); i++) {
                    if (seasonThemes.get(i).getBoundingRectangle().contains(touch)) {
                        game.setScreen(new SeasonScreen(game, seasonsAvailable.get(i)));
                        break;
                    }
                }
            }
        }
    }
    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        spriteBatch.begin();

        spriteBatch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (Sprite sprite : seasonThemes) {

            if (sprite.getBoundingRectangle().contains(
                    Gdx.input.getX(),
                    Gdx.graphics.getHeight() - Gdx.input.getY())) {
                sprite.setScale(hovervalue);
            } else {
                sprite.setScale(1.0f);
            }
            sprite.draw(spriteBatch);
        }


        if (backButtonSprite.getBoundingRectangle().contains(
                Gdx.input.getX(),
                Gdx.graphics.getHeight() - Gdx.input.getY())) {
            backButtonSprite.setScale(hovervalue);
        } else {
            backButtonSprite.setScale(1.0f);
        }
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
    }
}
