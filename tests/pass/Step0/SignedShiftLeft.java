package pass;

import java.lang.System;

public class SignedShiftLeft {
    public static int shiftleft(int x, int y) {
        return x<<y;
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 2;
        System.out.println("Signed Shift Left: " + x + " << " + y + " = " + shiftleft(x, y));
    }
}