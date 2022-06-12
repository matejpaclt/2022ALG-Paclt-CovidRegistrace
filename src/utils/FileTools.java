/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Place;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that handles scanning and writing to file
 *
 * @author Uživatel
 */
public class FileTools {

    /**
     * Method that writes string to a file
     *
     * @param file
     * @param string
     * @throws IOException
     */
    public static void writeToFile(File file, String string) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(string);
        }
    }

    /**
     * Method that scans file line after line
     *
     * @param file
     * @param string
     * @return
     * @throws IOException
     */
    public static String scanFile(File file, String string) throws IOException {
        Scanner scan = new Scanner(file);
        String fileContent = "";
        while (scan.hasNextLine()) {
            fileContent = fileContent.concat(scan.nextLine() + "\n");
        }
        return fileContent;
    }

    public static List<Place> readPlacesFromCSV(String fileName) {
        List<Place> places = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Place place = new Place(attributes[0], attributes[1], attributes[2]);
                places.add(place);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return places;
    }

    public static List<List<Integer>> readAvailableAmountFromCSV(LocalDate date) {
        DateTimeFormatter dtfl = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String datefile = "data/dates/" + date.format(dtfl) + ".csv";
        List<List<Integer>> availableAmount = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(datefile))) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                List<Integer> availablePlace = new ArrayList();
                for (int i = 0; i < attributes.length; i++) {
                    availablePlace.add(Integer.valueOf(attributes[i]));
                }
                availableAmount.add(availablePlace);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return availableAmount;
    }

    public static void createDate(LocalDate date) {
        DateTimeFormatter dtfl = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String datefile = "data/dates/" + date.format(dtfl) + ".csv";
        File f = new File(datefile);
        try {
            if (f.createNewFile()) {
                try (FileWriter fw = new FileWriter(f)) {
                    for (int i = 0; i < 143; i++) {
                        for (int j = 0; j < 12; j++) {
                            fw.write("5,");
                        }
                        fw.write("\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean fillOneSpace(LocalDate date, int num, int time) {
        DateTimeFormatter dtfl = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String datefile = "data/dates/" + date.format(dtfl) + ".csv";
        String tempFile = "data/dates/temp.csv";
        File oldFile = new File(datefile);
        File newFile = new File(tempFile);
        boolean full = false;
        time = time - 6;
        try {
            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            try (BufferedReader br = new BufferedReader(new FileReader(oldFile))) {
                String line = br.readLine();
                int i = 0;
                while (line != null) {
                    String[] attributes = line.split(",");
                    line = br.readLine();
                    if (i == num) {
                        for (int j = 0; j < 12; j++) {
                            if (j == time) {
                                String newfull = String.valueOf(Integer.parseInt(attributes[time]) - 1);
                                if (Integer.parseInt(newfull) >= 0) {
                                    pw.print(newfull + ",");
                                } else {
                                    String attribute = attributes[j];
                                    pw.print(attribute + ",");
                                    full = true;
                                }

                            } else {
                                String attribute = attributes[j];
                                pw.print(attribute + ",");
                            }
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            String attribute = attributes[j];
                            pw.print(attribute + ",");
                        }
                    }

                    pw.println();
                    i++;
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(datefile);
            newFile.renameTo(dump);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return full;
    }

    public static void main(String[] args) {
        //System.out.println(fillOneSpace("data/PlacesTest - kopie .csv", "1",6,1));
        //LocalDate today = LocalDate.now();
        //createDate(today);
    }

}
