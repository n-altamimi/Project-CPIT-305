import java.io.PrintStream;
import java.sql.SQLException;

public class Main {
    private static PrintStream out;

    public static void main(String[] args) {

        try {
            DBConnection db1 = DBConnection.getInstance();
            DBConnection db2 = DBConnection.getInstance();
            if (db1 == db2) {
                System.out.println("It's a singleton");
            } else {
                System.err.println("Error: Two different objects");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}