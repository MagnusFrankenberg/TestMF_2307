package magnus.Calculator_Problem5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Calculator {

    private boolean isTest;
    Scanner scanner;

    List<Double> parametersSecondCalculation;
    List<Character> operatorsSecondCalculation;

    public Calculator(boolean isTest) {
        this.isTest = isTest;
        parametersSecondCalculation = new ArrayList<>();
        operatorsSecondCalculation = new ArrayList<>();
        if (!isTest) {
             readInput(null);
        }
    }


    public double readInput(String testStr) {
        String input;
        Double output;
        List<Character> charList;
        scanner = isTest ? new Scanner(testStr) : new Scanner(System.in);

        while (true) {
            System.out.println("***With multiple operators of different precendence***\nCalculate:");
            input = scanner.nextLine();

            if (areValidCharacters(input)) {
                charList = createCharList(input);
                if (isValidOrder(charList)) {
                    calculateFirst(charList);
                    output = calculateSecond();
                    System.out.println(output);
                    return output;
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

    public boolean isOperatorFirst(char c){
        return c == '*'||c == '/';
    }

    public boolean isOperatorSecond(char c){
        return c == '+'||c == '-';
    }



    public void calculateFirst(List<Character> charList) {
        String currentNumberString = "";
        String previousNumberString = "";
        double currentNumber, previousNumber;
        double result = 0.0;
        char operator = '0';

        char current;
        for (int i = 0; i < charList.size(); i++) {
            current = charList.get(i);

            if(isNumber(current)||isDecimal(current)){
                currentNumberString += current;
            }
            if(isOperatorFirst(current)){
                if(!isOperatorFirst(operator)) {
                    previousNumberString = currentNumberString;
                    operator = current;
                    currentNumberString = "";
                }else if(isOperatorFirst(operator)){
                    currentNumber = Double.parseDouble(currentNumberString);
                    previousNumber = Double.parseDouble(previousNumberString);
                    result = makeOperation(previousNumber, currentNumber, operator);
                    previousNumberString = String.valueOf(result);
                    operator = current;
                    currentNumberString = "";
                }
            }
            if(isOperatorSecond(current)){
                if(i==0){
                    currentNumberString += current;
                }else if(operator=='0'){
                    currentNumber = Double.parseDouble(currentNumberString);
                    parametersSecondCalculation.add(currentNumber);
                    currentNumberString = "";
                    operator = current;
                    operatorsSecondCalculation.add(current);
                }else if(isOperatorFirst(operator)){
                    currentNumber = Double.parseDouble(currentNumberString);
                    previousNumber = Double.parseDouble(previousNumberString);
                    result = makeOperation(previousNumber, currentNumber, operator);
                    parametersSecondCalculation.add(result);
                    operatorsSecondCalculation.add(current);
                    operator = current;
                    currentNumberString = "";
                    previousNumberString = "";
                }else if(isOperatorSecond(operator)){
                    currentNumber = Double.parseDouble(currentNumberString);
                    parametersSecondCalculation.add(currentNumber);
                    operatorsSecondCalculation.add(current);
                    currentNumberString = "";
                }
            }
            if(i== charList.size()-1){
                if(isOperatorFirst(operator)){
                    currentNumber = Double.parseDouble(currentNumberString);
                    previousNumber = Double.parseDouble(previousNumberString);
                    result = makeOperation(previousNumber, currentNumber, operator);
                    parametersSecondCalculation.add(result);
                }else if(isOperatorSecond(operator)){
                    currentNumber = Double.parseDouble(currentNumberString);
                    parametersSecondCalculation.add(currentNumber);
                }
            }
        }
    }

    public double calculateSecond(){
       double result = parametersSecondCalculation.stream().reduce((acc, p)->
                makeOperation(acc,p,getNextOperator())).orElse(0.0);
       parametersSecondCalculation.clear();
       return result;
    }

    public char getNextOperator(){
        char nextOperator = operatorsSecondCalculation.get(0);
        operatorsSecondCalculation.remove(0);
        return nextOperator;
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
