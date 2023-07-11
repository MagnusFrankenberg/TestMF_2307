package magnus.Calculator_Problem4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator4 calculator4;

    @BeforeEach
    void setUp() {
       calculator4 = new Calculator4(true);
    }


    @Test
    void isnumber() {
        assertEquals(true,calculator4.isNumber('8'));
        assertEquals(true,calculator4.isNumber('0'));
        assertEquals(false,calculator4.isNumber('+'));
    }

    @Test
    void isOperator() {
        assertEquals(true, calculator4.isOperator('+'));
        assertEquals(true, calculator4.isOperator('-'));
        assertEquals(true, calculator4.isOperator('/'));
        assertEquals(true, calculator4.isOperator('*'));
        assertEquals(false, calculator4.isOperator('5'));
    }

    @Test
    void makeOperation() {
        assertEquals(5,calculator4.makeOperation(2,3,'+'));
        assertEquals(-5,calculator4.makeOperation(0,5,'-'));
        assertEquals(25,calculator4.makeOperation(5,5,'*'));
        assertEquals(2.5,calculator4.makeOperation(5,2,'/'));
    }

    @Test
    void calculate_Test1() {
        String input = "5+5";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(10, calculator4.calculate(chars));
    }

    @Test
    void calculate_Test2() {
        String input = "-5+5";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(0, calculator4.calculate(chars));
    }

    @Test
    void calculate_Test3() {
        String input = "5+5-5-5-5+20";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(15, calculator4.calculate(chars));
    }

    @Test
    void calculate_Test4() {
        String input = "2*3*4";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(24, calculator4.calculate(chars));
    }

    @Test
    void calculate_Test5() {
        String input = "2*3/4*20";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(30, calculator4.calculate(chars));
    }

    @Test
    void calculate_Test6() {
        String input = "2.5*3.5";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(8.75, calculator4.calculate(chars));
    }


    @Test
    void createCharList() {
        String str1 = " 1 + 2-3 - 7  ";
        assertEquals(7,calculator4.createCharList(str1).size());
        assertEquals('3',calculator4.createCharList(str1).get(4));
    }

    @Test
    void areValidCharacters() {
        String valid = "2+4-5*7/3";
        assertEquals(true,calculator4.areValidCharacters(valid));

        String nonValid = "a2+4+5+7";
        assertEquals(false,calculator4.areValidCharacters(nonValid));
    }

    @Test
    void isValidOrder_1() {
        String input = "5+5-5-5-5+20";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(true, calculator4.isValidOrder(chars));
    }

    @Test
    void isValidOrder_2() {
        String input = "5.3+5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(true, calculator4.isValidOrder(chars));
    }

    @Test
    void isValidOrder_3() {
        String input = "5.3++5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator4.isValidOrder(chars));
    }

    @Test
    void isValidOrder_4() {
        String input = "-5.3+-5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator4.isValidOrder(chars));
    }

    @Test
    void isValidOrder_5() {
        String input = "-5..3+5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator4.isValidOrder(chars));
    }

    @Test
    void isValidOrder_6() {
        String input = "-5.3+.-5.0";
        List<Character> chars = new ArrayList<>();
        input.chars().forEach(c -> chars.add((char) c));
        assertEquals(false, calculator4.isValidOrder(chars));
    }

    @Test
    void readInput() {
        assertEquals(24.0,calculator4.readInput("2+2+4+10+10-4"));
        assertEquals(10.0,calculator4.readInput("-2+6+4+2"));
        assertEquals(12.0,calculator4.readInput("2*2*2*3/2"));
    }
}