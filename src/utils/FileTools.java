/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import useless.Place;
import app.Table;
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
    public static void createPlaceFiles(File file, File dir){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                File f = new File(dir+"/"+data[0]+".csv");
                if(!f.exists()){
                        f.createNewFile();
                    }
                System.out.println(data[0]);
            }
            File d=new File(dir+"/");
            tablesToAllFiles(dir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void insertTable(File file,ArrayList<ArrayList<Integer>> table) throws IOException{
            PrintWriter printWriter = new PrintWriter(new FileWriter(file));
            for(int i=0;i<30;i++){
                    for(int j=0;j<11;j++){
                        printWriter.print(table.get(i).get(j) + ", ");
                    }
                    printWriter.println(table.get(i).get(11));
                }
                printWriter.close();
    }
    
    public static void tablesToAllFiles(File dir) throws IOException{
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                insertTable(file,Table.createTable());
            }
        }
    }
    public static void changeOneRow(String place,ArrayList<Integer> row,int day,int hour){
        
    }
    private static Scanner x;
    public static void main(String[] args){
    }
    public static void editFile(String filepath,String editTerm,String newID,String newName,String newAge){
        String tempFile = "data/temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String ID = ""; String name = "";String age="";
      try{
        FileWriter fw = new FileWriter(tempFile,true);
       BufferedWriter bw =new BufferedWriter(fw);
       PrintWriter pw = new PrintWriter(bw);
       x= new Scanner(new File(filepath));
       x.useDelimiter("[,\n]");
       while(x.hasNext()){
           ID = x.next();
           name = x.next();
           age = x.next();
           if(ID.equals(editTerm)){
               pw.println(newID+","+newName+","+newAge);
           }else{
            pw.println(ID+","+name+","+age);   
           }
       }
       x.close();
       pw.flush();
       pw.close();
       oldFile.delete();
       File dump = new File(filepath);
       newFile.renameTo(dump);
      }catch(Exception e){
          e.printStackTrace();
      }
    }
    
    /*
    public static void placesFileRead(File file1,File file2){
        try {
            //File f = new File(file1);
            if(!file1.exists()){
                file1.createNewFile();
            }  
            BufferedReader br = new BufferedReader(new FileReader(file2));
            FileWriter fileWriter = new FileWriter(file1);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String line;
             while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String place = data[1];
                place=place.trim();
                if(place.equals("misto")){  
                }else{
                    printWriter.print(place+","+data[data.length-2]+"\n");
                }
             }

             
                BufferedWriter outg = new BufferedWriter(new FileWriter(g));
                for(int i=1;i<31;i++){
                    Calendar cal = new GregorianCalendar();
                    SimpleDateFormat sdfD = new SimpleDateFormat("d/MM");
                    cal.add(Calendar.DAY_OF_MONTH, i);
                    String date = sdfD.format(cal.getTime());
                    outg.write(date+"\n");
                
                }
        
              
           printWriter.close(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
}

