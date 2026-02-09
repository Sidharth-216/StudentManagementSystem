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
            String user="root";
            String pass="sidu21605";
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
                        String name=sc.next();
                        sc.nextLine();
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
                        System.out.println("Successfully Registered");
                        break;
                    case 2:
                        System.out.println("Enter the Same User Id  , of the User Registration:");
                        int id1=sc.nextInt();
                        System.out.println("Enter the Account No: ");
                        long accno=sc.nextLong();
                        System.out.println("ENter the Initial Balance:");
                        Double amo=sc.nextDouble();
                        System.out.println("Enter the IFSCODE:");
                        String ifc=sc.next();
                        sc.nextLine();
                        System.out.println("Enter the Branch:");
                        String branch=sc.next();
                        sc.nextLine();
                        String query2="INSERT INTO accounts(uid,account_no,balance,ifscode,branch)values(?,?,?,?,?)";
                        PreparedStatement pt2=conn.prepareStatement(query2);
                        pt2.setInt(1,id1);
                        pt2.setLong(2,accno);
                        pt2.setDouble(3,amo);
                        pt2.setString(4,ifc);
                        pt2.setString(5,branch);
                        pt2.executeUpdate();
                        System.out.println("Successfully Account Opened");
                        break;
                    case 3:
                        System.out.println("Enter the Account No. to view the details:");
                        long acc1=sc.nextLong();
                        String query3="SELECT * FROM users , accounts where accounts.account_no=? AND accounts.uid=users.uid";
                        PreparedStatement pt3=conn.prepareStatement(query3);
                        pt3.setLong(1,acc1);
                        ResultSet rt=pt3.executeQuery();
                        while(rt.next())
                        {
                            int uid=rt.getInt("uid");
                            String uname=rt.getString("uname");
                            int age2=rt.getInt("age");
                            long adhaar1=rt.getLong("adhaar");
                            long pan1=rt.getLong("pan");
                            long accno1=rt.getLong("account_no");
                            double balc=rt.getDouble("balance");
                            String ifc1=rt.getString("ifscode");
                            String branch1=rt.getString("branch");
                            System.out.println(uid+" "+uname+" "+age2+" "+adhaar1+" "+pan1+" "+accno1+" "+balc+" "+ifc1+" "+branch1);

                        }
                        break;
                    case 4:
                        System.out.println("Enter the Account no.:");
                        long accno2=sc.nextLong();
                        System.out.println("ENter the Useer ID:");
                        int uid2=sc.nextInt();
                        System.out.println("Enter the Amount to Withdraw:");
                        double with=sc.nextDouble();
                        String query4="select balance from accounts where uid=?";
                        PreparedStatement pt4=conn.prepareStatement(query4);
                        pt4.setInt(1,uid2);
                        ResultSet rt4=pt4.executeQuery();
                        if(rt4.next())
                        {
                            double balance=rt4.getDouble("balance")-with;
                            String query5="UPDATE accounts set balance=? where account_no=? AND uid=?";
                            PreparedStatement pt5=conn.prepareStatement(query5);
                            pt5.setDouble(1,balance);
                            pt5.setLong(2,accno2);
                            pt5.setInt(3,uid2);
                            pt5.executeUpdate();
                            System.out.println("Successfully Withdrawn");
                        }
                        break;
                    case 5:
                        System.out.println("Enter the Account no.:");
                        long accno3=sc.nextLong();
                        System.out.println("ENter the Useer ID:");
                        int uid3=sc.nextInt();
                        System.out.println("Enter the Amount to Deposit:");
                        double dep=sc.nextDouble();
                        String query6="select balance from accounts where uid=?";
                        PreparedStatement pt6=conn.prepareStatement(query6);
                        pt6.setInt(1,uid3);
                        ResultSet rt6=pt6.executeQuery();
                        if(rt6.next())
                        {
                            double balance1=rt6.getDouble("balance")+dep;
                            String query7="UPDATE accounts set balance=? where account_no=? AND uid=?";
                            PreparedStatement pt7=conn.prepareStatement(query7);
                            pt7.setDouble(1,balance1);
                            pt7.setLong(2,accno3);
                            pt7.setInt(3,uid3);
                            pt7.executeUpdate();
                            System.out.println("Successfully Deposited");
                        }
                        break;
                    case 6:
                        System.out.println("Enter the EMPID:");
                        int empid=sc.nextInt();
                        System.out.println("Enter the Employee Name:");
                        String empname=sc.next();
                        sc.nextLine();
                        String query8="INSERT INTO bank_employee(empid,empname)VALUES(?,?)";
                        PreparedStatement pt9=conn.prepareStatement(query8);
                        pt9.setInt(1,empid);
                        pt9.setString(2,empname);
                        pt9.executeQuery();
                        System.out.println("Successfully Employee Registered");
                        break;
                    case 7:
                        System.out.println("Enter the Employee ID:");
                        int empid1=sc.nextInt();
                        String query10="SELECT * FROM bak_employee where empid=?";
                        PreparedStatement pt10=conn.prepareStatement(query10);
                        pt10.setInt(1,empid1);
                        ResultSet rt10=pt10.executeQuery();
                        while(rt10.next())
                        {
                            int eid=rt10.getInt("empid");
                            String ename=rt10.getString("empname");
                            System.out.println(eid+" "+ename);

                        }
                        break;
                    case 8:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("INVALID CHOICE!");
                }
                sc.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}