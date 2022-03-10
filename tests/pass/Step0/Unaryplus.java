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
		int i = 2;
		System.out.println("The value of i is " + Unaryplus(+i));

        char ch = 'A';
		System.out.println("The value of A is " + Unaryplus(+ch));
	}
}