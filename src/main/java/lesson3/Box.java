package lesson3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> storage;

    public ArrayList<T> getStorage() {
        return storage;
    }

    public void add(T fruit) {
        storage.add(fruit);
    }

    public double getWeight() {
        return storage.size() * storage.get(0).getWeight();
    }

    public boolean compare(Box<?> box) {
        return getWeight() == box.getWeight();
    }

    public Box() {
        storage = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Box{" + "storage=" + storage + '}';
    }

    public void pourInto(Box<T> box) {
        box.storage.addAll(storage);
        storage.clear();
    }
}
