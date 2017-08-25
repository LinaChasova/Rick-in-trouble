/**
 * Created by AlinaCh on 24.02.2017.
 */
public class Comparator {

    public Double ACCURACY = 1e-4;

    public boolean smallerThan(double a, double b) {
        return ((Math.abs(a - b) > ACCURACY) && ((b - a) > 0));
    }

    public boolean greaterThanOrEqual(double a, double b) {
        return ((Math.abs(a - b) < ACCURACY) || (Math.abs(a - b) > ACCURACY) && ((a - b) > 0));
    }

    public boolean greaterThan(double a, double b) {
        return (Math.abs(a - b) > ACCURACY) && ((a - b) > 0);
    }

    public boolean equal(double a, double b) {
        return (Math.abs(a - b) < ACCURACY);
    }
}
