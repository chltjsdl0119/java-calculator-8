package calculator.domain.delimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiters {
    private static final List<Delimiter> DEFAULT_DELIMITERS = List.of(
            Delimiter.of(","),
            Delimiter.of(":")
    );

    private final List<Delimiter> values;

    private Delimiters(List<Delimiter> values) {
        this.values = List.copyOf(values);
    }

    public static Delimiters of(String customDelimiterValue) {
        List<Delimiter> delimiters = new ArrayList<>(DEFAULT_DELIMITERS);

        if (customDelimiterValue != null && !customDelimiterValue.isBlank()) {
            delimiters.add(Delimiter.of(customDelimiterValue));
        }

        return new Delimiters(delimiters);
    }


    public String regex() {
        return values.stream()
                .map(Delimiter::getValue)
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
}
