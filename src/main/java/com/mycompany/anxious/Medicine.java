package com.mycompany.anxious;

public class Medicine implements Items{

    @Override
    public void function(Player user, Game game) {
        user.addLives(1);
        user.showAlert("+1 Health");
        user.changeTurn();
    }

    @Override
    public String getName() {
        return("Medicine");
    }

    
}
