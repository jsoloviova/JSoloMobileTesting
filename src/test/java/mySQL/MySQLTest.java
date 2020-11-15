package mySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTest {

    public static void main(String[] args) throws ClassNotFoundException {
        String jdbcDriver = "com.mysql.jdbc.Driver";
        String dbAddress = "jdbc:mysql://localhost:3306/";
        String userPass = "?user=root&password=";
        String dbName = "aqahillel";
        String tName = "friends";
        String userName = "root";
        String password = "";

        Connection connection = null;
        String url = dbAddress + userPass + "///";

        try {
            // 1. CREATE DATABASE
            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + dbName);

            if (connection != null) {
                System.out.println("Connected to the database");
            }

            // 2. CREATE TABLE
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbAddress + dbName + userPass + "///");
            Statement statement1 = connection.createStatement();
            String table = "CREATE TABLE " + tName + " ("
                    + "idNo INT(6) NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(255),"
                    + "age INT(100),"
                    + "skill VARCHAR(255), "
                    + "PRIMARY KEY(idNo))";

            statement1.executeUpdate(table);
            System.out.println("Table Created");

            // 3. INSERT
            String sql = "INSERT INTO " + tName + " VALUES (1, 'Monica', 26, 'Chef')";
            statement1.executeUpdate(sql);
            sql = "INSERT INTO " + tName +
                    " VALUES (2, 'Phoebe', 28, 'Masseur')";
            statement1.executeUpdate(sql);
            sql = "INSERT INTO " + tName +
                    " VALUES (3, 'Rachel', 26, 'Waitress')";
            statement1.executeUpdate(sql);
            System.out.println("Inserted records into the table:");

            // 4. SELECT
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery("SELECT * FROM " + dbName + "." + tName + ";");

            List allRows = new ArrayList();
            int numberColumns = 4;
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for(int i = 1; i <= numberColumns; i++) {
                    currentRow[i-1]=resultSet.getString(i);
                    System.out.print(currentRow[i-1] + " ");
                }
                System.out.println();
                allRows.add(currentRow);
            }

            // 5. UPDATE
            statement1.executeUpdate("UPDATE " + dbName + "." + tName + " SET skill = 'Sales assistant' WHERE idNo = 3;");

            // 6. SELECT
            ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM " + dbName + "." + tName + " WHERE idNo = 3;");
            int numberColumns1 = 4;
            System.out.println("\nChanges for Rachel:");
            while (resultSet1.next()) {
                String[] currentRow1 = new String[numberColumns1];
                for(int i = 1; i <= numberColumns1; i++) {
                    currentRow1[3]=resultSet1.getString(i);
                    System.out.print(currentRow1[3] + " ");
                }
            }

            // 7. DELETE
            statement1.executeUpdate("DELETE FROM " + dbName + "." + tName + " WHERE idNo = 3;");
            System.out.println();

            // 8. SELECT
            ResultSet resultSet2 = statement1.executeQuery("SELECT * FROM " + dbName + "." + tName + ";");

            List allRows1 = new ArrayList();
            int numberColumns2 = 4;
            System.out.println("\nDelete Rachel:");
            while (resultSet2.next()) {
                String[] currentRow2 = new String[numberColumns2];
                for(int i = 1; i <= numberColumns2; i++) {
                    currentRow2[i-1]=resultSet2.getString(i);
                    System.out.print(currentRow2[i-1] + " ");
                }
                System.out.println();
                allRows1.add(currentRow2);
            }

            // 9. DROP TABLE
            statement1.execute("DROP TABlE " + tName);
            System.out.println("Database deleted successfully\nDB tables:");
            statement1.execute("SHOW TABLES");

            // 10. DROP DB
            statement1.execute("DROP DATABASE " + dbName);
            statement1.execute("SHOW DATABASES");
            System.out.println("Database deleted successfully");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

//            String[] arr = null;
//            System.out.println("\nGirls skills:");
//            while (resultSet.next()) {
//                String em = resultSet.getString("skill");
//                arr = em.split("\n");
//                for (int i = 0; i < arr.length; i++) {
//                    System.out.println(arr[i]);
//                }
//            }
