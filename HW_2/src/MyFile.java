import java.util.ArrayList;

public class MyFile {
    private final String name;
    private final String text;
    private final ArrayList<String> requirementsNames;

    public MyFile(String name, String text, ArrayList<String> requirementsNames) {
        this.name = name;
        this.text = text;
        this.requirementsNames = requirementsNames;
    }

    public MyFile(MyFile file) {
        name = file.name;
        text = file.text;
        requirementsNames = new ArrayList<String>();
        requirementsNames.addAll(file.requirementsNames);
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public boolean doesRequireAnyFile() {
        return !requirementsNames.isEmpty();
    }

    public boolean doesRequireThisFile(MyFile file) {
        return requirementsNames.contains(file.name);
    }
}
