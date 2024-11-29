package com.angryBird.Screens;

import com.angryBird.Main;
import com.angryBird.objects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PauseScreen implements Screen {

    // Texture background;
    private Button resume;
    private Button returnHome;
    private Button saveGame;
    private Main game;
    private LevelScreenM levelScreen;

    private Sprite backSprite;
    private Sprite resumeSprite;
    private Sprite returnSprite;
    private Sprite saveGameSprite;
    private SpriteBatch spriteBatch;
    private Vector2 touch;
    public PauseScreen(Main game,LevelScreenM level){

        // Button tesButton = new
        resume = new Button("resume5.png");
        returnHome = new Button("returnHome5.png");
        saveGame = new Button("saveGame5.png");
        this.game = game;
        this.levelScreen = level;

        backSprite = new Sprite(new Texture("pauseBackground.png"));
        resumeSprite = resume.getButtonSprite();
        returnSprite = returnHome.getButtonSprite();
        saveGameSprite = saveGame.getButtonSprite();
        spriteBatch = new SpriteBatch();
        touch = new Vector2();

        setPostions();
    }

    public void setPostions(){


        float width = Gdx.graphics.getWidth()/10;
        float height = Gdx.graphics.getHeight()/10;

        backSprite.setSize(10*width, 10*height);
        resumeSprite.setBounds(4*width,6*height,5*(50) , 2*50);
        returnSprite.setBounds(4*width,6*height-resumeSprite.getHeight()-10,5*(50), 2*(50));
        saveGameSprite.setBounds(4*width, 6*height-2*resumeSprite.getHeight()-20, 5*50, 2*50);
    }


    public void saveGame() {
        try {
 
            int j = levelScreen.loadedBirdNum;
            Level l = levelScreen.getLevel();
            l.removebird((j));
            System.out.println("save click kiya");
            int currentLevelIndex = 1;
            ArrayList<Bird> unlockedBirds = new ArrayList<>();
            for (Bird bird : game.getBirdsUnlocked()) {
                unlockedBirds.add(bird);
            }

            ArrayList<Pig> unlockedPigs = new ArrayList<>();
            for (Pig pig : game.getPigsAvailable()) {
                unlockedPigs.add(pig);
            }

            ArrayList<Building> unlockedBuildings = new ArrayList<>();
//            for (Building building : game.getBuildingsAvailable()) {
//                unlockedBuildings.add(building);
//            }


            GameState gameState = new GameState(currentLevelIndex, unlockedBirds, unlockedPigs, unlockedBuildings);

            // Save to file
            FileOutputStream fileOut = new FileOutputStream("game_save.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameState);
            out.close();
            fileOut.close();
            System.out.println("Game saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void handleInput(){



        if (Gdx.input.justTouched()) {

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());


            if(returnSprite.getBoundingRectangle().contains(touch)){
                levelScreen.dispose();
                game.setScreen(new MainScreen(game));
            }
            else if(resumeSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(levelScreen);
            }
            else if(saveGameSprite.getBoundingRectangle().contains(touch)){
                saveGame();
                game.setScreen(new MainScreen(game));
            }
        }
    }
    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        handleInput();
        spriteBatch.begin();

        backSprite.draw(spriteBatch);
        resumeSprite.draw(spriteBatch);
        returnSprite.draw(spriteBatch);
        saveGameSprite.draw(spriteBatch);
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
        backSprite.getTexture().dispose();
        resumeSprite.getTexture().dispose();
        returnSprite.getTexture().dispose();
        saveGameSprite.getTexture().dispose();
        spriteBatch.dispose();
    }



}
