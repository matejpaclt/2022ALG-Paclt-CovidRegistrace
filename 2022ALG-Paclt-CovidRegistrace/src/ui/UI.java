/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.Appointment;
import app.Registration;
import com.itextpdf.text.DocumentException;
import utils.Place;
import static utils.Place.readPlacesFromCSV;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FileTools;

/**
 * User interface class, user interacts with program here
 * @author Uživatel
 */
public class UI {

    static Scanner sc = new Scanner(System.in);
/**
 * Menu, user can login, register and then choose one of the apps functions.
 * @throws IOException 
 */
    public static void menu() throws IOException {
        String login = "";
        try {

            File obj = new File("Registration.txt");

            if (obj.createNewFile()) {
                System.out.println("Soubor byl vytvořen.");
            }

        } catch (IOException e) {
            System.out.println("Nastala chyba.");
            e.printStackTrace();

        }

        int choice = 4;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Registrace. ");
        System.out.println("2. Přihlášení. ");
        System.out.println("0. Konec. ");
        boolean end;
        do{
        try{
        choice = sc.nextInt();
        }catch(Exception e){
            System.out.println("Zadejte číslo.");
            choice=4;
        }
        sc.nextLine();
        switch (choice) {
            case 1: {
                Registration user = new Registration();
                try {
                    user.register();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case 2: {
                Registration user = new Registration();
                login = user.login();
                break;
            }
            case 0: {
                    return;
            }
            default:
                System.out.println("Zvolte platnou možnost.");
                choice=4;    
                break;
        }
        }while(choice==4);                
        end = false;
        do {
            try{
            displayMenu();
            choice = sc.nextInt();
            }catch(Exception e){
            System.out.println("Zadejte číslo.");
            choice=4;
            sc.nextLine();
            }
            switch (choice) {
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
                    end=true;
                    break;
                default:
                    System.out.println("Neplatna volba.");
                    choice=4;
                    break;
            }
        } while (choice == 4 || !end);
                
    }
/**
 * Method that displays Menu
 */
    private static void displayMenu() {
        System.out.println("--------------MENU-------------");
        System.out.println("  1:Očkovaní                   ");
        System.out.println("  2:Testování                  ");
        System.out.println("  3:Zkotrolovat profil         ");
        System.out.println("  0:Konec                      ");
        System.out.println("--------------MENU-------------");
    }
/**
 * User fills up info for vaccination visit.
 * @param login
 * @throws IOException 
 */
    public static void menuOckovani(String login) throws IOException {
            List<Place> places = readPlacesFromCSV("data/PlacesVac.csv");
            Collections.sort(places);
            for (Place p : places) {
                System.out.println(p.getNumber() + p.getName());
            }
            boolean again = false;
            do{
            try {
            System.out.println("Zadejte číslo zařízení ve kterém se chcete nechat očkovat.");
            int number;
            do{
            if(again){sc.nextLine();}
            number = sc.nextInt() - 1;
            if (number<0 || number > 142){
                System.out.println("Vyberte platné číslo.");
            }
            }while(number<0 || number > 142);
            System.out.println("Zadejte datum kdy se chcete nechat očkovat.(den mesic)");
            int day;
            int month;
            do{
            day = sc.nextInt();
            month = sc.nextInt();
            if (month<1 || month>12 || day<0 || day>31){
                System.out.println("Vyberte platný datum.");
            }
            }while(month<1 || month>12 || day<0 || day>31);
            System.out.println("Kterou hodinu? (Otevírací hodiny 6-18h)");
            int hour;
            do{
            hour= sc.nextInt();
            if (hour<6 || hour>=18){
                System.out.println("Vyberte platnou hodinu");
            }
            }while(hour<6 || hour>=18);
            String name = Registration.getLoginInfo(login).get(3);
            Appointment apo = new Appointment(name, places.get(number).getName(),places.get(number).getAdress() , day, month, hour, false, Registration.getLoginInfo(login).get(4));
            apo.createInvite();
            
            again = false;
            } catch (DocumentException | IOException e) {
            System.out.println("Špatně zadáno. ");
            again = true;
        }
            }while(again);
        
    }
/**
 * User fills up info for Covid-testing visit.
 * @param login
 * @throws IOException 
 */
    public static void menuTestovani(String login) throws IOException {
            List<Place> places = readPlacesFromCSV("data/PlacesTest.csv");
            Collections.sort(places);
            for (Place p : places) {
                System.out.println(p.getNumber() + p.getName());
            }
            boolean again = false;
            do{
            try {
            System.out.println("Zadejte číslo zařízení ve kterém se chcete nechat testovat.");
            int number;
            do{
            if(again){sc.nextLine();}
            number = sc.nextInt() - 1;
            if (number<0 || number > 220){
                System.out.println("Vyberte platné číslo.");
            }
            }while(number<0 || number > 220);
            System.out.println("Zadejte datum kdy se chcete nechat testovat.(den mesic)");
            int day;
            int month;
            do{
            day = sc.nextInt();
            month = sc.nextInt();
            if (month<1 || month>12 || day<0 || day>31){
                System.out.println("Vyberte platný datum.");
            }
            }while(month<1 || month>12 || day<0 || day>31);
            System.out.println("Kterou hodinu? (Otevírací hodiny 6-18h)");
            int hour;
            do{
            hour= sc.nextInt();
            if (hour<6 || hour>=18){
                System.out.println("Vyberte platnou hodinu");
            }
            }while(hour<6 || hour>=18);
            String name = Registration.getLoginInfo(login).get(3);
            Appointment apo = new Appointment(name, places.get(number).getName(),places.get(number).getAdress(), day, month, hour, true, Registration.getLoginInfo(login).get(4));
            apo.createInvite();
            
            again = false;
            } catch (DocumentException | IOException e) {
            System.out.println("Špatně zadáno. ");
            again = true;
        }
            }while(again);

    }
/**
 * Menu where user can check their login info.
 * @param login
 * @throws IOException 
 */
    public static void menuZkontrolovat(String login) throws IOException {

        System.out.println("Uživatelské jméno: " + Registration.getLoginInfo(login).get(0));
        System.out.println("Heslo: " + Registration.getLoginInfo(login).get(1));
        System.out.println("Jméno: " + Registration.getLoginInfo(login).get(2));
        System.out.println("Příjmení: " +  Registration.getLoginInfo(login).get(3));
        System.out.println("Pohlaví: " + Registration.getLoginInfo(login).get(4));
        System.out.println("Email: " + Registration.getLoginInfo(login).get(5));
        System.out.println("Rodné číslo: " + Registration.getLoginInfo(login).get(6));
        System.out.println("Očkován: " + Registration.getLoginInfo(login).get(7));

    }

}
