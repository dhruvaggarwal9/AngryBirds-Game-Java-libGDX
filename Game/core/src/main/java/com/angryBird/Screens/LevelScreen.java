package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Bird;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
// import com.angryBird.objects.Level1;
import com.angryBird.objects.Pig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {
    
    Texture birdBase;
    Texture pigBase;
    Button pauseButton;
    Level level;
    Main game;
    
    Sprite birdBaseSprite;
    Sprite pigBaseSprite;
    Sprite pauseButtonSprite;
    Sprite catapultSprite;
    ArrayList<Sprite> birdSprites;
    ArrayList<Sprite> pigSprites;
    Sprite backgroundSprite;
    Sprite grass;
    Sprite groundLayer;
    ArrayList<Sprite> blockSprites;
    Vector2 touch;
    SpriteBatch spriteBatch;


    public LevelScreen(Main game,Level level){

        birdBase = new Texture("base.png");
        pigBase = new Texture("stone3.png");
        pauseButton = new Button("pause.png");
        this.level = level;
        this.game = game;

        birdBaseSprite = new Sprite(birdBase);
        pigBaseSprite = new Sprite(pigBase);
        pauseButtonSprite = pauseButton.getButtonSprite();
        catapultSprite = level.getCatapultSprite();
        birdSprites = new ArrayList<Sprite>();
        pigSprites = new ArrayList<Sprite>();
        backgroundSprite = level.getBackSprite();
        blockSprites = level.getBuilding().getBlockSprites();
        groundLayer = new Sprite(new Texture("groundlayer2.png"));
        touch = new Vector2();
        spriteBatch = new SpriteBatch();
        
        for (Bird bird : game.getBirdsUnlocked()) {
            birdSprites.add(new Sprite(bird.getImg()));
        }

        for (Pig pig : level.getPigEnemies()) {
            pigSprites.add(new Sprite(pig.getImg()));
        }

        setPosition();
    }

    public void setPosition() {
        // Resize your screen here. The parameters represent the new window size.
        float width = Gdx.graphics.getWidth()/10;
        float height = Gdx.graphics.getHeight()/10 + 20;
        birdBaseSprite.setBounds(width, 70, birdBaseSprite.getWidth(),birdBaseSprite.getHeight());
        pigBaseSprite.setBounds(8*width, 0, pigBaseSprite.getWidth()/2,pigBaseSprite.getHeight()*3/12);
        grass = new Sprite(new Texture("grass.png"));
        grass.setBounds(7*width+60, pigBaseSprite.getHeight(), 2*width, grass.getHeight());
        groundLayer.setBounds(0, 0, 10*width, groundLayer.getHeight()*3/2);
        // catapultSprite.setScale(3/4);
        catapultSprite.setBounds(birdBaseSprite.getX() + birdBaseSprite.getWidth()/2+40, birdBaseSprite.getY()+birdBaseSprite.getHeight()-20,catapultSprite.getWidth()*3/4,catapultSprite.getHeight()*3/4);

     
        for (Sprite sprite : birdSprites) {
            
            if(sprite.equals(birdSprites.getFirst())){
                

                sprite.setSize(sprite.getWidth()*3/4, sprite.getHeight()*3/4);
                sprite.setCenter(catapultSprite.getX()+20,catapultSprite.getY() + catapultSprite.getHeight()-20);
                

            }
            else{
                
                sprite.setSize(sprite.getWidth()*3/4, sprite.getHeight()*3/4);
                sprite.setCenter(birdBaseSprite.getX()+birdBaseSprite.getWidth()-30-50*(birdSprites.indexOf(sprite)+1),sprite.getHeight()+catapultSprite.getY()-15);
          
            }
            
        }

        //setPostion// of pig
        level.getBuilding().translate(pigBaseSprite.getX(), pigBaseSprite.getY() + pigBaseSprite.getHeight()+10); // translating sprites stored in building object realtive to level positions

        ArrayList<Rectangle> emptySpaces = level.getBuilding().getEmptySpaces();
        for(int i = 0; i< pigSprites.size(); i++){
            pigSprites.get(i).setSize(50, 50);
            Vector2 center = new Vector2();
            emptySpaces.get(i).getCenter(center);
            pigSprites.get(i).setPosition(center.x-10, center.y-30);
            // pigSprites.get(i).getBoundingRectangle().fitInside(emptySpaces.get(i));
        }


        ///building///

        pauseButtonSprite.setPosition(0,10*(height-20)-pauseButtonSprite.getHeight());
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }



    public void handleInput(){

        if (Gdx.input.justTouched()) {

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

            if(pauseButtonSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(new PauseScreen(game,this));
            }
          
        }

    }


    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        
        handleInput();

        spriteBatch.begin();
        ScreenUtils.clear(Color.BLACK);

        
        backgroundSprite.draw(spriteBatch);

        //building//

        //pig//
        catapultSprite.draw(spriteBatch);
        birdBaseSprite.draw(spriteBatch);
        pigBaseSprite.draw(spriteBatch);
        pauseButtonSprite.draw(spriteBatch);
        // catapultSprite.draw(spriteBatch);
        // grass.draw(spriteBatch);
        for (Sprite birdSprite : birdSprites) {
            birdSprite.draw(spriteBatch);
        }

        
        for (Sprite blockSprite : blockSprites) {
            blockSprite.draw(spriteBatch);
        }

        for (Sprite pigSprite : pigSprites) {
            pigSprite.draw(spriteBatch);
        }
        groundLayer.draw(spriteBatch);
        spriteBatch.end();

    }



    @Override
    public void show() {
        // Prepare your screen here.
    }

    public void input(){

        // if(){
        //     game.setScreen(new Secondscreen());
        // }
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

        level.getBuilding().translate(-pigBaseSprite.getX(),-(pigBaseSprite.getY() + pigBaseSprite.getHeight()+10)); 
    }



}
