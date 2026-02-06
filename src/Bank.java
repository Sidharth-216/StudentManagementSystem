import java.sql.*;
import java.util.*;
class Bank
{
    public static void main(String[]args)
    {
        Scanner sc=new Scanner (System.in);
        try
        {
            String url="jdbc:mysql://localhost:3306/Bank";
            String user="";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,user,pass);
            System.out.println("Successfully Connected");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try
        {
            while(true)
            {
                System.out.println("1.USER REGISTARTION");
                System.out.println("2.VIEW DETAILS");
                System.out.println("3.WITHDRAW");
                System.out.println("4.DEPOSIT");
                System.out.println("5.EMPLYEE REGISTRATION");
                System.out.println("6.EMPLOYEE DETAILS");
                System.out.println("7.EXIT");
                System.out.println("ENTER THE CHOICE");
                int cho=sc.nextInt();
                switch(cho)
                {
                    case 1:
                        break;
                    case 2:
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