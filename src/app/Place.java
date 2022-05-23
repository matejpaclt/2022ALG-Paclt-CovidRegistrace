/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Date;
import java.text.DateFormat;
import java.util.Scanner;

/**
 *
 * @author Uživatel
 */
public class Place {
    private String mainName;
    private String secondName;
    static Scanner sc = new Scanner(System.in);
    
    
    public Place(String mainName, String secondName){
        this.mainName = mainName;
        this.secondName = secondName;     
    }
    public String getMainName() {
        return mainName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    } 
}
