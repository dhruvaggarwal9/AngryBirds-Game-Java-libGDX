package com.angryBird.Screens;



import com.angryBird.objects.Bird;
import com.angryBird.objects.Block;
import com.angryBird.objects.Pig;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class HandleCollision implements ContactListener{

    

    public HandleCollision(){
        
    }
    @Override
    public void beginContact(Contact contact) {
        // TODO Auto-generated method stub


        Fixture bodyFixture1 = contact.getFixtureA();
        Fixture bodyFixture2 = contact.getFixtureB();


        Body body1 = bodyFixture1.getBody();
        Body body2 = bodyFixture2.getBody();
        
        
        if(body1.getUserData().getClass() == Block.class && body2.getUserData().getClass() == Bird.class){
            //HERE REDUCE BY BIRDS POWER AMOUNT
            Block block = (Block) body1.getUserData();
            block.reduceHealth(((Bird) body2.getUserData()).getStrength());
        }
        else if(body1.getUserData().getClass() == Bird.class && body2.getUserData().getClass() == Block.class){

            Block block = (Block) body2.getUserData();
            block.reduceHealth(((Bird) body1.getUserData()).getStrength());
        }



        if(body1.getUserData().getClass() == Pig.class && body2.getUserData().getClass() == Bird.class){

            Pig pig = (Pig) body1.getUserData();
            pig.reduceHealth(((Bird) body2.getUserData()).getStrength());
        }
        else if(body1.getUserData().getClass() == Bird.class && body2.getUserData().getClass() == Pig.class){

            Pig pig = (Pig) body2.getUserData();
            pig.reduceHealth(((Bird) body1.getUserData()).getStrength());
        }



        if(body1.getUserData().getClass() == String.class && body2.getUserData().getClass() == Pig.class){

            String bodyName = (String) body1.getUserData();
            Pig pig = (Pig) body2.getUserData();

            if(bodyName.equals("GROUND")){
                pig.reduceHealth(1);
            }

            
        }
        else if(body1.getUserData().getClass() == Pig.class && body2.getUserData().getClass() == String.class){

            String bodyName = (String) body2.getUserData();
            Pig pig = (Pig) body1.getUserData();

            if(bodyName.equals("GROUND")){
                pig.reduceHealth(1);
            }
        }

        
        
        if(body1.getUserData().getClass() == Block.class && body2.getUserData().getClass() == Pig.class){
        
            Block block = (Block) body1.getUserData();
            Pig pig = (Pig) body2.getUserData();
        
            pig.reduceHealth(1);
            block.reduceHealth(1);
        
        }
        else if(body1.getUserData().getClass() == Pig.class && body2.getUserData().getClass() == Block.class){
        
            Block block = (Block) body2.getUserData();
            Pig pig = (Pig) body1.getUserData();
        
            pig.reduceHealth(1);
            block.reduceHealth(1);
        }
    }

    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub

        

        // if((body1.getType() == BodyDef.BodyType.StaticBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.StaticBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // }
        // // else if((body1.getType() == BodyDef.BodyType.DynamicBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.DynamicBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // // }
        // else{
        //         System.out.println("In end contact");
        // }
        
        
   
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub


        // Fixture bodyFixture1 = contact.getFixtureA();
        // Fixture bodyFixture2 = contact.getFixtureB();


        // Body body1 = bodyFixture1.getBody();
        // Body body2 = bodyFixture2.getBody();

        // if((body1.getType() == BodyDef.BodyType.StaticBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.StaticBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // }
        // else if((body1.getType() == BodyDef.BodyType.DynamicBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.DynamicBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // }
        // else{
        //     System.out.println("In presolve");
        // }
        
        // // System.out.println("In presolve");

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
           // TODO Auto-generated method stu
        // Fixture bodyFixture1 = contact.getFixtureA();
        // Fixture bodyFixture2 = contact.getFixtureB();

        
        // Body body1 = bodyFixture1.getBody();
        // Body body2 = bodyFixture2.getBody();

        // if((body1.getType() == BodyDef.BodyType.StaticBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.StaticBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // }
        // else if((body1.getType() == BodyDef.BodyType.DynamicBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.DynamicBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // }
        // else{
        //     System.out.println("In postsolve");
        // }
                
        
      
    }
    
}



        // if((body1.getType() == BodyDef.BodyType.StaticBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.StaticBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){
        //     System.out.println("In begin contact");
        //     System.out.println("First(Position):" + body1.getType() +"(" + body1.getWorldCenter().x + " ," +body1.getWorldCenter().y + ")");
        //     System.out.println("Second(Position):" + body2.getType() +"(" + body2.getWorldCenter().x + " ," +body2.getWorldCenter().y + ")");
        // }
        // // else if((body1.getType() == BodyDef.BodyType.DynamicBody && body2.getType() ==  BodyDef.BodyType.DynamicBody)  || (body2.getType() == BodyDef.BodyType.DynamicBody && body1.getType() ==  BodyDef.BodyType.DynamicBody)){

        // // }
        // else{
            
        //     if(body1.getUserData() == null && body2.getUserData() != null){
        //         Bird bird = (Bird)body2.getUserData();
        //         System.out.println("In begin contact");
        //         System.out.println("First:" + body1.getUserData());
        //         System.out.println("Second:" + bird.getName());
        //     }
        //     else if(body1.getUserData() != null && body2.getUserData() == null){
        //         Bird bird = (Bird)body1.getUserData();
        //         System.out.println("In begin contact");
        //         System.out.println("First:" + body2.getUserData());
        //         System.out.println("Second:" + bird.getName());
        //     }
        //     else if(body1.getUserData() != null && body2.getUserData() != null){

        //         if(body1.getUserData().getClass() == Bird.class){
        //                 System.out.println("Hello Boy");
        //                 // body1.destroyFixture(bodyFixture2);
                        
        //         }
        //         Bird bird = (Bird)body1.getUserData();
        //         Bird bird2 = (Bird)body2.getUserData();
        //         // System.out.println("K" + Bird.class);
        //         System.out.println("In begin contact");
        //         System.out.println("First:" + bird2.getName());
        //         System.out.println("Second:" + bird.getName());
        //     }
        //     else{
        //         // Bird bird = (Bird)body2.getUserData();
        //         System.out.println("In begin contact");
        //         System.out.println("First(Position):" + body1 +"(" + body1.getWorldCenter().x + " ," +body1.getWorldCenter().y + ")");
        //         System.out.println("Second(Position):" + body2 +"(" + body2.getWorldCenter().x + " ," +body2.getWorldCenter().y + ")");
        //     }
            
        // }
        