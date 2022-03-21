package lesson1;

public class SkiTrack extends Obstacle {
    private double distance;

    public SkiTrack(String name, double distance) {
        super(name);
        this.distance = distance;
    }


    @Override
    public double getOvercomingTime(Athlete athlete) {
        return distance / athlete.getSkiSpeed();
    }
}
