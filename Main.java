package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Main {

    //Variables defined to use in the functions.
    public static final String url = "jdbc:postgresql://localhost:5432/a1_database";
    public static final String user = "postgres";
    public static final String password = "postgres";

    public static void main(String[] args) {
        getAllStudents();
        //addStudent("Jay", "Jonah","jayjonah@example.com","2023-09-03");
        //deleteStudent(4);
        //updateStudentEmail(3, "jayjay@example.com");
        //getAllStudents();
    }

    //Function to view all students
    public static void getAllStudents(){

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + " \t");
                System.out.print(resultSet.getString("first_name") + " \t");
                System.out.print(resultSet.getString("last_name") + " \t");
                System.out.print(resultSet.getString("email") + " \t");
                System.out.println(resultSet.getString("enrollment_date") + " \t");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    //Function to add students to the database
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            //parse date string as a date object
            LocalDate date = LocalDate.parse(enrollment_date);
            String query = String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('%s', '%s', '%s', '%tF');", first_name, last_name, email, date);
            statement.executeUpdate(query);
            System.out.println("Student added successfully!");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //Function to update student email by cross-referencing student_id
    public static void updateStudentEmail(Integer student_id, String new_email){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE students SET email = '%s' WHERE student_id = %d;", new_email,student_id);
            statement.executeUpdate(query);
            System.out.println("Student updated successfully!");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //Function to delete student from the database by cross-referencing student_id
    public static void deleteStudent(Integer student_id){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM students WHERE student_id = %d;",student_id);
            statement.executeUpdate(query);
            System.out.println("Deleted student with student_id: " + student_id);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
