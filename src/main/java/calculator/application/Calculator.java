package calculator.application;

import calculator.domain.delimiter.Delimiters;
import calculator.domain.number.Numbers;
import calculator.domain.parser.ParseResult;
import calculator.domain.parser.StringParser;

public class Calculator {
    private final StringParser stringParser;

    public Calculator(StringParser stringParser) {
        this.stringParser = stringParser;
    }

    public int add(String text) {
        // 빈 문자열일 경우, 0을 반환한다.
        if (text == null || text.isBlank()) {
            return 0;
        }

        ParseResult result = stringParser.parse(text);
        Delimiters delimiters = Delimiters.of(result.delimiter());
        String[] tokens = result.numbersText().split(delimiters.regex());
        Numbers numbers = Numbers.from(tokens);

        // 분리된 모든 숫자를 더한 결과를 반환한다.
        return numbers.sum();
    }
}
