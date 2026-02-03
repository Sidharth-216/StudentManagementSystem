import java.sql.*;
import java.util.*;
class Student2
{
    public static void main(String [] args)
    {
        Scanner sc=new Scanner (System.in);
        try
        {
                String url="jdbc:mysql://localhost:3306/college";
                String user="root";
                String pass="sidu21605";
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn=DriverManager.getConnection(url,user,pass);
                    Statement smt=conn.createStatement();
                    while(true)
                    {
                        System.out.println("1.INSERT");
                        System.out.println("2.DISPLAY");
                        System.out.println("3.UPDATE");
                        System.out.println("4.DELETE");
                        System.out.println("5.EXIT");
                        System.out.println("ENTER THE CHOICE:");
                        int cho=sc.nextInt();
                        switch(cho)
                        {
                            case 1:
                                System.out.println("ENTER THE ID:");
                                int id=sc.nextInt();
                                sc.nextLine();
                                System.out.println("ENTER THE NAME:");
                                String name=sc.nextLine();
                                System.out.println("ENTER THE EMAIL:");
                                String email=sc.nextLine();
                                System.out.println("ENTER THE COURSE:");
                                String course=sc.nextLine();
                                CallableStatement cs=conn.prepareCall("{call putstudent(?,?,?,?)}");

                                cs.setInt(1,id);
                                cs.setString(2,name);
                                cs.setString(3,email);
                                cs.setString(4,course);
                                cs.execute();


                                break;
                            case 2:
                                CallableStatement cs1=conn.prepareCall("{call getstudent()}");
                                ResultSet rs=cs1.executeQuery();
                                System.out.println("ID  NAME  EMAIL  COURSE");
                                while(rs.next())
                                {
                                    int id1=rs.getInt("id");
                                    String name1=rs.getString("name");
                                    String email1=rs.getString("email");
                                    String course1=rs.getString("course");
                                    System.out.println(id1+" "+name1+" "+email1+" "+course1);

                                }
                                break;
                            case 3:
                                CallableStatement cs3=conn.prepareCall("{call updatestudent(?,?,?,?)}");
                                System.out.println("Enter the Id where you want to update");
                                int id1=sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter the name:");
                                String name2=sc.nextLine();
                                System.out.println("Enter the email");
                                String email2=sc.nextLine();
                                System.out.println("Enter the course");
                                String course2=sc.nextLine();
                                cs3.setInt(1,id1);
                                cs3.setString(2,name2);
                                cs3.setString(3,email2);
                                cs3.setString(4,course2);
                                cs3.execute();
                                break;
                            case 4:
                                CallableStatement cs4=conn.prepareCall("{call deletestudent(?)}");
                                System.out.println("Enter the Id to be deleted:");
                                int id4=sc.nextInt();
                                cs4.setInt(1,id4);
                                cs4.execute();
                                break;
                            case 5:
                                System.exit(0);
                            default:
                                System.out.println("INVALID INPUT!");
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
