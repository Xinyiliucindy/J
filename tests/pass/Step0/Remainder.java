package pass;

import java.lang.System;

public class Remainder {
    public static int remainder(int x, int y) {
        return x%y;
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 3;
        System.out.println("Remainder: " + x + " % " + y + " = " + remainder(x, y));
    }
}
