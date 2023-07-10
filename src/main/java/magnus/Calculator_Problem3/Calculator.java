package magnus.Calculator_Problem3;

import java.util.Scanner;

public class Calculator {
    private boolean isTest;
    InputValidation inputValidation;
    Scanner scanner;

    public Calculator(boolean isTest) {
        this.isTest = isTest;
        if (!isTest) {
            inputValidation = new InputValidation(isTest);
            readInput(null);
        }
    }

    public void readInput(String testStr) {
        char[] input = null;
        scanner = isTest ? new Scanner(testStr) : new Scanner(System.in);

        while (true) {
            System.out.println("With One Operator, Calculate:");
            input = inputValidation.removeWhiteSpace(scanner.nextLine());

            if (inputValidation.isValidInput(input)) {
                System.out.println(calculate(input));
            }
        }
    }

    public String calculate(char[] chars) {
        String str = new String(chars);
        char operator = 0;
        int operatorIndex = 0;
        double parameter1, parameter2;
        double result = 0;


        //Identifiera operatorn
        if (str.indexOf('+', 1) > 0)
            operator = '+';
        else if (str.indexOf('*', 1) > 0)
            operator = '*';
        else if (str.indexOf('/', 1) > 0)
            operator = '/';
        else if (str.indexOf('-', 1) > 0) //Viktigt att denna ligger sist. (operator != neg tecken)
            operator = '-';


        operatorIndex = str.indexOf(operator, 1);

        parameter1 = Double.parseDouble(str.substring(0, operatorIndex));
        parameter2 = Double.parseDouble(str.substring(operatorIndex + 1));


        //Division med 0 är ej tillåtet, hoppa över slutkalkylering
        if (operator == '/' && parameter2 == 0) {
            return "Division with zero not allowed";
        } else {
            //Final calculation
            switch (operator) {
                case '+' -> result = parameter1 + parameter2;
                case '-' -> result = parameter1 - parameter2;
                case '*' -> result = parameter1 * parameter2;
                case '/' -> result = parameter1 / parameter2;
            }
            return "" + result;

        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator(false);
    }
}
