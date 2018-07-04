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
public class RealTime extends Classes{
  /* CLASSE ID */
    public final int REAL_TIME = 1;
    public final int DEBIT = 10;
    public String Name ;
  /*MAX NUMBER of transmission*/
    public static int NRbe;
    public int TRAM_NUMBER_MAX;
    public int TRAM_USED;
    
 /*TRANSMISSION NUMBER LEFT BY CURRENT USER */
    public int nbr_usr;
    
    public RealTime(){
        super();
        this.Name = "Real Time";
        this.TRAM_USED = 4;
        if(NRbe != 0){
            this.nbr_usr=NRbe;
        }
    }
    
    public static void setNRbe(int a){
           NRbe = a;
       }

    @Override
    void decrementNRbe() {
        if(nbr_usr != 0){
            this.nbr_usr--;
        }
    }
    @Override
    int getNbr() {
        return this.nbr_usr;
    }

    @Override
    int getResourceUsed() {
        return this.TRAM_USED;
    }

    @Override
    void decrementResource(int var) {
        this.TRAM_USED=this.TRAM_USED-var;
    }

    @Override
    String getClasseName() {
       return this.Name;
    }
}
