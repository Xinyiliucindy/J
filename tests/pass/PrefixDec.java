package pass;

import java.lang.System;

public class PrefixDec {
    public static int prefixDecrement(int x) {
	    return --x;
    }
    
    public static void main(String[] args) {
        int x = 3;
        System.out.println("PrefixDec: " + "--" + x + " = " + prefixDecrement(x));
    }
}