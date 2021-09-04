import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class Config {

    final static String MAIN_DIR = "src/main/resources/";
    final static String HOME = MAIN_DIR + "HOME/";
    final static String DEV = MAIN_DIR + "DEV/";
    final static String TEST = MAIN_DIR + "TEST/";
    final static String COUNT_TXT = "src/main/resources/HOME/count.txt";

    static List<Path> getDirectoryPaths() {
        return List.of(
                Paths.get(HOME),
                Paths.get(DEV),
                Paths.get(TEST)
        );
    }

    static Path getCountFilePath() {
        return Paths.get(COUNT_TXT);
    }
}