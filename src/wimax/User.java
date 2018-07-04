/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wimax;

import java.util.function.Predicate;
import java.util.stream.Stream;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Dell
 */
public class User {
    public static int INC_ID = 0;
    private int Backoff;
    private int CDMA_CODE;
    private int Classe;
    private int USERID;
    private Classes Classe_Type;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public User(){
        INC_ID++;
        this.USERID = INC_ID; 
    }
    public User(int cdma , int cl){
        this.CDMA_CODE = cdma;
        this.Classe = cl;
        this.Backoff = 0;
        INC_ID++;
        this.USERID = INC_ID; 
        initialClasse();
    }

    public Classes getClasse_Type() {
        return Classe_Type;
    }
    
    public void initialClasse(){
           switch (Classe){
               case 1: this.Classe_Type = new RealTime();break;
               case 2: this.Classe_Type = new NoRealTime();break;
               case 3: this.Classe_Type = new BestEffort();break;
           }
    }
    
    public User(int Classe) {
        INC_ID++;
        this.USERID = INC_ID; 
        this.Classe = Classe;
        initialClasse();
    }

    public int getCDMA_CODE() {
        return CDMA_CODE;
    }

    public int getClasse() {
        return Classe;
    }

    public void setCDMA_CODE(int CDMA_CODE) {
        this.CDMA_CODE = CDMA_CODE;
    }

    public void setClasse(int Classe) {
        this.Classe = Classe;
    }

    public int getBackoff() {
        return Backoff;
    }

    public void setBackoff(int Backoff) {
        this.Backoff = Backoff;
    }

    public int getUSERID() {
        return USERID;
    }
    
    

    
}
