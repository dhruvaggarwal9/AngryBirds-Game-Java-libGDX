


package com.angryBird.Screens;



import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import com.angryBird.Main;
import com.angryBird.objects.Bird;
import com.angryBird.objects.Block;
import com.angryBird.objects.BuildingMII;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
import com.angryBird.objects.Pig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.bullet.linearmath.btBlock;
import com.badlogic.gdx.physics.bullet.softbody.btSoftBody.fCollision;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;



public class LevelScreenM  implements Screen, InputProcessor {

    private Button pauseButton;
    private Sprite pauseButtonSprite;
    private Level level;
    private Main game;
    // private Sprite birdBaseSprite;
    // private Sprite pigBaseSprite;
    // private Sprite pauseButtonSprite;

    private boolean flag = false; // Tracks if the touch is on the bird
    private boolean launched = false; // Tracks if the bird has been launched
    private Vector2 initialTouchPosition = new Vector2(); // Initial touch position
    private Vector2 launch = new Vector2(); // Launch impulse
    private static final float lamda = 40f; // Convert pixels to Box2D units        1m is 40 pixels

    // private ArrayList<Sprite> pigSprites;
    private Sprite backgroundSprite;
    private Sprite groundLayer;
    // private ArrayList<Sprite> blockSprites;
    private SpriteBatch spriteBatch;
    private Vector2 touch;
    Body platform;

    // Body birdBaseBody;
    // Body pigBaseBody;

    //all bodies
    Body catapultBody;
    private Sprite catapultSprite;

    ArrayList<Body> birdBodies;
    ArrayList<Bird> myBirds;
    private ArrayList<Sprite> birdSprites;

    ArrayList<Body> pigBodies;
    ArrayList<Pig> myPigs;
    private ArrayList<Sprite> pigSprites;
    private ArrayList<Vector2> pigPositions;

    ArrayList<Body> blockBodies;
    ArrayList<Block> blocks;
    ArrayList<Sprite> blockSprites;
    ArrayList<Vector2> blockPositions;
    //world

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;


    Sprite loadedSprite;
    Body loadedBody;
    int loadedBirdNum = 0;


    Vector2 impulse;

    HandleCollision collisionHandler = new HandleCollision();



