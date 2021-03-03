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
public abstract class Constraint {
    private FutoshikiSquare before;
    private FutoshikiSquare after;
    private String constraint;
    
    public Constraint(FutoshikiSquare beforeConstraint, FutoshikiSquare afterConstraint) {
       before = beforeConstraint;
       after = afterConstraint;
    }
    
    
    public void setBefore(FutoshikiSquare square) {
        before = square;
    }
    
    public void setAfter(FutoshikiSquare square) {
        after = square;
    }
    
    public FutoshikiSquare getBefore() {
        return before;
    }

    public FutoshikiSquare getAfter() {
        return after;
    }

    public void setValue(String value) {
        constraint = value;
    }

    public String getValue() {
        return constraint;
    }

    public abstract boolean check();

    public abstract String getType();
    
}
