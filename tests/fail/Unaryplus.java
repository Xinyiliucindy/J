package fail;
import java.lang.System;
public class Unaryplus {
    public static void main(String[] args){
        int i = 2;
		boolean bool = false;
		i = 2;
		System.out.println(i);
		i = +i;
		System.out.println(i + i);
		i = +bool;
		System.out.println(i+i+i);
    }
    
}