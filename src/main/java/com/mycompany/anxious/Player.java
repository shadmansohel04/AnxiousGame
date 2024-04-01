package com.mycompany.anxious;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Alert;

public class Player {
    
    private int lives;
    private ArrayList<Items> items;
    private Player opponent;
    private int sanity;
    private String name;
    private static int num = 1;
    private boolean turn;
    
    public Player(){
        lives = 5;
        items = new ArrayList<Items>();
        sanity = 5;
        name = "P"+ num;
        if(num == 1){
            num++;
        }
        else{
            num = 1;
        }
        turn = true;
    }
    
    public boolean removeLives(int amount){
        lives -= amount;
        if(lives >= 0){
            return true;
        }
        lives = 0;
        return false;        
    }
    
    public void addLives(int amount){
        lives += amount;
    }
    
    public boolean getAlive(){
        if(lives <= 0 || sanity <= 0){
            return false;
        }
        return true;
    }
    
    public String addItem(){
        Random random = new Random();
        if(sanity <= 0){
            return "Poison";
        }
        String pill = "Medicine";
        int poison = 1;
        int medicine = 9;
        int steroid = 4;
        
        int prob = random.nextInt(10);
        
        switch(sanity){
            case 4:
                poison = 2;
                steroid = 5;
                medicine = 9;
                break;
            case 3:
                poison = 3;
                steroid = 6;
                medicine = 9;
                break;
            case 2:
                poison = 3;
                steroid = 7;
                medicine = 9;
                break;
            case 1:
                poison = 3;
                steroid = 9;
                medicine = 9;
                break;
        }
        
        if(prob < poison){
            items.add(new Poison());
            pill = "Poison";
        }
        else if(prob < steroid){
            items.add(new Steroid());
            pill = "Steroid";
        }
        else if(prob <= medicine){
            items.add(new Medicine());
            pill = "Medicine";
        }
        
        this.addSanity(-1);
        System.out.println(""+prob);
        System.out.println(pill);
        return pill;
    }
    
    
    public void shoot(boolean blank, int power){
        if(blank){
            opponent.removeLives(power);
            showAlert("BOOM");
        }
        else{
            showAlert("HAHA IT WAS BLANK");
        }
        
        changeTurn();
    }    
    
    public void changeTurn(){
        turn = false;
        opponent.setTurn(true);
    }
    
    public void setTurn(boolean turn){
        this.turn = turn;
    }
    
    public boolean getTurn(){
        return turn;
    }
    
    
    public ArrayList<Items> getItems(){
        return items;
    }
    
    public int getLives(){
        return lives;
    }
    
    public int getSanity(){
        return sanity;
    }
    
    public void addSanity(int number){
        sanity += number;
    }
    
    public void setOpponent(Player person){
        opponent = person;
    }      
    
    public String getName(){
        return name;
    }
    
    public void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(message);
        alert.setTitle(this.name + " IS GOING");
        alert.showAndWait();
    }
    
    
}
