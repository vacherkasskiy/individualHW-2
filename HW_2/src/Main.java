import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void recordAllFiles(File[] files, ArrayList<MyFile> allFiles) {
        for (File file : files) {
            if (file.isDirectory()) {
                recordAllFiles(file.listFiles(), allFiles);
            } else {
                String name = (new StringBuffer(file.getPath())).delete(0, 5).toString();
                StringBuilder text = new StringBuilder("");
                ArrayList<String> requirements = new ArrayList<String>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (Objects.equals(line.split(" ")[0], "require")) {
                            // get file path from "require" line
                            StringBuffer requirement = new StringBuffer(line.split(" ")[1]);
                            requirement.delete(0, 1);
                            requirement.delete(requirement.length() - 1, requirement.length());
                            //

                            // add this file to requirements of current file
                            requirements.add(requirement.toString());
                            //
                        } else {
                            text.append(line);
                            text.append('\n');
                        }
                    }
                    allFiles.add(new MyFile(name, text.toString(), requirements));
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<MyFile> allFiles = new ArrayList<MyFile>();
        File mainFolder = new File("Save");
        recordAllFiles(mainFolder.listFiles(), allFiles);

        // sorting by our own comparator
        allFiles.sort(new Comparator<MyFile>() {
            @Override
            public int compare(MyFile o1, MyFile o2) {
                if (o1.doesRequireThisFile(o2)) {
                    return 1;
                } else if (o2.doesRequireThisFile(o1)) {
                    return -1;
                }
                return 0;
            }
        });

        // output
        StringBuilder answer = new StringBuilder("");
        System.out.println();
        for (MyFile file : allFiles) {
            answer.append(file.getText());
            System.out.println(file.getName());
        }
        System.out.println();
        System.out.println(answer);
    }
}