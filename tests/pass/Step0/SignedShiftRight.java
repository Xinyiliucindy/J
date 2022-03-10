package pass;

import java.lang.System;

public class SignedShiftRight {
    public static int shiftright(int x, int y) {
        return x>>y;
    }

    public static void main(String[] args) {
        System.out.println(shiftright(10, 2));
    }
}