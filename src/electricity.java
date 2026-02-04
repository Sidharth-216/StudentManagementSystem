import java.sql.*;
import java.sql.Date;
import java.util.*;

class electricity
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        try
        {
            String url = "jdbc:mysql://localhost:3306/college";
            String user = "root";
            String pass = "sidu21605";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            while (true)
            {
                System.out.println("\n1. INSERT BILL");
                System.out.println("2. DISPLAY BILL");
                System.out.println("3. UPDATE PAYMENT STATUS");
                System.out.println("4. DELETE BILL");
                System.out.println("5. EXIT");
                System.out.print("ENTER YOUR CHOICE: ");

                int ch = sc.nextInt();

                switch (ch)
                {
                    // INSERT
                    case 1:
                        System.out.print("Enter Bill ID: ");
                        int billId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Customer Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        System.out.print("Enter Meter Number: ");
                        String meter = sc.nextLine();

                        System.out.print("Enter Billing Month: ");
                        String month = sc.nextLine();

                        System.out.print("Enter Billing Year: ");
                        int year = sc.nextInt();

                        System.out.print("Enter Previous Reading: ");
                        int prev = sc.nextInt();

                        System.out.print("Enter Current Reading: ");
                        int curr = sc.nextInt();

                        System.out.print("Enter Rate Per Unit: ");
                        double rate = sc.nextDouble();

                        System.out.print("Enter Fixed Charge: ");
                        double fixed = sc.nextDouble();

                        System.out.print("Enter Tax Amount: ");
                        double tax = sc.nextDouble();

                        System.out.print("Enter Due Date (YYYY-MM-DD): ");
                        String due = sc.next();

                        CallableStatement cs = conn.prepareCall("{call add_bill(?,?,?,?,?,?,?,?,?,?,?,?)}");

                        cs.setInt(1, billId);
                        cs.setString(2, name);
                        cs.setString(3, address);
                        cs.setString(4, meter);
                        cs.setString(5, month);
                        cs.setInt(6, year);
                        cs.setInt(7, prev);
                        cs.setInt(8, curr);
                        cs.setDouble(9, rate);
                        cs.setDouble(10, fixed);
                        cs.setDouble(11, tax);
                        cs.setDate(12, Date.valueOf(due));

                        cs.execute();
                        System.out.println("Bill Inserted Successfully!");
                        break;

                    // DISPLAY
                    case 2:
                        System.out.print("Enter Bill ID to Display: ");
                        int bid = sc.nextInt();

                        CallableStatement cs1 = conn.prepareCall("{call get_bill(?)}");
                        cs1.setInt(1, bid);

                        ResultSet rs = cs1.executeQuery();

                        System.out.println("\nID  NAME  UNITS  TOTAL  STATUS");
                        while (rs.next())
                        {
                            System.out.println(
                                rs.getInt("bill_id") + " " +
                                rs.getString("customer_name") + " " +
                                rs.getInt("units_consumed") + " " +
                                rs.getDouble("total_amount") + " " +
                                rs.getString("payment_status")
                            );
                        }
                        break;

                    // UPDATE
                    case 3:
                        System.out.print("Enter Bill ID: ");
                        int upId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Payment Status (Paid/Unpaid): ");
                        String status = sc.nextLine();

                        CallableStatement cs3 = conn.prepareCall("{call update_payment_status(?,?)}");
                        cs3.setInt(1, upId);
                        cs3.setString(2, status);

                        cs3.execute();
                        System.out.println("Payment Status Updated!");
                        break;

                    // DELETE
                    case 4:
                        System.out.print("Enter Bill ID to Delete: ");
                        int delId = sc.nextInt();

                        CallableStatement cs4 = conn.prepareCall("{call delete_bill(?)}");
                        cs4.setInt(1, delId);

                        cs4.execute();
                        System.out.println("Bill Deleted Successfully!");
                        break;

                    case 5:
                        System.out.println("Thank You!");
                        System.exit(0);

                    default:
                        System.out.println("INVALID CHOICE!");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
