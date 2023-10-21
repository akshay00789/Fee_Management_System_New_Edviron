package edviron01.Fee.Management.System.New.bean;

import java.sql.Date;

public class Student {


    private int school_id;
    private  int student_id;
    private String first_name;
    private String last_name;
    private String email;
    private  int phone;
    private int amount;
    private Date start_date;
    private Date due_date;
    private int freq_month;
    private int defaulter;

    public Student(int school_id, int student_id, String first_name, String last_name, String email, int phone, int amount, Date start_date, Date due_date, int freq_month,int defaulter) {
        this.school_id = school_id;
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.start_date = start_date;
        this.due_date = due_date;
        this.freq_month = freq_month;
        this.defaulter=defaulter;
    }

   public int getSchool_id(){
       return school_id;
    }

   public void setFirst_name(String first_name){
        this.first_name=first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getFreq_month() {
        return freq_month;
    }

    public void setFreq_month(int freq_month) {
        this.freq_month = freq_month;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getDefaulter() {
        return defaulter;
    }

    public void setDefaulter(int defaulter) {
        this.defaulter = defaulter;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
