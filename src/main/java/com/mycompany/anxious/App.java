package com.mycompany.anxious;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    private Label yourLives = new Label("Player 1   Lives: 5   Your Sanity: 5");
    private Label turn = new Label("TURN: P1");
    private Label demonLives = new Label("Player 2   Lives: 5:   Your Sanity: 5");
    private Label status = new Label("Game Ongoing");
    private Game game = new Game();
    private Label bullets = new Label(game.getBullets());
    private ListView<String> p1Items = new ListView<String>();
    private ListView<String> p2Items = new ListView<String>();    
    private String current = game.getTurn();
    
    @Override
    public void start(Stage primaryStage) {
        
        showAlert("Welcome to ANXIETY, the PVP game where you try to SURVIVE\n\n"
                +"As the game begins, both players are given five lives to start\n"
                +"The chamber holds three blanks and three live rounds, setting\n"
                +"the stage for an intense showdown. Each turn, players decide\n"
                +"whether to shoot, get an item, or use one already collected.\n"
                +"Grabbing an item won't count as a turn BUT be careful, grabbing\n"
                +"can mess with your head make and more vulnerable to poison.\n\n"
                +"ITEMS:\n"
                +"Steroid: Doubles the damage inflicted by your shot IF LIVE\n"
                +"Medicine: Restore one life\n"
                +"Poison: Automatically takes 2 of YOUR lives");
        Button shoot = new Button("SHOOT");
        Button getItem = new Button("GET ITEM");
        Button useItem = new Button("USE ITEM");
        Button newGame = new Button("NEW GAME");
        
        
        newGame.setOnAction(value ->{
            NewGame();
        });
        
        useItem.setOnAction(value ->{
            String item;
            if(current.equalsIgnoreCase("P1")){
                item = p1Items.getSelectionModel().getSelectedItem();            
            }
            else{
                item = p2Items.getSelectionModel().getSelectedItem();            
            }
            
            if(item != null && !item.isEmpty()){
                if(current.equalsIgnoreCase("P1")){
                    game.useItem(item, game.getP1());                    
                    p1Items.getItems().remove(item);
                }
                else if(current.equalsIgnoreCase("P2")){
                    game.useItem(item, game.getP2());
                    p2Items.getItems().remove(item);
                }
            }
            updater();
        });
        
        getItem.setOnAction(value -> {
            if (current.equalsIgnoreCase("P1")) {
                updateItems(game.getItem(game.getP1()), current);
            } 
            else {
                updateItems(game.getItem(game.getP2()), current);
            }
            updater();
        });

        
        shoot.setOnAction(value ->{
            if(game.getTurn().equalsIgnoreCase("P1")){
                game.getP1().shoot(game.getBullet(), 1);
            }
            else{
                game.getP2().shoot(game.getBullet(), 1);            
            }
            updater();      
        });
        
        HBox players = new HBox(40);
        VBox player1 = new VBox(20);
        VBox player2 = new VBox(20);

        VBox buttons = new VBox(20);
        buttons.setAlignment(Pos.CENTER);
        
        buttons.getChildren().addAll(shoot, getItem, useItem, status);
        player1.getChildren().addAll(yourLives,p1Items);
        player1.setAlignment(Pos.CENTER);
        player2.getChildren().addAll(demonLives, p2Items);
        player2.setAlignment(Pos.CENTER);
        
        players.getChildren().addAll(player1, buttons, player2);
        players.setAlignment(Pos.CENTER);
        VBox body = new VBox(20);
                
        body.getChildren().addAll(turn, players, bullets, newGame);
        body.setPadding(new Insets(20));
        body.setAlignment(Pos.CENTER);  
      
        Scene scene = new Scene(body, 1100, 500); 
        
        // Inside the start method before creating the scene
        // Setting background color
        body.setStyle("-fx-background-color: #1f1f1f;");

        // Setting text color and font for labels
        yourLives.setTextFill(Color.RED);
        demonLives.setTextFill(Color.RED);
        turn.setTextFill(Color.RED);
        status.setTextFill(Color.WHITE); // Change to red if preferred
        bullets.setTextFill(Color.WHITE); // Change to red if preferred
        yourLives.setStyle("-fx-font-size: 18px; -fx-font-family: 'Monospaced';");
        demonLives.setStyle("-fx-font-size: 18px; -fx-font-family: 'Monospaced';");
        turn.setStyle("-fx-font-size: 18px; -fx-font-family: 'Monospaced';");
        status.setStyle("-fx-font-size: 18px; -fx-font-family: 'Monospaced';");
        bullets.setStyle("-fx-font-size: 18px; -fx-font-family: 'Monospaced';");

        shoot.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-size: 18px;");
        getItem.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-size: 18px;");
        useItem.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-size: 18px;");
        newGame.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-size: 18px;");

        // Styling the list views
        p1Items.setStyle("-fx-control-inner-background: #2f2f2f; -fx-text-fill: white;");
        p2Items.setStyle("-fx-control-inner-background: #2f2f2f; -fx-text-fill: white;");
        
        scene.getStylesheets().add("Styles.css");
        // Styling the scene
        scene.setFill(Color.BLACK);
        
        primaryStage.setTitle("AHAHA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updater(){
        yourLives.setText("Player 1   Lives: " + game.getP1().getLives() + "   Your Sanity: " + game.getP1().getSanity());
        demonLives.setText("Player 2   Lives: " + game.getP2().getLives() + "   Your Sanity: " + game.getP2().getSanity());
        bullets.setText(game.getBullets());   
        turn.setText("TURN: " + game.getTurn());
        current = game.getTurn();
        if(!game.getP1().getAlive()){
            status.setText("PLAYER 2 WINS");
        }
        if(!game.getP2().getAlive() && game.getP1().getAlive()){
            status.setText("PLAYER 1 WINS");
        }   
        
    }
    
    public void updateItems(String item, String name){
       if(!item.equalsIgnoreCase("Poison")){
            if(name.equalsIgnoreCase("P1")){
                p1Items.getItems().add(item);
            }
            else{
                p2Items.getItems().add(item);
            }
            
       }
    }
    
    public void NewGame(){
        yourLives.setText("Player 1   lives: 5   Your Sanity: 5");
        demonLives.setText("Player 2   lives: 5   Your Sanity: 5");
        turn.setText("TURN: P1");
        status.setText("Game Ongoing");
        game = new Game();
        bullets.setText(game.getBullets());
        p1Items.getItems().clear();
        p2Items.getItems().clear();
    }
    
    
    public void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.setTitle("HEHEHE");
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
