/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;
import static wimax.BTSManager.DataTable;
import static wimax.BTSManager.UsersSuccess;
import static wimax.CDMAManager.AllUsers;
import static wimax.CDMAManager.SurrendedUsers;
import static wimax.BTSManager.UsersCollision;

/**
 *
 * @author abouf
 */
public class ResourceManager {
    public int BandWidth ;
    
    public ResourceManager(){
        
    }
    public void setBandWidth(int bw){
        this.BandWidth = bw;
    }
    
    public void ResourceSpreading(){
        LinkedList<User> RTSuccess;
      /* THIS LIST CONTAINS ONLY RT USERS */
        RTSuccess = UsersSuccess.stream().filter(x->x.getClasse()==1).collect(Collectors.toCollection(LinkedList<User>::new));
     /* THIS LIST CONTAINS NRT & BE USERS */
        LinkedList<User> LeftSuccess = UsersSuccess.stream().filter(x->x.getClasse()!=1).collect(Collectors.toCollection(LinkedList<User>::new));
        /* FIRST LOOP FOR GIVING THE PRIORITY TO RT USERS */
           for(User u : RTSuccess){
                /* TEST IF BANDWITDH ENOUGH & USER'S RESOURCES NEEDED FOR HIS COMMUNICATION */
                    if(this.BandWidth >= 10 && u.getClasse_Type().getResourceUsed()>0){
                        this.BandWidth = this.BandWidth-10;
                        u.getClasse_Type().decrementResource(1);
                     /* TEST IF THE USER COMPLETE HIS COMMUNICATION */
                        if(u.getClasse_Type().getResourceUsed()==0){
                            u.setState("Complete !!");
                            DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
                            UsersSuccess.remove(u);
                        }else{
                             u.setBackoff(u.getBackoff()+1);
                             u.setState("Trame transformed");
                             DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
                         }
                        
                    }else{
                        u.setState("Echec !!");
                        DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
                        SurrendedUsers.add(u);
                        UsersSuccess.remove(u);
                    }
               
           }
           /* CALCULATING THE BANDWIDTH LEFT FROM RT USERS */
           int bw = (this.BandWidth-(RTSuccess.size()*10))/LeftSuccess.size();
           for(User u : LeftSuccess){
                
                    if(this.BandWidth > 0 && u.getClasse_Type().getResourceUsed()>0){
                     /* IF USER RESOURCES NEEDED LESS THAN BANDWIDTH GIVEN,
                        THE USER WILL TAKE JUST HIS NEEDED
                        */
                        if(u.getClasse_Type().getResourceUsed()-bw<=0){
                             u.getClasse_Type().decrementResource(u.getClasse_Type().getResourceUsed());
                             this.BandWidth = this.BandWidth-bw;
                             u.setState("Complete !!");
                             DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
                             UsersSuccess.remove(u);
                        }else{
                             this.BandWidth = this.BandWidth-bw;
                             u.getClasse_Type().decrementResource(bw);
                             u.setBackoff(u.getBackoff()+1);
                             u.setState("Need more !! ");
                             DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
                        }
                        
                    }
           }
           
    }
}
