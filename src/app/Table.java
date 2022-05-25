/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.*;
/**
 *
 * @author Uživatel
 */
public class Table {
  ArrayList<ArrayList<Integer>> table;
       public static ArrayList<ArrayList<Integer>> createTable(){
       ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
       ArrayList<Integer> first = new ArrayList<>();
       for(int j=0;j<12;j++){
               first.add(j+8);
           }
       lists.add(first);
       for(int i=0;i<31;i++){
           ArrayList<Integer> a = new ArrayList<>();
           a.add(i+1);
           for(int j=0;j<12;j++){
               a.add(20);
           }
           lists.add(a);
       }
       
       return lists;
   } 
   
    public void setTable(ArrayList<ArrayList<Integer>> table) {
        this.table = table;
    }
    
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> lists =createTable();
        for(int i=0;i<31;i++){
           for(int j=0;j<11;j++){
             System.out.print(lists.get(i).get(j)+", ");
           }
           System.out.println(lists.get(i).get(11));
       }
     
    }      
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*public void addToInnerArray(int index, T element) {
        while (index >= this.size()) {
            this.add(new ArrayList<T>());
        }
        this.get(index).add(element);
    }

    public void addToInnerArray(int index, int index2, T element) {
        while (index >= this.size()) {
            this.add(new ArrayList<T>());
        }

        ArrayList<T> inner = this.get(index);
        while (index2 >= inner.size()) {
            inner.add(null);
        }

        inner.set(index2, element);
    }*/
