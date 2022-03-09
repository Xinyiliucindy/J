package pass;

import java.lang.System;
public class BitXor {
    public static int xor(int x, int y){
        return x ^ y;
    }

    public static void main(String[] args) {
        int x = 3;
        int y = 234;
        System.out.println(x + " ^ " + y + " = " + xor(x,y) );
	}
}