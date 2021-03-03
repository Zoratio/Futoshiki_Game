/*
 *
 */
package fpa3; 
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
/**
 * The FutoshikiPuzzle class represents a FutoshikiPuzzle object that has a certain size grid which can be decided by the parameters.
 * 
 * @author 
 * @version 2
 */                                
public class FutoshikiPuzzle {

    private FutoshikiSquare[][] grid;   
    private Constraint[][] colConstraints;  
    private Constraint[][] rowConstraints;
    private FutoshikiSquare[][] solved;
    
    /**
     * Constructor FutoshikiPuzzle, representing the games grid.
     * @param size to set the integer of grid size.
     */
    public FutoshikiPuzzle(int size){
        if (size > 1) {
            grid = new FutoshikiSquare[size][size]; //makes grid then sets all values to 0. i then j.  
            solved = new FutoshikiSquare[size][size];
            colConstraints = new Constraint[size-1][size]; //makes column grid and sets all values to blank space. i then j.            
            rowConstraints = new Constraint[size][size-1]; //makes row grid and sets all values to blank space. i then j.
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = new FutoshikiSquare();   
                    if(i < grid.length - 1){
                        colConstraints[i][j] = new EmptyConstraint(grid[i][j], grid[i][j]);
                    }
                    if(j < grid.length - 1){
                        rowConstraints[i][j] = new EmptyConstraint(grid[i][j], grid[i][j]);
                    } 
                }
            }             
        }
        else {
            System.out.println("Please input a size that is larger than 1 so that the game is playable.");
        }
    }    
    
    
    
    /**
     * Method setSquare. This will set a digit in a location on the grid as long as it meets the requirements.
     * @param digit to set the integer of the grid digit.
     * @param x to set the integer of the digits i location.
     * @param y to set the integer of the digits j location.
     */
    public void setSquare(int digit, int x, int y){ //lets us put a digit into a particular square.
        if ((digit <= grid.length) && (digit > 0)){
            if ((x < grid.length) && (x >= 0) && (y < grid[0].length) && (y >= 0)){
                if (this.grid[x][y].getEditable()) {
                    this.grid[x][y].setSquareValue(digit);
                }
                else {
                    System.out.println("This is not an editable box. Please try again making sure that your x and y are not selecting an uneditable square."); //if the square is editale.
                }
            }
            else {
                System.out.println("This is not a valid index. Please try again making sure that your x is betweem 0-" + Integer.toString(grid.length-1) + " and that your y is between 0-" + Integer.toString(grid[0].length-1)); //if the index is invalid.   
            }
        }
        else {
            System.out.println("This is not a valid digit. Please try again, entering a digit from 1-" + grid.length + " instead."); //if the digit is invalid.
        }
    }
    
    /**
     * Method getSquare. This will allow the grid element to be used externally. 
     * @param x to get the i location from the grid array.
     * @param y to get the j location from the grid array.
     * @return integer that will be the value of the specified location.
     */
    public FutoshikiSquare getSquare(int x, int y) {      
        return grid[x][y];
    }
     
    
    
    
    /**
     * Method setRowConstraint. This will set a constraint in a location on the row constraints array as long as it meets the requirements.
     * @param constraint to set the String of the row constraints array.
     * @param x to set the String in the i location.
     * @param y to set the String in the i location.
     */
    public void setRowConstraint(String constraint, int x, int y){ //lets us set the constraint between a square and the square to its right in a row.
        if (constraint.equals("<") || constraint.equals(">")){ 
            if ((x < rowConstraints.length) && (x >= 0) && (y < rowConstraints[0].length) && (y >= 0)){
                if (constraint.equals("<")){
                    this.rowConstraints[x][y] = new LessThan("row",grid[x][y],grid[x][y+1]);
                }
                else if (constraint.equals(">")){
                    this.rowConstraints[x][y] = new MoreThan("row",grid[x][y],grid[x][y+1]);
                }
            }
            else {
                System.out.println("This is not a valid index. Please try again making sure that your x is betweem 0-" + Integer.toString(rowConstraints.length-1) + " and that your y is between 0-" + Integer.toString(rowConstraints[0].length-1)); //if the index is invalid.    
            }
        }
        else {
            System.out.println("This is not a valid constraint. Please try again, entering either '<' or '>' instead."); //if the digit is invalid.      
        }
    }    
    /**
     * Method getRowConstraint. This will allow the row constraint element to be used externally.
     * @param x to get the i location from the row constraint array.
     * @param y to get the j location from the row constraint array.
     * @return String that will be the value of the specified location.
     */
    public Constraint getRowConstraint(int x, int y) {
        return rowConstraints[x][y];
    }
    
    
    
    
    /**
     * Method setColumnConstraint. This will set a constraint in a location on the column constraint array as long as it meets the requirements.
     * @param constraint to set the String of the column constraints array.
     * @param x to get the i location from the column constraints array.
     * @param y to get the j location from the column constraints array.
     */
    public void setColumnConstraint(String constraint, int x, int y){ //lets us set the constraint between a square and the square below it in a column.
        if (constraint.equals("v") || constraint.equals("^")){ 
            if ((x < colConstraints.length) && (x >= 0) && (y < colConstraints[0].length) && (y >= 0)){
                if (constraint.equals("^")){
                    this.colConstraints[x][y] = new LessThan("column",grid[x][y],grid[x+1][y]);
                }
                else if (constraint.equals("v")){
                    this.colConstraints[x][y] = new MoreThan("column",grid[x][y],grid[x+1][y]);
                }
            }
            else {
                System.out.println("This is not a valid index. Please try again making sure that your x is betweem 0-" + Integer.toString(colConstraints.length-1) + " and that your y is between 0-" + Integer.toString(colConstraints[0].length-1)); //if the index is invalid.           
            }
        }
        else {
            System.out.println("This is not a valid input. Please try again, entering either 'v' or '^' instead."); //if the digit is invalid. 
        }
    }   
    /**
     * Method getColumnConstraint. This will allow the column constraint element to be used externally.
     * @param x to get the i location from the column constraint array.
     * @param y to get the j location from the column constraint array.
     * @return String that will be the value of the specified location.
     */
    public Constraint getColumnConstraint(int x, int y) {
        return colConstraints[x][y];
    }

    
    
    /**
     * Method fillPuzzle. This will set a certain amount of random digits and constraints in random locations.
     * @param difficulty to set the difficulty of the current puzzle.
     */
    public void fillPuzzle(String difficulty) { 
        int gridSize = grid.length;
        int colSize = colConstraints.length;
        int rowSize = rowConstraints.length-1;
        int amountCon;                                         
        Random ran = new Random();
        do{
            resetPuzzle(grid.length);
            if(difficulty == "e"){
                amountCon = (gridSize*gridSize)/9 + 1;
                while(amountCon > 0){
                    setColumnConstraint(ran.nextBoolean()? "v" : "^", ran.nextInt(colSize), ran.nextInt(colSize));
                    setRowConstraint(ran.nextBoolean()? "<" : ">", ran.nextInt(rowSize), ran.nextInt(rowSize));
                    amountCon--;
                }
            }
            else if(difficulty == "m"){        
                amountCon = (gridSize*gridSize)/7 + 1;
                while(amountCon > 0){
                    setColumnConstraint(ran.nextBoolean()? "v" : "^", ran.nextInt(colSize), ran.nextInt(colSize));
                    setRowConstraint(ran.nextBoolean()? "<" : ">", ran.nextInt(rowSize), ran.nextInt(rowSize));
                    amountCon--;
                }
            }
            else{
                amountCon = (gridSize*gridSize)/5 + 1;
                while(amountCon > 0){
                    setColumnConstraint(ran.nextBoolean()? "v" : "^", ran.nextInt(colSize), ran.nextInt(colSize));
                    setRowConstraint(ran.nextBoolean()? "<" : ">", ran.nextInt(rowSize), ran.nextInt(rowSize));
                    amountCon--;
                }
            }
        }
        while(!solve());
    }
    
    
    
    public void resetPuzzle(int size){
        if (size > 1) {
            grid = new FutoshikiSquare[size][size]; //makes grid then sets all values to 0. i then j.   
            colConstraints = new Constraint[size-1][size]; //makes column grid and sets all values to blank space. i then j.            
            rowConstraints = new Constraint[size][size-1]; //makes row grid and sets all values to blank space. i then j.
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = new FutoshikiSquare();   
                    if(i < grid.length - 1){
                        colConstraints[i][j] = new EmptyConstraint(grid[i][j], grid[i][j]);
                    }
                    if(j < grid.length - 1){
                        rowConstraints[i][j] = new EmptyConstraint(grid[i][j], grid[i][j]);
                    } 
                }
            }             
        }
        else {
            System.out.println("Please input a size that is larger than 1 so that the game is playable.");
        }
    }    
    
    
    /**
     * Method getGrid. This will allow the grid to be used externally.
     * @return integer that will be the grid. 
     */
    public FutoshikiSquare[][] getGrid() {   
        return grid;
    }
    /**
     * Method getRow. This will allow the row array to be used externally.
     * @return String that will be the row.  
     */
    public Constraint[][] getCol() {
        return colConstraints;
    }
    /**
     * Method getCol. This will allow the col array to be used externally.
     * @return String that will be the col. 
     */
    public Constraint[][] getRow() {
        return rowConstraints;
    }
    
    
    
    /**
    * Method displayString. This will start assigning values from the arrays onto a String then will return the String once the array has finished.
    * @return String that represents the current puzzle that has been made.
    */
    public String displayString(){ //returns a String representation of the puzzle for display. This will display everything that has been written so far for constraints etc. It's also what will put all the lines in place etc for how the grid will look.
        int index = 0; //keeps track of which row of the game I'm on.
        String game = "";
        if (grid.length < 10) {   //if the grid has been made to be 9-, this is what will run instead to make sure that the layout isn't wrong
            while (index < grid[0].length){
                for (int i = 0; i < grid.length-1; i++) { //sets the top lines of the grid box.
                    game += "--- ";
                }
                game += "---\n"; //starts the next for loop on a new row.



                for (int i = 0; i < grid.length-1; i++) { //sets the lines boxes side layout, the numbers and the constraints that are between each box.
                    if (grid[index][i].getSquareValue() == 0) {   
                        game += "| |";
                        if(rowConstraints[index][i].getType().equals("emptyconstraint")) {
                            game += " ";
                        }
                        else {
                            game += rowConstraints[index][i].getValue();
                        }
                    }                                                      
                    else{
                        game += "|" + grid[index][i].getSquareValue() + "|"; 
                        if(rowConstraints[index][i].getType().equals("emptyconstraint")) {  
                            game+= " ";
                        }
                        else {
                            game+= rowConstraints[index][i].getValue();  
                        }
                    }
                }
                if (grid[index][grid.length-1].getSquareValue() == 0) {  
                    game += "| |\n"; //starts the next for loop on a new row.
                }
                else {
                    game += "|" + grid[index][grid.length-1].getSquareValue() + "|\n"; //starts the next for loop on a new row.   
                }


                for (int i = 0; i < grid.length-1; i++) { //sets the top lines of the grid box.
                    game += "--- ";
                }
                game += "---\n"; //starts the next for loop on a new row.


                if (index < grid.length-1){ //sets the column constraints that are between the box and the one below it.
                    for (int i = 0; i <grid[0].length-1; i++) {
                        if(colConstraints[index][i].getType().equals("emptyconstraint")) {
                            game += " " + "  " + " ";
                        }
                        else {
                            game += " " + colConstraints[index][i].getValue() + "  ";
                        }
                    }
                    if (colConstraints[index][colConstraints[0].length-1].getType().equals("emptyconstraint")) {
                        game += " " + " " + "\n";
                    }
                    else {
                        game += " " + colConstraints[index][colConstraints[0].length-1].getValue() + "\n"; //starts the next for loop on a new row.
                    }
                }


                index++;
            }
        }
        else {  //if the grid has been made to be 10+, this is what will run instead to make sure that the layout isn't wrong
            while (index < grid[0].length){
                for (int i = 0; i < grid.length-1; i++) { //sets the top lines of the grid box.
                    game += "---- ";
                }
                game += "----\n"; //starts the next for loop on a new row.



                for (int i = 0; i < grid.length-1; i++) { //sets the lines boxes side layout, the numbers and the constraints that are between each box.
                    if (grid[index][i].getSquareValue() == 0) {   
                        if(rowConstraints[index][i].getType().equals("emptyconstraint")) {
                            game += "|  | ";
                        }
                        else {
                            game += "|  |" + rowConstraints[index][i].getValue();
                        }
                    }
                    else{
                        if (grid[index][i].getSquareValue() < 10) {
                            if(rowConstraints[index][i].getType().equals("emptyconstraint")) {
                                game += "| " + grid[index][i].getSquareValue() + "| ";   
                            }
                            else {
                                game += "| " + grid[index][i].getSquareValue() + "|" + rowConstraints[index][i].getValue();  
                            }
                        }
                        else {  //more than 10
                            if(rowConstraints[index][i].getType().equals("emptyconstraint")) {
                                game += "|" + grid[index][i].getSquareValue() + "| ";   
                            }
                            else {
                                game += "|" + grid[index][i].getSquareValue() + "|" + rowConstraints[index][i].getValue();  
                            }
                        }
                    }
                }
                if (grid[index][grid.length-1].getSquareValue() == 0) { 
                    game += "|  |\n"; //starts the next for loop on a new row.
                }
                else {
                    if (grid[index][grid.length-1].getSquareValue() < 10)  
                        game += "| " + grid[index][grid.length-1].getSquareValue() + "|\n"; //starts the next for loop on a new row.   
                    else {
                        game += "|" + grid[index][grid.length-1].getSquareValue() + "|\n"; //starts the next for loop on a new row.   
                    }
                }


                for (int i = 0; i < grid.length-1; i++) { //sets the top lines of the grid box.
                    game += "---- ";
                }
                game += "----\n"; //starts the next for loop on a new row.


                if (index < grid.length-1){ //sets the column constraints that are between the box and the one below it.
                    for (int i = 0; i < grid[0].length-1; i++) {
                        if (colConstraints[index][i].getType().equals("emptyconstraint")) {
                            game += "  " + " " + "  ";
                        }
                        else {
                            game += "  " + colConstraints[index][i].getValue() + "  ";
                        }
                    }
                    if (colConstraints[index][colConstraints[0].length-1].getType().equals("emptyconstraint"))
                        game += "  " + " " + "\n"; //starts the next for loop on a new row.
                    else {
                        game += "  " + colConstraints[index][colConstraints[0].length-1].getValue() + "\n"; //starts the next for loop on a new row.
                    }
                }


                index++;
            }
        }
        return game; //the String which will show the whole game.
    }
    
    
    
    
    
    
    /**
     * Method isLegal. Will determine if the puzzle is currently legal or not.
     * @return boolean on whether or not the puzzle is legal.
     */
    public boolean isLegal() {
        //run all of the methods that im using to individualy check each factor legality
        return (areRowsUnique() && areColsUnique() && numberRange() && rowConLegal() && colConLegal());
    }
       
    /**
     * Method areRowsUnique. Will check if the row has unique numbers.
     * @return boolean on whether or not the puzzle is legal.
     */
    private boolean areRowsUnique(){
        for(FutoshikiSquare[] row: grid){   
            HashSet rowSet = new HashSet(); 
            for(FutoshikiSquare val: row){   
                if(val.getSquareValue() != 0 && !rowSet.add(val.getSquareValue())){    
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Method areColsUniques. will check if the column has unique numbers.
     * @return boolean on whether or not the puzzle is legal. 
     */
     private boolean areColsUnique(){
        for(int col = 0; col<grid.length; col++){               
            HashSet colSet = new HashSet();
            for(int row = 0; row<grid.length; row++){
                FutoshikiSquare val = grid[row][col];  
                if(val.getSquareValue() != 0 && !colSet.add(val.getSquareValue())){   
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Method numberRange. will check is there is a number that is out of range.
     * @return boolean on whether or not the puzzle is legal. 
     */
    private boolean numberRange() {
        boolean valid = true;
        int row = 0;  //used to count the amount of lines its gone through
        while(valid && row < grid.length-1) {
            int i = 0;  //number
            while(valid && i < grid.length-1) { 
                if(grid[row][i].getSquareValue() > grid.length) {    //if the values are the same     
                    valid = false;
                }
                i++;
            }
            row++;
        }
        return valid;
    }
    /**
     * Method rowConLegal. will check if there is an illegal instance of a row condition.
     * @return boolean on whether or not the puzzle is legal. 
     */
    private boolean rowConLegal() {
        boolean valid = true;
        int row = 0;  //used to count the amount of lines its gone through
        while(valid && row < rowConstraints.length) {
            int i = 0;  //row constraint position
            while(valid && i < rowConstraints.length-1) { 
                if(grid[row][i].getSquareValue() != 0 && grid[row][i+1].getSquareValue() != 0 && "<".equals(rowConstraints[row][i].getValue())) {    //to check for legal < 
                    if(grid[row][i].getSquareValue() >= grid[row][i+1].getSquareValue()) {   
                        valid = false;
                    }
                }
                else if(grid[row][i].getSquareValue() != 0 && grid[row][i+1].getSquareValue() != 0 && ">".equals(rowConstraints[row][i].getValue())) {    //to check for legal >  
                    if(grid[row][i].getSquareValue() <= grid[row][i+1].getSquareValue()) {  
                        valid = false;
                    }
                }
                if(grid[row][i].getSquareValue() == grid[0].length && "<".equals(rowConstraints[row][i].getValue())) {
                    valid = false;
                }
                else if(grid[row][i+1].getSquareValue() == grid[0].length && ">".equals(rowConstraints[row][i].getValue())) {
                    valid = false;
                }
                else if(grid[row][i].getSquareValue() == 1 && ">".equals(rowConstraints[row][i].getValue())) {
                    valid = false;
                }
                else if(grid[row][i+1].getSquareValue() == 1 && "<".equals(rowConstraints[row][i].getValue())) {
                    valid = false;
                }
            i++;
            }
        row++;
        }
        return valid;
    }
    /**
     * Method colConLegal. will check if there is an illegal instance of a column condition.
     * @return boolean on whether or not the puzzle is legal. 
     */
    private boolean colConLegal() {
        boolean valid = true;
        int y = 0;
        while(valid && y < colConstraints.length) {
            int x = 0;
            while(valid && x <= colConstraints.length ) {
                if(grid[y][x].getSquareValue() != 0 && grid[y+1][x].getSquareValue() != 0 && "v".equals(colConstraints[y][x].getValue())) {   
                    if(grid[y][x].getSquareValue() <= grid[y+1][x].getSquareValue()) {  
                        valid = false;
                    }
                }
                else if(grid[y][x].getSquareValue() != 0 && grid[y+1][x].getSquareValue() != 0 && "^".equals(colConstraints[y][x].getValue())) {  
                    if(grid[y][x].getSquareValue() >= grid[y+1][x].getSquareValue()) {   
                        valid = false;
                    }
                }
                if(grid[y][x].getSquareValue() == grid[0].length && "^".equals(colConstraints[y][x].getValue())) {
                    valid = false;
                }
                else if(grid[y+1][x].getSquareValue() == grid[0].length && "v".equals(colConstraints[y][x].getValue())) {
                    valid = false;
                }
                else if(grid[y][x].getSquareValue() == 1 && "v".equals(colConstraints[y][x].getValue())) {
                    valid = false;
                }
                else if(grid[y+1][x].getSquareValue() == 1 && "^".equals(colConstraints[y][x].getValue())) {
                    valid = false;
                }
            x++;            
            }
        y++;
        }
        return valid;
    }

    
    /**
     * Method getProblems. will run all the isLegal methods.
     * @return String of all the problems that are in the puzzle.
     */
    public String getProblems() {
        String problems = "\n";   //out print string of all the problem(s)
        if(areRowsUnique() == false) {
            problems += "There are repeated number(s) in your row(s) -\n";
        }
        if(areColsUnique() == false) {
            problems += "There are repeated number(s) in your column(s) -\n";
        }
        if(numberRange() == false) {
            problems += "There are number(s) that are out of range of the grid size -\n";
        }
        if(rowConLegal() == false) {
            problems += "There are number(s) that are not legal for your row constraint(s) -\n";
        }
        if(colConLegal() == false) {
            problems += "There are number(s) that are not legal for your column constraint(s) -\n";
        }
        return problems;
    }
    
    
    /**
     * Method empty. will empty a square if editable.
     * @param x to get the x location from the square array.
     * @param y to get the y location from the square array.
     * @return boolean on whether the square is editable.
     */
    public boolean empty(int x, int y) {
        if(grid[x][y].getEditable() == true) {
            if(grid[x][y].getSquareValue() != 0) {
                grid[x][y].setSquareValue(0);
                System.out.println("This location has now been cleared.");
            }
            else {
                System.out.println("This location is already empty.");
            }    
            return true;
        }
        else {
            System.out.println("This is not an editable box. Please try again making sure that your x and y are not selecting an uneditable square.");
            return false;
        }
    }
    
    /**
     * Method isPuzzleComplete. will scan through the whole array making sure it's legal and isn't empty. 
     * @return boolean on if the puzzle is complete.
     */
    public boolean isPuzzleComplete() {
        if(!isLegal()) {
            return false;
        }
        for (int i = 0; i < grid.length -1; i++) {
            for (int j = 0; j < grid.length -1; j++) {
                if(grid[i][j].getSquareValue() == 0) {
                    return false;
                }
            }
        }             
        return true;
    }
    
   
    
    /**
     * Method solve. will search through the whole grid to find a legal puzzle using recursion.
     * @return boolean for if its solvable.
     */
    public boolean solve() {
        boolean check = false;
        if(!isLegal()) {
            return check;
        }
        else {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].getSquareValue() == 0) {
                        for (int val = 1; val <= grid.length; val++) {
                            grid[i][j].setSquareValue(val);
                            grid[i][j].setEditable(false);
                            check = solve();
                            if (check) {
                                return check;
                            }
                        }
                        grid[i][j].setSquareValue(0);
                        grid[i][j].setEditable(false);   
                        return false;
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    solved[i][j] = new FutoshikiSquare();
                    solved[i][j].setSquareValue(grid[i][j].getSquareValue());
                }
            }
            Random ran = new Random();
            int amount = (grid.length*grid.length) - grid.length;
            while (amount > 0) {
                int ran1 = ran.nextInt(grid.length);                    
                int ran2 = ran.nextInt(grid.length);       
                grid[ran1][ran2].setSquareValue(0);
                grid[ran1][ran2].setEditable(true);
                amount--;
            }
            return true;
        }
    }
    

    /**
     * Method getSolved. will return the solved grid
     * @return solved
     */
    public FutoshikiSquare[][] getSolved() {
        return solved;
    }
    
    /**
     * Method getSolvedSquare. will return the value of the solved location
     * @param x to get the x location from the solved array
     * @param y to get the y location from the solved array
     * @return the integer value of the location x y
     */
    public int getSolvedSquare(int x, int y) {
        return solved[x][y].getSquareValue();
    }
    
    
    
    /**
     * Method areRowsUniqueEasy. will find all the locations where numbers are duplicated in the rows
     * @return the string of all the locations
     */
    private String areRowsUniqueEasy(){                 
        String problems = "";
        int i = 0;
        for(FutoshikiSquare[] row: grid){  
            i++;
            HashSet rowSet = new HashSet(); 
            int j = 0;
            for(FutoshikiSquare val: row){   
                j++;
                if(val.getSquareValue() != 0 && !rowSet.add(val.getSquareValue())){    
                    problems+="There is a duplicate number for your rows at row " + i + " column " + j + " -\n";
                }
            }
        }
        return problems;
    }
    /**
     * Method areColsUniquesEasy. will find all the locations where numbers are duplicated in the columns
     * @return the string of all the locations 
     */
     private String areColsUniqueEasy(){                 
        String problems = "";
        int i = 0;
        for(int col = 0; col<grid.length; col++){
            i++;
            HashSet colSet = new HashSet();
            int j = 0;
            for(int row = 0; row<grid.length; row++){
                j++;
                FutoshikiSquare val = grid[row][col];  
                if(val.getSquareValue() != 0 && !colSet.add(val.getSquareValue())){   
                    problems+="There is a duplicate number for your columns at row " + j + " column " + i + " -\n";
                }
            }
        }
        return problems;
    }
    /**
     * Method rowConLegalEasy. will find all the illegal instance of a row condition.
     * @return string of all the locations 
     */
    private String rowConLegalEasy() {                          
        String problems = "";
        int row = 0;  //used to count the amount of lines its gone through
        while(row < rowConstraints.length) {
            int column = 0;  //row constraint position
            while(column < rowConstraints.length-1) { 
                if(grid[row][column].getSquareValue() != 0 && grid[row][column+1].getSquareValue() > 1 && "<".equals(rowConstraints[row][column].getValue())) {    //to check for legal < 
                    if(grid[row][column].getSquareValue() >= grid[row][column+1].getSquareValue()) {   
                        problems+="Numbers in row " + (row+1) + " column " + (column+1) + " and column " + (column+2) + " break its row constraint -\n";
                    }
                }
                else if(grid[row][column].getSquareValue() > 1 && grid[row][column+1].getSquareValue() != 0 && ">".equals(rowConstraints[row][column].getValue())) {    //to check for legal >  
                    if(grid[row][column].getSquareValue() <= grid[row][column+1].getSquareValue()) {  
                        problems+="Numbers in row " + (row+1) + " column " + (column+1) + " and column " + (column+2) + " break its row constraint -\n";
                    }
                }
                if(grid[row][column].getSquareValue() == grid[0].length && grid[row][column+1].getSquareValue() == 0 && "<".equals(rowConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " column " + (column+1) + " and column " + (column+2) + " break its row constraint -\n";
                }
                else if(grid[row][column+1].getSquareValue() == grid[0].length && grid[row][column].getSquareValue() == 0 && ">".equals(rowConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " column " + (column+1) + " and column " + (column+2) + " break its row constraint -\n";
                }
                else if(grid[row][column].getSquareValue() == 1 && ">".equals(rowConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " column " + (column+1) + " and column " + (column+2) + " break its row constraint -\n";
                }
                else if(grid[row][column+1].getSquareValue() == 1 && "<".equals(rowConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " column " + (column+1) + " and column " + (column+2) + " break its row constraint -\n";
                }
            column++;
            }
        row++;
        }
        return problems;
    }
    /**
     * Method colConLegal. will find all the illegal instance of a column condition.
     * @return string of all the locations 
     */
    private String colConLegalEasy() {                      
        String problems = "";
        int row = 0;
        while(row < colConstraints.length) {
            int column = 0;
            while(column <= colConstraints.length ) {
                if(grid[row][column].getSquareValue() != 0 && grid[row+1][column].getSquareValue() > 1 && "v".equals(colConstraints[row][column].getValue())) {   
                    if(grid[row][column].getSquareValue() <= grid[row+1][column].getSquareValue()) {  
                        problems+="Numbers in row " + (row+1) + " and row " + (row+2) + " column " + (column+1) + " break its column constraint -\n";
                    }
                }
                else if(grid[row][column].getSquareValue() != 0 && grid[row+1][column].getSquareValue() > 1 && "^".equals(colConstraints[row][column].getValue())) {  
                    if(grid[row][column].getSquareValue() >= grid[row+1][column].getSquareValue()) {   
                        problems+="Numbers in row " + (row+1) + " and row " + (row+2) + " column " + (column+1) + " break its column constraint -\n";
                    }
                }
                if(grid[row][column].getSquareValue() == grid[0].length && grid[row+1][column].getSquareValue() == 0 && "^".equals(colConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " and row " + (row+2) + " column " + (column+1) + " break its column constraint -\n";
                }
                else if(grid[row+1][column].getSquareValue() == grid[0].length && grid[row][column].getSquareValue() == 0 && "v".equals(colConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " and row " + (row+2) + " column " + (column+1) + " break its column constraint -\n";
                }
                else if(grid[row][column].getSquareValue() == 1 && "v".equals(colConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " and row " + (row+2) + " column " + (column+1) + " break its column constraint -\n";
                }
                else if(grid[row+1][column].getSquareValue() == 1 && "^".equals(colConstraints[row][column].getValue())) {
                    problems+="Numbers in row " + (row+1) + " and row " + (row+2) + " column " + (column+1) + " break its column constraint -\n";
                }
            column++;            
            }
        row++;
        }
        return problems;
    }
    
    /**
     * Method getProblemsEasy. will combine all the string from the other 4 methods to make one string
     * @return the string of all strings
     */
    public String getProblemsEasy() {
        String problems = areRowsUniqueEasy() + areColsUniqueEasy() + rowConLegalEasy() + colConLegalEasy();
        return problems;
    }    
    
    
    
    
    
    /**
     * Method main. This will be the main method for the class for it to be able to run externally.
     * @param args to construct a FutoshikiPuzzle object.
     */
    public static void main(String[] args) {   
        FutoshikiPuzzle futoshiki = new FutoshikiPuzzle(5);
//        futoshiki.solve();
//        futoshiki.fillPuzzle("m");
//        System.out.println(futoshiki.isLegal());
//        System.out.println(futoshiki.isPuzzleComplete());
        System.out.println(futoshiki.displayString());
    }

}