package pass;

import java.lang.System;

public class BitwiseAnd {
    public static int bitand (int x, int y){
        return x & y;
    }

    public static void main(String[] args){
        int x = 5;
        int y = 241;
        System.out.println("AND: "+ x + " & " + y + " = " + bitand(x,y));
    }
}