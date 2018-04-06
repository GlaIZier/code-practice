package ru.glaizier.com.hackerrank.bracketsvalidator;

public class BracketsValidator {

    public static void main(String[] args) {
        System.out.println("isValid(\")\") = " + isValid(")"));
        System.out.println("isValid(\"(\") = " + isValid("("));
        System.out.println("isValid(\"())(\") = " + isValid("())("));
        System.out.println("isValid(\"a((()())(ff()fff()))\") = " + isValid("a((()())(ff()fff()))"));
    }

    public static boolean isValid(String bracketsString) {
        int balance = 0;
        for (int charIndex = 0; charIndex < bracketsString.length(); charIndex++) {
            if (bracketsString.charAt(charIndex) == '(')
                balance++;
            else if (bracketsString.charAt(charIndex) == ')')
                balance--;
            if (balance < 0)
                return false;
        }
        return (balance == 0);
    }

}