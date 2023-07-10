package magnus.Calculator_Problem5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    private boolean isTest;
    Scanner scanner;

    public Calculator(boolean isTest) {
        this.isTest = isTest;
        if (!isTest) {
            // readInput(null);
        }
    }


    public void readInput(String testStr) {
        String input = null;
        List<Character> charList;
        scanner = isTest ? new Scanner(testStr) : new Scanner(System.in);

        while (true) {
            System.out.println("***With multiple operators of different precendence***\nCalculate:");
            input = scanner.nextLine();

            if (areValidCharacters(input)) {
                charList = createCharList(input);
                if (isValidOrder(charList)) {
                    System.out.println(calculate(charList));
                }
            }
        }
    }

    public boolean areValidCharacters(String str) {
        boolean allowedChars = str.matches("^[0-9+\\-*/.]+$");
        if (!allowedChars) {
            System.out.println("You have non valid characters in your input");
        }
        return allowedChars;
    }

    public boolean isValidOrder(List<Character> charList) {
        char current;
        char next;
        for (int i = 0; i < charList.size() - 1; i++) {
            current = charList.get(i);
            next = charList.get(i + 1);

            //  if(isOperator(current)&&next=='-'){}else

            if ((isOperator(current) && isOperator(next)) ||
                    (isOperator(current) && next == '.') ||
                    (next == '.' && !isNumber(current)) ||
                    (current == '.' && !isNumber(next))) {
                System.out.println("Non valid input");
                return false;
            }
        }
        return true;
    }

    public List<Character> createCharList(String input) {
        List<Character> charList = new ArrayList<>();
        String cleanFromWhite = input.replaceAll("\\s", "");
        cleanFromWhite.chars().forEach(c -> charList.add((char) c));
        return charList;
    }

    public boolean isNumber(char c) {
        String str = String.valueOf(c);
        return str.matches(".*\\d.*");
    }

    public boolean isOperator(char c) {
        String str = String.valueOf(c);
        return str.matches("[+\\-*/]");
    }

    public boolean isDecimal(char c) {
        return c == '.';
    }


    public static void main(String[] args) {
        Calculator c = new Calculator(false);
    }

}
