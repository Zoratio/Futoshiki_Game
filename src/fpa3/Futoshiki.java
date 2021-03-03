/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpa3;

/**
 *
 * @author tb352
 */
public class Futoshiki {
    
    public static void main(String args[]) {
        Parser p = new Parser();
        Command c = null;
        FutoshikiPuzzle puzzle = new FutoshikiPuzzle(5);
        puzzle.fillPuzzle("e");
        System.out.println("|'~.,.~'~.,.~'~.,.~'| FUTOSHIKI |'~.,.~'~.,.~'~.,.~'|\n");
        System.out.println("Possible commands:\n\n- NEW <size> ~ will make a puzzle of set size, must be higher than size 1. (Please note that the larger you make the grid, the longer it will take to be produced).\n- MARK <row> <column> <value> ~ will set a value in a position if editable.\n- CLEAR <row> <column> ~ will clear the value in a position if editable.\n- QUIT ~ will quit the game.\n");
        System.out.println("To help usability for the player, the indexing has been adjusted to work from 1, not 0.\n");
        System.out.println("Enter the the command 'new' as shown above... If you do not wish to use the 'new' command, a default size of 5 will be created for you:\n");
        while ((c = p.getCommand()) != null && c.getCommand() != CommandWord.QUIT) {    //THIS WILL KEEP ON LOOPING UNTIL 'QUIT' HAS BEEN ENTERED
            CommandWord cw = c.getCommand();
            switch (cw){
                case NEW:     
                    int checker = c.getValue();
                    if (checker > 1) {
                        puzzle = new FutoshikiPuzzle(c.getValue());
                    }
                    else {
                        System.out.println("\nWhen setting the puzzle size, it must be higher than 1 to be playable!");
                        break;
                    }
                    puzzle.fillPuzzle("e");
                    System.out.println("\n******************************************************\n");
                    System.out.println(puzzle.displayString());
                    System.out.println("- The grid has now been made.");
                    break;
                case MARK:
                    System.out.println("\n******************************************************\n");
                    puzzle.setSquare(c.getValue(), c.getRow()-1, c.getColumn()-1);
                    System.out.println(puzzle.displayString());
                    break;
                case CLEAR:
                    System.out.println("\n******************************************************\n");
                    if(puzzle.empty(c.getRow()-1, c.getColumn()-1)) {
                        System.out.println(puzzle.displayString());
                    }       
                    else {
                        System.out.println(puzzle.displayString());
                    }
                    break;
                case UNKNOWN:
                    System.out.println("Unknown command, please try again.");
                    break;
            }
            System.out.println("- Please enter the command you wish to use:\n");    
        }
        System.out.println("Thank you for playing!");
        System.exit(0);
    }
}
