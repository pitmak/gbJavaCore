package lesson3;

public class Orange extends Fruit {
    @Override
    public String toString() {
        return "Orange{" + "id=" + id + '}';
    }

    @Override
    double getWeight() {
        return 1.5;
    }
}
