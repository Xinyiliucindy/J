
package pass;

import java.lang.System;

static class A {
    public static int a = 10;
    public static int getA() {
        return a;
    }
}

public class B extends A {
    public static int b = 20;
    public static int getA() {
        return a+b;
    }

    public static void main(String[] args) {
        int B = getA();
        System.out.println("B = " + B);
    }
}
