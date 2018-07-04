/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

/**
 *
 * @author Dell
 */
public abstract class Classes {
    
   
    
    public Classes(){
        
    }
    abstract String getClasseName();
    abstract void decrementNRbe();
    abstract void decrementResource(int var);
    abstract int getNbr();
    abstract int getResourceUsed();
    
}
