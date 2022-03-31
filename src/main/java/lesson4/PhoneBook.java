package lesson4;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    HashMap<String, ArrayList<String>> storage;

    public PhoneBook() {
        storage = new HashMap<>();
    }

    public void add(String name, String phone) {
        if (!storage.containsKey(name)) {
            storage.put(name, new ArrayList<>());
        }
        storage.get(name).add(phone);
    }

    public String get(String name) {
        if (storage.containsKey(name)) {
            return storage.get(name).toString();
        } else {
            return "";
        }
    }
}
