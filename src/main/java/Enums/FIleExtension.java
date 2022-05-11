package Enums;

public enum FIleExtension {
    MP3(".mp3"),
    JAR(".jar"),
    PDF(".pdf"),
    XML(".xml"),
    JPG(".jpeg");

    public String extension;

    FIleExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}

