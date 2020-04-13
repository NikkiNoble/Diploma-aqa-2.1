import lombok.val;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInteractions {

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection ("jdbc:postgresql://192.168.99.100:5432/app", "app", "pass");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String getStatus() throws SQLException {
        val statusSQL = "SELECT status FROM payment_entity;";
        String status = "";

        try (val statusStmt = connection.createStatement();) {

            try (val rs = statusStmt.executeQuery(statusSQL)) {
                if (rs.next()) {
                    status = rs.getString(1);
                }
            }
        }
        return status;
    }
    public static void clearStatus() throws SQLException {
        val clearSQL = "DELETE FROM payment_entity;";

        try (val clearStmt = connection.prepareStatement(clearSQL);) {
            clearStmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String getStatusCredit() throws SQLException {
        val statusSQL = "SELECT status FROM credit_request_entity;";
        String status = "";

        try (val statusStmt = connection.createStatement();) {

            try (val rs = statusStmt.executeQuery(statusSQL)) {
                if (rs.next()) {
                    status = rs.getString(1);
                }
            }
        }
        return status;
    }
    public static void clearStatusCredit() throws SQLException {
        val clearSQL = "DELETE FROM credit_request_entity;";

        try (val clearStmt = connection.prepareStatement(clearSQL);) {
            clearStmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
