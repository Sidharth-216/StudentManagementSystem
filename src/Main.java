//javac -cp "/usr/share/java/mysql-connector-j-9.6.0.jar" src/Main.java src/Student.java
//java -cp ".:src:/usr/share/java/mysql-connector-j-9.6.0.jar" Main

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main
 {

   
    private static final String URL = "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";
    private static final String PASSWORD = "sidu21605";

    public static void main(String[] args)
     {

        try {
            
            Class.forName("com.mysql.jdbc.Driver");

            
            Connection con= DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Database connected successfully!");

            Student student=new Student(con);
            Scanner sc=new Scanner(System.in);

            while (true)
             {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1.Add Student");
                System.out.println("2.View Student");
                System.out.println("3.Update Student");
                System.out.println("4.Delete Student");
                System.out.println("5.Exit");
                System.out.print("Enter THe choice: ");

                int choice = sc.nextInt();

                switch (choice) 
                {
                    case 1:
                        student.addStudent();
                        break;
                    case 2:
                        student.viewStudents();
                        break;
                    case 3:
                        student.updateStudent();
                        break;
                    case 4:
                        student.deleteStudent();
                        break;
                    case 5:
                        con.close();
                        System.out.println("Exited successfully.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            }

        }
         catch (Exception e)
         {
            System.out.println(e);
        }
    }
}
