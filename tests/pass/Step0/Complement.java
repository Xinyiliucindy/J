package pass;
import java.lang.System;

// gengxingguang: I couldnt get through operator complement, because i couldnt find the CLE code for the complement operator.
public class Complement {
    public static int complement(int x){
       
        return ~x;
    }


    public static void main(String[] args) {
		
		System.out.println("~5 -> "+complement(5));
	}
}