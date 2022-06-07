/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import utils.FileTools;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Class that stores and creates appointments
 * @author Uživatel
 */
interface AppointmentInterface{
     String person = "Člověk";
     String place = "místo";
     String adress = "Praha 1";
     int day = 30;
     int month = 11;
     int hour = 12;
     boolean isTest = false;
}
enum Gender{
    M,F;
}
public class Appointment implements AppointmentInterface{
    String person;
    String place;
    String adress;
    int day;
    int month;
    int hour;
    boolean isTest;
    String gender;
/**
 * 
 * @param person
 * @param place
     * @param adress
 * @param day
 * @param month
 * @param hour
 * @param isTest
 * @param gender 
 */
    public Appointment(String person, String place, String adress, int day, int month, int hour, boolean isTest, String gender) {
        this.person = person;
        this.place = place;
        this.adress = adress;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.isTest = isTest;
        this.gender = gender;
    }
    /**
     * Creates Invitation in txt,pdf and as a output
     * @throws IOException
     * @throws DocumentException 
     */
    public void createInvite() throws IOException, DocumentException {
        File ftxt = new File("data/Invitations/" + getPerson() + "pozvanka.csv");
        File fpdf = new File("data/Invitations/" + getPerson() + "pozvanka.pdf");
        if (!ftxt.exists()) {
            ftxt.createNewFile();
        }
        String testocko = (isIsTest()) ? ("testování") : ("očkování");
        String testockoadj = (isIsTest()) ? ("testovacího") : ("očkovacího");
        String g;
        if (getGender().equals(Gender.F.toString())){
            g = "Vážená Paní ";
        } else {
            g = "Vážený Pane ";
        }
        LocalDate lde = LocalDate.of(2022,getMonth(), getDay());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M");
        int plusone = getHour() + 1;
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtfl = DateTimeFormatter.ofPattern("d.M.yyyy");
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        sb1.append(g);
        sb1.append(getPerson());
        sb1.append(","); 
        sb2.append("toto je vaše pozvánka na ");
        sb2.append(testocko);
        sb2.append(" proti onemocnění COVID-19.");
        sb3.append("Na ");
        sb3.append(testocko);
        sb3.append(" se dostavte ");
        sb3.append(lde.format(dtf));
        sb3.append(". mezi ");
        sb3.append(getHour());
        sb3.append(":00 a ");
        sb3.append(plusone);
        sb3.append(":00 do ");
        sb3.append(testockoadj);
        sb3.append(" zařízení ");
        sb3.append(getPlace());
        sb3.append(", ");
        sb3.append(getAdress());
        sb3.append(".");
        sb4.append(" Těšíme se na vás.\n Ministerstvo zdravotnictví.");
        sb4.append(" Dne ");
        sb4.append(ld.format(dtfl));
        String fullText= sb1.toString() + "\n" + sb2.toString() + "\n" + sb3.toString() + "\n" + sb4.toString();
        FileTools.writeToFile(ftxt, fullText);
        System.out.println(fullText);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fpdf));
        File fontFile = new File("data/times new roman.ttf");
        document.open();
        BaseFont unicode = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fonti = new Font(unicode, 12);
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(helvetica, 12);
        document.add(new Paragraph(sb1.toString(),font));
        document.add(new Paragraph(sb2.toString(),font));
        document.add(new Paragraph(sb3.toString(),font));
        document.add(new Paragraph(sb4.toString(),font));
        document.close();
    }
/**
 * 
 * @param person 
 */
    public void setPerson(String person) {
        this.person = person;
    }
/**
 * 
 * @param month 
 */
    public void setMonth(int month) {
        this.month = month;
    }

    public String getAdress() {
        return adress;
    }
    
/**
 * 
 * @param isTest 
 */
    public void setIsTest(boolean isTest) {
        this.isTest = isTest;
    }
/**
 * 
 * @param gender 
 */
    public void setGender(String gender) {
        this.gender = gender;
    }
/**
 * 
 * @return 
 */
    public String getPerson() {
        return person;
    }

    public int getMonth() {
        return month;
    }
/**
 * 
 * @return 
 */
    public boolean isIsTest() {
        return isTest;
    }
/**
 * 
 * @return 
 */
    public String getGender() {
        return gender;
    }
/**
 * 
 * @param place 
 */
    public void setPlace(String place) {
        this.place = place;
    }
/**
 * 
 * @param day 
 */
    public void setDay(int day) {
        this.day = day;
    }
/**
 * 
 * @param hour 
 */
    public void setHour(int hour) {
        this.hour = hour;
    }
/**
 * 
 * @return 
 */
    public String getPlace() {
        return place;
    }
/**
 * 
 * @return 
 */
    public int getDay() {
        return day;
    }
/**
 * 
 * @return 
 */
    public int getHour() {
        return hour;
    }


}
