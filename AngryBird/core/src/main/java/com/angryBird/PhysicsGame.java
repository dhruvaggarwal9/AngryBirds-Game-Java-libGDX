package com.angryBird;
import java.nio.BufferUnderflowException;
import java.nio.channels.FileChannel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PhysicsGame extends ApplicationAdapter {
	World world;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	Body player;
	Body player2;
	Sprite test;
	SpriteBatch spriteBatch;

	boolean isReleased;
	Vector2 impulse;
	static final float lamda = 10f;  //(1 meter = 10 pixels)  
	@Override
	public void create () {
		world = new World(new Vector2(0, -10), true);
		camera = new OrthographicCamera(192, 108);				//whole screen is (192 meters, 108 meters)
		debugRenderer = new Box2DDebugRenderer();
		spriteBatch = new SpriteBatch();
		isReleased = false;
		impulse = new Vector2();
// 		BodyDef bodyDef = new BodyDef();
// 		bodyDef.type = BodyType.DynamicBody;
// 		bodyDef.position.set(5, 10);

// 		Body body = world.createBody(bodyDef);

// 		bodyDef.type = BodyType.DynamicBody;
// 		bodyDef.position.set(15, 10);
// 		Body body2 = world.createBody(bodyDef);

// 		BodyDef bodyDef2 = new BodyDef();
// 		bodyDef2.type = BodyType.KinematicBody;
// 		bodyDef2.position.set(0, 0);

// 		Body bodyKin = world.createBody(bodyDef2);
// 		// bodyKin.
// 		CircleShape circle = new CircleShape();
// 		circle.setRadius(6f);

// 		FixtureDef fixtureDef = new FixtureDef();
// 		fixtureDef.shape = circle;
// 		fixtureDef.density = 0.5f;
// 		fixtureDef.friction = 0.4f;
// 		fixtureDef.restitution = 0.6f;

// 		// Fixture fixture = body.createFixture(fixtureDef);
// 		circle.setRadius(3f);
// 		fixtureDef.shape = circle;
// 		fixtureDef.density = 0.5f;
// 		fixtureDef.friction = 0.1f;
// 		fixtureDef.restitution = 0.1f;
// 		Fixture fixture2 = body2.createFixture(fixtureDef);
// 		bodyKin.setLinearVelocity(0.1f, 0);
// 		// Fixture fixture3 = bodyKin.createFixture(fixtureDef);
		
// 		circle.dispose();
// 		body2.applyForce(new Vector2(-5000f, 12000f), body2.getPosition(), true);	
// 		// body2.applyLinearImpulse(-200.0f, 0, body2.getPosition().x, body2.getPosition().y, true);
// 		// body.applyForceToCenter(12000f, 12001f, true);
// // 		BodyDef groundBodyDef = new BodyDef();  
// // // Set its world position
// // 		// groundBodyDef.position.set(0, 10);  

// // // Create a body from the definition and add it to the world
// // 		Body groundBody = world.createBody(groundBodyDef);  

// // // Create a polygon shape
// // 		PolygonShape groundBox = new PolygonShape();  
// // // Set the polygon shape as a box which is twice the size of our view port and 20 high
// // // (setAsBox takes half-width and half-height as arguments)
// // 		// groundBox.setAsBox(0, 0, new Vector2(5f,5f), 10);

// // 		// groundBox.setAsBox(camera.viewportWidth/10, camera.viewportHeight/10, new Vector2(0f, 0f), 10);;
// // // Create a fixture from our polygon shape and add it to our ground body  
// // 		groundBox.setAsBox(camera.viewportWidth/2, 14.0f);
// // 		groundBody.createFixture(groundBox, 0.0f);
// // 	// Clean up after ourselves
// // 		groundBox.dispose();
// 		// ground
// 		createEdge(BodyDef.BodyType.StaticBody, -20, -10f, 20, -10f, 0);
// 		// left wall
// 		createEdge(BodyDef.BodyType.StaticBody, -20, -10, -20, 10, 0);
// 		// right wall
// 		createEdge(BodyDef.BodyType.StaticBody, 20, -10, 20, 10, 0);

// 		// createCircle(BodyDef.BodyType.DynamicBody, 0, 0, 1, 3);

// 		// Gdx.input.setInputProcessor(new InputAdapter() {

// 		// 	@Override
// 		// 	public boolean touchDown (int x, int y, int pointer, int button) {

// 		// 		Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
// 		// 		camera.unproject(touchedPoint);

// 		// 		if(MathUtils.randomBoolean()) {
// 		// 			createBox(BodyDef.BodyType.DynamicBody, touchedPoint.x, touchedPoint.y, 1, 1, 1);
// 		// 		}
// 		// 		else{
// 		// 			createCircle(BodyDef.BodyType.DynamicBody, touchedPoint.x, touchedPoint.y, 1, 3);
// 		// 		}

// 		// 		return true;
// 		// 	}
// 		// });

		BodyDef staticBody = new BodyDef();
		staticBody.type = BodyType.StaticBody;
		// staticBody.position.set(0, 2.0f);
		Body platform = world.createBody(staticBody);

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
		dynamicBody.position.set(-camera.viewportWidth/2+30, -camera.viewportHeight/2+10);
		player = world.createBody(dynamicBody);
		test = new Sprite(new Texture("Red.png"));
		
		player.setUserData(test);
		
		// player2 = world.createBody(dynamicBody);
		// test = new Sprite(new Texture("Red.png"));
		// player.setUserData(test);


		CircleShape circle1 = new CircleShape();
		// circle1.setPosition(new Vector2(-20f, -10f));
		circle1.setRadius(4f);
		
		// CircleShape circle2 = new CircleShape();
		// circle2.setPosition(new Vector2(0f, -10f));
		// circle2.setRadius(2f);

		FixtureDef playerProperties = new FixtureDef();
		playerProperties.shape = circle1;
		playerProperties.density = 0.5f;
		playerProperties.friction = 0.5f;
		playerProperties.restitution = 0.6f;
		

		// FixtureDef playerProperties2 = new FixtureDef();
		// playerProperties2.shape = circle2;
		// playerProperties2.density = 0.5f;
		// playerProperties2.friction = 0.5f;
		// playerProperties2.restitution = 0.6f;

		// Fixture playerProperties = player.createFixture(circle1, 0.5f);
		// playerProperties.setFriction(0.5f);
		// playerProperties.setRestitution(0.6f);

		Fixture playerFixture = player.createFixture(playerProperties); 
		
		// playerFixture.setUserData(test);
		
		// Fixture playerFixture2 = player2.createFixture(playerProperties2); 
		// playerFixture.setUserData(test);
		// playerProperties.getBody()
		
		// test.getBoundingRectangle().
		// playerProperties.setUserData(test);

		
		// Gdx.input.setInputProcessor(new InputAdapter() {

		// 	public boolean touchDragged (int x, int y, int pointer) {

		// 		// Vector3 touchedPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		// 		player.applyLinearImpulse(800, 800, player.getWorldCenter().x, player.getWorldCenter().y, true);
		// 		return true;
		// 	}
		// });


		
	}

	public Vector2 screenPosition(Vector2 pos){
		Vector2 offset = new Vector2(camera.viewportWidth/2, camera.viewportHeight/2);
		pos = pos.add(offset);
		return pos.scl(lamda);
	}
	public void handleInput(){

		Vector2 vel = player.getLinearVelocity();
		Vector2 pos = player.getWorldCenter();
		// player.
		Vector2 touch = new Vector2(Gdx.input.getX(),Gdx.input.getY());
		if(Gdx.input.isKeyPressed(Keys.LEFT) && vel.x > -50){
			player.applyLinearImpulse(-1f, 0, pos.x, pos.y, true);
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT) && vel.x < 50){
			player.applyLinearImpulse(1f, 0, pos.x, pos.y, true);
		}
		if(Gdx.input.isKeyPressed(Keys.UP) && vel.y < 50){
			player.applyLinearImpulse(0, 10f, pos.x, pos.y, true);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN) && vel.y > -50){
			player.applyLinearImpulse(0, -10f, pos.x, pos.y, true);
			// test.setPosition(camera.viewportWidth, camera.viewportHeight);
		}

		// if(Gdx.input.getInputProcessor().touchDragged((int)touch.x,(int) touch.y, 0)){
		// 		player.setLinearVelocity(new Vector2(1,1));
		// }

		// if(Gdx.input.justTouched()){
		// 	touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		// 	// player.applyLinearImpulse(0, 100f, pos.x, pos.y, true);
		// 	if(test.getBoundingRectangle().contains(touch)){
		// 		// player.setLinearVelocity(new Vector2(1,0));
		// 		// player.applyLinearImpulse(0, 800f, pos.x, pos.y, true);
		// 	}
		// }

		
		// if(Gdx.input.isTouched()){
		// 	touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		// 	// player.applyLinearImpulse(0, 100f, pos.x, pos.y, true);
		// 	if(test.getBoundingRectangle().contains(touch)){
		// 		// player.setLinearVelocity(new Vector2(1,0));
		// 		// player.applyLinearImpulse(0, 800f, pos.x, pos.y, true);
		// 		float del_X = (lamda*camera.viewportWidth/2+ lamda*player.getWorldCenter().x-touch.x);
		// 		float del_Y = (lamda*camera.viewportHeight/2 + lamda*player.getWorldCenter().y - touch.y);
		// 		if(del_X*del_X + del_Y*del_Y< (4*lamda)*(4*lamda)*4){
		// 			test.setCenter(touch.x + Gdx.input.getDeltaX(), touch.y + Gdx.input.getDeltaY());
		// 			impulse.x = del_X*del_X*del_X;
		// 			impulse.y = del_Y*del_Y*del_Y;
		// 		}
		// 		// else{
		// 		// 	player.applyForceToCenter(new Vector2(del_X, del_Y*10), true);
		// 		// }
				
		// 	}
		// 	// else{
		// 		// test = (Sprite) player.getUserData();
		// 		// test.setCenter(lamda*camera.viewportWidth/2+ lamda*player.getWorldCenter().x,lamda*camera.viewportHeight/2 + lamda*player.getWorldCenter().y);  //origin of world coordinates and the pixel coordinated are different 
		// 	// }
		// }
		// // else if((impulse.x !=0 || impulse.y !=0) ){
		// 	// test = (Sprite) player.getUserData();
		// 	// player.applyLinearImpulse(impulse, player.getWorldCenter(), true);
		// // }
		// // else{
		// 	// test.setCenter(lamda*camera.viewportWidth/2+ lamda*player.getWorldCenter().x,lamda*camera.viewportHeight/2 + lamda*player.getWorldCenter().y);  //origin of world coordinates and the pixel coordinated are different 
		// // }
		// else{
		// 	test.setCenter(lamda*camera.viewportWidth/2+ lamda*player.getWorldCenter().x,lamda*camera.viewportHeight/2 + lamda*player.getWorldCenter().y);  //origin of world coordinates and the pixel coordinated are different 
		// 	player.applyLinearImpulse(impulse, player.getWorldCenter(), true);
		// }
		
		// if(Gdx.input.getInputProcessor().touchDragged(Gdx.input.getX(), Gdx.input.getY(), 0)){
		// 	// touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
		// 	player.applyLinearImpulse(0, 800f, pos.x, pos.y, true);
		// 	// if(test.getBoundingRectangle().contains(touch)){
		// 	// 	// player.setLinearVelocity(new Vector2(1,0));
		// 	// 	player.applyLinearImpulse(0, 800f, pos.x, pos.y, true);
		// 	// }
		Vector2 bodyPos = new Vector2();
		if(Gdx.input.isTouched()){
			touch.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			if(test.getBoundingRectangle().contains(touch)){
				bodyPos = screenPosition(player.getWorldCenter());
				if(touch.sub(bodyPos).len2() < (8*lamda)*(8*lamda)){
					// test.setCenter(touch.x + Gdx.input.getDeltaX(), touch.y + Gdx.input.getDeltaY());
					impulse = touch.sub(bodyPos).scl(1);
				}
				else{
					
					impulse = touch.sub(bodyPos).scl((8*lamda)*(8*lamda)/bodyPos.sub(touch).len2());
					// test.setCenter(impulse.x, impulse.y);
				}
			}
			else{
				bodyPos = screenPosition(player.getWorldCenter());
				test.setCenter(bodyPos.x,bodyPos.y);
			}
		}
		else if(!impulse.isZero()){
			
			player.applyLinearImpulse(impulse.scl(-1,5), player.getWorldCenter(), true);
			impulse = new Vector2(0,0);
			isReleased = true;
		}
		// else if(isReleased == false){
		// 	bodyPos = screenPosition(player.getWorldCenter());
		// 	test.setCenter(bodyPos.x,bodyPos.y);
		// }
		bodyPos = screenPosition(player.getWorldCenter());
		test.setCenter(bodyPos.x,bodyPos.y);
		
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(4f, 4f, 4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		// test = (Sprite) player.getUserData();
		// test.setCenter(lamda*camera.viewportWidth/2+ lamda*player.getWorldCenter().x,lamda*camera.viewportHeight/2 + lamda*player.getWorldCenter().y);  //origin of world coordinates and the pixel coordinated are different 
		test.draw(spriteBatch);
		spriteBatch.end();
		debugRenderer.render(world, camera.combined);
		world.step(1/100f, 6, 2);  // each call advances the simulation by 1/60th seconds,(thus the velocity,acceleration etc changes accordingly)
		handleInput();													//time step/world step is like after every call you check update velocities,accel accordinlgy , you check all contacts and update them accordingly 
	}
	
	@Override
	public void dispose () {
		world.dispose();
		debugRenderer.dispose();
	}

	private Body createBox(BodyDef.BodyType type, float x, float y, float width, float height, float density) {
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(width, height);

		BodyDef def = new BodyDef();
		def.type = type;
		Body body = world.createBody(def);
		body.createFixture(poly, density);
		body.setTransform(x, y, 0);
		poly.dispose();

		return body;
	}

	private Body createEdge(BodyDef.BodyType type, float x1, float y1, float x2, float y2, float density) {
		EdgeShape poly = new EdgeShape();
		poly.set(new Vector2(0, 0), new Vector2(x2 - x1, y2 - y1));

		BodyDef def = new BodyDef();
		def.type = type;
		Body body = world.createBody(def);
		body.createFixture(poly, density);
		body.setTransform(x1, y1, 0);
		poly.dispose();

		return body;
	}

	private Body createCircle(BodyDef.BodyType type, float x, float y, float radius, float density) {
		CircleShape poly = new CircleShape();
		poly.setRadius(radius);

		BodyDef def = new BodyDef();
		def.type = type;
		Body body = world.createBody(def);
		body.createFixture(poly, density);
		body.setTransform(x, y, 0);
		poly.dispose();

		return body;
	}
}