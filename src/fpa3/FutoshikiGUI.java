/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpa3;


import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author
 */
public class FutoshikiGUI extends Application {
    private Text problems = new Text(400,400,"");
    private int size = 5;
    private String hardness = "e";
    private Text puzzleHint = new Text("");
    private TextField name = new TextField();
    
    
    FutoshikiPuzzle puzzle;

    
    @Override
    public void start(Stage primaryStage) {
        puzzle = new FutoshikiPuzzle(size);
        puzzle.fillPuzzle(hardness);
        puzzle.solve();

                

        BorderPane borderPane = new BorderPane();
        
        HBox bottomPane = new HBox(20);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setStyle("-fx-border-color: black; -fx-background-color: #C8C8C8");
        bottomPane.setPadding(new Insets(10,10,10,10));

        
        HBox topPane = new HBox();
        topPane.setAlignment(Pos.CENTER);
        topPane.setStyle("-fx-border-color: black; -fx-background-color: #C8C8C8");
        topPane.setPadding(new Insets(10,10,10,10));
       
        VBox leftPane = new VBox();
        leftPane.setAlignment(Pos.TOP_LEFT);
        leftPane.setPadding(new Insets(10,10,10,10));
        leftPane.setPrefWidth(400);        

        
        VBox rightPane = new VBox();
        rightPane.setAlignment(Pos.TOP_RIGHT);
        rightPane.setPadding(new Insets(10,10,10,10));
        rightPane.setPrefWidth(400);        
        
        
        
        
        puzzleDisplay(size, borderPane, hardness);



        

        Text title = new Text("Futoshiki Puzzle");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 40;");
        topPane.getChildren().add(title);

        
        
        Text introduction = new Text("Introduction:");
        introduction.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        Text howToPlay = new Text("\nHow to play:");
        howToPlay.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        puzzleHint.setStyle("-fx-font-weight: bold; -fx-font-size: 15;");
        leftPane.getChildren().addAll(introduction, 
        new Label("The aim of futoshiki is to fill all the tiles with numbers that\n" +
                  "are legal. You can not have duplicates in either your rows or \n" +
                  "columns. You also can not have numbers that break the constraints\n" +
                  "between the tiles.\n"), howToPlay, 
        new Label("- Customise the game with the options at the bottom, e.g. size of the\n" +
                  "  puzzle and difficulty. The harder you make the difficulty, the more\n" +
                  "  constraints there will be on the puzzle.\n" +
                  "- To edit a tile, left click the tile to increase the iteration and\n" +
                  "  cycle round to 0 to 'clear'. Right click to change its state from\n" +
                  "  pencil (shown as grey) to true, vice versa\n" +
                  "- You will not be able to edit the uneditable tiles that are shown as\n" +
                  "  bold with white text\n" +
                  "- If you have any illegal moves in your game after making the tiles\n" +
                  "  state true, a message will appear on the right saying problems that\n" +
                  "  are in your puzzle\n"+ " \n"+ " \n"+ " \n"+ " \n"+ " \n"+ " \n"), puzzleHint);
        
        
                                                       
                                                                                
        
        Text legal = new Text("Current illegal parts in your game:");
        legal.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        rightPane.getChildren().add(legal);
        rightPane.getChildren().add(problems);
        problems.setTextAlignment(TextAlignment.RIGHT);


        ComboBox<String> difficultyHBox = new ComboBox<>();
        difficultyHBox.getItems().addAll(
            "Easy","Medium","Hard");
        difficultyHBox.getSelectionModel().select(0);
        difficultyHBox.setOnAction(e -> {
            String selected = difficultyHBox.getValue();
            if(selected == "Easy") {
                hardness = "e";
            }
            if(selected == "Medium") {
                hardness = "m";
            }
            if(selected == "Hard") {
                hardness = "h";
            }
            puzzleDisplay(size, borderPane, hardness);
            problems.setText(puzzle.getProblemsEasy());
        });
        
        
        
        ComboBox<String> SizeHBox = new ComboBox<>();
        SizeHBox.getItems().addAll(
            "2x2","3x3","4x4","5x5","6x6","7x7","8x8");
        SizeHBox.getSelectionModel().select(3);
        SizeHBox.setOnAction(e -> {
            String selected = SizeHBox.getValue();
            if(selected == "2x2") {
                size = 2;
            }
            if(selected == "3x3") {
                size = 3;
            }
            if(selected == "4x4") {
                size = 4;
            }
            if(selected == "5x5") {
                size = 5;
            }
            if(selected == "6x6") {
                size = 6;
            }
            if(selected == "7x7") {
                size = 7;
            }
            if(selected == "8x8") {
                size = 8;
            }
            puzzleDisplay(size, borderPane, hardness);
            problems.setText(puzzle.getProblemsEasy());
        });
        
        
        
        
        
        ComboBox<String> ColourHBox = new ComboBox<>();
        ColourHBox.getItems().addAll(
            "Grey","Yellow","Green", "Blue", "Red");
        ColourHBox.getSelectionModel().select(0);
        ColourHBox.setOnAction(e -> {
            String selected = ColourHBox.getValue();
            if(selected == "Grey") {
                bottomPane.setStyle("-fx-border-color: black; -fx-background-color: #C8C8C8");
                topPane.setStyle("-fx-border-color: black; -fx-background-color: #C8C8C8");
            }
            if(selected == "Yellow") {
                bottomPane.setStyle("-fx-border-color: black; -fx-background-color: #FFEF6E");
                topPane.setStyle("-fx-border-color: black; -fx-background-color: #FFEF6E");
            }
            if(selected == "Green") {
                bottomPane.setStyle("-fx-border-color: black; -fx-background-color: #59F091");
                topPane.setStyle("-fx-border-color: black; -fx-background-color: #59F091");
            }
            if(selected == "Blue") {
                bottomPane.setStyle("-fx-border-color: black; -fx-background-color: #2FB5FF");
                topPane.setStyle("-fx-border-color: black; -fx-background-color: #2FB5FF");
            }
            if(selected == "Red") {
                bottomPane.setStyle("-fx-border-color: black; -fx-background-color: #FF4747");
                topPane.setStyle("-fx-border-color: black; -fx-background-color: #FF4747");
            }
        });
        
        
        
        
        Button QuitButton = new Button("Quit");
        QuitButton.setOnAction(e -> {
            Stage subStage = new Stage();
            subStage.setTitle("Quit");
            subStage.setResizable(false);
               
            BorderPane quitPane = new BorderPane();
                      
            Text quit = new Text("Are you sure you want to quit the game?");

            Button Yes = new Button("Yes");
            Yes.setOnAction(p -> {
                primaryStage.close();
                subStage.close();
                Stage commiserationsStage = new Stage();
                commiserationsStage.setTitle("Commiserations");
                commiserationsStage.setResizable(false);
                BorderPane commiserationsPane = new BorderPane();

                Text commiserations = new Text("Commiserations " + name.getText() + " on not completing the game!");

                Button playAgain = new Button("Play again");           
                playAgain.setOnAction(q -> {
                    commiserationsStage.close();
                    size = 5;
                    hardness = "e";
                    problems.setText(puzzle.getProblemsEasy());
                    start(primaryStage);
                });

                Button close = new Button("Close");
                close.setOnAction(t -> commiserationsStage.close());


                HBox top = new HBox();
                top.setAlignment(Pos.CENTER);
                top.setPadding(new Insets(20,10,10,10));
                top.getChildren().add(commiserations);

                HBox bottom = new HBox();
                bottom.setAlignment(Pos.CENTER);
                bottom.setPadding(new Insets(10,10,20,10));
                bottom.getChildren().addAll(playAgain, close);
                bottom.setSpacing(15);
                
                commiserationsPane.setTop(top);
                commiserationsPane.setBottom(bottom);
                
                Scene scene = new Scene(commiserationsPane, 300, 100);
            
                commiserationsStage.setScene(scene);
                commiserationsStage.show();
            });
           
            Button No = new Button("No");
            No.setOnAction(p -> subStage.close());

            HBox topQuit = new HBox();
            topQuit.setAlignment(Pos.CENTER);
            topQuit.setPadding(new Insets(20,10,10,10));
            topQuit.getChildren().add(quit);

            HBox bottomQuit = new HBox();
            bottomQuit.setAlignment(Pos.CENTER);
            bottomQuit.setPadding(new Insets(10,10,20,10));
            bottomQuit.getChildren().addAll(Yes, No);
            bottomQuit.setSpacing(15);
            
            quitPane.setTop(topQuit);
            quitPane.setBottom(bottomQuit);
            
            Scene scene = new Scene(quitPane, 300, 100);
            
            subStage.setScene(scene);
            subStage.show();
        });

         

        Button hint = new Button("Hint");
        hint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean allowed = false;
                while (!allowed) {
                    Random ran = new Random();
                    int ran1 = ran.nextInt(puzzle.getGrid().length);                    
                    int ran2 = ran.nextInt(puzzle.getGrid().length);            
                    int set = puzzle.getSolvedSquare(ran1, ran2);
                    if (puzzle.getSquare(ran1, ran2).getEditable() == true) {
                        puzzleHint.setText("HINT - Put the value " + set + " in row " + (ran1+1) + ", column " + (ran2+1));
                        allowed = true;                                                         
                    }
                }
            }
        });
        
        
 
        Button New = new Button("New");
        New.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("New");
            newStage.setResizable(false);

            BorderPane newPane = new BorderPane();
            
            Text newText = new Text("Are you sure you want to generate a new puzzle " + name.getText() + "?");
            
            Button Yes = new Button("Yes");
            Yes.setOnAction(o -> {
                newStage.close();
                
                puzzleDisplay(size, borderPane, hardness);
                problems.setText(puzzle.getProblemsEasy());
            });

            Button No = new Button("No");
            No.setOnAction(o -> {
                newStage.close();
            });

            HBox topNew = new HBox();
            topNew.setAlignment(Pos.CENTER);
            topNew.setPadding(new Insets(20,10,10,10));
            topNew.getChildren().add(newText);

            HBox bottomNew = new HBox();
            bottomNew.setAlignment(Pos.CENTER);
            bottomNew.setPadding(new Insets(10,10,20,10));
            bottomNew.getChildren().addAll(Yes, No);
            bottomNew.setSpacing(15);

            newPane.setTop(topNew);
            newPane.setBottom(bottomNew);
            Scene scene = new Scene(newPane, 300, 100);
            newStage.setScene(scene);
            newStage.show();
            
        });
        

        
        Button check = new Button("Check");
        check.setOnAction(p -> {
            boolean done = true;
            int i = 0;
            while (i < puzzle.getGrid().length && done) {
                int j = 0;
                while (j < puzzle.getGrid().length && done) {
                    if (puzzle.getSquare(i,j).getSquareValue() == 0) {
                        done = false;
                        Stage checkStage = new Stage();
                        checkStage.setTitle("Check");
                        checkStage.setResizable(false);

                        BorderPane checkPane = new BorderPane();
                        
                        Text checkText = new Text("You still haven't completed the puzzle " + name.getText() + "!");
                        Button close = new Button("Close");
                        close.setOnAction(o -> {
                            checkStage.close();
                        });
                        
                        HBox topCheck = new HBox();
                        topCheck.setAlignment(Pos.CENTER);
                        topCheck.setPadding(new Insets(20,10,10,10));
                        topCheck.getChildren().add(checkText);

                        HBox bottomCheck = new HBox();
                        bottomCheck.setAlignment(Pos.CENTER);
                        bottomCheck.setPadding(new Insets(10,10,20,10));
                        bottomCheck.getChildren().add(close);
                        bottomCheck.setSpacing(15);

                        checkPane.setTop(topCheck);
                        checkPane.setBottom(bottomCheck);
                        Scene scene = new Scene(checkPane, 300, 100);
                        checkStage.setScene(scene);
                        checkStage.show();
                    }
                    j++;
                }
                i++;
            }
            if (done) {
                Stage wonStage = new Stage();
                wonStage.setTitle("Congratulations");
                wonStage.setResizable(false);

                BorderPane checkPane = new BorderPane();

                Text checkText = new Text("Well done " + name.getText() + ", you have won the game!");
                
                Button playAgain = new Button("Play again");
                playAgain.setOnAction(o -> {
                    wonStage.close();
                    puzzleDisplay(size, borderPane, hardness);
                    problems.setText(puzzle.getProblemsEasy());
                }); 
                
                Button close = new Button("Close");
                close.setOnAction(o -> {
                    wonStage.close();
                    primaryStage.close();
                }); 

                HBox topCheck = new HBox();
                topCheck.setAlignment(Pos.CENTER);
                topCheck.setPadding(new Insets(20,10,10,10));
                topCheck.getChildren().add(checkText);

                HBox bottomCheck = new HBox();
                bottomCheck.setAlignment(Pos.CENTER);
                bottomCheck.setPadding(new Insets(10,10,20,10));
                bottomCheck.getChildren().addAll(playAgain, close);
                bottomCheck.setSpacing(15);

                checkPane.setTop(topCheck);
                checkPane.setBottom(bottomCheck);
                Scene scene = new Scene(checkPane, 300, 100);
                wonStage.setScene(scene);
                wonStage.show();
            }
        });
      
        Label sizeLabel = new Label("Size:");
        Label difficultyLabel = new Label("Difficulty:");
        Label colourLabel = new Label("Colour:");
        Label nameLabel = new Label("Name:");
        

        borderPane.setBottom(bottomPane);
        borderPane.setTop(topPane);
        borderPane.setLeft(leftPane);
        borderPane.setRight(rightPane);
        bottomPane.getChildren().addAll(nameLabel, name, colourLabel, ColourHBox, sizeLabel, SizeHBox, difficultyLabel, difficultyHBox, hint, New, check, QuitButton);
        

        Scene scene = new Scene(borderPane, 1900, 1000);
        primaryStage.setResizable(false);
        
        primaryStage.setTitle("Futoshiki");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void puzzleDisplay(int size, BorderPane borderPane, String hardness) {
        GridPane centerPane = new GridPane();
        puzzle = new FutoshikiPuzzle(size);
        puzzle.fillPuzzle(hardness);
        puzzle.solve();
        centerPane.setMaxSize(950, 950);
        centerPane.setAlignment(Pos.CENTER);
        problems.setTextAlignment(TextAlignment.RIGHT);
        problems.prefWidth(400);    
        for(int i = 0; i < (puzzle.getGrid().length*2)-1; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100/(puzzle.getGrid().length*2)-1);
            column.setHgrow(Priority.ALWAYS);
            centerPane.getColumnConstraints().add(column);
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100/(puzzle.getGrid().length*2)-1);
            row.setVgrow(Priority.ALWAYS);
            centerPane.getRowConstraints().add(row);
        }
        for(int i = 0; i < (puzzle.getGrid().length*2)-1; i++){
            for(int j = 0; j < (puzzle.getGrid().length*2)-1; j++){ 
                int rowIndex = i/2;
                int colIndex = j/2;
                if(i%2 == 0){
                   if ((j % 2) == 0) {
                        if(puzzle.getSquare(rowIndex, colIndex).getSquareValue() != 0){
                            Button button = new Button("" + puzzle.getSquare(rowIndex, colIndex).getSquareValue());
                            button.setMaxSize(10000, 10000);
                            button.setStyle("-fx-text-fill: white;" + "-fx-border-color: black;" + "-fx-background-color: #626262;" + "-fx-font-weight: bold;" + "-fx-font-size: " + (100/puzzle.getGrid().length));
                            centerPane.add(button, j, i);
                        }
                        else{
                            Button button = new Button();
                            button.setMaxSize(10000, 10000);
                            button.setStyle("-fx-border-color: black;" + "-fx-background-color: #E4E4E4;" + "-fx-font-size: " + (100/puzzle.getGrid().length));       
                            
                            button.setOnMouseClicked(new EventHandler<MouseEvent>() {   
                                @Override
                                public void handle(MouseEvent event) {
                                    MouseButton click = event.getButton();
                                    if(click==MouseButton.PRIMARY){
//                                        int result;
                                        int i;
                                        if(button.getText() == "") {
                                            i = 0;
                                        }
                                        else{
                                            i = Integer.parseInt(button.getText());     
                                        }
                                        if(i < puzzle.getGrid().length) {       
                                            i ++;
                                            String letter = Integer.toString(i);
                                            puzzle.getSquare(rowIndex, colIndex).setSquareValue(0);            
                                            button.setText(letter); 
                                            button.setStyle("-fx-text-fill: grey;" + "-fx-border-color: black;" + "-fx-background-color: #E4E4E4;" + "-fx-font-size: " + (100/puzzle.getGrid().length));       
                                        }
                                        else {
                                            puzzle.getSquare(rowIndex, colIndex).setSquareValue(0);
                                            button.setText("");
                                            button.setStyle("-fx-text-fill: grey;" + "-fx-border-color: black;" + "-fx-background-color: #E4E4E4;" + "-fx-font-size: " + (100/puzzle.getGrid().length));
                                        }
                                    problems.setText(puzzle.getProblemsEasy());
                                    }else if(click == MouseButton.SECONDARY){
                                        int i;
                                        if(button.getText() == "") {
                                            i = 0;
                                        }
                                        else{
                                            i = Integer.parseInt(button.getText());     
                                        }
                                        
                                        if(puzzle.getSquare(rowIndex, colIndex).getSquareValue() == 0 && button.getText() != "") {
                                            puzzle.getSquare(rowIndex, colIndex).setSquareValue(i);
                                            button.setStyle("-fx-text-fill: black;" + "-fx-border-color: black;" + "-fx-background-color: #B1B1B1;" + "-fx-font-weight: bold;" + "-fx-font-size: " + (100/puzzle.getGrid().length)); 
                                            problems.setText(puzzle.getProblemsEasy());
                                            puzzleHint.setText("");
                                        }
                                        else{
                                            puzzle.getSquare(rowIndex, colIndex).setSquareValue(0);
                                            button.setStyle("-fx-text-fill: grey;" + "-fx-border-color: black;" + "-fx-background-color: #E4E4E4;" + "-fx-font-size: " + (100/puzzle.getGrid().length));
                                            problems.setText(puzzle.getProblemsEasy());
                                        }
                                            
                                    }
                                }
                            });
                            centerPane.add(button, j, i);
                        }
                    } else {
                        if(colIndex < puzzle.getGrid().length - 1){    
                           Label newLabel = new Label(puzzle.getRowConstraint(rowIndex, colIndex).getValue());
                           GridPane.setValignment(newLabel, VPos.CENTER);
                           GridPane.setHalignment(newLabel, HPos.CENTER);
                           newLabel.setStyle("-fx-font-weight: bold");
                           newLabel.setStyle("-fx-font-size: " + (100/puzzle.getGrid().length));
                           centerPane.add(newLabel , j , i); 
                        }
                   }
                }
                else{
                    if ((j % 2) == 0) {
                        Label newLabel = new Label(puzzle.getColumnConstraint(rowIndex, colIndex).getValue());
                        GridPane.setHalignment(newLabel, HPos.CENTER);
                        newLabel.setStyle("-fx-font-weight: bold");
                        newLabel.setStyle("-fx-font-size: " + (100/puzzle.getGrid().length));
                        centerPane.add(newLabel , j , i);
                    }
                    else{
                        centerPane.add(new Label(""), j, i);
                    }
                } 
            }
        }
        puzzleHint.setText("");
        centerPane.setStyle("-fx-border-color: black; -fx-background-color: white");
        borderPane.setCenter(centerPane);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}