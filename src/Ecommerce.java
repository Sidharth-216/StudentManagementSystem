import java.sql.*;
import java.util.*;
class Ecommerce
{
    public static void main(String [] args)
    {   
        String url="jdbc:mysql://localhost:3306/EcommerceMysql";
        String user="root";
        String pass="sidu21605";
        Scanner sc=new Scanner(System.in);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,user,pass);
            Statement stmt=conn.createStatement();
            while(true)
            {
                System.out.println("1.CUSTOMER REGISTRATION");
                System.out.println("2.INSERT PRODUCT:");
                System.out.println("3.DISPLAY PRODUCTS");
                System.out.println("4.PLACE ORDER");
                System.out.println("5.SHOW ORDERS");
                System.out.println("6.EXIT");
                System.out.println("ENTER THE CHOICE:");
                int cho=sc.nextInt();
                switch(cho)
                {
                    case 1:
                        System.out.println("Enter the customer id:");
                        int  cid=sc.nextInt();
                        System.out.println("ENter the Name:");
                        String name=sc.nextLine();
                        sc.next();
                        System.out.println("ENter the email:");
                        String email=sc.nextLine();
                        sc.next();
                        System.out.println("Enter the mobile");
                        long mob=sc.nextLong();
                        String query="insert into customers(cid,name,email,mobile)values(?,?,?,?)";
                        PreparedStatement pt=conn.prepareStatement(query);  
                        pt.setInt(1,cid);
                        pt.setString(2,name);
                        pt.setString(3,email);
                        pt.setLong(4,mob);
                        pt.executeUpdate();
                        break;
                    case 2:
                        System.out.println("Enter the product id:");
                        int  pid8=sc.nextInt();
                        System.out.println("ENter the Product Name:");
                        String pname8=sc.nextLine();
                        sc.next();
                        System.out.println("ENter the Price:");
                        Double price8=sc.nextDouble();
                         String query8="insert into products(pid,pname,price)values(?,?,?)";
                        PreparedStatement pt8=conn.prepareStatement(query8);  
                        pt8.setInt(1,pid8);
                        pt8.setString(2,pname8);
                        pt8.setDouble(3,price8);
                        pt8.executeUpdate();
                        break;
                    case 3:
                        String query1="SELECT * FROM products";
                        PreparedStatement pt1=conn.prepareStatement(query1);
                        ResultSet rt=pt1.executeQuery();
                        System.out.println("PID  PNMAE  PRICE");
                        while(rt.next())
                        {
                            int pid=rt.getInt("pid");
                            String pname=rt.getString("pname");
                            Double price=rt.getDouble("price");
                            System.out.println(pid+ " "+pname+" "+price);
                        }
                        break;
                    case 4:
                        System.out.println("Enter the CID:");
                        int cid1=sc.nextInt();
                        System.out.println("ENter the orderno:");
                        int orderno=sc.nextInt();
                        System.out.println("Enter the Total Amount");
                        Double amo=sc.nextDouble();
                        System.out.println("ENter the pid:");
                        int pid1=sc.nextInt();
                        System.out.println("ENter the product name");
                        String pname1=sc.nextLine();
                        sc.next();
                        String query3="INSERT INTO orders (cid,orderno,totalamount,pid,pname)values(?,?,?,?,?)";
                        PreparedStatement pt3=conn.prepareStatement(query3);
                        pt3.setInt(1,cid1);
                        pt3.setInt(2,orderno);
                        pt3.setDouble(3,amo);
                        pt3.setInt(4,pid1);
                        pt3.setString(5,pname1);
                        pt3.executeUpdate();
                        System.out.println("Done");
                        break;
                    case 5:
                        String query4="SELECT * FROM orders";
                        PreparedStatement pt5=conn.prepareStatement(query4);
                        ResultSet rt5=pt5.executeQuery();
                        System.out.println("CID  ORDNO  TOTAMO   PNMAE   PID");
                        while(rt5.next())
                        {
                            int cid5=rt5.getInt("cid");
                            int od5=rt5.getInt("orderno");
                            Double amo5=rt5.getDouble("totalamount");
                            String pname5=rt5.getString("pname");
                            int pid5=rt5.getInt("pid");
                            System.out.println(cid5+" "+od5+ " "+amo5+" "+pname5+" "+pid5);
                        }
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("INVALID INPUT !");

                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}