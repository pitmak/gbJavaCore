package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HomeWork4 {
    public static void main(String[] args) {
        String[] arr = {
                "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten",
                "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine", "ten"
        };

        HashMap<String, Integer> dict = new HashMap<>();
        for (String elem : arr) {
            if (dict.containsKey(elem)) {
                dict.put(elem, dict.get(elem) + 1);
            } else {
                dict.put(elem, 1);
            }
        }
        System.out.println(dict);

        PhoneBook pb = new PhoneBook();
        pb.add("Иванов", "8951234567");
        pb.add("Петров", "8951111111");
        pb.add("Иванов", "8958888888");
        System.out.println(pb.get("Иванов"));
        System.out.println(pb.get("Петров"));
    }
}
