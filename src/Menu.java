import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import entities.Person;
import sqlConnection.ConnDB;

public class Menu {

    Scanner scan = new Scanner(System.in);

    public void homePage() throws SQLException {

        Options options = new Options();

        int option = -1;
        boolean optionValid = false;
        do {
            System.out.println("=== Conecta DB ===");
            options.seeOptions();

            try {

                option = scan.nextInt();
                optionValid = options.validOption(option);

            } catch (Exception e) {
                System.out.println("Error: Options is Invalid");
                scan.nextLine();
            }

        } while (optionValid == false);

        options.selectOption(option);

    }

    public void login() throws SQLException {

        ConnDB dbconn = new ConnDB();

        System.out.print("Email: ");
        String email = scan.nextLine();

        System.out.println();

        System.out.print("Senha: ");
        String password = scan.nextLine();

        ResultSet rs = dbconn.createQuery(email, password);

        boolean isValid = dbconn.credentialValidation(email, password, rs);

        if (isValid == true){
            System.out.println("Sucess: Bem-Vindo ");
        }
        else{
            System.out.println("Error: Email ou senha incorreta");
        }
    }
}
