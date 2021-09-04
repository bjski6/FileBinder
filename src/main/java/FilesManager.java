import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilesManager {

    private static Integer filesMoved = 0;
    private static Integer filesMovedToDev = 0;
    private static Integer filesMovedToTest = 0;

    public static void moveFile() throws IOException {

        File file = new File(Config.HOME);
        Path count = Config.getCountFilePath();

        if (!Files.exists(count)) {
            Files.createFile(count);
        } else setCountOfOperation(count);

        for (String fileName : file.list()) {
            Path pathHome = Paths.get(Config.HOME, fileName);
            Path pathDev = Paths.get(Config.DEV, fileName);
            Path pathTest = Paths.get(Config.TEST, fileName);

            if (fileName.endsWith(".jar")) {
                int hour = getFileCreationHour(fileName);
                if (isEven(hour)) extractFile(pathHome, pathDev, true);
                else extractFile(pathHome, pathTest, false);
            }
            else if (fileName.endsWith(".xml")) extractFile(pathHome, pathDev, true);
        }
        makeReport(count);
    }


    private static boolean isEven(int value) {
        return value % 2 == 0;
    }

    private static int getFileCreationHour(String fileName) throws IOException {
        BasicFileAttributeView attributes = Files.getFileAttributeView(Paths.get(Config.HOME + fileName), BasicFileAttributeView.class);
        BasicFileAttributes attr = attributes.readAttributes();
        LocalDateTime localDateTime = attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.getHour();
    }


    private static void extractFile(Path pathHome, Path path, boolean isMovedToDev) throws IOException {
        Files.move(pathHome, path);
        filesMoved++;
        if (isMovedToDev) filesMovedToDev++;
        else filesMovedToTest++;
    }

    private static void makeReport(Path count) throws IOException {
        List<String> counting = new ArrayList<>();
        counting.add("Moved files: " + filesMoved);
        counting.add("Moved files to Test: " + filesMovedToTest);
        counting.add("Moved files to Dev: " + filesMovedToDev);
        Files.write(count, counting);

    }

    private static void setCountOfOperation(Path count) throws IOException {
        Scanner scanner = new Scanner(count);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splittedLine = line.split(" ");
            int result = Integer.parseInt(splittedLine[splittedLine.length - 1]);

            if (line.contains("Test")) filesMovedToTest = result;
            else if (line.contains("Dev")) filesMovedToDev = result;
            else filesMoved = result;
        }
    }

}
