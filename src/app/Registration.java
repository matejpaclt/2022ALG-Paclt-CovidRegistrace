package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
 
public class Registration {
     
    public void register() throws FileNotFoundException
    {
        Scanner sc=new Scanner(System.in);
         
        System.out.println("Enter User Name: ");
        String Uname=sc.nextLine();
        System.out.println(Uname);
         
        System.out.println("Enter Password: ");
        String Pass=sc.nextLine();
        System.out.println(Pass);
         
        System.out.println("Confirm Password: ");
        String ConPass=sc.nextLine();
        System.out.println(ConPass);
        
        System.out.println("Zadejte jméno: ");
        String Name=sc.nextLine();
        System.out.println(Name);
        
        System.out.println("Zadejte příjmení: ");
        String SurName=sc.nextLine();
        System.out.println(SurName);
        
        System.out.println("Zadejte email: ");
        String Email=sc.nextLine();
        System.out.println(Email);
        System.out.println("Zadejte rodné číslo: ");
        int pid=sc.nextInt();
        System.out.println(pid);
        sc.nextLine();
        System.out.println("Byli jste již očkováni?(a/n) ");
        boolean vac=Character.toLowerCase(sc.nextLine().charAt(0)) != 'n';
        System.out.println((vac)?("ano"):("ne"));
        
        Name=Name.trim();
        SurName=SurName.trim();
        Email=Email.trim();
        Uname=Uname.trim();
        Pass=Pass.trim();
        ConPass=ConPass.trim();
         
         
         
 
        String x= Uname+" "+Pass;
        if(Pass.equals(ConPass))
        {
             
              File f = new File("Registration.txt");
              Scanner content = new Scanner(f);
               
               
              int flag=0;
              while (content.hasNextLine()) {
                String data = content.nextLine();
                if(data.equals(x))
                {
                    System.out.println("Already Registered");
                    flag=1;
                    System.out.println("1. Registration. ");
                    System.out.println("2. Login. ");
                     
                    System.out.println("Enter your Choice");
                    int choice=sc.nextInt();
                    if(choice==1)
                    {
                        this.register();
                    }
                    else if(choice==2)
                    {
                        this.login();
                    }
                    else
                    {
                        System.out.println("Choose Proper Option");
                    }
                    break;
                }
                
              }
                if(flag==0)
                {
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter("Registration.txt", true)); 
                        out.write(Uname+","+Pass+","+Name+","+SurName+","+Email+","+pid+","+String.valueOf(vac)+"\n");
                        out.close();
                    }
                    catch (IOException e) {
                        System.out.println("exception occoured" + e);
                    }
                     
                    System.out.println("Successfully Registered");
                    System.out.println("Please login");
                    this.login();
                }
             
             
        }
        else
        {
            System.out.println("Recheck");
            System.out.println("1. Registration. ");
            System.out.println("2. Login. ");
             
            System.out.println("Enter your Choice");
            int choice=sc.nextInt();
            if(choice==1)
            {
                this.register();
            }
            else if(choice==2)
            {
                this.login();
            }
            else
            {
                System.out.println("Choose Proper Option");
            }
        }
        
    }
     
    public void login()
    {
         
        Scanner sc=new Scanner(System.in);
         
        System.out.println("Enter User Name: ");
        String Uname=sc.nextLine();
        System.out.println(Uname);
         
        System.out.println("Enter Password: ");
        String Pass=sc.nextLine();
        System.out.println(Pass);
        Uname=Uname.trim();
        Pass=Pass.trim();
        String x= Uname.concat(Pass);
         
         
        try {
             
              File f = new File("Registration.txt");
              Scanner content = new Scanner(f);
              int flag=0;
              while (content.hasNextLine()) {
                String line = content.nextLine();
                String[] data = line.split(",");
                String unamepass=data[0].concat(data[1]);
                if(unamepass.equals(x))
                {
                    System.out.println("Login Successful");
                    System.out.println("Welcome to the Application.");
                    flag=1;
                    break;
                }
              }
                if(flag==0)
                {
                    System.out.println("Login Failed");
                    System.out.println("1. Registration. ");
                    System.out.println("2. Login. ");
                     
                    System.out.println("Enter your Choice");
                    int choice=sc.nextInt();
                    if(choice==1)
                    {
                        this.register();
                    }
                    else if(choice==2)
                    {
                        this.login();
                    }
                    else
                    {
                        System.out.println("Choose Proper Option");
                    }
                }
               
              
            } 
            catch (FileNotFoundException e) {
             
                 
              System.out.println("Error.");
              e.printStackTrace();
            }
         
        
    }
     
        
    public static void main(String[] args) throws FileNotFoundException{
     
        
     
    }
}
