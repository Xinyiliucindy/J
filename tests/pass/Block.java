package pass;

import java.lang.System;


public class ABlock {
    
    // Static Block
    static {
        System.out.println("A static block");
    }

    // IIB: Instance Initialization Block
    {
        System.out.println("A IIB");
    }

    public void Amethod(){
        System.out.println("Method of ABlock");
    }
}


public class BBlock extends ABlock {
    {
        System.out.println("B IIB");
    }

    static {
        System.out.println("B static block");
    }

    public void Bmethod(){
        System.out.println("Method of BBlock");
    }    

    public static void main(String[] args) {
        System.out.println("Invoke main in BBlock.");

        BBlock bb = new BBlock();
        bb.Amethod();
        bb.Bmethod();
    }
}
