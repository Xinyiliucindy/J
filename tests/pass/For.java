package pass;

import java.lang.System;

public class For {
    public static int forloop(int m) {
        int sum=0;

        for(int i=0;i<m;i++)
        {
            sum=sum+1;
        }
        return sum;
    }
    public static void main(String[] args) {
        int m=10;
        System.out.println("The number of loop is: " + For.forloop(m));
        //output should be 10
    }
}
