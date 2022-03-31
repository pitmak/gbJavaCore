package lesson3;

import java.util.Random;

public abstract class Fruit {
    private static final Random idGenerator = new Random();

    protected int id;

    @Override
    public String toString() {
        return "Fruit{" + "id=" + id + '}';
    }

    public Fruit() {
        id = 1000 + idGenerator.nextInt(9000);
    }

    abstract double getWeight();
}
