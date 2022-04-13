package lesson7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String API_KEY = "pXJd8MokcZCdrd2MsoGl2DBZAyCa0zvv";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW -> {
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("language", "ru-ru")
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                JsonNode jsonNode = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0);

                String dateOfForecast = jsonNode.at("/Date").asText();
                String minTemperature = jsonNode.at("/Temperature/Minimum/Value").asText();
                String maxTemperature = jsonNode.at("/Temperature/Maximum/Value").asText();
                String atDayForecast = jsonNode.at("/Day/IconPhrase").asText();
                String atNightForecast = jsonNode.at("/Night/IconPhrase").asText();

                System.out.println("Погода в городе " + selectedCity + " на " + dateOfForecast + ":");
                System.out.println("  температура от " + minTemperature + " до " + maxTemperature + " °C");
                System.out.println("  днем: " + atDayForecast + ", ночью: " + atNightForecast);
            }
            case FIVE_DAYS -> {
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment("5day")
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("language", "ru-ru")
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                System.out.println("Погода в городе " + selectedCity + " на 5 дней:");
                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                for (int i = 0; i < 5; i++) {
                    JsonNode jsonNode = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(i);

                    String dateOfForecast = jsonNode.at("/Date").asText();
                    String minTemperature = jsonNode.at("/Temperature/Minimum/Value").asText();
                    String maxTemperature = jsonNode.at("/Temperature/Maximum/Value").asText();
                    String atDayForecast = jsonNode.at("/Day/IconPhrase").asText();
                    String atNightForecast = jsonNode.at("/Night/IconPhrase").asText();

                    System.out.println("  " + dateOfForecast + ":");
                    System.out.println("    температура от " + minTemperature + " до " + maxTemperature + " °C");
                    System.out.println("    днем: " + atDayForecast + ", ночью: " + atNightForecast);
                }
            }
        }
    }


    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
