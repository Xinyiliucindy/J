package pass;

import java.lang.System;

public class UnSignedShiftRight {
    public static int unsignedshiftright(int x, int y) {
        return x>>>y;
    }

    public static void main(String[] args) {
        System.out.println(unsignedshiftright(10, 2));
    }
}