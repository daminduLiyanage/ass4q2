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

        RRWaitQ rrq = new RRWaitQ();
        RRGantt rrGantt = new RRGantt();
        RR rr = new RR(rrq, rrGantt, ia);
        //
    }
}
