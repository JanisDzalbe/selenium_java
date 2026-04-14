package selenium;

public class ChangeToFileExtension {

    public String extension() {
        System.out.println(System.getProperty("os.name") );
        if (System.getProperty("os.name").toLowerCase().contains("mac") || System.getProperty("os.name").toLowerCase().contains("linux"))
            return "";
        else
            return ".exe";
    }
}
