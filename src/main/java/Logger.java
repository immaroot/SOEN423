import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;

public class Logger {

    String name;
    Path file_path;

    public Logger(String name) throws IOException {
        this.name = name;
        Files.createDirectories(Path.of("./logs"));
        file_path = Path.of("./logs/", name + ".txt");
        String welcomeMessage =  Calendar.getInstance().getTime() +  "\n--------------\nWelcome: " + name;
        message(welcomeMessage);
    }

    public void message(String message) {
        File file = new File(String.valueOf(file_path));
        FileWriter fr = null;
        BufferedWriter br = null;
        String messageWithNewLine = message + System.getProperty("line.separator");
        try{
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.write(messageWithNewLine);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                assert br != null;
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
