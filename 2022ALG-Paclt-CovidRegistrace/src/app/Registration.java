package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Class that handles user registration, login and access to login information
 * @author Uživatel
 */
interface RegistrationInterface{
     String register() throws FileNotFoundException;
     String login();
}
public class Registration implements RegistrationInterface{
/**
 * Method used to register the user, returns users login
 * @return
 * @throws FileNotFoundException 
 */
    @Override
    public String register() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String login = "";
        System.out.println("Zadejte uživatelské jméno: ");
        String uname = sc.nextLine();

        System.out.println("Zadejte heslo: ");
        String pass = sc.nextLine();

        System.out.println("Potvrďte heslo: ");
        String conPass = sc.nextLine();

        System.out.println("Zadejte jméno: ");
        String name = sc.nextLine();

        System.out.println("Zadejte příjmení: ");
        String surName = sc.nextLine();
        
        System.out.println("Zadejte pohlaví: (M/F) ");
        String gender = sc.nextLine();
        
        System.out.println("Zadejte email: ");
        String email = sc.nextLine();
        System.out.println("Zadejte rodné číslo: ");
        int pid = sc.nextInt();
        sc.nextLine();
        System.out.println("Byli jste již očkováni?(a/n) ");
        boolean vac = Character.toLowerCase(sc.nextLine().charAt(0)) != 'n';
        System.out.println((vac) ? ("ano") : ("ne"));

        name = name.trim();
        surName = surName.trim();
        gender = gender.trim().toUpperCase();
        email = email.trim();
        uname = uname.trim();
        pass = pass.trim();
        conPass = conPass.trim();

        String x = uname + " " + pass;
        if (pass.equals(conPass) || email.contains("@") || gender.equals("M") || gender.equals("F") ) {

            File f = new File("data/Registration.txt");
            Scanner content = new Scanner(f);

            int flag = 0;
            while (content.hasNextLine()) {
                String data = content.nextLine();
                if (data.equals(x)) {
                    System.out.println("Už zaregistrován");
                    flag = 1;
                    System.out.println("1. Registrace. ");
                    System.out.println("2. Přihlášení. ");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        this.register();
                    } else if (choice == 2) {
                        login = this.login();
                    } else {
                        System.out.println("Vyberte platnou možnost.");
                    }
                    break;
                }

            }
            if (flag == 0) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter("data/Registration.txt", true));
                    out.write(uname + "," + pass + "," + name + "," + surName + "," + gender + "," + email + "," + pid + "," + String.valueOf(vac) + "\n");
                    out.close();
                } catch (IOException e) {
                    System.out.println("Vyskytla se vyjímka." + e);
                }

                System.out.println("Úspěšně zaregistrováni.");
                System.out.println("Prosím přihlašte se.");
                login = this.login();
            }

        } else {
            System.out.println("Zkotrolujte zdali jste zadali všechny údaje správně.");
            System.out.println("1. Registrace. ");
            System.out.println("2. Přihlášení. ");
            int choice = sc.nextInt();
            if (choice == 1) {
                this.register();
            } else if (choice == 2) {
                login = this.login();
            } else {
                System.out.println("Vyberte platnou možnost.");
            }
        }
        return login;
    }
/**
 * Login method, returns username
 * @return 
 */
    @Override
    public String login() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte uživatelské jméno: ");
        String uname = sc.nextLine();
        System.out.println("Zadejte heslo: ");
        String pass = sc.nextLine();
        uname = uname.trim();
        pass = pass.trim();
        String x = uname.concat(pass);

        try {

            File f = new File("data/Registration.txt");
            Scanner content = new Scanner(f);
            int flag = 0;
            while (content.hasNextLine()) {
                String line = content.nextLine();
                String[] data = line.split(",");
                String unamepass = data[0].concat(data[1]);
                if (unamepass.equals(x)) {
                    System.out.println("Přihlášení úspěšné");
                    System.out.println("Vítejte v systému CovidRegistrace");
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("Neúspěšné přihlášení");
                System.out.println("1. Registrace. ");
                System.out.println("2. Přihlášení. ");
                int choice = sc.nextInt();
                if (choice == 1) {
                    this.register();
                } else if (choice == 2) {
                    this.login();
                } else {
                    System.out.println("Vyberte platnou možnost.");
                }
            }

        } catch (FileNotFoundException e) {

            System.out.println("Chyba.");
            e.printStackTrace();
        }

        return uname;
    }
/**
 * Method used to get more info about the user
 * @param uname
 * @return
 * @throws FileNotFoundException
 * @throws IOException 
 */
    public static ArrayList<String> getLoginInfo(String uname) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/Registration.txt"));
        String line;
        ArrayList<String> arr = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(uname)) {
                arr.addAll(Arrays.asList(data));
                return arr;
            }

        }
        return arr;
    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}
