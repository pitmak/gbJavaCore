package lesson7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccuweatherModel {
    private static final String API_KEY = "pXJd8MokcZCdrd2MsoGl2DBZAyCa0zvv";

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final DataBaseRepository dataBaseRepository = new DataBaseRepository();

    private static final HashMap<String, String> citiesCache = new HashMap<>();

    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW -> {
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme("https")
                        .host("dataservice.accuweather.com")
                        .addPathSegment("forecasts")
                        .addPathSegment("v1")
                        .addPathSegment("daily")
                        .addPathSegment("1day")
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter("apikey", API_KEY)
                        .addQueryParameter("language", "ru-ru")
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response response = okHttpClient.newCall(request).execute();
                String responseString = response.body().string();
                JsonNode jsonNode = objectMapper.readTree(responseString).at("/DailyForecasts").get(0);

                String dateOfForecast = jsonNode.at("/Date").asText().substring(0, 10);
                String minTemperature = jsonNode.at("/Temperature/Minimum/Value").asText();
                String maxTemperature = jsonNode.at("/Temperature/Maximum/Value").asText();
                String atDayForecast = jsonNode.at("/Day/IconPhrase").asText();
                String atNightForecast = jsonNode.at("/Night/IconPhrase").asText();

                System.out.println("Погода в городе " + selectedCity + " на " + dateOfForecast + ":");
                System.out.println("  температура от " + minTemperature + " до " + maxTemperature + " °C");
                System.out.println("  днем: " + atDayForecast + ", ночью: " + atNightForecast + "\n");

                Weather weatherToDB = new Weather(selectedCity, dateOfForecast, maxTemperature);
                dataBaseRepository.saveWeatherToDataBase(weatherToDB);
            }
            case FIVE_DAYS -> {
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme("https")
                        .host("dataservice.accuweather.com")
                        .addPathSegment("forecasts")
                        .addPathSegment("v1")
                        .addPathSegment("daily")
                        .addPathSegment("5day")
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter("apikey", API_KEY)
                        .addQueryParameter("language", "ru-ru")
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                System.out.println("Погода в городе " + selectedCity + " на 5 дней:");
                Response response = okHttpClient.newCall(request).execute();
                String responseString = response.body().string();
                List<Weather> weatherToDBs = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    JsonNode jsonNode = objectMapper.readTree(responseString).at("/DailyForecasts").get(i);

                    String dateOfForecast = jsonNode.at("/Date").asText().substring(0, 10);
                    String minTemperature = jsonNode.at("/Temperature/Minimum/Value").asText();
                    String maxTemperature = jsonNode.at("/Temperature/Maximum/Value").asText();
                    String atDayForecast = jsonNode.at("/Day/IconPhrase").asText();
                    String atNightForecast = jsonNode.at("/Night/IconPhrase").asText();

                    System.out.println("  " + dateOfForecast + ":");
                    System.out.println("    температура от " + minTemperature + " до " + maxTemperature + " °C");
                    System.out.println("    днем: " + atDayForecast + ", ночью: " + atNightForecast);

                    weatherToDBs.add(new Weather(selectedCity, dateOfForecast, maxTemperature));
                }
                dataBaseRepository.saveWeatherToDataBase(weatherToDBs);
            }
            case DB -> {
                dataBaseRepository.getSavedToDBWeather();
            }
        }
    }

    public boolean isValidCity(String city) {
        if ("".equals(city)) return false;
        if (citiesCache.containsKey(city)) {
            return !citiesCache.get(city).equals("");
        }

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("q", city)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.body() != null) {
                String responseString = response.body().string();
                if (!"[]".equals(responseString)) {
                    String cityCode = objectMapper.readTree(responseString).get(0).at("/Key").asText();
                    citiesCache.put(city, cityCode);
                    return true;
                } else {
                    citiesCache.put(city, "");
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private String detectCityKey(String selectCity) {
        assert citiesCache.containsKey(selectCity);
        return citiesCache.get(selectCity);
    }
}
