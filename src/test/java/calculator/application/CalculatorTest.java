package calculator.application;

import calculator.domain.parser.StringParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        StringParser parser = new StringParser();
        calculator = new Calculator(parser);
    }

    @Test
    @DisplayName("기본 숫자 입력: 1,2,3 -> 합계 6")
    void addNumbers() {
        // Given
        String input = "1,2,3";

        // When
        int result = calculator.add(input);

        // Then
        assertEquals(6, result);
    }

    @Test
    @DisplayName("기본 구분자 쉼표와 콜론 혼합: 1,2:3 -> 합계 6")
    void addDefaultDelimiters() {
        // Given
        String input = "1,2:3";

        // When
        int result = calculator.add(input);

        // Then
        assertEquals(6, result);
    }

    @Test
    @DisplayName("빈 문자열 입력 -> 결과 0")
    void addEmptyInput() {
        // Given
        String input = "";

        // When
        int result = calculator.add(input);

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("null 입력 -> 결과 0")
    void addNullInput() {
        // Given
        String input = null;

        // When
        int result = calculator.add(input);

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("음수가 포함된 입력 -> IllegalArgumentException 발생")
    void addNegativeNumber() {
        // Given
        String input = "1,-2,3";

        // When & Then
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(input)
        );
    }

    @Test
    @DisplayName("잘못된 커스텀 구분자 입력 -> IllegalArgumentException 발생")
    void addInvalidInput() {
        // Given
        String input = "//\n1,2,3";

        // When & Then
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(input)
        );
    }
}