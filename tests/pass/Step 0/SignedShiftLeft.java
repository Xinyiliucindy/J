package pass;

import java.lang.System;

public class SignedShiftLeft {
    public static int shiftleft(int x, int y) {
        return x<<y;
    }

    public static void main(String[] args) {
        System.out.println(shiftleft(10, 2));
    }
}