import Service.BinderUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;


@Slf4j
public class DirectoryCreator {

    public void createDirectory() {

        Config.getDirectoryPaths().forEach(path -> {
            if (Files.exists(path)) {
                try {
                    log.info("directory already exist" + BinderUtils.getFileCreationTime(path.toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Files.createDirectory(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
