package calculator.domain.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringParser 테스트")
class StringParserTest {

    @Nested
    @DisplayName("커스텀 구분자 파싱")
    class 커스텀_구분자 {

        @Test
        @DisplayName("단일 문자 커스텀 구분자 및 숫자 본문 추출")
        void 단일_문자_구분자_추출() {
            StringParser parser = new StringParser();
            ParseResult result = parser.parse("//;\\n1;2;3");
            assertEquals(";", result.delimiter());
            assertEquals("1;2;3", result.numbersText());
        }

        @Test
        @DisplayName("커스텀 시도지만 형식이 잘못된 경우 -> IllegalArgumentException 발생")
        void 잘못된_커스텀_형식() {
            StringParser parser = new StringParser();
            assertThrows(IllegalArgumentException.class, () -> parser.parse("//;1;2"));
        }
    }

    @Nested
    @DisplayName("기본 동작")
    class 기본_동작 {

        @Test
        @DisplayName("커스텀 구분자 시도가 없으면 빈 구분자와 원문 반환")
        void 커스텀_없음() {
            StringParser parser = new StringParser();
            ParseResult result = parser.parse("1,2:3");
            assertEquals("", result.delimiter());
            assertEquals("1,2:3", result.numbersText());
        }

        @Test
        @DisplayName("빈 문자열 입력 시 빈 구분자와 빈 원문 반환")
        void 빈_문자열_입력() {
            StringParser parser = new StringParser();
            ParseResult result = parser.parse("");
            assertEquals("", result.delimiter());
            assertEquals("", result.numbersText());
        }
    }
}
