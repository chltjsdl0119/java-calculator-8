package calculator.domain.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)" + Pattern.quote("\\n") + "(.*)");

    public ParseResult parse(String text) {
        // 커스텀 구분자가 존재할 경우, 해당 구분자를 사용한다.
        if (isCustomDelimiterAttempt(text)) {
            return extractCustomDelimiter(text);
        }

        return new ParseResult("", text);
    }

    private boolean isCustomDelimiterAttempt(String text) {
        return text.startsWith("//");
    }

    private ParseResult extractCustomDelimiter(String text) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(text);

        // 잘못된 구분자 형식을 입력할 경우 IllegalArgumentException을 발생시킨다.
        if (!matcher.matches()) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다: " + text);
        }

        String customDelimiter = matcher.group(1);

        // 입력 문자열에서 구분자를 기준으로 숫자를 분리한다.
        String numbersText = matcher.group(2);

        return new ParseResult(customDelimiter, numbersText);
    }
}
