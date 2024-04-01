package com.mycompany.anxious;

public class Poison implements Items{

    @Override
    public void function(Player user, Game game) {
        user.removeLives(2);
        user.showAlert("POISONED");
    }

    @Override
    public String getName() {
        return("Poison");
    }
    
}
