package lesson5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HomeWork5 {
    public static void main(String[] args) {
        String[] header = {"Title 1", "Title 2", "Title 3"};
        int[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        AppData appData = new AppData(header, data);
        System.out.println(appData);
        save(appData);
        AppData loadingData = load();
        System.out.println(loadingData);
    }

    private static AppData load() {
        return null;
    }

    public static void save(AppData appData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appdata.csv"))) {
            writer.write(appData.getHeader()[0]);
            for (int i = 1; i < appData.cols(); i++) {
                writer.write(";" + appData.getHeader()[i]);
            }
            writer.newLine();
            for (int i = 0; i < appData.rows(); i++) {
                writer.write("" + appData.getData()[i][0]);
                for (int j = 1; j < appData.cols(); j++) {
                    writer.write(";" + appData.getData()[i][j]);
                }
                if (i < appData.rows() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
