package lesson7;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final AccuweatherModel weatherModel = new AccuweatherModel();
    private final Map<Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.NOW);
        variants.put(5, Period.FIVE_DAYS);
    }

    public void getWeather(String userInput, String selectedCity) throws IOException {
        Integer userIntegerInput = Integer.parseInt(userInput);
        weatherModel.getWeather(selectedCity, variants.get(userIntegerInput));
    }

    public boolean isValidCommand(String c) {
        return "1".equals(c) || "5".equals(c);
    }
}