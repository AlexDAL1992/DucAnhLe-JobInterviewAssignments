import java.util.ArrayList;
import java.io.*;

public class ReadFile {
    public static ArrayList<String> readFile(String fileName) throws IOException {

        ArrayList<String> puzzle = new ArrayList<String>();
        String readRow = null;
        boolean rowsLeft = true;

        FileReader file = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(file);

        while (rowsLeft) {
            readRow = reader.readLine();
            if (readRow == null) {
                rowsLeft = false;
            } else {
                puzzle.add(readRow);
            }
        }
        reader.close();

        return puzzle;
    }
}
