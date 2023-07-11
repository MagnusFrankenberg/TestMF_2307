package magnus.Calculator_Problem5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Calculator5Test {

    Calculator5 calculator5;

    @BeforeEach
    void setUp() {

        calculator5 = new Calculator5(true);
    }



    @Test
    void isnumber() {
        assertEquals(true,calculator5.isNumber('8'));
        assertEquals(true,calculator5.isNumber('0'));
        assertEquals(false,calculator5.isNumber('+'));
    }

    @Test
    void isOperator() {
        assertEquals(true, calculator5.isOperator('+'));
        assertEquals(true, calculator5.isOperator('-'));
        assertEquals(true, calculator5.isOperator('/'));
        assertEquals(true, calculator5.isOperator('*'));
        assertEquals(false, calculator5.isOperator('5'));
    }

    @Test
    void makeOperation() {
        assertEquals(5,calculator5.makeOperation(2,3,'+'));
        assertEquals(-5,calculator5.makeOperation(0,5,'-'));
        assertEquals(25,calculator5.makeOperation(5,5,'*'));
        assertEquals(2.5,calculator5.makeOperation(5,2,'/'));
    }

    @Test
    void createCharList() {
        String str1 = " 1 + 2-3 - 7  ";
        assertEquals(7,calculator5.createCharList(str1).size());
        assertEquals('3',calculator5.createCharList(str1).get(4));
    }

    @Test
    void areValidCharacters() {
        String valid = "2+4-5*7/3";
        assertEquals(true,calculator5.areValidCharacters(valid));

        String nonValid = "a2+4+5+7";
        assertEquals(false,calculator5.areValidCharacters(nonValid));
    }

    @Test
    void isValidOrder_1() {
        String input = "5+5-5-5-5+20";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(true, calculator5.isValidOrder(chars));
    }

    @Test
    void isValidOrder_2() {
        String input = "5.3+5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(true, calculator5.isValidOrder(chars));
    }

    @Test
    void isValidOrder_3() {
        String input = "5.3++5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator5.isValidOrder(chars));
    }

    @Test
    void isValidOrder_4() {
        String input = "-5.3+-5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator5.isValidOrder(chars));
    }

    @Test
    void isValidOrder_5() {
        String input = "-5..3+5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator5.isValidOrder(chars));
    }

    @Test
    void isValidOrder_6() {
        String input = "-5.3+.-5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator5.isValidOrder(chars));
    }


    @Test
    void isOperatorFirst() {
        assertEquals(false,calculator5.isOperatorFirst('+'));
        assertEquals(false,calculator5.isOperatorFirst('-'));
        assertEquals(true,calculator5.isOperatorFirst('*'));
        assertEquals(true,calculator5.isOperatorFirst('/'));
    }

    @Test
    void isOperatorSecond() {
        assertEquals(true,calculator5.isOperatorSecond('+'));
        assertEquals(true,calculator5.isOperatorSecond('-'));
        assertEquals(false,calculator5.isOperatorSecond('*'));
        assertEquals(false,calculator5.isOperatorSecond('/'));
    }

    @Test
    void calculateFirst_Test1() {
        String input = "2+2+2*4*4-5";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        calculator5.calculateFirst(chars);
        assertEquals("[2.0, 2.0, 32.0, 5.0]",calculator5.parametersSecondCalculation.toString());
        assertEquals("[+, +, -]",calculator5.operatorsSecondCalculation.toString());
        System.out.println(calculator5.parametersSecondCalculation);
        System.out.println(calculator5.operatorsSecondCalculation);
    }

    @Test
    void calculateFirst_Test2() {
        String input = "2*2.5*2*4/2+2-5";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        calculator5.calculateFirst(chars);
        assertEquals("[20.0, 2.0, 5.0]",calculator5.parametersSecondCalculation.toString());
        assertEquals("[+, -]",calculator5.operatorsSecondCalculation.toString());
        System.out.println(calculator5.parametersSecondCalculation);
        System.out.println(calculator5.operatorsSecondCalculation);
    }


    @Test
    void calculateSecond_Test1() {
        String input = "2*2.5*2*4/2+2-5+20.5";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        calculator5.calculateFirst(chars);
        System.out.println(calculator5.parametersSecondCalculation);
        System.out.println(calculator5.operatorsSecondCalculation);

        assertEquals(37.5,calculator5.calculateSecond());
    }


    @Test
    void readInput() {
        assertEquals(122,calculator5.readInput("2+3*40"));
        assertEquals(26.0,calculator5.readInput("2+2+4-2+2*3*10/3"));
    }

}