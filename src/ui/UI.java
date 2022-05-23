/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
import app.Appointment;
import app.Registration;
import app.Place;
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
/**
 *
 * @author Uživatel
 */
public class UI {
    static Scanner sc = new Scanner(System.in);
    public static void menu(){
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
         
        if(choice==1)
        {
            Registration user = new Registration();
            try {
                user.register();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(choice==2)
        {
            Registration user = new Registration();
            user.login();
        }
        else
        {
            System.out.println("Choose Proper Option");
        }
     
        boolean end = false;
        
        do {
            displayMenu();
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    menuOckovani();
                    break;
                case 2:
                    menuTestovani();
                    break;
                case 3:
                    menuZkontrolovat();
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
        System.out.println("  3:Zkotrolovat stav           ");
        System.out.println("  0:Konec                      ");
        System.out.println("--------------MENU-------------");
    }
    public static void menuOckovani(){
        utils.FileTools.placesFileRead("data\\PlacesVac.csv","data\\prehled-ockovacich-mist.csv","data\\VacTimeTables\\");
       /* System.out.println("Ve kterém městě se chcete nechat očkovat?(Zadejte presny nazev ze seznamu)");
        String currentFirstPlace = sc.nextLine();
        System.out.println("Ve které městské části se chcete nechat očkovat? (Zadejte presny nazev ze seznamu)");
        String currentSecondPlace = sc.nextLine();
        System.out.println("Jaký datum se chcete nechat očkovat? (den mesic)");
        int day = sc.nextInt();
        int month = sc.nextInt();
        System.out.println("Kterou hodinu?");
        int hour = sc.nextInt();*/
    }
     public static void menuTestovani(){
        
         utils.FileTools.placesFileRead("data\\PlacesTest.csv","data\\prehled-odberovych-mist.csv","data\\TestTimeTables\\");
    }
    
    public static void menuZkontrolovat(){
       
    }

    private static void menuPrihlaseni() {
        
    }
    
}
