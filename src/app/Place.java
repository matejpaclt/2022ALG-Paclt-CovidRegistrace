/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Class that stores number, name and adress of a place
 * @author Uživatel
 */
public class Place implements Comparable<Place>{
    String number;
    String name;
    String adress;
    String full;
    /**
     * Place constructor
     * @param number
     * @param name 
     * @param adress 
     */
    public Place(String number, String name, String adress, String full) {
         this.number=number;
         this.name=name;
         this.adress=adress;
         this.full=full;
    }
/**
 * Number getter
 * @return 
 */
    public String getNumber() {
        return number;
    }

    public String getFull() {
        return full;
    }


/**
 * Adress getter
 * @return 
 */
    public String getAdress() {
        return adress;
    }
    
/**
 * Name getter
 * @return 
 */
    public String getName() {
        return name;
   }
/**
 * Number setter
 * @param number 
 */
    public void setNumber(String number) {
        this.number = number;
    }
/**
 * Name setter
 * @param name 
 */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Reads lines from csv file and returns them in a list
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
        return new Place(number, place,adress,full);
    }
        
public static void main(String... args) {
        List<Place> places = readPlacesFromCSV("data/PlacesVac.csv");
        Collections.sort(places);
        for (Place p : places) {
            System.out.println(p.getNumber() + p.getName());
        }
    }
/**
 * CompareTo method, descending order sorting
 * @param o
 * @return 
 */
    @Override
    public int compareTo(Place o) {
        int thisnum = Integer.parseInt(this.number);
        int onum = Integer.parseInt(o.number);
        return thisnum - onum;

    }


}
