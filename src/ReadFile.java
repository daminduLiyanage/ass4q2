import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Damindu on 11/27/2016.
 */
public class ReadFile {
    static boolean lock = false;
    static final int PID = 0;
    static final int BT= 2;
    static final int PRIORITY= 1;
    static final int AT = 3;

    public int[][] getArray() throws IOException {
        int[][] array;
        Path path = Paths.get(".\\src\\input2.txt");
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String string;
            StringTokenizer token;
            string = br.readLine();
            array = new int[Integer.parseInt(string)][4];
            int i = 0;
                while ((string = br.readLine()) != null){
                    token = new StringTokenizer(string);
                    while (token.hasMoreElements()){
                        array[i][PID] = i+1;
                        array[i][PRIORITY] = Integer.parseInt(token.nextElement().toString());
                        array[i][BT] = Integer.parseInt(token.nextElement().toString());
                        array[i][AT] = Integer.parseInt(token.nextElement().toString());
                        i++;
                    }
                }
        }
        return array;
    }
}
