import com.github.javafaker.Faker;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DirectoryCreator directoryCreator = new DirectoryCreator();
        directoryCreator.createDirectory();

        Scanner scanner = new Scanner(System.in);
        Faker faker = new Faker();

        DataInputManager dataInputManager = new DataInputManager(scanner);
        TestDataGenerator testDataGenerator = new TestDataGenerator(dataInputManager, faker);
        testDataGenerator.testDataGenerate();

        try {
            FilesManager.segregateFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

