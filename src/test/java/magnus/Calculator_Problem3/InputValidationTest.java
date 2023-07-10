package magnus.Calculator_Problem3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationTest {

    InputValidation inputValidation;
    @BeforeEach
    void setUp() {
        inputValidation = new InputValidation(true);
    }

    @Test
    void removeWhiteSpace(){
        String testStr = "1  + 2  ";

        assertEquals('1',inputValidation.removeWhiteSpace(testStr)[0]);
        assertEquals('+',inputValidation.removeWhiteSpace(testStr)[1]);
        assertEquals('2',inputValidation.removeWhiteSpace(testStr)[2]);
    }

    @Test
    void areValidChars() {
        char[] chars1 = {'1','+','3'};
        assertEquals(true,inputValidation.areValidChars(chars1));

        char[] chars2 = "1234567890+-*/.".toCharArray();
        assertEquals(true,inputValidation.areValidChars(chars2));

        char[] chars3 = "% €&".toCharArray();
        assertEquals(false,inputValidation.areValidChars(chars3));

        char[] chars4 = "%1234567890+-*/.".toCharArray();
        assertEquals(false,inputValidation.areValidChars(chars4));

    }


    @Test
    void validStartEnd() {
        char[] chars1 = {'1','+','3'};
        assertEquals(true,inputValidation.validStartEnd(chars1));

        char[] chars2 = {'+','3','+','3'};
        assertEquals(false,inputValidation.validStartEnd(chars2));

        char[] chars3 = {'-','3','+','3'};
        assertEquals(true,inputValidation.validStartEnd(chars3));

        char[] chars4 = {'-','3','4','*'};
        assertEquals(false,inputValidation.validStartEnd(chars4));
    }

    @Test
    void operatorIncluded() {
        char[] chars1 = {'1','+','3'};
        assertEquals(true,inputValidation.operatorIncluded(chars1));

        char[] chars2 = {'-','1','+','4'};
        assertEquals(true,inputValidation.operatorIncluded(chars2));

        char[] chars3 = {'-','1','4','-'};
        assertEquals(false,inputValidation.operatorIncluded(chars3));

        char[] chars4 = {'*','1','4','5'};
        assertEquals(false,inputValidation.operatorIncluded(chars4));

    }

    @Test
    void maxOneOperator() {
        char[] chars1 = {'1','+','3'};
        assertEquals(true,inputValidation.maxOneOperator(chars1));

        char[] chars2 = {'-','1','+','4'};
        assertEquals(true,inputValidation.maxOneOperator(chars2));

        char[] chars3 = {'-','1','*','-','4'};
        assertEquals(true,inputValidation.maxOneOperator(chars3));

        char[] chars6 = {'-','1','-','-','4'};
        assertEquals(true,inputValidation.maxOneOperator(chars6));

        char[] chars4 = {'-','1','*','*','4'};
        assertEquals(false,inputValidation.maxOneOperator(chars4));

        char[] chars5 = {'-','1','-','*','4'};
        assertEquals(false,inputValidation.maxOneOperator(chars5));


    }

    @Test
    void maxOneDecimal() {
        char[] chars1 = {'0','.','1','+','0','.','3'};
        assertEquals(true,inputValidation.maxOneDecimal(chars1));

        char[] chars2 = {'0','.','1','+','0','.','.','3'};
        assertEquals(false,inputValidation.maxOneDecimal(chars2));
    }

    @Test
    void isValidInput() {
        char[] chars1 = {'0','.','1','+','0','.','3'};
        assertEquals(true,inputValidation.maxOneDecimal(chars1));

        char[] chars2 = {'-','1','*','*','4'};
        assertEquals(false,inputValidation.maxOneOperator(chars2));

        char[] chars3 = "%1234567890+-*/.".toCharArray();
        assertEquals(false,inputValidation.areValidChars(chars3));
    }


}