    public LevelScreenM(Main game,Level level){

        pauseButton = new Button("pause4.png");
        this.level = level;
        this.game = game;
        // pauseButtonSprite = pauseButton.getButtonSprite();
        catapultSprite = level.getCatapultSprite();


        touch = new Vector2();
        spriteBatch = new SpriteBatch();



        birdBodies = new ArrayList<Body>();
        myBirds = level.getBirdHeroes();
        birdSprites = new ArrayList<Sprite>();


        pigBodies = new ArrayList<Body>();
        // myPigs = level.getPigEnemies();
        myPigs = new ArrayList<Pig>();
        for (Pig pig : level.getPigEnemies()) {
            myPigs.add(pig);
        }
        // myPigs.addAll(level.getPigEnemies());    //creating a seperate copy
        pigSprites = new ArrayList<Sprite>();
        pigPositions = level.getBuilding().getPigPositions();


        impulse = new Vector2();
        
        blocks =new ArrayList<Block>();
        for (Block block : level.getBuilding().getBlocks()){
                blocks.add(block);
        }
        blockBodies = new ArrayList<Body>();
        blockSprites = new ArrayList<Sprite>();
        blockPositions = level.getBuilding().getBlockPositions();
        pauseButton = new Button("pause4.png");
        

        //createWorld and pov
        world = new World(new Vector2(0, -9.8f), true);
        camera = new OrthographicCamera(48, 27);
        debugRenderer = new Box2DDebugRenderer();

        createAndResizeSprite();
        createBoxes();

        
        // world.setContactListener(collisionHandler);

        System.out.println(myPigs.size());
        System.out.println(pigBodies.size());
        System.out.println("sprite:" + pigSprites.size());

    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        System.out.println("Here1");

        loadedSprite = birdSprites.get(loadedBirdNum);

        int touchx = Gdx.input.getX();
        int touchy = Gdx.graphics.getHeight() - Gdx.input.getY();
        flag = loadedSprite.getBoundingRectangle().contains(touchx, touchy);

//
        if (flag && !launched) {
            System.out.println("Here4");
            initialTouchPosition.set(touchx / lamda, touchy / lamda);
        }
        return true;
    }



    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("Touch Dragged");
        if (flag && !launched) {
            int touchx = Gdx.input.getX();
            int touchy = Gdx.graphics.getHeight() - Gdx.input.getY();
            touch.set(touchx, touchy);
            
            System.out.println(touch);

        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // world.setContactListener(collisionHandler);
        System.out.println("Here3");
        if (flag && !launched) {
            System.out.println("Here6");

            loadedBody.applyLinearImpulse(launch, loadedBody.getWorldCenter(), true);
            System.out.println("This is it" + loadedBody.getLinearVelocity());
            System.out.println("Launch Vector: " + launch);
            System.out.println("Loaded Body Position: " + loadedBody.getWorldCenter());




            launched = true;
            flag = false;
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }


    public void createAndResizeSprite(){

        float unitWidth  = Gdx.graphics.getWidth()/192;             // 1 unitWidth = 10 pixels
        float unitHeight  = Gdx.graphics.getHeight()/108;           // 1 unitHeight = 10 pixels   
        //bird sprites
        for (Bird bird : myBirds) {
            birdSprites.add(bird.getSprite());
        }

        for (Sprite birdSprite : birdSprites) {
            birdSprite.setSize(4.5f*unitWidth, 4.5f*unitHeight);
        }


        //pig sprites
        for (Pig pig : myPigs) {
            pigSprites.add(pig.getSprite());
        }

        for (Sprite pigSprite : pigSprites) {
            pigSprite.setSize(40,40);
        }

        //blocks
        for (Block block : blocks) {
            blockSprites.add(block.getBlockSprite());
            blockSprites.getLast().setSize(48,48);
        }

        //background Sprite
        backgroundSprite = level.getBackSprite();
        backgroundSprite.setBounds(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //groundlayer Sprite
        groundLayer = new Sprite(new Texture("groundlayer2.png"));
        groundLayer.setBounds(0, 0, 1920, 4*lamda);

        //catapult sprite
        float catapultx = -camera.viewportWidth/2 +5f;
        float catapulty = -camera.viewportHeight/2 +4f;
        catapultSprite.setPosition(screenPosition(catapultx,catapulty).x - 27, screenPosition(catapultx,catapulty).y);

        //pausebutton sprite
        pauseButtonSprite = pauseButton.getButtonSprite();
        pauseButtonSprite.setSize(60,60);
        pauseButtonSprite.setPosition(0,10*(  Gdx.graphics.getHeight()/10) -pauseButtonSprite.getHeight());
    }

    public void createBoxes(){


        // Initialize block bodies ArrayList


        //bodyTypes
        BodyDef staticBody = new BodyDef();
        staticBody.type = BodyType.StaticBody;


        BodyDef dynamicBody = new BodyDef();
        dynamicBody.type = BodyType.DynamicBody;

        //shapes
        PolygonShape rectangle1 = new PolygonShape();                  // used for platform
        rectangle1.setAsBox(camera.viewportWidth/2, 2, new Vector2(0,-camera.viewportHeight/2 +2), 0);

        PolygonShape rectangle2 = new PolygonShape();                   //used for catapult
        rectangle2.setAsBox(0.8f,1.5f,new Vector2(5f -camera.viewportWidth/2, -camera.viewportHeight/2+4f +1.5f), 0);

        CircleShape circle = new CircleShape();             //used in birds objects and pig objects
        circle.setRadius(0.75f);

        EdgeShape edge1 = new EdgeShape();
        edge1.set(new Vector2(-camera.viewportWidth/2,camera.viewportHeight/2), new Vector2(-camera.viewportWidth/2,-camera.viewportHeight/2));

        EdgeShape edge2 = new EdgeShape();
        edge2.set(new Vector2(camera.viewportWidth/2,camera.viewportHeight/2), new Vector2(camera.viewportWidth/2,-camera.viewportHeight/2));


        //side walls created
        Body leftW = world.createBody(staticBody);
        leftW.createFixture(edge1,0);
        leftW.setUserData("leftWall");

        Body rightW = world.createBody(staticBody);
        rightW.createFixture(edge2,0);
        rightW.setUserData("rightWall");

        //platform created
        platform = world.createBody(staticBody);
        platform.createFixture(rectangle1, 0);
        platform.setUserData("GROUND");


        //catapult created
        catapultBody = world.createBody(staticBody);
        catapultBody.createFixture(rectangle2,0);
        catapultBody.setUserData(catapultSprite);



        //Bird Boxes
        int num = 1;
        for (Bird bird : myBirds) {
            Body temp = world.createBody(dynamicBody);
            FixtureDef birdProperty = new FixtureDef();
            dynamicBody.type = BodyType.DynamicBody;
            circle.setRadius(0.75f);
            birdProperty.density = 10f;
            birdProperty.restitution = 0.3f; // A moderate restitution
            birdProperty.shape = circle;

            if(bird.equals(myBirds.getFirst())){

                circle.setPosition(new Vector2(-camera.viewportWidth/2 + 5f, -camera.viewportHeight/2+7));
                birdProperty.shape = circle;
                temp.createFixture(birdProperty);
                birdBodies.add(temp);
                birdBodies.getLast().setUserData(myBirds.get(num-1));
            }
            else{
                circle.setPosition(new Vector2(-camera.viewportWidth/2 + 5f-num*circle.getRadius() - circle.getRadius(), -camera.viewportHeight/2+4f));
                birdProperty.shape = circle;
                temp.createFixture(birdProperty);
                birdBodies.add(temp);
                birdBodies.getLast().setUserData(myBirds.get(num-1));
            }
            num++;

        }

        //loading first bird on catapult
        loadedBody = birdBodies.get(loadedBirdNum);
        loadedSprite = birdSprites.get(loadedBirdNum);




        PolygonShape sqaure = new PolygonShape();
        //Pig bodies
        num = 0;
        for (Pig pig : myPigs) {

            

            Body temp = world.createBody(dynamicBody);
            FixtureDef pigProperty = new FixtureDef();
            pigProperty.density = 10f;
            pigProperty.restitution = 0.3f;
            pigProperty.friction = 3f;
            sqaure.setAsBox(0.3f, 0.5f, new Vector2(pigPositions.get(num).x+camera.viewportWidth/2 - 10f,pigPositions.get(num).y -camera.viewportHeight/2 + 4f), 0);

            pigProperty.shape = sqaure;
            temp.createFixture(pigProperty);
            pigBodies.add(temp);
            pigBodies.getLast().setUserData(myPigs.get(num));
            // System.out.println(pigBodies.getLast().getWorldCenter());
            num++;
            System.out.println(num + ":" + pigBodies.getLast().getWorldCenter());
        }

        //Block Bodies
        
        num = 0;
        for (Block block : blocks) {

            Body temp = world.createBody(dynamicBody);
            FixtureDef blockProperty = new FixtureDef();
            blockProperty.density = 10f;
            blockProperty.restitution = 0.3f;
            blockProperty.friction = 3f;
            sqaure.setAsBox(0.5f, 0.5f,new Vector2(blockPositions.get(num).x+camera.viewportWidth/2 - 10f,blockPositions.get(num).y -camera.viewportHeight/2 + 4f), 0);

            blockProperty.shape = sqaure;
            temp.createFixture(blockProperty);
            blockBodies.add(temp);
            blockBodies.getLast().setUserData(blocks.get(num));

            num++;
        }

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




    public void renderBoxes(){



        spriteBatch.begin();

       
        backgroundSprite.draw(spriteBatch);
        // groundLayer.draw(spriteBatch);
         catapultSprite.draw(spriteBatch);
         pauseButtonSprite.draw(spriteBatch);
        //Loading next bird o catapult
        Vector2 loadPos = new Vector2(-camera.viewportWidth/2 + 5f, -camera.viewportHeight/2+7f);
        if((loadedBody.getWorldCenter().x > loadPos.add(0.8f, 0).x || loadedBody.getWorldCenter().x < loadPos.add(-0.8f, 0).x) && loadedBirdNum < birdBodies.size()){
            loadedBody = birdBodies.get(loadedBirdNum);
            loadedSprite = birdSprites.get(loadedBirdNum);

        }

        if (launched && loadedBirdNum < birdBodies.size() - 1) {
            // Reset launch flag
            launched = false;

            // Increment bird number
            loadedBirdNum++;

            // Load next bird
            loadedBody = birdBodies.get(loadedBirdNum);
            loadedSprite = birdSprites.get(loadedBirdNum);

            // Reposition the bird on the catapult
            loadedBody.setTransform(new Vector2((loadedBirdNum+2)*0.75f, 3.75f), 0);
            if(loadedBirdNum-1 == 0){
                world.setContactListener(collisionHandler);
            }
        }


        //rendering Bird Sprites
        for(int i = 0; i < birdBodies.size(); i++){
            Body birdBody = birdBodies.get(i);
            birdSprites.get(i).setCenter(screenPosition(birdBody.getWorldCenter()).x,screenPosition(birdBody.getWorldCenter()).y);
            birdSprites.get(i).draw(spriteBatch);
        }

        //rendering Pig Sprites
        for(int i = 0; i < pigBodies.size(); i++){
            Body pigBody = pigBodies.get(i);
            pigSprites.get(i).setCenter(screenPosition(pigBody.getWorldCenter()).x,screenPosition(pigBody.getWorldCenter()).y);
            pigSprites.get(i).draw(spriteBatch);
        }


        //rednering Block Sprites
        for(int i = 0; i < blockBodies.size(); i++){
            Body blockBody = blockBodies.get(i);
            blockSprites.get(i).setCenter(screenPosition(blockBody.getWorldCenter()).x,screenPosition(blockBody.getWorldCenter()).y);
            blockSprites.get(i).draw(spriteBatch);
        }



        spriteBatch.end();


        //Destroying Pigs
        int num_pigs= pigBodies.size()-1;
        for(int i = num_pigs; i >=0 ; i--){

            // Block b = (Block) blockBodies.get(i).getUserData();

            if(myPigs.get(i).getHealth() <= 0){                         
                // blockBodies.get(i).destroyFixture(blockFixtures.get(i));
                world.destroyBody(pigBodies.get(i));
                myPigs.remove(i);
                pigBodies.remove(i);
                pigSprites.remove(i);
                if(myPigs.isEmpty()){
                    game.setScreen(new WinScreen(game));
                }
            }
        }

        //Destroying Blocks
        int num_blocks= blockBodies.size()-1;
        for(int i = num_blocks; i >=0 ; i--){

            // Block b = (Block) blockBodies.get(i).getUserData();

            if(blocks.get(i).getHealth() <= 0){
                // blockBodies.get(i).destroyFixture(blockFixtures.get(i));
                world.destroyBody(blockBodies.get(i));
                blocks.remove(i);
                blockBodies.remove(i);
                blockSprites.remove(i);
            }
        }

    }


    public void drawLaunchTrajectory() {



        if (flag && !launched) {
            System.out.println("Trajectory Debug Start ---");


            int touchx = Gdx.input.getX();
            int touchy = Gdx.graphics.getHeight() - Gdx.input.getY();
            touchx /= lamda;
            touchy /= lamda;
                    
            float impulseX = initialTouchPosition.x - touchx;
            float impulseY = initialTouchPosition.y - touchy;
            float dragDistance = initialTouchPosition.dst(touchx, touchy);
                    
            launch.set(impulseX, impulseY).scl(dragDistance * 12f);

            Vector2 velocity = new Vector2(launch.x/loadedBody.getMass(),launch.y/loadedBody.getMass());
            System.out.println("What we calc:" + velocity);



            ShapeRenderer shapeRenderer = new ShapeRenderer();


            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);



                float offX = screenPosition(loadedBody.getWorldCenter()).x;
                float offY = screenPosition(loadedBody.getWorldCenter()).y;


                    float X = 0;
                    float Y = 0;
                    // Vector2 launchV = new Vector2(impulseX,impulseY);
                    // launchV = launchV.scl(launchV.len()*12f);
                    float A = MathUtils.tan(velocity.angleRad());
                    float B = (world.getGravity().y)/(2*lamda*velocity.len2()*(MathUtils.cos(velocity.angleRad()))*(MathUtils.cos(velocity.angleRad())));


                    while(X<10000){
                        shapeRenderer.circle(offX+X, offY+Y, 4);
                        X = X+10;
                        Y = X*A + B*X*X;
                    }


                
                
                

            shapeRenderer.end();


       }
    }


    public void handleInput(){

        if (Gdx.input.justTouched()) {

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

            if(pauseButtonSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(new PauseScreen(game,this));
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            dispose();
            game.setScreen(new WinScreen(game));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            dispose();
            game.setScreen(new LoseScreen(game));
        }

        // Draw trajectory last
        if (Gdx.input.isTouched()) {

            drawLaunchTrajectory();
        }

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderBoxes();

        // Draw debug renderer
        // debugRenderer.render(world, camera.combined);

        // Simulate physics
        world.step(1/60f, 5, 2);

        handleInput();



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
    public void show() {
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void pause() {
        // invoked when your application is paused.
    }
    @Override
    public void resume() {
        // invoked when your application is resumed after pause.
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
    public Level getLevel(){
        return this.level;
    }

    @Override
    public boolean keyDown(int keycode) { return false; }
    @Override
    public boolean keyUp(int keycode) { return false; }
    @Override
    public boolean keyTyped(char character) { return false; }
    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override
    public boolean scrolled(float amountX, float amountY) { return false; }


}