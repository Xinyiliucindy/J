public class TryCatchFinally {


    public static void main(String[] args) {
        try{
            double test = Double.parseDouble(args[1]);
            double math = test/0;
        }catch(ArrayIndexOutOfBoundsException e1){
            System.out.println(e1.getMessage());
        }catch(NumberFormatException e2){
            System.out.println(e2.getMessage());
        }catch(ArithmeticException e3){
            System.out.println(e3.getMessage());
        }finally{
            System.out.println("finally done");
        }
    }
}
