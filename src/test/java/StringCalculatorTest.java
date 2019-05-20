import org.junit.jupiter.api.Test;
import pl.qbsapps.Calculator;
import pl.qbsapps.exception.NegativesNotAllowedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringCalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    void shouldReturnZeroForEmptyString() {
        assertEquals(calculator.add(""), 0);
    }

    @Test
    void shouldReturnProperValueForSingleCharacter() {
        assertEquals(calculator.add("5"), 5);
        assertEquals(calculator.add("123"), 123);
        assertEquals(calculator.add("5098"), 5098);
    }

    @Test
    void shouldReturnSumOfTwoNumbers() {
        assertEquals(calculator.add("5, 7"), 12);
        assertEquals(calculator.add("123, 7"), 130);
        assertEquals(calculator.add("5098, 2000"), 7098);
    }

    @Test
    void shouldReturnSumOfSeveralNumbers() {
        assertEquals(calculator.add("5, 7, 10"), 22);
        assertEquals(calculator.add("123, 7, 3, 6"), 139);
        assertEquals(calculator.add("5098, 2000, 1000, 4000"), 12098);
    }

    @Test
    void shouldReturnProperSumForNumbersSeparatedWithNewLinesAndCommas() {
        assertEquals(calculator.add("5\n7, 10"), 22);
        assertEquals(calculator.add("123\n7\n3\n6"), 139);
        assertEquals(calculator.add("5098, 2000, 1000\n4000"), 12098);
    }

    @Test
    void shouldSupportSettingDelimiters() {
        assertEquals(calculator.add("//;\n1;2"), 3);
        assertEquals(calculator.add("//test\n1test4"), 5);
        assertEquals(calculator.add("//--\n1--7"), 8);
    }

    @Test
    void shouldThrowProperExceptionForNegativeNumbers() {
        assertThrows(NegativesNotAllowedException.class, () -> calculator.add("5, -7, 10"));
        assertThrows(NegativesNotAllowedException.class, () -> calculator.add("-123, -7, -3, -6"));
        assertThrows(NegativesNotAllowedException.class, () -> calculator.add("1, 2, -3"));
    }

    @Test
    void thrownExceptionShouldContainProperMessage() {
        NegativesNotAllowedException negativesNotAllowedException = assertThrows(NegativesNotAllowedException.class, () -> calculator.add("1, 2, -3"));
        assertTrue(negativesNotAllowedException.getMessage().contains("Negatives not allowed [-3]"));

        negativesNotAllowedException = assertThrows(NegativesNotAllowedException.class, () -> calculator.add("-1, -2, -3"));
        assertTrue(negativesNotAllowedException.getMessage().contains("Negatives not allowed [-1, -2, -3]"));
    }
}
