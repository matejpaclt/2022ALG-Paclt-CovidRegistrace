/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import utils.FileTools;

/**
 *
 * @author Uživatel
 */
public class Appointment {
    String person;
    String place;
    int day;
    int hour;
    boolean isTest;
    public Appointment(String person,String place,int day, int hour, boolean isTest){
        this.person=person;
        this.place=place;
        this.day=day;
        this.hour=hour;
        this.isTest=isTest;
    }
    public static void createInvite(Appointment apo) throws IOException{
        File f =new File("data/Invitations/"+ apo.person + "pozvanka.csv");
        if(!f.exists()){
           f.createNewFile();
        }
         String testocko = (apo.isTest)?("testování"):("očkování");
         String testockoadj = (apo.isTest)?("testovacího"):("očkovacího");
         int plusone = apo.hour+1;
         String invitationText="Vážený Pane/Vážená Paní " + apo.person + ",\n toto je vaše pozvánka na " + testocko + " proti onemocnění COVID-19. Počet dní zbývajících do "+ testocko +": "+ apo.day +".\n Na "
                 + testocko +" se dostavte mezi "+ apo.hour +":00 a "+ plusone + ":00 do " + testockoadj + " zařízení " + apo.place + ".\n Těšíme se na vás.\n Ministerstvo zdravotnictví.";
        FileTools.writeToFile(f, invitationText);
        String dir = (apo.isTest)?("data/TestTimeTables/"):("data/VacTimeTables/");
        File d = new File(dir+apo.place+".csv");
        BufferedReader br = new BufferedReader(new FileReader(d));
        PrintWriter pw = new PrintWriter(d);
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
                if(i==apo.day-1){
                String[] data = line.split(",");
                int[] numbers = new int[data.length];
                for(int k = 0;k < data.length;k++){
                    if(!(k==apo.hour)){
                    numbers[k] = Integer.parseInt(data[k]);
                    }else{
                       numbers[k] = Integer.parseInt(data[k])-1; 
                    }
                }
                
                }
                i++;
            }
        System.out.println(invitationText);
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getPlace() {
        return place;
    }


    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }
    
}

