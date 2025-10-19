package calculator.domain.number;

import java.util.Arrays;
import java.util.List;

public class Numbers {
    private final List<Integer> numbers;

    private Numbers(List<Integer> numbers) {
        validateNegative(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public static Numbers from(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException("숫자가 존재하지 않습니다.");
        }

        List<Integer> numbers = Arrays.stream(tokens)
                .map(token -> {
                    validateNumberFormat(token);
                    return Integer.parseInt(token);
                })
                .toList();

        return new Numbers(numbers);
    }

    public int sum() {
        try {
            int sum = 0;

            for (int number : numbers) {
                sum = Math.addExact(sum, number);
            }

            return sum;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("숫자의 합이 정수 범위를 초과합니다.");
        }
    }

    private static void validateNumberFormat(String token) {
        try {
            Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자 형식입니다: " + token);
        }
    }

    private void validateNegative(List<Integer> numbers) {
        List<Integer> negativeNumbers = numbers.stream()
                .filter(n -> n < 0)
                .toList();

        // 음수가 포함되어 있다면 IllegalArgumentException을 발생시킨다.
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + negativeNumbers);
        }
    }
}
