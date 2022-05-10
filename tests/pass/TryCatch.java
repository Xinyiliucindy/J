package pass;
import java.lang.Exception;
import java.lang.ArithmeticException;
public class TryCatch {

    public boolean try_it(boolean a) {
        int tst;
        try {
            int tsts = 2 / 0;
        } catch (ArithmeticException e) {
            tst = 1;
        } finally {
            tst = 0;
        }
        return a;
    }

    public boolean try_double_catch(boolean a) {
        int tst;
        try {
            int tsts = 2 / 0;
        } catch (ArithmeticException e) {
            tst = 1;
        } catch (Exception e) {
            tst = 0;
        }
        return a;
    }

    public boolean try_finally(boolean a) {
        int tst;
        try {
            int tsts = 2 / 0;
        } finally {
            tst = 0;
        }
        return a;
    }

}
