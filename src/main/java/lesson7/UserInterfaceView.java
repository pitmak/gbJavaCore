package lesson7;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private final Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("0 - выход из программы");

        while (true) {
            String city;
            do {
                System.out.println("Введите название города: ");
                city = scanner.nextLine();
            } while (!"0".equals(city) && !controller.isValidCity(city));

            if ("0".equals(city)) break;

            String command;
            do {
                System.out.println("Введите 1 для получения погоды на сегодня; " +
                        "Введите 5 для прогноза на 5 дней:");
                command = scanner.nextLine();
            } while (!"0".equals(command) && !controller.isValidCommand(command));

            if ("0".equals(command)) break;

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
