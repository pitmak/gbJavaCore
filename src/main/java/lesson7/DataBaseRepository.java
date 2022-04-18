package lesson7;

import lesson7.entity.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {
    private final String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
    private final String getWeather = "select * from weather";
    private static final String DB_PATH = "jdbc:sqlite:mydb.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveWeatherToDataBase(Weather weather) {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getLocalDate());
            saveWeather.setString(3, weather.getTemperature());
            saveWeather.execute();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public void saveWeatherToDataBase(List<Weather> weatherList) {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            for (Weather weather : weatherList) {
                saveWeather.setString(1, weather.getCity());
                saveWeather.setString(2, weather.getLocalDate());
                saveWeather.setString(3, weather.getTemperature());
                saveWeather.addBatch();
            }
            saveWeather.executeBatch();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public List<Weather> getSavedToDBWeather() {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print("  ");
                System.out.print(resultSet.getString("city"));
                System.out.print("  ");
                System.out.print(resultSet.getString("localdate"));
                System.out.print("  ");
                System.out.println(resultSet.getString("temperature"));
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("localdate"),
                        resultSet.getString("temperature")));
            }
        } catch (SQLException t) {
            t.printStackTrace();
        }
        return weathers;
    }
}