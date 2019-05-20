import org.junit.jupiter.api.Test;
import pl.qbsapps.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    void calculatorShouldReturnZeroForEmptyString() {
        assertEquals(calculator.add(""), 0);
    }

    @Test
    void calculatorShouldReturnProperValueForSingleCharacter() {
        assertEquals(calculator.add("5"), 5);
        assertEquals(calculator.add("123"), 123);
        assertEquals(calculator.add("5098"), 5098);
    }

    @Test
    void calculatorShouldReturnSumOfTwoNumbers() {
        assertEquals(calculator.add("5, 7"), 12);
        assertEquals(calculator.add("123, 7"), 130);
        assertEquals(calculator.add("5098, 2000"), 7098);
    }

    @Test
    void calculatorShouldReturnSumOfSeveralNumbers() {
        assertEquals(calculator.add("5, 7, 10"), 22);
        assertEquals(calculator.add("123, 7, 3, 6"), 139);
        assertEquals(calculator.add("5098, 2000, 1000, 4000"), 12098);
    }
}
