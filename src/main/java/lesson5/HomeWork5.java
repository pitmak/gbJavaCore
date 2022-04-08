package lesson5;

import java.io.*;
import java.util.ArrayList;

public class HomeWork5 {
    public static void main(String[] args) {
        String[] header = {"English", "Русский", "日本語"};
        int[][] data = {{100, 200, 300}, {40, 50, 60}};
        AppData appData = new AppData(header, data);
        System.out.println(appData);
        save(appData);
        AppData loadingData = load();
        System.out.println(loadingData);
    }

    private static AppData load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("appdata.csv"))) {
            String[] header = reader.readLine().split(";");

            ArrayList<String> lines = new ArrayList<>();

            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                lines.add(nextLine);
            }
            int[][] data = new int[lines.size()][header.length];
            for (int i = 0; i < lines.size(); i++) {
                String[] splitLine = lines.get(i).split(";");
                for (int j = 0; j < splitLine.length; j++) {
                    data[i][j] = Integer.parseInt(splitLine[j]);
                }
            }
            return new AppData(header, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
