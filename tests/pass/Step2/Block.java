package pass;

import java.lang.System;


public class ABlock {
    
    // Static Block
    static {
        System.out.println("A static block");
    }

    // IIB: Instance Initialization Block
    {
        System.out.println("A IIB 1");
    }

    {
        System.out.println("A IIB 2");
    }

    public ABlock(){
        System.out.println("A Constructor called");
    }

    public Hello(){
        System.out.println("A Normal Block");
    }

}


public class BBlock extends ABlock {
    static {
        System.out.println("B static block");
    }

    {
        System.out.println("B IIB 1");
    }

    BBlock(){
        System.out.println("B Constructor called");
    }

    {
        System.out.println("B IIB 2");
    }


    public static void main(String[] args) {
        System.out.println("Invoke main in BBlock.");

        new BBlock();
        System.out.println("-----------------------");
        new BBlock().Hello();
    }
}
