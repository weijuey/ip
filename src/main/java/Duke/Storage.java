package Duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class Storage {
    private Path savedTaskFile;
    private DateTimeFormatter dateParser;

    public Storage() {
        Path path = Paths.get("data");
        if (!Files.exists(path)) {
            File d = path.toFile();
            if (!d.mkdir()) {
                System.out.println("Something went wrong, try again");
                System.exit(0);
            }
        }
        Path saved = Paths.get("data", "saved.txt");
        if (!Files.exists(saved)) {
            File s = saved.toFile();
            try {
                if (!s.createNewFile()) {
                    System.out.println("Something went wrong, try again");
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        savedTaskFile = saved;
        dateParser = DateTimeFormatter.ofPattern("HHmm ddMMyyyy");
    }

    public void loadSaved(TaskList lst) throws IOException{
        BufferedReader saved = Files.newBufferedReader(savedTaskFile);
        String sl = saved.readLine();
        while (sl != null) {
            char type = sl.charAt(0);
            int done = sl.charAt(1);
            int split = sl.indexOf('|',2);
            String des = sl.substring(2, split);
            String da = sl.substring(split + 1);
            switch (type) {
                case 'T':
                    lst.addTask(new ToDo(des));
                    break;
                case 'D':
                    lst.addTask(new Deadline(des, LocalDateTime.parse(da, dateParser)));
                    break;
                case 'E':
                    lst.addTask(new Event(des, LocalDateTime.parse(da, dateParser)));
                    break;
            }
            if (done == '1') {
                lst.mark(lst.length() - 1);
            }
            sl = saved.readLine();
        }
        saved.close();
    }

    public void toggleMark(int index) {
        try {
            BufferedReader old = Files.newBufferedReader(savedTaskFile);
            String nl = old.readLine();
            StringBuilder edited = new StringBuilder();
            int i = 0;
            while (nl != null) {
                char[] toEdit = nl.toCharArray();
                if (i == index) {
                    toEdit[1] = (toEdit[1] == '0' ? '1' : '0');
                }
                edited.append(toEdit).append("\n");
                i++;
                nl = old.readLine();
            }
            old.close();
            BufferedWriter file = Files.newBufferedWriter(savedTaskFile);
            file.write(edited.toString());
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTask(String task) {
        try {
            BufferedWriter file = Files.newBufferedWriter(savedTaskFile, APPEND);
            file.write(task);
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int index) {
        try {
            BufferedReader old = Files.newBufferedReader(savedTaskFile);
            String nl = old.readLine();
            StringBuilder edited = new StringBuilder();
            int i = 0;
            while (nl != null) {
                if (i != index) {
                    edited.append(nl).append("\n");
                }
                i++;
                nl = old.readLine();
            }
            old.close();
            BufferedWriter file = Files.newBufferedWriter(savedTaskFile);
            file.write(edited.toString());
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll() {
        try {
            BufferedWriter file = Files.newBufferedWriter(savedTaskFile, TRUNCATE_EXISTING);
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
