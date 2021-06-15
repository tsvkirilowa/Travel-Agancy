import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DataBaseProject {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private static Scanner input = new Scanner(System.in);




    public void openConnection() {

// Step 1: Load IBM DB2 JDBC driver

        try {

            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());

        } catch (Exception cnfex) {

            System.out.println("Problem in loading or registering IBM DB2 JDBC driver");

            cnfex.printStackTrace();
        }

// Step 2: Opening database connection


        try {

            connection = DriverManager.getConnection("jdbc:db2://62.44.108.24:50000/SAMPLE", "db2admin", "db2admin");

            statement = connection.createStatement();

        } catch (SQLException s) {

            s.printStackTrace();

        }

    }

    public void closeConnection() {

        try {

            if (null != connection) {

                // cleanup resources, once after processing

                resultSet.close();

                statement.close();


                // and then finally close connection

                connection.close();

            }

        } catch (SQLException s) {

            s.printStackTrace();

        }

    }

    public void select(String stmnt, int column) {

        try {

            resultSet = statement.executeQuery(stmnt);

            String result = "";

            while (resultSet.next()) {

                for (int i = 1; i <= column; i++) {

                    result += resultSet.getString(i);

                    if (i == column) result += " \n";
                    else result += ", ";
                }
            }

            System.out.println("Executing query: " + stmnt + "\n");
            System.out.println("Result output \n");
            System.out.println("---------------------------------- \n");
            System.out.println(result);

        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public void insert(String stmnt) {

        try {

            statement.executeUpdate(stmnt);

        } catch (SQLException s) {

            s.printStackTrace();

        }

        System.out.println("Successfully inserted!");

    }


    public void delete(String stmnt) {

        try {

            statement.executeUpdate(stmnt);

        } catch (SQLException s) {

            s.printStackTrace();

        }

        System.out.println("Successfully deleted!");

    }

    public static void printInfo()
    {
    System.out.println("Menu:");
        System.out.println("1 - Find Client by id:");
        System.out.println("2 - Show Offers in BG");
        System.out.println("3 - Hire staff: ");
        System.out.println("4 - Show Offers Abroad:");
        System.out.println("5 - Create offer abroad:");
        System.out.println("6 - Create offer in BG");
        System.out.println("7 - Exit");
}

    //choice 1
    private static void choice1(DataBaseProject app)
    {
        System.out.print("Enter id: ");
        String id = input.next();
        app.select("SELECT * FROM FN71987.CLIENTS WHERE ID = " + id, 5);

    }

    private static void choice2(DataBaseProject db2Obj) {

        System.out.println("Selected all");
        db2Obj.select("SELECT * FROM FN71987.OFFERSINBG", 7);
    }

    private static void choice3(DataBaseProject db2Obj) {
        input.nextLine();
        String name;
        System.out.print("Enter name: ");
        name = input.nextLine();

        int phone;
        System.out.print("Enter phone: ");
        phone =Integer.parseInt(input.nextLine());

        String region;
        System.out.print("Enter the region: ");
        region = input.nextLine();

        int snum;
        System.out.print("Enter staff number: ");
        snum =Integer.parseInt(input.nextLine());

        db2Obj.insert("INSERT INTO FN71987.STAFFF VALUES ('" + name + "', '" + phone + "', '" + region + "', '" + snum + "')");
    }


    private static void choice4(DataBaseProject db2Obj) {
        System.out.println("Selected offers abroad");
        db2Obj.select("SELECT * FROM FN71987.OFFRESABROAD", 9);
    }

    private static void choice5(DataBaseProject db2Obj) {
        input.nextLine();
        String type;
        System.out.println("Enter type: ");
        type = input.nextLine();

        double price;
        System.out.println("Enter price: ");
        price = Double.parseDouble(input.nextLine());

        String location;
        System.out.println("Enter location: ");
        location = input.nextLine();

        int tv;
        System.out.println("Enter is there a tv: ");
        tv = Integer.parseInt(input.nextLine());

        String transport;
        System.out.println("Enter transport: ");
        transport= input.nextLine();


        int food;
        System.out.println("Enter is food is include: ");
        food = Integer.parseInt(input.nextLine());

        int id;
        System.out.println("Enter client id: ");
        id = Integer.parseInt(input.nextLine());

        int insurance;
        System.out.println("Do you have insurance: ");
        insurance = Integer.parseInt(input.nextLine());

        String corona;
        System.out.println("Do you have PCR or VACCINE: ");
        corona= input.nextLine();

        String st ="INSERT INTO FN71987.OFFERSABROAD VALUES ('"+ type +"','" + price +"','" + location +"','"+ tv
                +"','" + transport +"','"+ food +"','"+ id +"','" + insurance +"','"+ corona +"')";
        db2Obj.insert(st);


    }


    private static void choice6(DataBaseProject db2Obj) {

        String type;
        System.out.print("Enter type: ");
        type = input.nextLine();

        double price;
        System.out.print("Enter price: ");
        price = Double.parseDouble(input.nextLine());

        String location;
        System.out.print("Enter location: ");
        location = input.nextLine();

        int tv;
        System.out.print("Enter is there a tv: ");
        tv = Integer.parseInt(input.nextLine());

        String transport;
        System.out.print("Enter transport: ");
        transport= input.nextLine();

        int food;
        System.out.print("Enter is food is include: ");
        food = Integer.parseInt(input.nextLine());

        int id;
        System.out.print("Enter client id: ");
        id = Integer.parseInt(input.nextLine());

        db2Obj.insert("INSERT INTO FN71987.OFFERSINBG VALUES ('" + type + "', '" + price + "', '" + location + "', '" + tv + "', '" + transport + "','" + food +"' , '" +id + "')");
    }


    public static void main(String[] args) {

        DataBaseProject db2Obj = new DataBaseProject();
        db2Obj.openConnection();

        int currentChoise;
        do {

            printInfo();
            System.out.print("Your choice: ");
            currentChoise = input.nextInt();

            switch (currentChoise) {
                case 1: {
                    choice1(db2Obj);
                    break;
                }
                case 2: {
                    choice2(db2Obj);
                    break;
                }
                case 3: {
                    choice3(db2Obj);
                    break;
                }
                case 4: {
                    choice4(db2Obj);
                    break;
                }
                case 5: {
                    choice5(db2Obj);
                    break;
                }
                case 6: {
                    choice6(db2Obj);
                    break;
                }
                case 7: {
                    System.out.println("Thank you for using our program");
                    break;
                }

            }
        } while (currentChoise != 7);

        db2Obj.closeConnection();

    }

}
