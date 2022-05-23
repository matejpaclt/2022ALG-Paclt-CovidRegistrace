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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uživatel
 */
public class FileTools {
    public static void writeToFile(File file, String string) throws IOException{
        FileWriter writer = new FileWriter(file);
        writer.write(string);
        writer.close();
    }
    public static String scanFile(File file, String string) throws IOException{
        Scanner scan = new Scanner(file);
        String fileContent="";
        while(scan.hasNextLine()){
        fileContent=fileContent.concat(scan.nextLine() + "\n");
        }
        return fileContent;
    }
    public static void placesFileRead(String file1,String file2, String dir){
        try {
            //String aktDir = System.getProperty("data.dir");
            File f = new File(file1);
            if(!f.exists()){
                f.createNewFile();
            }  
            BufferedReader br = new BufferedReader(new FileReader(file2));
            FileWriter fileWriter = new FileWriter(f);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String line;
            ArrayList<String> fileArray= new ArrayList<>();
             while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String place = data[1];
                place=place.trim();
                if(place.equals("misto")){  
                }else{
                    printWriter.print(place+";"+data[data.length-2]+"\n");
                   // System.out.println(place+"\n");
                
               
                String x = dir+place+".csv";
                fileArray.add(x);
                System.out.println(x);
                }
             }
                for(int i=0;i<fileArray.size();i++){
                    
                }
             
                /*BufferedWriter outg = new BufferedWriter(new FileWriter(g));
                for(int i=1;i<31;i++){
                    Calendar cal = new GregorianCalendar();
                    SimpleDateFormat sdfD = new SimpleDateFormat("d/MM");
                    cal.add(Calendar.DAY_OF_MONTH, i);
                    String date = sdfD.format(cal.getTime());
                    outg.write(date+"\n");
                
                }*/
        
              
           printWriter.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
