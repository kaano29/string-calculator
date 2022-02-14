import com.sun.org.glassfish.gmbal.ParameterNames;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    void returnZero_whenGivenNull() {
        int result = calculator.addition(null);

        assertEquals(0, result);
    }

    @Test
    void returnZero_whenGivenEmptyString() {
        int result = calculator.addition("");

        assertEquals(0, result);
    }

    @Test
    void returnOne_whenGivenOneAsString() {
        int result = calculator.addition("1");

        assertEquals(1, result);
    }

    @Test
    void returnHundred_whenGivenHundredAsString() {
        int result = calculator.addition("100");

        assertEquals(100, result);
    }

    @Test
    void returnSum_whenGivenTwoNumbers() {
        int result = calculator.addition("8,5");

        assertEquals(13, result);

    }

    @Test
    void returnSum_whenGivenMoreThanTwoNumbers() {
        int result = calculator.addition("10,5,5,7,8");

        assertEquals(35, result);

    }

    @Test
    void returnSum_whenGivenNewLinesBetweenNumbers() {
        int result = calculator.addition("10\n1\n2");

        assertEquals(13, result);
    }

    @Test
    void returnSum_whenGivenNewLinesAndCommaBetweenNumbers() {
        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.addition("10,1\n,"));

        String expectedMessage = "wrong format";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {";", ":", ":."})
    void customDelimiters(String delimiter) {
        String message = "//" + delimiter + "\n1" + delimiter + "2";

        int result = calculator.addition(message);

        assertEquals(3, result);
    }

    @Test
    void negativeNumbers_shouldThrowException() {
        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.addition("-1,5,-5"));

        String expectedMessage = "negatives not allowed:[-1, -5]";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void numbersBiggerThan1000_shouldBeIgnored() {
        int result = calculator.addition("2,1001");

        assertEquals(2, result);
    }


}