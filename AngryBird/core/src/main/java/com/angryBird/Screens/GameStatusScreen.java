package com.angryBird.Screens;

import com.angryBird.Main;
import com.angryBird.objects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GameStatusScreen implements Screen {

    private Button crossButton;
    private Button newGameButton;
    private Button continueButton;
    private Main game;
    private Level selectedLevel;
    private SeasonScreen seasonScreen;

    private LevelScreenM forthis;

    private Sprite backgroundSprite;
    private Sprite crossButtonSprite;
    private Sprite newGameSprite;
    private Sprite continueSprite;
    private SpriteBatch spriteBatch;
    private Vector2 touch;


    public GameStatusScreen(Main game,SeasonScreen seasonScreen,Level selectedLevel){

        // Button tesButton = new

        crossButton = new Button("crossButton.png");
        newGameButton = new Button("newGame5.png");
        continueButton = new Button("continue6.png");
        this.game = game;
        this.seasonScreen = seasonScreen;
        this.selectedLevel = selectedLevel;
        // Texture t = new Texture("seasonBackground1.png");
        // t.
        backgroundSprite = seasonScreen.getBlurBackground();
        newGameSprite  = newGameButton.getButtonSprite();
        continueSprite = continueButton.getButtonSprite();
        crossButtonSprite = crossButton.getButtonSprite();

        spriteBatch = new SpriteBatch();
        touch = new Vector2();

        setPostions();
    }

    public void setPostions(){


        float width = Gdx.graphics.getWidth()/10;
        float height = Gdx.graphics.getHeight()/10;

        backgroundSprite.setSize(10*width, 10*height);
        continueSprite.setBounds(4*width,6*height,5*(50) , 2*50);
        newGameSprite.setBounds(4*width,6*height-continueSprite.getHeight()-10,5*(50), 2*(50));
        crossButtonSprite.setBounds(4*width + continueSprite.getWidth(), 6*height + continueSprite.getHeight(), 60, 60);
    }


    public void handleInput() {
        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            if (crossButtonSprite.getBoundingRectangle().contains(touch)) {
                game.setScreen(seasonScreen);
            } else if (newGameSprite.getBoundingRectangle().contains(touch)) {
                LevelScreenM prev  =new LevelScreenM(game, selectedLevel);
                game.setScreen(prev);
            } else if (continueSprite.getBoundingRectangle().contains(touch)) {
                loadGame();
            }
        }
    }

    private void loadGame() {
        try {
            System.out.println("Game loaded successfully1223!");

            FileInputStream fileIn = new FileInputStream("game_save.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Read the saved GameState
            GameState gameState = (GameState) in.readObject();
            in.close();
            fileIn.close();

            // Update the game state
            ArrayList<Bird> unlockedBirds = gameState.unlockedBirds;
            ArrayList<Pig> unlockedPigs = gameState.unlockedPigs;
            ArrayList<Building> unlockedBuildings = gameState.unlockedBuildings;

            // Set the unlocked birds, pigs, and buildings in the Main game class
//            game.setBirdsUnlocked(unlockedBirds);
//            game.setPigsAvailable(unlockedPigs);
//            game.setBuildingsAvailable(unlockedBuildings);
            LevelScreenM prev  =new LevelScreenM(game, selectedLevel);
            game.setScreen(prev);
            // Navigate to the appropriate level
            int currentLevelIndex = 1; // Replace with a property in `GameState` if necessary
            game.setScreen(new LevelScreenM(game, selectedLevel));

            System.out.println("Game loaded successfully!");
        } catch (Exception e) {
            LevelScreenM prev  =new LevelScreenM(game, selectedLevel);
            game.setScreen(prev);
        }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        handleInput();
        spriteBatch.begin();

        backgroundSprite.draw(spriteBatch);
        crossButtonSprite.draw(spriteBatch);
        continueSprite.draw(spriteBatch);
        newGameSprite.draw(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }


    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.

        backgroundSprite.getTexture().dispose();
        crossButtonSprite.getTexture().dispose();
        newGameSprite.getTexture().dispose();
        continueSprite.getTexture().dispose();
        spriteBatch.dispose();
    }


}
