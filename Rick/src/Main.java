import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;
import java.text.NumberFormat;

/**
 * Created by AlinaCh on 23.02.2017.
 */
public class Main {

    public static Double[][] table;
    public static int rows;
    public static int columns;
    public static Comparator c;

    /**
     * reads input file, and puts it into array
     */
    public static void read() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(new File("input.csv"));
        ArrayList<Double[]> data = new ArrayList<>();
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        while (sc.hasNextLine()) {
            String[]temp = sc.nextLine().split(",");
            columns = temp.length;
            Double[]d = new Double[columns];
            for (int j = 0; j < columns; j++) {
                d[j] = format.parse(temp[j]).doubleValue();
            }
            data.add(d);
        }
        rows = data.size();
        table = data.toArray(new Double[rows][columns]);
    }

    /**
     * writes the column which stores time measurements.
     * @param s column
     */
    public static void write(String s) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "ascii"))) { writer.write(s); }
        catch (IOException ex) { }
    }

    /**
     * sorts array in increasing order. it goes sequentially swapping elements, so
     * smaller one goes before bigger one. if in first iteration no swapping was made, array is sorted
     * @param col number of column which should be sorted
     */
    public static void bubbleSort(int col) {
        boolean swapped = true;
        int j = 0;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < rows - j; i++) {
                if (c.greaterThan(table[i][col], table[i + 1][col])) {
                    swap(i, i+1, col);
                    swapped = true;
                }
            }
        }
    }

    /**
     * swapping elements, by assigning temporary variable value on of the elements
     * @param i element to be swapped
     * @param j element to be swapped
     * @param col number of column
     */
    public static void swap(int i, int j, int col){
        double temp = table[i][col];
        table[i][col] = table[j][col];
        table[j][col] = temp;
    }

    /**
     * sorts columns and then searches for the arithmetic progression that is different from 0
     * @return number of columng with arithmetic progression
     */
    public static int rickProblem() {
        c = new Comparator();
        double difference;
        int res = 0;
        for (int j = 0; j < columns; j++) {
            bubbleSort(j);
            if (!c.equal(table[1][j], table[0][j])){
                difference = table[1][j] - table[0][j];
                for (int i = 1; i < (rows - 1); i++) {
                    if (!c.equal(table[i + 1][j] - table[i][j], difference))
                        break;
                    res = j;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) throws FileNotFoundException, ParseException {
        read();
        write(Integer.toString(rickProblem()));
    }
}
