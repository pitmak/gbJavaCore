package lesson3;

import java.util.Arrays;

public class HomeWork3 {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(numbers));
        swapElements(numbers, 1, 3);
        System.out.println(Arrays.toString(numbers));

        String[] strings = {"one", "two", "three", "four", "five"};
        System.out.println(Arrays.toString(strings));
        swapElements(strings, 0, 4);
        System.out.println(Arrays.toString(strings));

        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        System.out.println(appleBox1);
        System.out.println("Вес: " + appleBox1.getWeight());

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        System.out.println(orangeBox1);
        System.out.println("Вес: " + orangeBox1.getWeight());
        System.out.println("Массы " + (!appleBox1.compare(orangeBox1) ? "не " : "") + "равны");

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(new Orange());
        orangeBox1.pourInto(orangeBox2);
        System.out.println(orangeBox2);
        System.out.println(orangeBox1);
    }

    public static <T> void swapElements(T[] arr, int a, int b) {
        T s = arr[a];
        arr[a] = arr[b];
        arr[b] = s;
    }
}
