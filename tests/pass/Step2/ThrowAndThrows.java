package pass;

import java.lang.System;
import java.lang.IllegalArgumentException;
import java.lang.Math;
import java.lang.Double;
import java.lang.Exception;

public class ThrowAndThrows {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double result = sqrt(x);
        System.out.println(result);
    }

    private static double sqrt(double x) throws IllegalArgumentException {
//        if (x < 0.0) {
//            throw new IllegalArgumentException("x must be positve");
//        }
        return Math.sqrt(x);
    }

    private static void throwtest(){
        IllegalArgumentException x = new IllegalArgumentException("This is a throw test!");
        throw x;
    }
}
