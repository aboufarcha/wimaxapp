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
public class NoRealTime extends Classes{
    public final int NO_REAL_TIME = 2;
    public String Name;
    public int User_Download_File;
    public static int NRbe;
    public int nbr_usr;
    
    public NoRealTime(){
        super();
        this.Name = "No Real Time";
        this.User_Download_File=1750;
        if(NRbe != 0){
            this.nbr_usr=NRbe;
        }
    }
    
    public void decrementResource(int DownloadedSize){
            this.User_Download_File = this.User_Download_File-DownloadedSize;
    }
    
    @Override
    int getResourceUsed() {
        return this.User_Download_File;
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
    String getClasseName() {
        return this.Name;
    }

   
}
