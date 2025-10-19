package calculator.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Numbers 테스트")
class NumbersTest {

    @Nested
    @DisplayName("정상 입력")
    class 정상_입력 {

        @Test
        @DisplayName("단일 숫자")
        void 단일_숫자() {
            Numbers numbers = Numbers.from(new String[] { "1" });
            assertEquals(1, numbers.sum());
        }

        @Test
        @DisplayName("여러 숫자")
        void 여러_숫자() {
            Numbers numbers = Numbers.from(new String[] { "1", "2", "3" });
            assertEquals(6, numbers.sum());
        }

        @Test
        @DisplayName("큰 수 합산 (오버플로우 아님)")
        void 큰_수_합산() {
            Numbers numbers = Numbers.from(new String[] { String.valueOf(Integer.MAX_VALUE), "0" });
            assertEquals(Integer.MAX_VALUE, numbers.sum());
        }
    }

    @Nested
    @DisplayName("예외 입력")
    class 예외_입력 {

        @Test
        @DisplayName("null 입력 -> IllegalArgumentException")
        void 널_입력() {
            assertThrows(IllegalArgumentException.class, () -> Numbers.from(null));
        }

        @Test
        @DisplayName("빈 배열 입력 -> IllegalArgumentException")
        void 빈_배열_입력() {
            assertThrows(IllegalArgumentException.class, () -> Numbers.from(new String[] {}));
        }

        @Test
        @DisplayName("잘못된 숫자 형식 -> IllegalArgumentException")
        void 잘못된_숫자_형식() {
            assertThrows(IllegalArgumentException.class, () -> Numbers.from(new String[] { "1", "a" }));
        }

        @Test
        @DisplayName("음수 포함 -> IllegalArgumentException")
        void 음수_포함() {
            assertThrows(IllegalArgumentException.class, () -> Numbers.from(new String[] { "1", "-2" }));
        }

        @Test
        @DisplayName("합산 중 오버플로우 -> IllegalArgumentException")
        void 합산_오버플로우() {
            Numbers numbers = Numbers.from(new String[] { String.valueOf(Integer.MAX_VALUE), "1" });
            assertThrows(IllegalArgumentException.class, numbers::sum);
        }
    }
}
