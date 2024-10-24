package com.angryBird.Screens;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainScreen implements Screen {
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Button singlePlayerButton;
    private Button multiPlayerButton;
    private Button profileButton;
    private Main game;
    private Button settingButton;

    private static final float Y_OPTIONS = 31f;

    private int hoveredButtonIndex = -1;

    private static final float BUTTON_GAP = 50f; // Gap between buttons
    private static final float hovervalue = 1.1f; // Hover scale factor

    public MainScreen(Main game) {
        this.game = game;
        spriteBatch = new SpriteBatch();

        backgroundTexture = new Texture("MainScreen.png");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        singlePlayerButton = new Button("mainplay.png");
        multiPlayerButton = new Button("multiplayer.png");
        profileButton = new Button("profile.png");
        settingButton = new Button("setting.png");
        float buttonWidth = 400f;
        float buttonHeight = 200f;
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        Sprite singlePlayerSprite = singlePlayerButton.getButtonSprite();
        Sprite multiPlayerSprite = multiPlayerButton.getButtonSprite();
        Sprite profileSprite = profileButton.getButtonSprite();
        Sprite settingSprite = settingButton.getButtonSprite();

        singlePlayerSprite.setSize(buttonWidth, buttonHeight);
        multiPlayerSprite.setSize(buttonWidth, buttonHeight);
        profileSprite.setSize(buttonWidth, buttonHeight);
        settingSprite.setSize(80, 80);

        singlePlayerSprite.setPosition(BUTTON_GAP, Y_OPTIONS);
        multiPlayerSprite.setPosition(singlePlayerSprite.getX() + singlePlayerSprite.getWidth() + BUTTON_GAP, Y_OPTIONS);
        profileSprite.setPosition(multiPlayerSprite.getX() + multiPlayerSprite.getWidth() + BUTTON_GAP, Y_OPTIONS);
        settingSprite.setPosition(screenWidth-settingSprite.getWidth(), screenHeight-settingSprite.getHeight());
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        handleInput();

        spriteBatch.begin();
        backgroundSprite.draw(spriteBatch);

        drawButton(singlePlayerButton.getButtonSprite(), 0);
        drawButton(multiPlayerButton.getButtonSprite(), 1);
        drawButton(profileButton.getButtonSprite(), 2);
        drawButton(settingButton.getButtonSprite(), 3);

        spriteBatch.end();
    }


    private void drawButton(Sprite buttonSprite, int buttonIndex) {
        if (hoveredButtonIndex == buttonIndex) {
            buttonSprite.setScale(hovervalue);
        } else {
            buttonSprite.setScale(1.0f);
        }
        buttonSprite.draw(spriteBatch);
    }


    private void handleInput() {
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        hoveredButtonIndex = -1;

        if (singlePlayerButton.getButtonSprite().getBoundingRectangle().contains(touchX, touchY)) {
            hoveredButtonIndex = 0;
        } else if (multiPlayerButton.getButtonSprite().getBoundingRectangle().contains(touchX, touchY)) {
            hoveredButtonIndex = 1;
        } else if (profileButton.getButtonSprite().getBoundingRectangle().contains(touchX, touchY)) {
            hoveredButtonIndex = 2;
        }
        else if(settingButton.getButtonSprite().getBoundingRectangle().contains(touchX,touchY)){
            hoveredButtonIndex = 3;
        }

        if (Gdx.input.justTouched()) {
            if (hoveredButtonIndex == 0) {
                game.setScreen(new SeasonsListScreen(game));
            } else if (hoveredButtonIndex == 1) {
                game.setScreen(new ComingsoonScreen(game));
            } else if (hoveredButtonIndex == 2) {
                //profile button click
            }else if(hoveredButtonIndex == 3){
                game.setScreen(new SettingsScreen(game, this));
            }
            
        }
    }

    @Override
    public void resize(int width, int height) {

        backgroundSprite.setSize(width, height);


        float buttonWidth = width * 0.3f;
        float buttonHeight = height * 0.175f;

        Sprite singlePlayerSprite = singlePlayerButton.getButtonSprite();
        Sprite multiPlayerSprite = multiPlayerButton.getButtonSprite();
        Sprite profileSprite = profileButton.getButtonSprite();

        singlePlayerSprite.setSize(buttonWidth, buttonHeight);
        multiPlayerSprite.setSize(buttonWidth, buttonHeight);
        profileSprite.setSize(buttonWidth, buttonHeight);


        singlePlayerSprite.setPosition(BUTTON_GAP, Y_OPTIONS);
        multiPlayerSprite.setPosition(singlePlayerSprite.getX() + singlePlayerSprite.getWidth() + BUTTON_GAP, Y_OPTIONS);
        profileSprite.setPosition(multiPlayerSprite.getX() + multiPlayerSprite.getWidth() + BUTTON_GAP, Y_OPTIONS);


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
        backgroundTexture.dispose();
    }
}
