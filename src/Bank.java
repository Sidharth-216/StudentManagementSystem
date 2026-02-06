import java.sql.*;
import java.util.*;
class Bank
{
    public static void main(String[]args)
    {
        try
        {
            Scanner sc=new Scanner (System.in);
            String url="jdbc:mysql://localhost:3306/Bank";
            String user="";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,user,pass);
            System.out.println("Successfully Connected");
        
            while(true)
            {
                System.out.println("1.USER REGISTARTION");
                System.out.println("2.OPEN ACCOUNT");
                System.out.println("3.VIEW DETAILS");
                System.out.println("4.WITHDRAW");
                System.out.println("5.DEPOSIT");
                System.out.println("6.EMPLYEE REGISTRATION");
                System.out.println("7.EMPLOYEE DETAILS");
                System.out.println("8.EXIT");
                System.out.println("ENTER THE CHOICE");
                int cho=sc.nextInt();
                switch(cho)
                {
                    case 1:
                        System.out.println("Enter the User Id:");
                        int id=sc.nextInt();
                        System.out.println("Enter the Name:");
                        String name=sc.nextLine();
                        sc.next();
                        System.out.println("Enter the age:");
                        int age=sc.nextInt();
                        System.out.println("Enter the adhaar number:");
                        long adhar=sc.nextLong();
                        System.out.println("Enter the PAN Number:");
                        long pan=sc.nextLong();

                        String query1="INSERT INTO users(uid,uname,age,adhaar,pan)values(?,?,?,?,?)";
                        PreparedStatement pt1=conn.prepareStatement(query1);
                        pt1.setInt(1,id);
                        pt1.setString(2,name);
                        pt1.setInt(3,age);
                        pt1.setLong(4,adhar);
                        pt1.setLong(5,pan);
                        pt1.executeUpdate();
                        break;
                    case 2:
                        System.out.println("Enter the Same User Id  , of the User Registration:");
                        System.out.println("Enter the Account No: ");
                        System.out.println("ENter the Initial Balance:");
                        System.out.println("Enter the IFSCODE:");
                        System.out.println("Enter the Branch:");
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("INVALID CHOICE!");
                }

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}