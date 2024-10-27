package com.angryBird.objects;

public class Profile {
    
    private String userName;
    private String userID;
    private int totalStarsachieved;
    private int currentLevel;
    
    
    public Profile(String userName,String userID,int totalStars,int currLevel){
        this.userName = userName;
        this.userID = userID;
        this.totalStarsachieved = totalStars;
        this.currentLevel = currLevel;
    }
}
