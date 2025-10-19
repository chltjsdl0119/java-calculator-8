package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Delimiter 테스트")
class DelimiterTest {

    @Nested
    @DisplayName("정상 입력")
    class 정상_입력 {

        @Test
        @DisplayName("단일 문자 구분자 \":\" 생성")
        void 단일_문자_생성() {
            Delimiter delimiter = Delimiter.of(":");
            assertEquals(":", delimiter.getValue());
        }

        @Test
        @DisplayName("여러 문자 구분자 \"***\" 생성")
        void 여러_문자_생성() {
            Delimiter delimiter = Delimiter.of("***");
            assertEquals("***", delimiter.getValue());
        }
    }

    @Nested
    @DisplayName("예외 입력")
    class 예외_입력 {

        @Test
        @DisplayName("null 입력 -> IllegalArgumentException 발생")
        void 널_입력() {
            assertThrows(IllegalArgumentException.class, () -> Delimiter.of(null));
        }

        @Test
        @DisplayName("빈 문자열 입력 -> IllegalArgumentException 발생")
        void 빈_문자열_입력() {
            assertThrows(IllegalArgumentException.class, () -> Delimiter.of(""));
        }

        @Test
        @DisplayName("공백 문자열 입력 -> IllegalArgumentException 발생")
        void 공백_문자열_입력() {
            assertThrows(IllegalArgumentException.class, () -> Delimiter.of("   "));
        }
    }
}
