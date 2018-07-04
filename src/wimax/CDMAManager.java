/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static wimax.BTSManager.DataTable;
import static wimax.BTSManager.UsersSuccess;
import static wimax.BTSManager.UsersCollision;

/**
 *
 * @author Dell
 */
public class CDMAManager {
   public static LinkedList<User> AllUsers  ;
   public static LinkedList<User> SurrendedUsers ;
   public static int History ;
   public static int RTUsersNumber,NRTUsersNumber,BEUsersNumber;
   
   
   
   public CDMAManager()
   {
     /* THIS LINKEDLIST USED FOR ADDING USERS TEMPORARY BY EACH CYCLE  */
       AllUsers = new LinkedList<>();
    /* THIS LINKEDLIST USED FOR USERS WHO ARE LEAVING THE COMMUNICATION */
       SurrendedUsers = new LinkedList<>();
   }
 
 /*------------------ THIS METHOD FOR ADDING USERS AND GIVING CLASSES RANDOMLY ------------------------*/
   public void AllUsersClasse(int number,int currentCycle){
        Random random = new Random();
        int Usernumber = random.nextInt(number)+1;
    // ADDING NEW USERS INTO @AllUsers LINKEDLIST AND GIVE THEM THIER CLASSES.
        for(int i=0 ; i<Usernumber;i++){
            // GENERATING THE RANDOM CLASSES
            int ClasseRandom = random.nextInt(3)+1;
            User u = new User(ClasseRandom);
            AllUsers.add(u);
        }
    // ADDING USERS WHO ARE IN COLLUSION IN LAST CYCLE
        if(!UsersCollision.isEmpty()){
            for(User u : UsersCollision){
                if( u.getBackoff() == currentCycle){
                    if(u.getClasse_Type().getNbr()==0){
                       SurrendedUsers.add(u);
                       switch(u.getClasse()){
                            case 1 : Statistics.SurrendedRTNumber++;break;
                            case 2 : Statistics.SurrendedNRTNumber++;break;
                            case 3 : Statistics.SurrendedBENumber++;break;
                        }
                    }else{
                       AllUsers.add(u);
                    }
                }
            }
        }
    //ADDING USERS WHO PASSED SUCCESSFULLY  LAST CYCLE AND TRY TO COMPLETE THIER COMMUNICATION
        if(!UsersSuccess.isEmpty()){
            for(User u : UsersSuccess){
                if( u.getBackoff() == currentCycle){
                    if(u.getClasse_Type().getNbr()==0){
                       u.setState("Abandoned");
                       SurrendedUsers.add(u);
                       switch(u.getClasse()){
                            case 1 : Statistics.SurrendedRTNumber++;break;
                            case 2 : Statistics.SurrendedNRTNumber++;break;
                            case 3 : Statistics.SurrendedBENumber++;break;
                        }
                       DataTable.add(new Data(u.getUSERID(),u.getClasse_Type().getClasseName(),u.getClasse_Type().getResourceUsed(),u.getState()));
                    }else{
                       AllUsers.add(u);
                    }
                }
            }
        }
   }
   
/*------------------ THIS METHOD FOR GENERATING THE CDMA ----------------------------------------------*/
   public void UsersCDMA(int RTCDMAINT1,int RTCDMAINT2,int NRTCDMAINT1,int NRTCDMAINT2,int BECDMAINT1,int BECDMAINT2){
       Random random = new Random();
        for(User u : AllUsers){         
                switch(u.getClasse()){
                    case 1: 
                           int RTCDMAPool = random.nextInt((RTCDMAINT2-RTCDMAINT1)+1)+RTCDMAINT1;
                           u.setCDMA_CODE(RTCDMAPool);
                           RTUsersNumber++;
                           break;
                    case 2:
                            int NRTCDMAPool = random.nextInt((NRTCDMAINT2-NRTCDMAINT1)+1)+NRTCDMAINT1;
                            u.setCDMA_CODE(NRTCDMAPool);
                            NRTUsersNumber++;
                            break;
                    case 3 : 
                            int BECDMAPool = random.nextInt((BECDMAINT2-BECDMAINT1)+1)+BECDMAINT1;
                            u.setCDMA_CODE(BECDMAPool);
                            BEUsersNumber++;
                            break;
                }
                History++;
                //s.TotalNumber++;
        }
   }
}
