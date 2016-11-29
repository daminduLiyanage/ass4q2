import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Damindu on 11/24/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        final int PID = 0;
        final int BT = 1;
        final int PRIORITY = 2;


        ReadFile file = new ReadFile();
        InputArray ia = new InputArray(file.getArray());
        Heapsort hp = new Heapsort();
        RRWaitQ rrq = new RRWaitQ();
        RRGantt rrGantt = new RRGantt();
        RR rr = new RR(rrq, rrGantt, ia, hp);

        System.out.println("\nPress enter to exit");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
