package lesson3;

public class Apple extends Fruit {
    @Override
    public String toString() {
        return "Apple{" + "id=" + id + '}';
    }

    @Override
    double getWeight() {
        return 1.0;
    }
}
