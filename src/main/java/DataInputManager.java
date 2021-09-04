import java.util.Scanner;

public class DataInputManager {

    private Scanner scanner;

    public DataInputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public int enterIntValue(String message) {
        int intValue = 0;
        System.out.println(message);
        getInt(scanner);
        intValue = scanner.nextInt();
        while (intValue <= 0) {
            System.out.println("Wrowadź liczbę większą od 0");
            getInt(scanner);
            intValue = scanner.nextInt();
        }
        return intValue;
    }

    private void getInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("podaj liczbę");
            scanner.next();
        }
    }
}
