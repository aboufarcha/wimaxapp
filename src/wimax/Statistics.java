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
public class Statistics {
    
    public static int SuccessRTNumber;
    public static int SuccessNRTNumber;
    public static int SuccessBENumber;
   
    public static int CollusionRTNumber;
    public static int CollusionNRTNumber;
    public static int CollusionBENumber;
    
    public static  int SurrendedRTNumber;
    public static int SurrendedNRTNumber;
    public static int SurrendedBENumber;
    
    
    
    public int TotalNumber;
    public Statistics(){
           SuccessRTNumber=0;
           SuccessNRTNumber=0;
           SuccessBENumber=0;
           
           CollusionRTNumber=0;
           CollusionNRTNumber=0;
           CollusionBENumber=0;
           
           SurrendedRTNumber=0;
           SurrendedNRTNumber=0;
           SurrendedBENumber=0;
           TotalNumber = 0;
    }
    
/*------------------ THIS METHOD RETURNS HOW MANY RT USER ---------------------*/
    public static float getRealTimePourcentage(){
            return (CDMAManager.RTUsersNumber*100/CDMAManager.History);
    }
  
/*------------------ THIS METHOD RETURNS HOW MANY NRT USER ---------------------*/
    public static float getNoRealTimePourcentage(){
            return (CDMAManager.NRTUsersNumber*100/CDMAManager.History);
    }
    
/*------------------ THIS METHOD RETURNS HOW MANY BE USER ---------------------*/
    public static float getBestEffortPourcentage(){
            return (CDMAManager.BEUsersNumber*100/CDMAManager.History);
    }
    
/*------------------ THIS METHOD RETURNS HOW MANY RT USERS WERE SUCCESSFULLY  
                        TRANSMITTING THIER DATA ---------------------*/
    public static float getRealTimeSuccess(){
         return ((SuccessRTNumber*100)/CDMAManager.RTUsersNumber);
    }
    
/*------------------ THIS METHOD RETURNS HOW MANY NRT USERS WERE SUCCESSFULLY  
                        TRANSMITTING THIER DATA ---------------------*/
    public static float getNoRealTimeSuccess(){
         return ((SuccessNRTNumber*100)/CDMAManager.NRTUsersNumber);
    }

/*------------------ THIS METHOD RETURNS HOW MANY BE USERS WERE SUCCESSFULLY  
                        TRANSMITTING THIER DATA ---------------------*/
    public static float getBestEffortSuccess(){
         return ((SuccessBENumber*100)/CDMAManager.BEUsersNumber);
    }
    
/*------------------ THIS METHOD RETURNS HOW MANY RT USERS GET 
                    COLLUSION ---------------------*/
    public static float getRealTimeCollusion(){
         return ((CollusionRTNumber*100)/CDMAManager.RTUsersNumber);
    }
 
/*------------------ THIS METHOD RETURNS HOW MANY NRT USERS GET 
                    COLLUSION ---------------------*/
    public static float getNoRealTimeCollusion(){
         return ((CollusionNRTNumber*100)/CDMAManager.NRTUsersNumber);
    }
    
/*------------------ THIS METHOD RETURNS HOW MANY BE USERS GET 
                    COLLUSION ---------------------*/    
    public static float getBestEffortCollusion(){
         return ((CollusionBENumber*100)/CDMAManager.BEUsersNumber);
    }
 
/*------------------ THIS METHOD RETURNS HOW MANY RT USERS ARE
                    ADANDONED ---------------------*/  
    public static float getRealTimeSurrended(){
         return ((SurrendedRTNumber*100)/CDMAManager.RTUsersNumber);
    }
  
/*------------------ THIS METHOD RETURNS HOW MANY NRT USERS ARE
                    ADANDONED ---------------------*/ 
    public static float getNoRealTimeSurrended(){
         return ((SurrendedNRTNumber*100)/CDMAManager.NRTUsersNumber);
    }

/*------------------ THIS METHOD RETURNS HOW MANY BE USERS ARE
                    ADANDONED ---------------------*/     
    public static float getBestEffortSurrended(){
         return ((SurrendedBENumber*100)/CDMAManager.BEUsersNumber);
    }
}
