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
public class FutoshikiSquare {
    private boolean editable;
    private boolean marked;
    private int squareValue;
    
    public FutoshikiSquare() {
        squareValue = 0;
        editable = true;
    }
    
    
    public void setEditable(boolean editable) {   //to find out if its editable
        this.editable = editable;
    }

    public boolean getEditable() {   //to find out if its editable
        return editable;
    }

    public void setSquareValue(int digit) {     //square number
        squareValue = digit;
    }
    public int getSquareValue() {
        return squareValue;
    }
    
    
    public boolean isEmpty() {
        return squareValue == 0;
    }
    
}
