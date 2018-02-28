import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2018/2/13.
 * Note：this class will not compile yet
 */
public class ListOfNumber {
    private List<Integer> list;
    private static final int SIZE = 20;
    public ListOfNumber() {
        list = new ArrayList<>();
        for(int  i = 0; i < SIZE; i++) {
            list.add(new Integer(i));
        }
    }
    public void writeList() {
        // The FileWriter constructor throws IOException,which mush be caught.
        PrintWriter out = null;
        try {
            out = new PrintWriter(new File("OutFile.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < SIZE; i++) {
            // The get(int) mothod throws IndexOutOfBoundsException,which mush bu caugth.
            out.println("value at:" + i + "= " + list.get(i));
        }
        out.close();
    }
}
