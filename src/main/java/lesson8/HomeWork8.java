package lesson8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork8 {
    public static void main(String[] args) {
        List<Student> testData = new ArrayList<>(Arrays.asList(
                new Student("Иванов", new ArrayList<>(Arrays.asList(
                        new Course("биология"),
                        new Course("история"),
                        new Course("шахматы"),
                        new Course("математика"),
                        new Course("физика")
                ))),
                new Student("Петров", new ArrayList<>(Arrays.asList(
                        new Course("геометрия"),
                        new Course("астрономия"),
                        new Course("информатика"),
                        new Course("история")
                ))),
                new Student("Сидоров", new ArrayList<>(Arrays.asList(
                        new Course("геометрия"),
                        new Course("информатика"),
                        new Course("история")
                ))),
                new Student("Рабинович", new ArrayList<>(Arrays.asList(
                        new Course("физика"),
                        new Course("биология")
                )))
        ));

        HomeWork8 testObject = new HomeWork8();
        System.out.println(testObject.getUniqueCourseList(testData));
        System.out.println(testObject.getMostCuriousStudentsList(testData));
        System.out.println(testObject.getAttendingStudentsList(testData, new Course("информатика")));
    }

    private List<Course> getUniqueCourseList(List<Student> students) {
        return students.stream()
                .map(Student::getAllCourses)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Student> getMostCuriousStudentsList(List<Student> students) {
        return students.stream()
                .sorted((a, b) -> b.getAllCourses().size() - a.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());

    }

    private List<Student> getAttendingStudentsList(List<Student> students, Course course) {
        return students.stream()
                .filter(s -> s.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }
}
