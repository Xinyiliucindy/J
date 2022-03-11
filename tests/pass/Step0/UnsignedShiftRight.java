package pass;

import java.lang.System;

public class UnsignedShiftRight {
    public static int unsignedshiftright(int x, int y) {
        return x>>>y;
    }

    public static void main(String[] args) {
        int x = 10;
        int y = 2;
        System.out.println("Unsigned Shift Right: " + x + " >>> " + y + " = " + unsignedshiftright(x, y));
    }
}