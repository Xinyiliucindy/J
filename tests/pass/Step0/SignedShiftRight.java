package pass;

import java.lang.System;

public class SignedShiftRight {
    public static int shiftright(int x, int y) {
        return x>>y;
    }

    public static void main(String[] args) {
        int x = 100;
        int y = 2;
        System.out.println("Signed Shift Right: " + x + " >> " + y + " = " + shiftright(x, y));
    }
}