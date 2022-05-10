package pass;

import java.lang.System;
// import java.nio.file.SecureDirectoryStream;

interface FirstInterface {
    public abstract void myFirstMethod(); // interface method
}

interface SecondInterface {
    public abstract void mySecondMethod(); // interface method
}

interface ThirdInterface extends SecondInterface {
    public abstract void mySecondMethod();
}

public class DemoClass implements FirstInterface, SecondInterface, ThirdInterface {
    public void myFirstMethod() {
        System.out.println("Some text..");
    }
    public void mySecondMethod() {
        System.out.println("Some other text...");
    }

    public static void main (String[] args) {
        DemoClass myObj = new DemoClass();
        myObj.myFirstMethod();
        myObj.mySecondMethod();
    }
}
