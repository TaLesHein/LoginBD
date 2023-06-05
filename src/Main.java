import java.sql.SQLException;

import sqlConnection.ConnDB;

public class Main {
    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        ConnDB connDB = new ConnDB();

        connDB.OpenDatabase();
        menu.homePage();
    }
}
