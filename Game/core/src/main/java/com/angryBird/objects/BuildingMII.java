package com.angryBird.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import com.badlogic.gdx.math.Vector2;

public class BuildingMII {

    private String buildingName;
    private ArrayList<ArrayList<Vector2>> blockPositions;
    private ArrayList<ArrayList<Integer>> blockStrengths;
    private ArrayList<Vector2> pigPositions;

    private  LinkedHashMap<Block,Vector2> blocks;


    public BuildingMII(String buildingName, ArrayList<ArrayList<Vector2>> blockPositions,ArrayList<ArrayList<Integer>> blockStrengths,ArrayList<Vector2> pigPositions){
        
        this.buildingName = buildingName;
        this.blockPositions = blockPositions;
        this.blockStrengths = blockStrengths;
        this.pigPositions = pigPositions;
        blocks = new LinkedHashMap<Block,Vector2>();

        createBlocks();
    }



    public void createBlocks(){

        int num = 0;
        System.out.println(blockPositions.size());
        for (int i = 0; i < blockPositions.size(); i++) {

            ArrayList<Vector2> tier = blockPositions.get(i); 
            System.out.println(tier.size());
            for (int j = 0; j < tier.size(); j++) {
                
                String img;
                if(blockStrengths.get(i).get(j) == 0){
                    img = "block.png";
                }
                else if(blockStrengths.get(i).get(j) == 1){
                    img = "block.png";
                }
                else if(blockStrengths.get(i).get(j) == 2){
                    img = "block.png";
                }
                else{
                    img = "block.png";
                }

                if(i%2 == 0){
                    blocks.put(new Block(img, 0.2f, 0.8f, 1, true), tier.get(j));
                }
                else{
                    blocks.put(new Block(img, 0.8f, 0.2f, 1, false), tier.get(j));
                }
                
            }
        }   
    }

    public LinkedHashMap<Block, Vector2> getBlocks() {
        return blocks;
    }

    public ArrayList<Vector2> getPigPositions() {
        return pigPositions;
    }

}

