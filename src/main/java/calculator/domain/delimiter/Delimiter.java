package calculator.domain.delimiter;

public class Delimiter {
    private final String value;

    private Delimiter(String value) {
        // 구분자 값이 비어있을 경우 IllegalArgumentException을 발생시킨다.
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("구분자 값은 비어있을 수 없습니다.");
        }

        this.value = value;
    }

    public static Delimiter of(String value) {
        return new Delimiter(value);
    }

    public String getValue() {
        return value;
    }
}
