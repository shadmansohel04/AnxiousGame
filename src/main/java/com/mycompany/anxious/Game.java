package com.mycompany.anxious;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Alert;

public class Game {
    private int shots;
    private Player p1;
    private Player p2;
    private ArrayList<String> bullets;
    private static int round;
    
    public Game(){
        bullets = new ArrayList<String>();
        p1 = new Player();
        p2 = new Player();
        shots = 6;
        round = 1;
        p1.setOpponent(p2);
        p2.setOpponent(p1);  
        newRound();
    }
    
    public String getItem(Player person){
        String item = person.addItem();
        if(item.equalsIgnoreCase("Poison")){
            useItem(item ,person);
        }
        return item;
    }
    
    public String getTurn(){
        if(p1.getTurn()){
            return p1.getName();
        }
        return p2.getName();
       
    }
    
    public boolean useItem(String item, Player person){
        for(Items each: person.getItems()){
            if(item.equalsIgnoreCase(each.getName())){
                each.function(person, this);
                person.getItems().remove(each);
                return true;
            }
        }
        System.out.println("used item NO work");
        return false;
    }
    
    public boolean getBullet(){
        Random random = new Random();
        
        int shot = random.nextInt(bullets.size());        
        if(bullets.get(shot).equalsIgnoreCase("Real")){
            bullets.remove(shot);
            if(bullets.isEmpty()){
                newRound();
            }
            return true;
        }
        bullets.remove(shot);
        if(bullets.isEmpty()){
            newRound();
        }
        return false;
    }
    
    public void newRound(){
        for(int i = 0; i < 3; i++){
            bullets.add("Blank");
        }
        for(int i = 0; i < 3; i++){
            bullets.add("Real");
        }
        round ++;
    }
    
    
    public Player getP1(){
        return p1;
    }
    
    public Player getP2(){
        return p2;
    }
    
    public String getBullets(){
        return bullets.toString();
    }
    
    
}
