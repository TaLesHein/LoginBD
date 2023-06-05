
/*  Classe destinada a manipulação de opções, em forma de saída de dados,
 *   validação de dados e seleção de opção.
 */

import java.sql.SQLException;

public class Options {

    String options[] = new String[2];

    public Options() {

        options[0] = "[0] Login";
        options[1] = "[1] Cadastrar";

    }

    public void seeOptions() {

        for (int i = 0; i < options.length; i++) {

            System.out.println(options[i]);

        }
    }

    public boolean validOption(int option) {

        if (option <= this.options.length - 1 && option >= 0) {
            return true;

        } else {
            System.out.printf("%n%nError: Option does not exist%n%n");
            return false;
        }

    }

    public void selectOption(int option) throws SQLException {

        Menu menu = new Menu();
        
        switch (option) {
            case 0:
            menu.login();
            break;

            case 1:
            System.out.println("Teste case 0");
            break;
        }
    }

    public String[] getOptions() {
        return options;
    }

}
