/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Uživatel
 */
public class Person {

    String uname;
    String pass;
    String name;
    String surName;
    String gender;
    String email;
    int pid;

    public Person(String uname, String pass, String name, String surName, String gender, String email, int pid) {
        this.uname = uname;
        this.pass = pass;
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.email = email;
        this.pid = pid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public int getPid() {
        return pid;
    }

}
