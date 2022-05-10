package pass;
import java.lang.System;



public class doubleOperators {
    static double i = 7.3e44d;
    static double o = 8.2;
    static double b = 0.3;
    static double c = 6.;
    static double d= 8.5;
    static double[] a = {1.2, 2.5, 36., 4.6, 5., 6., 7., 8., 9., 10.};
    static double e = 0.3;
    static double f = 7.4;
    static double g = 4.6;
    static double h = 3.7;
    static double j = 6.667;
    static double k = 2.54E2;
    
    
    
    public static double plus(double x) {
		double[] da = {1.2, 2.5, 36., 4.6, 5., 6., 7., 8., 9., 10.};
		double v = x + da[2];
        return v;
	}
    

    public static void main(String[] args) {
        i = i * 0.934;
        b = b - 0.1;
        c = c + 0.2;
        d = d / 5.4;
        e = e % 0.5;
        int l = (int)1.5;
        k = k - 1.3;

        o /= 0.5;
        f += 0.6;
        g *= 8.5;
        h %= 9.5;
        j -= 2.3;
        
        System.out.println("i * 0.934 =" + i);
        System.out.println("b - 0.1 =" + b);
        System.out.println("c + 0.2 =" + c);
        System.out.println("a[5]=" + a[5]);
        System.out.println("d / 5.4 =" +d);
        System.out.println("e % 0.5 =" + e);


        System.out.println(" o /= 0.5 is " + o);
        System.out.println(" f += 0.6 is "+ f);
        System.out.println(" g *= 8.5 is " + g);
        System.out.println(" h %= 9.5 is " + h);
        System.out.println(" j -= 2.3 is " + j);
        System.out.println("(int)1.5 = " +l);



        System.out.println(plus(2.5));
        




   
        
        
    }
}