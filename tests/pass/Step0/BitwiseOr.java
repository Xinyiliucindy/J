package pass;

import java.lang.System;

public class BitwiseOr {

    public static int bitwiseOr(int x, int y) {
        return x | y;
    }

    public static void main(String[] args) {
        int x = 2;
        int y = 5;
        System.out.println("BitwiseOr: " + x + " | " + y + " = " + bitwiseOr(x, y));
    }
}