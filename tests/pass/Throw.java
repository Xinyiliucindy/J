package pass;
import java.lang.Exception;
import java.lang.System;

public class Throw {
    public void m() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
