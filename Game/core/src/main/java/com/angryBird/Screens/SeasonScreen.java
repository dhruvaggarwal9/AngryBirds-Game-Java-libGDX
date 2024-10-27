package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
import com.angryBird.objects.Season;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SeasonScreen implements Screen{
    
    private Button backButton;
    // Button continueButton;
    // Button newGameButton;
    private Season season;
    private Main game;

    private Sprite backButtonSprite;
    private Sprite backgroundSprite;
    private Sprite blurBackgroundSprite;      // used in displaying the levelStatusscreen
    // Sprite optionsBack;
    // Sprite newGameSprite;
    // Sprite continueSprite; 
    private SpriteBatch spriteBatch;
    boolean clickedLevel = false;
    private Level selectedLevel;

    private ArrayList<Vector2> levelIconPositons = new ArrayList<Vector2>();
    private ArrayList<Sprite> levelIcons = new ArrayList<Sprite>();

    private Vector2 touch;
    
    
    public SeasonScreen(Main game,Season season){

        backButton = new Button("back4.png");
        // newGameButton = new Button("newGame5.png");
        // continueButton = new Button("continue5.png");
        this.game = game;
        this.season = season;
        this.blurBackgroundSprite = new Sprite(season.getBlurBackground());

        backButtonSprite =  backButton.getButtonSprite(); 
        backgroundSprite = new Sprite(season.getBackground());
        // newGameSprite = newGameButton.getButtonSprite();
        // continueSprite = continueButton.getButtonSprite();
        spriteBatch = new SpriteBatch();

        touch = new Vector2();

        
        levelIconPositons.add(new Vector2(192, 4*108+50));
        levelIconPositons.add(new Vector2(500, 6*108+50));
        levelIconPositons.add(new Vector2(800, 4*108));
        levelIconPositons.add(new Vector2(1100, 5*108));
        levelIconPositons.add(new Vector2(1300+40, 4*108));
        levelIconPositons.add(new Vector2(1520,5*108));
        levelIconPositons.add(new Vector2(1700, 6*108-40));


        levelIcons.add(new Sprite(new Texture("01.png")));
        levelIcons.add(new Sprite(new Texture("02.png")));
        levelIcons.add(new Sprite(new Texture("03.png")));
        levelIcons.add(new Sprite(new Texture("04.png")));
        levelIcons.add(new Sprite(new Texture("05.png")));
        levelIcons.add(new Sprite(new Texture("06.png")));
        levelIcons.add(new Sprite(new Texture("07.png")));

        setPosition();

    }

    public Sprite getBlurBackground(){
        return blurBackgroundSprite;
    }


    public void setPosition() {
        // Resize your screen here. The parameters represent the new window size.
        backButtonSprite.setSize(70, 70);
        for(int i = 0; i<levelIconPositons.size(); i++){

            levelIcons.get(i).setBounds(levelIconPositons.get(i).x, levelIconPositons.get(i).y,60,60);
            
        }

        // newGameSprite.setBounds(4*Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/5 - 2*newGameSprite.getHeight()-10,6*50,2*50);
        // continueSprite.setBounds(4*Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/5,6*50,2*50);
        
        backButtonSprite.setPosition(0,Gdx.graphics.getHeight() - backButtonSprite.getHeight());
        
        
    }


    public void handleInput(){

        if(Gdx.input.justTouched()){

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            
            if(backButtonSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(new SeasonsListScreen(game));
            }
            else{
                int i = 0;
                for (Sprite sprite : levelIcons) {
                    if(i<season.getLevels().size()){   // this makes sure only unlocked level can be played
                        if(sprite.getBoundingRectangle().contains(touch)){
                            // clickedLevel = true;
                            // selectedLevel = season.getLevels().get(i);
                            game.setScreen(new GameStatusScreen(game, this, season.getLevels().get(i)));
                            // game.setScreen(new LevelScreen(game,season.getLevels().get(i)));
                        }
                    }
                    i++;

                }
            }

            // if(newGameSprite.getBoundingRectangle().contains(touch)){
            //     game.setScreen(new LevelScreen(game,selectedLevel));
            // }

            // if(continueSprite.getBoundingRectangle().contains(touch)){
            //     //
            // }
            
        }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        handleInput();

        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundSprite.draw(spriteBatch);
        
        
        for (Sprite sprite : levelIcons) {
            
            sprite.draw(spriteBatch);
        }

        // if(clickedLevel == true){
        //     continueSprite.draw(spriteBatch);
        //     newGameSprite.draw(spriteBatch);
        // }
        backButtonSprite.draw(spriteBatch);
        
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
        backButtonSprite.getTexture().dispose();
        backgroundSprite.getTexture().dispose();
        blurBackgroundSprite.getTexture().dispose();
  
        spriteBatch.dispose();

        ArrayList<Sprite> levelIcons = new ArrayList<Sprite>();
        for (Sprite sprite : levelIcons) {
            sprite.getTexture().dispose();
        }
    }


}
