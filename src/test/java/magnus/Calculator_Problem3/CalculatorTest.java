package magnus.Calculator_Problem3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator3 calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator3(true);
    }

    @Test
    void calculate() {
        char[] chars1 = {'1','+','3'};
        assertEquals("4.0",calculator.calculate(chars1));

        char[] chars2 = "1.3+4.3".toCharArray();
        assertEquals("5.6",calculator.calculate(chars2));

        char[] chars3 = "5/2".toCharArray();
        assertEquals("2.5",calculator.calculate(chars3));

        char[] chars4 = "50*3".toCharArray();
        assertEquals("150.0",calculator.calculate(chars4));

        char[] chars5 = "50*-3".toCharArray();
        assertEquals("-150.0",calculator.calculate(chars5));

        char[] chars6 = "-50--3".toCharArray();
        assertEquals("-47.0",calculator.calculate(chars6));

        char[] chars7 = "50/0".toCharArray();
        assertEquals("Division with zero not allowed",calculator.calculate(chars7));

    }

    @Test
    void readInput() {
    }
}