/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import fpa3.Constraint;
//import fpa3.EmptyConstraint;
import fpa3.FutoshikiPuzzle;
//import fpa3.FutoshikiSquare;
//import fpa3.LessThan;
//import fpa3.MoreThan;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tb352
 */
public class Tests {
    
    public Tests() {
    }
    
                                                                    //my solve is only every used at the start so these are the only valid tests I can do
    @Test
    public void solvedRegular() {
        FutoshikiPuzzle futoshiki = new FutoshikiPuzzle(5);
        futoshiki.fillPuzzle("m");
        futoshiki.solve();
        assertTrue(futoshiki.isLegal());
    }
    
    @Test
    public void solvedEasier() {
        FutoshikiPuzzle futoshiki = new FutoshikiPuzzle(2);
        futoshiki.fillPuzzle("e");
        futoshiki.solve();
        assertTrue(futoshiki.isLegal());
    }
    
    @Test
    public void solvedHarder() {
        FutoshikiPuzzle futoshiki = new FutoshikiPuzzle(8);
        futoshiki.fillPuzzle("h");
        futoshiki.solve();
        assertTrue(futoshiki.isLegal());
    }
}
