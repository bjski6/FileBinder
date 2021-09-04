import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;


@Slf4j
public class DirectoryCreator {

    public static void createDirectory() {

        Config.getDirectoryPaths().forEach(path -> {
            if (Files.exists(path)) log.info("directory already exist");
            else {
                try {
                    Files.createDirectory(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
