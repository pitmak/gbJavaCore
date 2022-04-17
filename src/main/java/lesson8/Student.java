package lesson8;

import java.util.List;

public class Student {
    private final String name;
    private final List<Course> allCourses;

    public Student(String name, List<Course> allCourses) {
        this.name = name;
        this.allCourses = allCourses;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    @Override
    public String toString() {
        return name + "." + allCourses;
    }
}
