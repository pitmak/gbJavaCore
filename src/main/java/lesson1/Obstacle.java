package lesson1;

public abstract class Obstacle {
    private String name;

    public String getName() {
        return name;
    }

    public Obstacle(String name) {
        this.name = name;
    }

    public abstract double getOvercomingTime(Athlete athlete);
}
