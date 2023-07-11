package magnus.Calculator_Problem4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    private boolean isTest;
    Scanner scanner;


    public Calculator(boolean isTest) {
        this.isTest = isTest;
        if(!isTest){
            readInput(null);
        }
    }


    public double readInput(String testStr) {
        String input = null;
        double output;
        List<Character> charList;
        scanner = isTest ? new Scanner(testStr) : new Scanner(System.in);

        while (true) {
            System.out.println("***With multiple operators of same precendence***\nCalculate:");
            input = scanner.nextLine();

            if (areValidCharacters(input)) {
                charList = createCharList(input);
                if(isValidOrder(charList)) {
                    output =calculate(charList);
                    System.out.println(output);
                    return output;
                }
            }
        }
    }


    public boolean areValidCharacters(String str){
        boolean allowedChars = str.matches("^[0-9+\\-*/.]+$");
        if(!allowedChars){
            System.out.println("You have non valid characters in your input");
        }
        return allowedChars;
    }

    public boolean isValidOrder(List<Character> charList){
        char current;
        char next;
        for (int i = 0; i < charList.size()-1; i++) {
            current= charList.get(i);
            next= charList.get(i+1);

                if((isOperator(current)&&isOperator(next))||
                    (isOperator(current)&&next=='.')||
                    (next=='.'&&!isNumber(current))||
                    (current=='.'&&!isNumber(next))) {
                    System.out.println("Non valid input");
                return false;
            }
        }
        return true;
    }



    public List<Character> createCharList(String input) {
        List<Character> charList = new ArrayList<>();
        String cleanFromWhite = input.replaceAll("\\s","");
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

    public boolean isDecimal(char c){
        return c == '.';
    }



    public double calculate(List<Character> chars) {
        double result = 0.0;
        double currentNumber;
        String currentNumberString = "";
        char operator = chars.get(0)=='-'? '-':'+';

        for (int i = 0; i < chars.size(); i++) {
            if(isNumber(chars.get(i))||isDecimal(chars.get(i))){
                currentNumberString += chars.get(i);
            }else if(isOperator(chars.get(i))){
                if(i!=0) {
                    currentNumber = Double.parseDouble(currentNumberString);
                    result = makeOperation(result, currentNumber, operator);
                    operator = chars.get(i);  //next rounds operator
                   // currentNumberString = chars.get(i+1)=='-'?"-":"";
                       currentNumberString = "";
                }
            }
        }
        //last number in string is added outside loop
        currentNumber = Double.parseDouble(currentNumberString);
        result = makeOperation(result,currentNumber,operator);
        return result;
    }


    public double makeOperation(double param1, double param2, char operator){
        double result=0.0;
        switch (operator) {
            case '+' -> result = param1 + param2;
            case '-' -> result = param1 - param2;
            case '*' -> result = param1 * param2;
            case '/' -> result = param1 / param2;
        }
        return result;
    }

    public static void main(String[] args) {
        Calculator c = new Calculator(false);
    }



}
