import java.sql.*;
class Code1
{
    public static void main(String [] args)
    {
        try
        {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user="root";
            String url="jdbc:mysql://localhost:3306/college";
            String pass="sidu21605";
            Connection conn=DriverManager.getConnection(url,user,pass);
            System.out.println("Successfully connected");
            String query="SELECT * FROM students ";
            Statement stmt=conn.createStatement();
            ResultSet rt=stmt.executeQuery(query);
            System.out.println("NAME  |  EMAIL  |  COURSE");
            while(rt.next())
            {
                String name=rt.getString("name");
                String email=rt.getString("email");
                String course=rt.getString("course");
                System.out.println(name+" | "+email+" | "+course);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}