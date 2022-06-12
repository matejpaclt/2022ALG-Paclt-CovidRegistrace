/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.Appointment;
import app.Person;
import app.Registration;
import com.itextpdf.text.DocumentException;
import app.Place;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.NullPointerException;
import java.util.InputMismatchException;

/**
 * User interface class, user interacts with program
 *
 * @author Uživatel
 */
public class UI {

    static Scanner sc = new Scanner(System.in);

    /**
     * Menu, user can login, register and then choose one of the apps functions.
     */
    public static void menu() {
        Person login = null;
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
        do {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Zadejte číslo.");
                choice = 4;
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
                    choice = 4;
                    break;
            }
        } while (choice == 4);
        end = false;
        do {
            try {
                displayMenu();
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Zadejte číslo.");
                choice = 4;
                sc.nextLine();
            }
            try {
                switch (choice) {
                    case 1:
                        menuOckovani(login);
                        break;
                    case 2:
                        menuTerminy(login);
                        break;
                    case 3:
                        menuZkontrolovat(login);
                        break;
                    case 0:
                        end = true;
                        break;
                    default:
                        System.out.println("Neplatna volba.");
                        choice = 4;
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                end = true;
            }
        } while (choice == 4 || !end);

    }

    /**
     * Method that displays Menu
     */
    private static void displayMenu() {
        System.out.println("--------------MENU-------------");
        System.out.println("  1:Zapsat termin na ockovani  ");
        System.out.println("  2:Zkontrolovat Vaše termíny  ");
        System.out.println("  3:Zkotrolovat profil         ");
        System.out.println("  0:Konec                      ");
        System.out.println("--------------MENU-------------");
    }

    /**
     * User fills up info for vaccination visit.
     *
     * @param login
     */
    public static void menuOckovani(Person login) {
        try {
            LocalDate date1 = LocalDate.now();
            System.out.println("Zadejte datum očkování.(den mesic rok)");
            boolean b = false;
            boolean past = false;
            do {
                try {
                    System.out.println("Den:");
                    int day = sc.nextInt();
                    System.out.println("Mesic:");
                    int month = sc.nextInt();
                    System.out.println("Rok:");
                    int year = sc.nextInt();
                    date1 = LocalDate.of(year, month, day);
                    past = LocalDate.now().isAfter(date1);
                    if (past) {
                        System.out.println("Toto datum jiz probehlo.");
                    }
                    b = false;
                } catch (DateTimeException dte) {
                    System.out.println("Zadejte platne datum.");
                    sc.nextLine();
                    b = true;
                } catch (InputMismatchException ime) {
                    System.out.println("Zadejte cislo prosim.");
                    sc.nextLine();
                    b = true;
                }
            } while (b || past);
            utils.FileTools.createDate(date1);
            List<Place> places = utils.FileTools.readPlacesFromCSV("data/PlacesVac.csv");
            List<List<Integer>> availableAmount = utils.FileTools.readAvailableAmountFromCSV(date1);
            Collections.sort(places);
            int l = 0;
            for (Place p : places) {
                int daily = 0;
                for (int i = 0; i < availableAmount.get(l).size(); i++) {
                    daily += availableAmount.get(l).get(i);
                }
                p.setAvailableAmount(daily);
                System.out.println(p.getNumber() + p.getName() + " [" + p.getAvailableAmount() + "] ");
                l++;
            }
            try {
                System.out.println("Zadejte číslo zařízení ve kterém se chcete nechat očkovat.");
                int number = 0;
                do {
                    b=false;
                    try {
                        number = sc.nextInt() - 1;
                        if (number < 0 || number > 143) {
                            System.out.println("Vyberte platné číslo.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Zadejte číslo.");
                        sc.nextLine();
                        b = true;
                    }
                } while (number < 0 || number > 143 || b);
                System.out.println("Obsazenost časů [počet míst]: ");
                int k = 6;
                for (Integer a : availableAmount.get(number)) {
                    System.out.println(k + ":00 [" + a + "] ");
                    k++;
                }
                System.out.println("Kterou hodinu? (Otevírací hodiny 6-18h)");
                int hour = 6;
                boolean full = true;
                do {
                    b=false;
                    try {
                        hour = sc.nextInt();
                        if (hour < 6 || hour >= 18) {
                            System.out.println("Vyberte platnou hodinu");
                        }
                        full = utils.FileTools.fillOneSpace(date1, number, hour);
                        if (full) {
                            System.out.println("Vyberte čas s volnými místy.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Zadejte číslo.");
                        sc.nextLine();
                        b = true;
                    }
                } while (hour < 6 || hour >= 18 || full || b);
                Appointment apo = new Appointment(login, places.get(number), date1, hour);
                apo.createInvite();
                apo.createAppointment();
            } catch (DocumentException e) {
                System.out.println("Špatně zadáno. ");
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Soubor PlacesVac.csv nenalezen.");
        }

    }

    public static void menuTerminy(Person login) {
        try {
            File f = new File("data/Appointments.csv");
            Scanner content = new Scanner(f);
            int flag = 0;
            while (content.hasNextLine()) {
                String line = content.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(login.getUname())) {
                    System.out.println(data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5]);
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("Nemáte žádné termíny.");
            }
        } catch (FileNotFoundException e) {

            System.out.println("Chyba.");
            e.printStackTrace();
        }

    }

    /**
     * User fills up info for Covid-testing visit.
     *
     * @param login
     * @throws IOException
     */
    /**
     * Menu where user can check their login info.
     *
     * @param login
     */
    public static void menuZkontrolovat(Person login) {

        System.out.println("Uživatelské jméno: " + login.getUname());
        System.out.println("Heslo: " + login.getPass());
        System.out.println("Jméno: " + login.getName());
        System.out.println("Příjmení: " + login.getSurName());
        System.out.println("Pohlaví: " + login.getGender());
        System.out.println("Email: " + login.getEmail());
        System.out.println("Rodné číslo: " + login.getPid());

    }

}
