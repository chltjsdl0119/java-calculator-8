package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Delimiters 테스트")
class DelimitersTest {

    @Nested
    @DisplayName("기본 동작")
    class 기본_동작 {

        @Test
        @DisplayName("커스텀 없을 때 기본 구분자만 포함")
        void 기본_구분자만_포함() {
            Delimiters delimiters = Delimiters.of(null);
            String expected = Pattern.quote(",") + "|" + Pattern.quote(":");
            assertEquals(expected, delimiters.regex());
        }

        @Test
        @DisplayName("빈 문자열 커스텀 -> 기본 구분자만 포함")
        void 빈_문자열_커스텀() {
            Delimiters delimiters = Delimiters.of("");
            String expected = Pattern.quote(",") + "|" + Pattern.quote(":");
            assertEquals(expected, delimiters.regex());
        }

        @Test
        @DisplayName("공백 문자열 커스텀 -> 기본 구분자만 포함")
        void 공백_문자열_커스텀() {
            Delimiters delimiters = Delimiters.of("   ");
            String expected = Pattern.quote(",") + "|" + Pattern.quote(":");
            assertEquals(expected, delimiters.regex());
        }
    }

    @Nested
    @DisplayName("커스텀 구분자 추가")
    class 커스텀_구분자 {

        @Test
        @DisplayName("단일 문자 커스텀 \";\" 추가")
        void 단일_문자_커스텀() {
            Delimiters delimiters = Delimiters.of(";");
            String expected = Pattern.quote(",") + "|" + Pattern.quote(":") + "|" + Pattern.quote(";");
            assertEquals(expected, delimiters.regex());
        }

        @Test
        @DisplayName("여러 문자 커스텀 \"***\" 추가")
        void 여러_문자_커스텀() {
            Delimiters delimiters = Delimiters.of("***");
            String expected = Pattern.quote(",") + "|" + Pattern.quote(":") + "|" + Pattern.quote("***");
            assertEquals(expected, delimiters.regex());
        }

        @Test
        @DisplayName("생성된 정규식으로 문자열 분리 가능")
        void 정규식으로_분리() {
            Delimiters delimiters = Delimiters.of(";");
            String regex = delimiters.regex();
            String input = "1,2:3;4";
            String[] parts = input.split(regex);
            assertArrayEquals(new String[] { "1", "2", "3", "4" }, parts);
        }
    }
}
