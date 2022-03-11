
package fail;

import java.lang.Integer;
import java.lang.System;

// This program has type errors and shouldn't compile.

public class SignedShiftLeftErrors {

    public static void main(String[] args) {
        char ch = 'a';
        int i = -2;
        System.out.println(ch >> i);
    }
}
