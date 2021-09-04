package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class BinderUtils {

    public static LocalDateTime getFileCreationTime(String fileName) throws IOException {
        BasicFileAttributeView attributes = Files.getFileAttributeView(Paths.get(fileName), BasicFileAttributeView.class);
        BasicFileAttributes attr = attributes.readAttributes();
        LocalDateTime localDateTime = attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    public static boolean isEven(int value) {
        return value % 2 == 0;
    }
}
