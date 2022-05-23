/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Uživatel
 */
public class Appointment {
    Place place;
    int month;
    int day;
    int hour;
    private boolean[][][] isFullTime;
    public Appointment(Place place,int month, int day, int hour){
        this.place=place;
        this.day=day;
        for (int i = 0; i<12;i++){
            for (int j = 0; j < 31; j++) {
                for (int k = 0; k < 11; k++) {
                    this.isFullTime[i][j][k]=true; 
                }
            }
        }
        this.isFullTime[month-1][day-1][hour-1]=false;
    }    
}
