package selenium;

public class ChangeToFileExtension {

    public String extension() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac") || os.contains("linux"))
            return "";
        else
            return ".exe";
    }
}