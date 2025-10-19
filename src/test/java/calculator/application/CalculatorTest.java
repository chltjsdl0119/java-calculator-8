package calculator.application;

import calculator.domain.parser.StringParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator 테스트")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void 설정하기() {
        StringParser parser = new StringParser();
        calculator = new Calculator(parser);
    }

    @Nested
    @DisplayName("정상 입력 테스트")
    class 정상_입력 {

        @Test
        @DisplayName("기본 숫자 입력: 1,2,3 -> 합계 6")
        void 기본_숫자_입력() {
            // Given
            String input = "1,2,3";

            // When
            int result = calculator.add(input);

            // Then
            assertEquals(6, result);
        }

        @Test
        @DisplayName("기본 구분자 쉼표와 콜론 혼합: 1,2:3 -> 합계 6")
        void 기본_구분자_혼합() {
            // Given
            String input = "1,2:3";

            // When
            int result = calculator.add(input);

            // Then
            assertEquals(6, result);
        }
    }

    @Nested
    @DisplayName("경계 조건 입력 테스트")
    class 경계_조건 {

        @Test
        @DisplayName("빈 문자열 입력 -> 결과 0")
        void 빈_문자열_입력() {
            // Given
            String input = "";

            // When
            int result = calculator.add(input);

            // Then
            assertEquals(0, result);
        }

        @Test
        @DisplayName("null 입력 -> 결과 0")
        void 널_입력() {
            // Given
            String input = null;

            // When
            int result = calculator.add(input);

            // Then
            assertEquals(0, result);
        }

        @Test
        @DisplayName("정수 최대값 초과 입력 -> IllegalArgumentException 발생")
        void 정수_최대값_초과() {
            // Given
            String input = "2147483647,1";

            // When & Then
            assertThrows(
                    IllegalArgumentException.class,
                    () -> calculator.add(input)
            );
        }
    }

    @Nested
    @DisplayName("예외 입력 테스트")
    class 예외_입력 {

        @Test
        @DisplayName("음수가 포함된 입력 -> IllegalArgumentException 발생")
        void 음수_포함() {
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
        void 잘못된_커스텀_구분자() {
            // Given
            String input = "//\n1,2,3";

            // When & Then
            assertThrows(
                    IllegalArgumentException.class,
                    () -> calculator.add(input)
            );
        }
    }
}