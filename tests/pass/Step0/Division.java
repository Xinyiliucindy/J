package pass;

import java.lang.System;

public class Division {
    public static int divide (int x, int y) {
        return x/y;
    }

    public static void main(String[] args) {
        int x = 15;
        int y = 2;
        System.out.println("Division: " + x + " / " + y + " = " + divide(x,y));
    }
}
