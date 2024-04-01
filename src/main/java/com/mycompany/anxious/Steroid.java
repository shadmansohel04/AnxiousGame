package com.mycompany.anxious;

public class Steroid implements Items{

    @Override
    public void function(Player user, Game game) {
        user.shoot(game.getBullet(), 2);
    }

    @Override
    public String getName() {
        return "Steroid";
    }
    
}
