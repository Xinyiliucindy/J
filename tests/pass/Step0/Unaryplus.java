package pass;

import java.lang.System;

public class Unaryplus {
    public static int Unaryplus(int x){
        int INT = x;
        return +INT;
    }
    public static int Unaryplus(char c){
        char ch = c;
        return +ch;
    }

    public static void main(String[] args) {		
        System.out.println("Unary Plus: ");

		int i = 2;
		System.out.println(" + " + i + " = " + Unaryplus(+i));

        char ch = 'A';
		System.out.println(" + " + ch + " = " + Unaryplus(+ch));
	}
}