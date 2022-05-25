/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
import app.Appointment;
import app.Registration;
import useless.Place;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FileTools;
/**
 *
 * @author Uživatel
 */
public class UI {
    static Scanner sc = new Scanner(System.in);
    public static void menu() throws IOException{
        String login="";
        try {
             
              File obj = new File("Registration.txt");
               
              if (obj.createNewFile()) 
              {
                System.out.println("File is created");
              } 
               
            } 
            catch (IOException e) 
            {
              System.out.println("An error occurred.");
              e.printStackTrace();
               
            }
         
        int choice;
        Scanner sc=new Scanner(System.in);
        System.out.println("1. Registration. ");
        System.out.println("2. Login. ");
         
        System.out.println("Enter your Choice");
        choice=sc.nextInt();
        sc.nextLine();
         
        switch (choice) {
            case 1:
                {
                    Registration user = new Registration();
                    try {
                        user.register();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }       break;
                }
            case 2:
                {
                    Registration user = new Registration();
                    login=user.login();
                    break;
                }
            default:
                System.out.println("Choose Proper Option");
                break;
        }
     
        boolean end = false;
        
        do {
            displayMenu();
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    menuOckovani(login);
                    break;
                case 2:
                    menuTestovani(login);
                    break;
                case 3:
                    menuZkontrolovat(login);
                    break;
                case 0:
                    end = true;
                    break;
                default:
                    System.out.println("Neplatna volba.");
            }
        } while (!end);
    }
     private static void displayMenu() {
        System.out.println("--------------MENU-------------");
        System.out.println("  1:Ockovani                   ");
        System.out.println("  2:Testovani                  ");
        System.out.println("  3:Zkotrolovat profil         ");
        System.out.println("  0:Konec                      ");
        System.out.println("--------------MENU-------------");
    }
    public static void menuOckovani(String login) throws IOException {
        FileTools.createPlaceFiles(new File("data/PlacesVac.csv"), new File("data/VacTimeTables/"));
        System.out.println("Ve kterém městě se chcete nechat očkovat?(Zadejte presny nazev ze seznamu)");
        String place = sc.nextLine();
        System.out.println("Za kolik dní nechat očkovat? (den mesic)");
        int day = sc.nextInt();
        //int month = sc.nextInt();
        System.out.println("Kterou hodinu?");
        int hour = sc.nextInt();
        String name = Registration.getLoginInfo(login).get(3);
        Appointment apo = new Appointment(name,place,day,hour,false);
        Appointment.createInvite(apo);
    }
     public static void menuTestovani(String login) throws IOException{           
        FileTools.createPlaceFiles(new File("data/PlacesTest.csv"), new File("data/TestTimeTables/"));
        System.out.println("Ve kterém městě se chcete nechat testovat?(Zadejte presny nazev ze seznamu)");
        String place = sc.nextLine();
        System.out.println("Za kolik dní se chcete nechat testovat?");
        int day = sc.nextInt();
        //int month = sc.nextInt();
        System.out.println("Kterou hodinu?");
        int hour = sc.nextInt();
        String name = Registration.getLoginInfo(login).get(3);
        Appointment apo = new Appointment(name,place,day,hour,true);
        Appointment.createInvite(apo);
         
    }
    
    public static void menuZkontrolovat(String login) throws IOException{
            
        System.out.println("Uživatelské jméno: "+Registration.getLoginInfo(login).get(0));
        System.out.println("Heslo: "+Registration.getLoginInfo(login).get(1));
        System.out.println("Jméno: "+Registration.getLoginInfo(login).get(2));
        System.out.println("Příjmení: "+Registration.getLoginInfo(login).get(3));
        System.out.println("Email: "+Registration.getLoginInfo(login).get(4));
        System.out.println("Rodné číslo: "+Registration.getLoginInfo(login).get(5));
        System.out.println("Očkován: "+Registration.getLoginInfo(login).get(6));
        
    }

    
}
