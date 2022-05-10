package pass;

import java.lang.System;
import java.lang.Exception;

public class For {
    public static int forloop(int m) {
        int sum=0;
        int[] item=new int[11];
        for(int i=0;i<=m;i=i+1)
        {
            sum=sum+1;
            item[i]=sum;
        }
        for(int j : item){
           System.out.println(j);
        }
        return sum;
    }
    public static void main(String[] args) {
        int m=10;
        System.out.println("The number of loop is: " + For.forloop(m));
        //output should be 11
    }
}
