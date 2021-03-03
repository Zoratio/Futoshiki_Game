/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpa3;

/**
 *
 * @author 
 */
public class LessThan extends Constraint {
    
    public LessThan(String direction, FutoshikiSquare beforeConstraint, FutoshikiSquare afterConstraint){
        super(beforeConstraint, afterConstraint);
        if (direction.equals("row")){
            setValue("<");
        }else if (direction.equals("column")){
            setValue("^");
        }
    }
    
    @Override           //I chose to not do any tests for these as they are getters which are not needed to be tested
    public boolean check(){
        return getBefore().getSquareValue() < getAfter().getSquareValue();
    }

    @Override           //I chose to not do any tests for these as they are getters which are not needed to be tested
    public String getType() {
         return "lessthan";
    }

}
