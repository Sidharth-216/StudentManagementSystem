import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student
 {

    private Connection con;
    private Scanner sc = new Scanner(System.in);

    public Student(Connection con) 
    {
        this.con = con;
    }

 
    public void addStudent() 
    {
        try 
        {
            System.out.print("Enter name: ");
            String name = sc.next();

            System.out.print("Enter email: ");
            String email = sc.nextLine();
            sc.nextLine();

            System.out.print("Enter course: ");
            String course = sc.next();

            String query = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);

            ps.executeUpdate();
            System.out.println("Student added successfully!");

        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    
    public void viewStudents() 
    {
        try 
        {
            String query="SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nID\tName\tEmail\t\tCourse");
            System.out.println("-------------------------------------");

            while (rs.next())
             {
                int id1=rs.getInt("id");
                String name1=rs.getString("name");
                String email1=rs.getString("email");
                String course1=rs.getString("course");
                System.out.println(id1+" "+name1+" "+email1+" "+course1+" ");
            }

        } 
        catch (Exception e)
         {
            System.out.println(e);
        }
    }

    
    public void updateStudent() {
        try 
        {
            System.out.print("Enter student ID to update: ");
            int id = sc.nextInt();

            System.out.print("Enter new course: ");
            String course = sc.next();

            String query = "UPDATE students SET course=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, course);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student updated successfully!");
            else
                System.out.println("Student not found!");

        } 
        catch (Exception e)
         {
            System.out.println(e);
        }
    }


    public void deleteStudent()
     {
        try 
        {
            System.out.print("Enter student ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student deleted successfully!");
            else
                System.out.println("Student not found!");

        }
         catch (Exception e) 
         {
            System.out.println(e);
        }
    }
}
