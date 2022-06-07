/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Uživatel
 */
public class Place implements Comparable<Place>{
    String number;
    String name;
    String adress;
    String full;
    /**
     * 
     * @param number
     * @param name 
     * @param full 
     */
    public Place(String number, String name,String adress, String full) {
         this.number=number;
         this.name=name;
         this.adress=adress;
         this.full=full;
    }
/**
 * 
 * @return 
 */
    public String getNumber() {
        return number;
    }
/**
 * 
 * @return 
 */
    public String getFull() {
        return full;
    }
/**
 * 
 * @param full 
 */
    public void setFull(String full) {
        this.full = full;
    }

    public String getAdress() {
        return adress;
    }
    
/**
 * 
 * @return 
 */
    public String getName() {
        return name;
   }
/**
 * 
 * @param number 
 */
    public void setNumber(String number) {
        this.number = number;
    }
/**
 * 
 * @param name 
 */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Reads the list of places from csv file and returns them in a list
     * @param fileName
     * @return 
     */
    public static List<Place> readPlacesFromCSV (String fileName) {
        List<Place> places = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Place place = createPlace(attributes);
                places.add(place);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return places;
    }
/**
 * creates one instance of place with its number and place name 
 * @param metadata
 * @return 
 */
    private static Place createPlace(String[] metadata) {
        String number = metadata[0];
        String place = metadata[1];
        String adress = metadata[2];
        String full = metadata[3];
        return new Place(number, place,adress, full);
    }
        
public static void main(String... args) {
        List<Place> places = readPlacesFromCSV("data/PlacesTest.csv");
        for (Place p : places) {
            System.out.println(p.getName());
        }
    }

    @Override
    public int compareTo(Place o) {
        int thisnum = Integer.parseInt(this.number);
        int onum = Integer.parseInt(o.number);
        return onum - thisnum;

    }


}
