package pass;

import java.lang.System;

public class BitwiseOr {

    public static void main(String[] args) {
        System.out.println("6 | 7 =" + bitwiseOr(6, 7));
    }

    public static int bitwiseOr(int x, int y) {
        return x | y;
    }

}