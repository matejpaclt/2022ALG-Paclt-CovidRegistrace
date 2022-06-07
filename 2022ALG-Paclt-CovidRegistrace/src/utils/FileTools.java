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
        FileWriter writer = new FileWriter(file);
        writer.write(string);
        writer.close();
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

    /*public static boolean fillOneSpace(String filepath, String editTerm, int time, int date) {
        String tempFile = "data/temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        boolean full = false;
        time=((date)*12)+(time-6+3);
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
                String line = br.readLine();
                while (line != null) {
                    String[] attributes = line.split(",");
                    line = br.readLine();
                    String newfull = String.valueOf(Integer.parseInt(attributes[time]) - 1);
                    if (attributes[0].equals(editTerm)) {
                        if (Integer.parseInt(attributes[time]) - 1 < 0) {
                            full = true;
                            for (int i = 0; i < attributes.length; i++) {
                                String attribute = attributes[i];
                                pw.print(attribute + ",");
                            }
                            pw.println();
                        } else {
                            for (int i = 0; i < attributes.length; i++) {
                                if(!(i==time)){
                                String attribute = attributes[i];
                                pw.print(attribute + ",");
                                }else {
                                    pw.print(newfull + ",");
                                }
                            }
                            pw.println();
                        }
                    } else {
                        for (int i = 0; i < attributes.length; i++) {
                                String attribute = attributes[i];
                                pw.print(attribute + ",");
                            }
                            pw.println();
                    }
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return full;
    }*/

    public static void main(String[] args) {
        //System.out.println(fillOneSpace("data/PlacesTest.csv", "1",6,1));
    }
}
