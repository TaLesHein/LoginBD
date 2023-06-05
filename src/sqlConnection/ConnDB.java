package sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {

    // URL
    private String URL = "jdbc:mysql://localhost:3306/login";
    private String USER = "root";
    private String PWD = null;

    private static Connection dbconn;
    private static Statement stmt;

    public void OpenDatabase() throws SQLException {

        try {
            dbconn = DriverManager.getConnection(URL, USER, PWD);
            stmt = dbconn.createStatement();
            System.out.println("Conectado com sucesso em: " + URL);
        } catch (SQLException e) {
            catchException(e);
        }

    }

    public void ExecuteQuery(String sql) throws SQLException {
        try {
            stmt.executeQuery(sql);
            System.out.println("ExecuteQuery executado com sucesso");
        } catch (SQLException e) {
            catchException(e);
        }
    }

    public ResultSet ResultQuery(String sql) {

        try {
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("ResultSet executado com sucesso");
            return rs;

        } catch (SQLException e) {
            catchException(e);
        }

        return null;

    }

    public ResultSet createQuery(String email, String password) throws SQLException {

        // Criando comando SQL com parâmetros de segurança
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ? ";

        PreparedStatement pstmt = dbconn.prepareStatement(sql);

        pstmt.setString(1, email);
        pstmt.setString(2, password);

        // Executando a Query
        ResultSet rs = pstmt.executeQuery();

        return rs;

    }

    public boolean credentialValidation(String email, String password, ResultSet rs) throws SQLException {

        rs.next();
        String emailBD = rs.getString("email");
        String passwordBD = rs.getString("senha");

        System.out.println("Valor de email: " + email);
        System.out.println("Valor de password: " + password);
        System.out.println("Valor de emailBD: " + emailBD);
        System.out.println("Valor de passwordBD: " + passwordBD);

        // Validação das credenciais
        if (rs.next()) {

            if (email.equals(emailBD) && password.equals(passwordBD)) {
                return true;
            }
        }
        return false;

    }

    private void catchException(Exception e) {
        System.out.println(e.getMessage());
    }
}
