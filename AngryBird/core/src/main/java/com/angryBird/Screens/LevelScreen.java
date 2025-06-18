package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Bird;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
// import com.angryBird.objects.Level1;
import com.angryBird.objects.Pig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {

    private Texture birdBase;
    private Texture pigBase;
    private Button pauseButton;
    private Level level;
    private Main game;

    private Sprite birdBaseSprite;
    private Sprite pigBaseSprite;
    private Sprite pauseButtonSprite;
    private Sprite catapultSprite;
    private ArrayList<Sprite> birdSprites;
    private ArrayList<Sprite> pigSprites;
    private Sprite backgroundSprite;
    private Sprite grass;
    private Sprite groundLayer;
    private ArrayList<Sprite> blockSprites;
    private Vector2 touch;
    private SpriteBatch spriteBatch;




    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    Body platform;
    private ArrayList<Body> birdBodies;
    private ArrayList<Body> pigBodies;
    private ArrayList<Body> blockBodies;


    static final float lamda = 10f;  //(1 meter = 10 pixels)


    public LevelScreen(Main game,Level level){

        birdBase = new Texture("base.png");
        pigBase = new Texture("stone3.png");
        pauseButton = new Button("pause4.png");
        this.level = level;
        this.game = game;

        birdBaseSprite = new Sprite(birdBase);
        pigBaseSprite = new Sprite(pigBase);
        pauseButtonSprite = pauseButton.getButtonSprite();
        catapultSprite = level.getCatapultSprite();
        birdSprites = new ArrayList<Sprite>();
        pigSprites = new ArrayList<Sprite>();
        backgroundSprite = level.getBackSprite();
        // blockSprites = level.getBuilding().getBlockSprites();
        groundLayer = new Sprite(new Texture("groundlayer2.png"));
        touch = new Vector2();
        spriteBatch = new SpriteBatch();

        // for (Bird bird : game.getBirdsUnlocked()) {
        //     birdSprites.add(new Sprite(bird.getImg()));
        // }

        // for (Pig pig : level.getPigEnemies()) {
        //     pigSprites.add(new Sprite(pig.getImg()));
        // }

        setPosition();
        create();
    }

    public Vector2 screenPosition(Vector2 pos){
		Vector2 offset = new Vector2(camera.viewportWidth/2, camera.viewportHeight/2);
		pos = pos.add(offset);
		return pos.scl(lamda);
	}

    public Vector2 screenPosition(float x,float y){

        Vector2 pos = new Vector2(x,y);
		Vector2 offset = new Vector2(camera.viewportWidth/2, camera.viewportHeight/2);
		pos = pos.add(offset);
		return pos.scl(lamda);
	}
    public Vector2 worldPosition(float x,float y){

        Vector2 offset = new Vector2(camera.viewportWidth/2, camera.viewportHeight/2);
        Vector2 pos = new Vector2(x,y);
        pos = pos.scl(lamda);
        pos = pos.sub(offset);

		return pos;
    }
    public void create(){

        world = new World(new Vector2(0, -10), true);
        camera = new OrthographicCamera(192, 108);				//whole screen is (192 meters, 108 meters);
        debugRenderer = new Box2DDebugRenderer();
        birdBodies = new ArrayList<Body>();
        BodyDef staticBody = new BodyDef();
		staticBody.type = BodyType.StaticBody;

        platform = world.createBody(staticBody);
        EdgeShape edge1 = new EdgeShape();
        edge1.set(new Vector2(-camera.viewportWidth-2, -camera.viewportHeight/2+10), new Vector2(camera.viewportWidth, -camera.viewportHeight/2+10));

        FixtureDef platformProperties = new FixtureDef();
		platformProperties.shape = edge1;
		platformProperties.density = 0.5f;
		platformProperties.friction = 0.4f;
		platformProperties.restitution = 0.6f;
        Fixture platformFixture = platform.createFixture(platformProperties);


        BodyDef dynamicBody = new BodyDef();
		dynamicBody.type = BodyType.DynamicBody;
		// dynamicBody.position.set(-camera.viewportWidth/2+30, -camera.viewportHeight/2+10);
        CircleShape circle1 = new CircleShape();
        circle1.setRadius(10f);
        FixtureDef birdProperties = new FixtureDef();
		birdProperties.shape = circle1;
		birdProperties.density = 0.5f;
		birdProperties.friction = 0.5f;
		birdProperties.restitution = 0.6f;



        for (Sprite bird : birdSprites) {
            Vector2 pos = worldPosition(bird.getX(), bird.getY());
            dynamicBody.position.set(pos.x,pos.y);
            birdBodies.add(world.createBody(dynamicBody));
            birdBodies.getLast().setUserData(bird);
            Fixture birdFixture = birdBodies.getLast().createFixture(birdProperties);
        }


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
        // level.getBuilding().translate(pigBaseSprite.getX(), pigBaseSprite.getY() + pigBaseSprite.getHeight()+10); // translating sprites stored in building object realtive to level positions

        // ArrayList<Rectangle> emptySpaces = level.getBuilding().getEmptySpaces();
        for(int i = 0; i< pigSprites.size(); i++){
            pigSprites.get(i).setSize(50, 50);
            Vector2 center = new Vector2();
            // emptySpaces.get(i).getCenter(center);
            pigSprites.get(i).setPosition(center.x-10, center.y-30);
            // pigSprites.get(i).getBoundingRectangle().fitInside(emptySpaces.get(i));
        }


        ///building///
        pauseButtonSprite.setSize(60,60);
        pauseButtonSprite.setPosition(0,10*(height-20)-pauseButtonSprite.getHeight());
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }



    public void handleInput(){

//        if (Gdx.input.justTouched()) {
//
//            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
//
//            if(pauseButtonSprite.getBoundingRectangle().contains(touch)){
//                game.setScreen(new PauseScreen(game,this));
//            }
//
//        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            dispose();
            game.setScreen(new WinScreen(game));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            dispose();
            game.setScreen(new LoseScreen(game));
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
        // for (Sprite birdSprite : birdSprites) {

        //     birdSprite.setCenter(birdSprite., delta);
        //     birdSprite.draw(spriteBatch);
        // }

        // for(int i = 0; i < birdSprites.size(); i++){
        //     birdSprites.get(i).setCenter(birdBodies.get(i).getWorldCenter().x, birdBodies.get(i).getWorldCenter().y);
        // }
        // for (Body birdBody : birdBodies) {
        //     birdBody.getUserData().
        // }
        for (Sprite blockSprite : blockSprites) {
            blockSprite.draw(spriteBatch);
        }

        for (Sprite pigSprite : pigSprites) {
            pigSprite.draw(spriteBatch);
        }
        // groundLayer.draw(spriteBatch);
        spriteBatch.end();

        // debugRenderer.re
        debugRenderer.render(world, camera.combined);
		world.step(1/100f, 6, 2);
        // world.step(1/60f, 6, 2);
        // debugRenderer.render(world, camera.combined);

    }

    // private float accumulator = 0;

    // private void doPhysicsStep(float deltaTime) {
    //     // fixed time step
    //     // max frame time to avoid spiral of death (on slow devices)
    //     float frameTime = Math.min(deltaTime, 0.25f);
    //     accumulator += frameTime;
    //     while (accumulator >= Constants.TIME_STEP) {
    //         WorldManager.world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
    //         accumulator -= Constants.TIME_STEP;
    //     }
    // }


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

        // level.getBuilding().translate(-pigBaseSprite.getX(),-(pigBaseSprite.getY() + pigBaseSprite.getHeight()+10));

    }



}
