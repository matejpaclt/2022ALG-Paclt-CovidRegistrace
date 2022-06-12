/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import static utils.FileTools.*;

/**
 * Class that stores number, name and adress of a place
 *
 * @author Uživatel
 */
public class Place implements Comparable<Place> {

    String number;
    String name;
    String adress;
    int availableAmount;

    /**
     * Place constructor
     *
     * @param number
     * @param name
     * @param adress
     */
    public Place(String number, String name, String adress) {
        this.number = number;
        this.name = name;
        this.adress = adress;
    }

    /**
     * Number getter
     *
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Adress getter
     *
     * @return
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Name getter
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    /**
     * Number setter
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Name setter
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * creates one instance of place with its number and place name
     *
     * @param args
     */
    public static void main(String... args) {
        List<Place> places = readPlacesFromCSV("data/PlacesVac.csv");
        Collections.sort(places);
        for (Place p : places) {
            System.out.println(p.getNumber() + p.getName());
        }
    }

    /**
     * CompareTo method, descending order sorting
     *
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
