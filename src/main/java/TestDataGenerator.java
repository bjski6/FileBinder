import Enums.FIleExtension;
import com.github.javafaker.Faker;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class TestDataGenerator {

    private DataInputManager dataInputManager;
    private Faker faker;
    private final String inputSampleMessage = "Wprowadż liczbę prób do wylosowania plików";

    public TestDataGenerator(DataInputManager dataInputManager, Faker faker) {
        this.dataInputManager = dataInputManager;
        this.faker = faker;
    }

    public void testDataGenerate() {
        int numOfSample = dataInputManager.enterIntValue(inputSampleMessage);
        filesGenerate(FIleExtension.MP3.getExtension(), FIleExtension.JAR.getExtension(), numOfSample);
        filesGenerate(FIleExtension.PDF.getExtension(), FIleExtension.XML.getExtension(), numOfSample);
    }

    private void filesGenerate(String target, String replacement, int range) {
        IntStream.range(0, range)
                .mapToObj(number -> faker.file().fileName())
                .filter(fileName -> fileName.endsWith(target))
                .map(fileName -> prepareFileName(fileName, target, replacement))
                .map(Paths::get)
                .forEach(filePath -> {
                    try {
                        Files.createFile(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private String prepareFileName(String name, String target, String replacement) {
        String result = name.replaceAll("/", "&").replace(target, replacement);
        return Config.HOME + result;
    }
}


