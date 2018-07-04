/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static wimax.CDMAManager.AllUsers;

/**
 *
 * @author Dell
 */
public class BTSManager {
    
    public static LinkedList<User> UsersCollision ;
    public static LinkedList<User> UsersSuccess ;
    public static LinkedList<Data> DataTable = new LinkedList();
    
    public BTSManager(){
      /* THIS LINKEDLIST FOR SAVING USERS THAT THEY HAVE A COLLUSION */
        UsersCollision = new LinkedList();
      /* THIS LINKEDLIST FOR ADDING THE USERS WHO PASSED SUCCESSFULLY */
        UsersSuccess = new LinkedList();
    }
   
/*-------------------------------------- THIS METHOD FOR CHECKING THE CDMA -------------------------------------------------*/
    public void checkCDMA(int currentCycle,int CycleNumber){
       ArrayList<Integer> Temp ;
       Random random = new Random();
        Temp = AllUsers.stream().map(p -> p.getCDMA_CODE()).collect(Collectors.toCollection(ArrayList<Integer>::new));
        for(User u : AllUsers){
            int count = Collections.frequency(Temp,u.getCDMA_CODE());
            if(count > 1){
               int Backoff = random.nextInt((CycleNumber-currentCycle)+1)+currentCycle;
               u.setBackoff(Backoff);
               u.getClasse_Type().decrementNRbe();
               u.setState("Collision");
               UsersCollision.add(u);
               switch(u.getClasse()){
                 case 1 : Statistics.CollusionRTNumber++;break;
                 case 2 : Statistics.CollusionNRTNumber++;break;
                 case 3 : Statistics.CollusionBENumber++;break;
             }
               
            DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
             }else{
             UsersSuccess.add(u);
             switch(u.getClasse()){
                 case 1 : Statistics.SuccessRTNumber++;break;
                 case 2 : Statistics.SuccessNRTNumber++;break;
                 case 3 : Statistics.SuccessBENumber++;break;
             }
            }
        }
    }
   
    
}
