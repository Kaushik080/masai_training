package com.example;

public class Main{
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result1 = calculator.add(3,2);
        int result2 = calculator.subtract(10,2);
        int result3 = calculator.multiply(3,2);
        int result4 = calculator.divide(10,2);
//        int result5 = calculator.divide(10,0);

        System.out.println("Add : " + result1 + " Sub : " + result2 + " Mul : " + result3 + " Div1 : " + result4 );

        StringUtils util = new StringUtils();
        boolean resulta = util.isPalindrome("Racecar");
        boolean resultb = util.isPalindrome("carrace");

        System.out.println(resulta);
        System.out.println(resultb);

        String resultc = util.reverse("Thomas");
        System.out.println(resultc);

    }
}