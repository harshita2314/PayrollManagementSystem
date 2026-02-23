import java.sql.*;
import java.util.Scanner;

public class PayrollManagement {

    static final String URL = "jdbc:mysql://localhost:3306/payroll_db";
    static final String USER = "root";
    static final String PASSWORD = "yourpassword"; // Replace with your MySQL password

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("==== Payroll Management System ====");

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();

            System.out.print("Enter Basic Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Enter Leaves Taken: ");
            int leaves = sc.nextInt();

            double deduction = leaves * 500; // ₹500 per leave
            double netSalary = salary - deduction;

            String query = "INSERT INTO employees (name, designation, basic_salary, leaves_taken) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, designation);
            pst.setDouble(3, salary);
            pst.setInt(4, leaves);

            pst.executeUpdate();

            System.out.println("\nEmployee Added Successfully!");
            System.out.println("Net Salary: ₹" + netSalary);

            conn.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}