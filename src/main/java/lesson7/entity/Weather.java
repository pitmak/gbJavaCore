package lesson7.entity;

public class Weather {
    private String city;
    private String localDate;
    private String temperature;

    public Weather(String city, String localDate, String temperature) {
        this.city = city;
        this.localDate = localDate;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", localDate='" + localDate + '\'' +
                ", temperature=" + String.format("%.2f", temperature) +
                '}';
    }
}