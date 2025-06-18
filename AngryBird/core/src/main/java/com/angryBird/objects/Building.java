package com.angryBird.objects;

import java.io.Serializable;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Building implements Serializable {
    private static final long serialVersionUID = 1L; // Serialization version control

    private String name;
    private ArrayList<Vector2> Positions;
    private ArrayList<Rectangle> recPositions;
    private transient ArrayList<Sprite> blockSprites; // Transient since Sprite is not serializable
    private transient Texture blockImg; // Transient for reloading the texture
    private String blockImgPath; // Store image path for reloading

    public Building(String n, Block block, ArrayList<Vector2> positions) {
        name = n;
        Positions = positions;
        recPositions = new ArrayList<>();
        blockSprites = new ArrayList<>();
        blockImg = block.getImg();
        blockImgPath = "path/to/your/image.png"; // Replace with actual path
        createStructure();
    }

    public void createStructure() {
        for (Vector2 vector : Positions) {
            recPositions.add(new Rectangle(vector.x, vector.y, 70, 70));
        }
        for (Rectangle rectangle : recPositions) {
            Sprite sprite = new Sprite(blockImg);
            sprite.setSize(70, 70);
            sprite.setPosition(rectangle.x, rectangle.y);
            blockSprites.add(sprite);
        }
    }

    public void translate(float offsetX, float offsetY) {
        for (Sprite blockSprite : blockSprites) {
            blockSprite.translate(offsetX, offsetY);
        }
        for (Rectangle rectangle : recPositions) {
            rectangle.setPosition(rectangle.x + offsetX, rectangle.y + offsetY);
        }
    }

    public ArrayList<Sprite> getBlockSprites() {
        return blockSprites;
    }

    public ArrayList<Rectangle> getEmptySpaces() {
        return recPositions;
    }

    public String getName(){
        return name;
    }

    // Reload transient fields during deserialization
    private void readObject(java.io.ObjectInputStream ois) throws Exception {
        ois.defaultReadObject(); // Deserialize non-transient fields
        blockImg = new Texture(blockImgPath); // Reload texture
        blockSprites = new ArrayList<>(); // Reinitialize sprites
        createStructure(); // Rebuild sprites
    }
}
