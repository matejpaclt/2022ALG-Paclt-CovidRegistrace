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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import utils.FileTools;

/**
 * Class that stores and creates appointments
 * @author Uživatel
 */
interface AppointmentInterface{
     int day = 30;
     int hour = 12;
}
enum Gender{
    M,F;
}
/**
 * Class that creates apointment from data
 * @author Uživatel
 */
public class Appointment implements AppointmentInterface{
    Person person;
    Place place;
    LocalDate date;
    int hour;
/**
 * 
 * @param person
 * @param place
 * @param date
 * @param hour 
 */
    public Appointment(Person person, Place place, LocalDate date, int hour) {
        this.person = person;
        this.place = place;
        this.date = date;
        this.hour = hour;
    }
    /**
     * Creates Invitation in txt,pdf and as a output
     * @throws IOException
     * @throws DocumentException 
     */
    public void createInvite() throws IOException, DocumentException {
        File ftxt = new File("data/Invitations/" + getPerson().getName() + "pozvanka.csv");
        File fpdf = new File("data/Invitations/" + getPerson().getName() + "pozvanka.pdf");
        if (!ftxt.exists()) {
            ftxt.createNewFile();
        }
        String g;
        if (getPerson().getGender().equals(Gender.F.toString())){
            g = "Vážená Paní ";
        } else {
            g = "Vážený Pane ";
        }
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M");
        int plusone = getHour() + 1;
        LocalDate today = LocalDate.now();
        LocalDate apoday = getDate();
        DateTimeFormatter dtfl = DateTimeFormatter.ofPattern("d.M.yyyy");
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        sb1.append(g);
        sb1.append(getPerson().getName());
        sb1.append(","); 
        sb2.append("toto je vaše pozvánka na očkování proti onemocnění COVID-19.");
        sb3.append("Na očkování se dostavte ");
        sb3.append(apoday.format(dtf));
        sb3.append(". mezi ");
        sb3.append(getHour());
        sb3.append(":00 a ");
        sb3.append(plusone);
        sb3.append(":00 do očkovacího zařízení ");
        sb3.append(getPlace().getName());
        sb3.append(", ");
        sb3.append(getPlace().getAdress());
        sb3.append(".");
        sb4.append(" Těšíme se na vás.\n Ministerstvo zdravotnictví.");
        sb4.append(" Dne ");
        sb4.append(today.format(dtfl));
        String fullText= sb1.toString() + "\n" + sb2.toString() + "\n" + sb3.toString() + "\n" + sb4.toString();
        FileTools.writeToFile(ftxt, fullText);
        System.out.println(fullText);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fpdf));
        document.open();
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font font = new Font(helvetica, 12);
        document.add(new Paragraph(sb1.toString(),font));
        document.add(new Paragraph(sb2.toString(),font));
        document.add(new Paragraph(sb3.toString(),font));
        document.add(new Paragraph(sb4.toString(),font));
        document.close();
    }
    public void createAppointment(){
        DateTimeFormatter dtfl = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateform = getDate().format(dtfl);
        try {
                    BufferedWriter out = new BufferedWriter(new FileWriter("data/Appointments.csv", true));
                    out.write(getPerson().uname + "," + getPerson().name + "," + dateform + "," + getHour() + ":00-" + (getHour()+1) + ":00," + getPlace().name + "," + getPlace().adress + "\n");
                    out.close();
                } catch (IOException e) {
                    System.out.println("Vyskytla se vyjímka." + e);
                }
    }
/**
 * 
 * @param person 
 */
    public void setPerson(Person person) {
        this.person = person;
    }
/**
 * 
 * @return 
 */
    public Person getPerson() {
        return person;
    }
/**
 * 
 * @param place 
 */
    public void setPlace(Place place) {
        this.place = place;
    }
/**
 * 
 * @param date 
 */
    public void setDate(LocalDate date) {
        this.date = date;
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
    public Place getPlace() {
        return place;
    }
/**
 * 
 * @return 
 */
    public LocalDate getDate() {
        return date;
    }
/**
 * 
 * @return 
 */
    public int getHour() {
        return hour;
    }


}
