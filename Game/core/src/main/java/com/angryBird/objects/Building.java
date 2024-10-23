package com.angryBird.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Building {
    
    String name;
    ArrayList<Vector2> Positions;
    ArrayList<Rectangle> recPositions;
    ArrayList<Sprite> blockSprites;
    Texture blockImg;

    public Building(String n,Block block,ArrayList<Vector2> positions){

        name = n;
        Positions = positions;
        recPositions = new ArrayList<Rectangle>();
        blockSprites = new ArrayList<Sprite>();
        blockImg = block.getImg();

        createStructure();
    }


    public void createStructure(){

        for (Vector2 vector : Positions) {
            recPositions.add(new Rectangle(vector.x, vector.y, 70,70 ));
        }
        for (Rectangle rectangle : recPositions) {
           
            if(rectangle.getY()== 0){
                blockSprites.add(new Sprite(blockImg));
                blockSprites.getLast().setSize(blockImg.getWidth(),rectangle.getWidth()+20);
                blockSprites.getLast().rotate(90);
                blockSprites.getLast().setPosition(rectangle.getX()-5, rectangle.getY()-rectangle.getHeight()-blockImg.getWidth()); 
            }
            blockSprites.add(new Sprite(blockImg));
            blockSprites.getLast().setSize(blockImg.getWidth(), rectangle.getHeight());
            blockSprites.getLast().setPosition(rectangle.getX(), rectangle.getY());

            blockSprites.add(new Sprite(blockImg));
            blockSprites.getLast().setSize(blockImg.getWidth(), rectangle.getHeight());
            blockSprites.getLast().setPosition(rectangle.getX()+ rectangle.getWidth(), rectangle.getY());
            
            blockSprites.add(new Sprite(blockImg));
            
            blockSprites.getLast().setSize(blockImg.getWidth(),rectangle.getWidth()+10);
            blockSprites.getLast().rotate(90);
            blockSprites.getLast().setPosition(rectangle.getX()-15, rectangle.getY()-blockImg.getWidth());    
        }
    }

    
    // public void createStructure2(){

    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     blockSprites.add(new Sprite(blockImg));
    //     // blockSprites.add(new Sprite(blockImg));
    //     // blockSprites.add(new Sprite(blockImg));
    //     // blockSprites.add(new Sprite(blockImg));
    //     // blockSprites.add(new Sprite(blockImg));
    //     // blockSprites.add(new Sprite(blockImg));
    //     // blockSprites.add(new Sprite(blockImg));

    //     blockSprites.get(0).setBounds(0, 0, blockImg.getWidth(), 60);
    //     blockSprites.get(1).setBounds(70, 0, blockImg.getWidth(), 60);
    //     blockSprites.get(2).setBounds(140, 0, blockImg.getWidth(), 60);
    //     blockSprites.get(3).setBounds(35, 60, blockImg.getWidth(), 60);
    //     blockSprites.get(4).setBounds(105, 60, blockImg.getWidth(), 60);

    //     blockSprites.get(5).setSize(blockImg.getWidth(), 70);
    //     blockSprites.get(5).rotate(90);
    //     blockSprites.get(5).setPosition(0, 60);


    //     blockSprites.get(6).setSize(blockImg.getWidth(), 70);
    //     blockSprites.get(6).rotate(90);
    //     blockSprites.get(6).setPosition(70, 60);

      
    //     blockSprites.get(7).setSize(blockImg.getWidth(), 70);
    //     blockSprites.get(7).rotate(90);
    //     blockSprites.get(7).setPosition(35, 120);
       

    // }


    public void translate(float offsetX,float offsetY){   // we can't translate in render coz every time we render it will 
                                                     //be translated by that amount in each frame thus we will call this function once only.
        for (Sprite blockSprite : blockSprites) {
            blockSprite.translate(offsetX, offsetY);
        }

        for (Rectangle rectangle : recPositions) {
            rectangle.setPosition(rectangle.getX() + offsetX,rectangle.getY() + offsetY);
        }
    }

    public ArrayList<Sprite> getBlockSprites(){
        return blockSprites;
    }
    public ArrayList<Rectangle> getEmptySpaces(){
        return recPositions;
    }
    
    
    // public void render(SpriteBatch sb,float offsetX,float offsetY){             //components of object building are set relative (0,0) (left-bottom)
        
    //     for (Sprite blockSprite : blockSprites) {

    //         blockSprite.translate(offsetX, offsetY);
    //         blockSprite.draw(sb);
    //     }
    // }

}
