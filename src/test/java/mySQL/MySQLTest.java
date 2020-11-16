package mySQL;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MySQLTest {

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String dbAddress = "jdbc:mysql://localhost:3306/";
    String userPass = "?user=root&password=";
    String dbName = "aqahillel";
    String tName = "friends";

    Connection connection = null;
    Statement statement;
    String url = dbAddress + userPass + "***";

    // 1. CREATE DATABASE
    @BeforeClass
    public void createDB() {
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + dbName);

            if (connection != null) {
                System.out.println("Connected to the database");
            }
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

    @BeforeMethod
    public void setConnection() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection(dbAddress + dbName + userPass + "***");
        Class.forName(jdbcDriver);
        statement = connection.createStatement();
    }

    // 2. CREATE TABLE
    @Test
    public void createTable() {
        try {
            String table = "CREATE TABLE " + tName + " ("
                    + "idNo INT(6) NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(255),"
                    + "age INT(100),"
                    + "skill VARCHAR(255), "
                    + "PRIMARY KEY(idNo))";

            statement.executeUpdate(table);
            System.out.println("Table Created");
//            Set<String> s = fr.listTables(db);
//            assertEquals(1, s.size());

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

    // 3. INSERT
    @Test(dependsOnMethods = "createTable")
    public void insertValuesToTable() {
        try {
            String sql = "INSERT INTO " + tName + " VALUES (1, 'Monica', 26, 'Chef')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO " + tName +
                    " VALUES (2, 'Phoebe', 28, 'Masseur')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO " + tName +
                    " VALUES (3, 'Rachel', 26, 'Waitress')";
            statement.executeUpdate(sql);
            System.out.println("Inserted records into the table:");

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

    // 4. SELECT
    @Test(dependsOnMethods = "insertValuesToTable")
    public void checkInsertedValues() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + dbName + "." + tName + ";");

            List allRows = new ArrayList();
            int numberColumns = 4;
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = resultSet.getString(i);
                    System.out.print(currentRow[i - 1] + " ");
                }
                System.out.println();
                assertTrue(allRows.add(currentRow));
            }
            assertEquals(allRows.size(), 3);


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

    // 5. UPDATE
    @Test(dependsOnMethods = "checkInsertedValues")
    public void updateTable() {
        try {
            statement.executeUpdate("UPDATE " + dbName + "." + tName + " SET skill = 'Sales assistant' WHERE idNo = 3;");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + dbName + "." + tName + " WHERE idNo = 3;");

            // not sure about this:
            assertTrue(resultSet.rowUpdated());
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

    // 6. SELECT
    @Test(dependsOnMethods = "updateTable")
    public void checkUpdatedTable() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + dbName + "." + tName + " WHERE idNo = 3;");
            int numberColumns = 4;
            System.out.println("\nChanges for Rachel:");
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[3] = resultSet.getString(i);
                    System.out.print(currentRow[3] + " ");
                }
                assertEquals(resultSet.getString(4), "Sales assistant");
            }
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

    // 7. DELETE
    @Test(dependsOnMethods = "checkUpdatedTable")
    public void deleteRowInTable() {
        try {
            statement.executeUpdate("DELETE FROM " + dbName + "." + tName + " WHERE idNo = 3;");
            System.out.println();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + dbName + "." + tName + " WHERE idNo = 3;");

            // not sure about this:
            assertTrue(resultSet.rowDeleted());
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

    // 8. SELECT
    @Test(dependsOnMethods = "deleteRowInTable")
    public void checkRowWasDeleted() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + dbName + "." + tName + ";");

            List allRows = new ArrayList();
            int numberColumns = 4;
            System.out.println("\nDelete Rachel:");
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = resultSet.getString(i);
                    System.out.print(currentRow[i - 1] + " ");
                }
                System.out.println();
                allRows.add(currentRow);
            }
            assertEquals(allRows.size(), 2);

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

    // 9. DROP TABLE
    @Test(dependsOnMethods = "checkRowWasDeleted")
    public void dropTable() {
        try {
            statement.execute("DROP TABlE " + tName);
            String[] types = { "TABLE" };
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet tables = dbMetaData.getTables(dbName, null, "%", types);
            while(tables.next())
            {
                System.out.println("tables  =  " + tables.getString(3));
                assertFalse(tables.getString(3).contains("friends"), "Table 'friends' was not dropped!");
            }
//            ResultSet resultSet = connection.getMetaData().getTables(dbName, null, "%", null);
//            assertFalse(resultSet.getMetaData().getTableName(3).contains("friends"));
            System.out.println("Table 'friends' deleted successfully");

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

    // 10. DROP DB
    @Test(dependsOnMethods = "dropTable")
    public void dropDB() {
        try {
            statement.execute("DROP DATABASE " + dbName);
            DatabaseMetaData dbMetaData = connection.getMetaData();
            ResultSet catalogs = dbMetaData.getCatalogs();
            while(catalogs.next())
            {
                System.out.println("catalogs  =  " + catalogs.getString(1));
                assertFalse(catalogs.getString(1).contains(dbName), "DB 'aqahillel' was not dropped!");
            }
            System.out.println("Database 'aqahillel' deleted successfully");
//            ResultSet resultSet = connection.getMetaData().getCatalogs();
//            assertFalse(resultSet.getMetaData().getCatalogName(3).contains("aqahillel"));

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
