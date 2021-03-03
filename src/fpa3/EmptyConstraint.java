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
public class EmptyConstraint extends Constraint {
    
    
    public EmptyConstraint(FutoshikiSquare beforeConstraint, FutoshikiSquare afterConstraint) {
        super(beforeConstraint, afterConstraint);
        setValue( " ");
    }

    @Override
    public boolean check() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getType() {
        return "emptyconstraint";
    }
            
}
