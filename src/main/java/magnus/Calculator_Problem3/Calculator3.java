package magnus.Calculator_Problem3;

import magnus.Main;

import java.util.Scanner;

public class Calculator3 {
    private boolean isTest;
    InputValidation inputValidation;
    Scanner scanner;

    public Calculator3(boolean isTest) {
        this.isTest = isTest;
        if (!isTest) {
            inputValidation = new InputValidation(isTest);
            readInput(null);
        }
    }

    public void readInput(String testStr) {
        String inputStr;
        char[] input;
        scanner = isTest ? new Scanner(testStr) : new Scanner(System.in);

        while (true) {
            System.out.println("***With One Operator, Calculate: (exit = exit)***");
            inputStr = scanner.nextLine();
            if(inputStr.equalsIgnoreCase("exit")){
                break;
            }
            input = inputValidation.removeWhiteSpace(inputStr);

            if (inputValidation.isValidInput(input)) {
                System.out.println(calculate(input));
            }
        }
        Main.main(null);
    }

    public String calculate(char[] chars) {
        String str = new String(chars);
        char operator = 0;
        int operatorIndex = 0;
        double parameter1, parameter2;
        double result = 0;


        //Identify operator
        if (str.indexOf('+', 1) > 0)
            operator = '+';
        else if (str.indexOf('*', 1) > 0)
            operator = '*';
        else if (str.indexOf('/', 1) > 0)
            operator = '/';
        else if (str.indexOf('-', 1) > 0) //Important to have this last. (operator != neg sign)
            operator = '-';


        operatorIndex = str.indexOf(operator, 1);

        parameter1 = Double.parseDouble(str.substring(0, operatorIndex));
        parameter2 = Double.parseDouble(str.substring(operatorIndex + 1));


        //Div with 0 not allowed
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
        Calculator3 calc = new Calculator3(false);
    }
}
