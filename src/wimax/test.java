/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static wimax.CDMAManager.AllUsers;
import static wimax.CDMAManager.SurrendedUsers;

/**
 *
 * @author abouf
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
       CDMAManager C = new CDMAManager();
        BTSManager bts = new BTSManager();
        ResourceManager RM = new ResourceManager();
        RealTime.setNRbe(4);
        NoRealTime.setNRbe(2);
        BestEffort.setNRbe(4);
        CDMAManager.History = 0;
        CDMAManager.BEUsersNumber=0;
        CDMAManager.RTUsersNumber=0;
        CDMAManager.NRTUsersNumber=0;
        Statistics s = new Statistics();
        
        int i=0;
       /* while(i<5){
                //System.out.println(" i  = "+i);
                C.AllUsersClasse(40,i);
                C.UsersCDMA(1, 10, 11, 30, 31, 90);
                bts.checkCDMA(i,5);
                /*RM.ResourceSpreading();
                AllUsers.clear();*/
                
                //System.err.println("Allusers size "+AllUsers.size());
              //  i++;
                
     //   }
       Classes c1 = new RealTime();
       Classes c2 = new NoRealTime();
       Classes c3 = new BestEffort();
       System.out.println(c1.getClasseName());
       System.out.println(c2.getClasseName());
       System.out.println(c3.getClasseName());
       User u = new User(1,2);
       System.out.println(u.getClasse_Type().getClasseName());
      //  executor.shutdown();
        
      //  System.out.println(CDMAThread.counter);
    }
    
}
