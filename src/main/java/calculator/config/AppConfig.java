package calculator.config;

import calculator.application.Calculator;
import calculator.domain.parser.StringParser;
import calculator.view.InputView;
import calculator.view.OutputView;

public class AppConfig {

    public void run() {
        Calculator calculator = new Calculator(new StringParser());
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String input = inputView.readInput();
        int result = calculator.add(input);
        outputView.printResult(result);
    }
}
