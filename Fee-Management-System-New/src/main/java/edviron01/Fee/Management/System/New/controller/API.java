package edviron01.Fee.Management.System.New.controller;

import edviron01.Fee.Management.System.New.bean.Pay;
import edviron01.Fee.Management.System.New.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

@RestController
public class API {


    @Autowired
    JdbcTemplate jdbc;
    @GetMapping("schoolregistration")
    String schoolregistration(@RequestParam int school_id,String school_name,String adress,String email,int telephone){

        try( Connection cn = jdbc.getDataSource().getConnection()){

          String  query = "insert into schoolinfo values(?,?,?,?,?)";
          PreparedStatement stmt = cn.prepareStatement(query);
          stmt.setInt(1,school_id);
          stmt.setString(2,school_name);
            stmt.setString(3,adress);
            stmt.setString(4,email);
            stmt.setInt(5,telephone);
            stmt.executeUpdate();
            return "School registerd Succesfully...  WELCOME to  Edviron !!!";

        }catch(Exception e){
                return "Something went wrong";
        }

    }

    @PostMapping("student")
      String student(@RequestBody Student student){

        try(Connection cn = jdbc.getDataSource().getConnection()){

            String query="select * from schoolinfo where school_id=?";

            PreparedStatement stmt = cn.prepareStatement(query);
            stmt.setInt(1,student.getSchool_id());
            ResultSet rs= stmt.executeQuery();

            if(rs.next()){

                String query1= "insert into student(school_id,first_name,last_name,email,phone,amount,start_date,due_date,freq_month,defaulter) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt1 = cn.prepareStatement(query1);
                stmt1.setInt(1,student.getSchool_id());
                stmt1.setString(2,student.getFirst_name());
                stmt1.setString(3,student.getLast_name());
                stmt1.setString(4,student.getEmail());
                stmt1.setInt(5,student.getPhone());
                stmt1.setInt(6,student.getAmount());
                stmt1.setDate(7,student.getStart_date());
                stmt1.setDate(8,student.getDue_date());
                stmt1.setInt(9,student.getFreq_month());
                stmt1.setInt(10,student.getDefaulter());

                stmt1.executeUpdate();
                return "student registered succesfully";

            }else{
              return  "Your Schhol is not registerd with our platform till now ";
            }

        }catch (Exception e){
            return "something went wrong";
        }
    }
    @PostMapping("pay")
    String payyy(@RequestBody Pay pay) {

        try (Connection cn = jdbc.getDataSource().getConnection()) {
//
//            String query4= "select defaulter from student where studentid=? and defaulter=?";
//            PreparedStatement stmt4 = cn.prepareStatement(query4);
//            stmt4.setInt(1,pay.getStudent_id());
//            stmt4.setInt(2,1);
//            ResultSet rs4 = stmt4.executeQuery();
//            if(rs4.next()){
//                return "your Fee Status is UpToDate";
//            }

            String query2 = "select amount,due_date,Freq_month from student where student_id = ?";
            PreparedStatement stmt1 = cn.prepareStatement(query2);
            stmt1.setInt(1, pay.getStudent_id());
            ResultSet rs = stmt1.executeQuery();

            // Update the due date

            int amountt = 0;
            Date due_date = null;

            int freq_month = 0;

            if (rs.next()) {
                amountt = rs.getInt("amount");
                due_date = rs.getDate("due_date");
                freq_month = rs.getInt("freq_month");

            }else{
                return "no data find";
            }

            String query = "insert into transaction (student_id,amount,trans_date) values(?,?,CURDATE()) ";
            PreparedStatement stmt = cn.prepareStatement(query);
            stmt.setInt(1, pay.getStudent_id());
            stmt.setInt(2, amountt);
            stmt.executeUpdate();

            String query3 = "update student set due_date= Date_add(?, interval ? month) , defaulter=? where student_id=?";
            PreparedStatement stmt3 = cn.prepareStatement(query3);
            stmt3.setDate(1, (java.sql.Date) due_date);
            stmt3.setInt(2, freq_month);
            stmt3.setInt(3, 1);
            stmt3.setInt(4, pay.getStudent_id());
            stmt3.executeUpdate();

            return "Payment Done Successfully..  Thank You !!";
        } catch (Exception e) {
            return "something went wrong";
        }
    }

    @GetMapping("check_due")
    HashMap checkdue(){

        try (Connection cn = jdbc.getDataSource().getConnection()) {

            String query = "select * from student where due_date < CURDATE()";
            PreparedStatement stmt = cn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            HashMap<Integer,String> hm = new HashMap<>();

           while(rs.next()){
                hm.put(rs.getInt("student_id"),rs.getString("first_name"));
            }
            return hm;

        }catch(Exception e){
               e.printStackTrace();
        }
        return null;
    }

    @GetMapping("defaulter")
    HashMap<Integer, String> defaulter(){

        try(Connection cn = jdbc.getDataSource().getConnection()){

            String query = "select student_id,first_name from student where due_date < CURDATE()";
            PreparedStatement stmt = cn.prepareStatement(query);
            //stmt.setInt(1, 0);
            ResultSet rs = stmt.executeQuery();

            HashMap<Integer,String> hm = new HashMap<>();

            while(rs.next()){
               hm.put(rs.getInt("student_id"), rs.getString("first_name"));
            }
        return hm;

        }catch (Exception e ){
          e.printStackTrace();
        }
        return null;
    }
}
