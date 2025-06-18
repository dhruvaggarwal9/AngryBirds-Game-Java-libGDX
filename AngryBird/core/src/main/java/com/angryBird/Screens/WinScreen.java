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

public class WinScreen implements Screen {
    private SpriteBatch batch;
    private Texture background;
    private Button TryAgain;
    private Button NextLevel;
    private Button MainMenu;
    private Button backButton;
    private Sprite backButtonSprite;
    private Game game;
    private final float hovervalue = 1.1f; // Hover scale value
    private static float EDGE_PADDING = 50f;

    public WinScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("winback.png");

        Button.buttonWidth = 400;
        Button.buttonHeight = 100;

        TryAgain = new Button("tryagain.png");
        TryAgain.getButtonSprite().setPosition(140, 100);

        NextLevel = new Button("nextlevelbutton.png");
        NextLevel.getButtonSprite().setPosition(140, 230);

        MainMenu = new Button("mainmenubutton.png");
        MainMenu.getButtonSprite().setPosition(140, 360);

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

        if (TryAgain.getButtonSprite().getBoundingRectangle().contains(touch)) {
            TryAgain.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new SeasonsListScreen((Main) game));
            }
        } else {
            TryAgain.getButtonSprite().setScale(1.0f);
        }

        if (NextLevel.getButtonSprite().getBoundingRectangle().contains(touch)) {
            NextLevel.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                //game.setScreen(new NextLevelScreen((Main) game));
            }
        } else {
            NextLevel.getButtonSprite().setScale(1.0f);
        }

        if (MainMenu.getButtonSprite().getBoundingRectangle().contains(touch)) {
            MainMenu.getButtonSprite().setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainScreen((Main) game));
            }
        } else {
            MainMenu.getButtonSprite().setScale(1.0f);
        }


        if (backButtonSprite.getBoundingRectangle().contains(touch)) {
            backButtonSprite.setScale(hovervalue);
            if (Gdx.input.justTouched()) {
                game.setScreen(new MainScreen((Main) game));
            }
        } else {
            backButtonSprite.setScale(1.0f);
        }


        TryAgain.getButtonSprite().draw(batch);
        NextLevel.getButtonSprite().draw(batch);
        MainMenu.getButtonSprite().draw(batch);
        backButtonSprite.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        TryAgain.getButtonSprite().getTexture().dispose();
        NextLevel.getButtonSprite().getTexture().dispose();
        MainMenu.getButtonSprite().getTexture().dispose();
        backButton.getButtonSprite().getTexture().dispose();
    }

    // Required Screen interface methods
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}