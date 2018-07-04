/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimax;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author abouf
 */
public class Data {
    public SimpleIntegerProperty IDUser;
    public SimpleStringProperty ClasseUser;
    public SimpleIntegerProperty UsedRes;
    
    public SimpleStringProperty UserState;

    public Data(int IDUser, String ClasseUser, int UsedRes, String UserState) {
        this.IDUser = new SimpleIntegerProperty(IDUser);
        this.ClasseUser =new SimpleStringProperty(ClasseUser);
        this.UsedRes = new SimpleIntegerProperty(UsedRes);
        this.UserState = new SimpleStringProperty(UserState);
    }

    public int getIDUser() {
        return IDUser.get();
    }

    public String getClasseUser() {
        return ClasseUser.get();
    }

    public int getUsedRes() {
        return UsedRes.get();
    }

    public String getUserState() {
        return UserState.get();
    }
    
    
}
