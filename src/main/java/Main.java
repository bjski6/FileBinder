import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        DirectoryCreator.createDirectory();
        try {
            FilesManager.moveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

