package lesson1;

public class Main {
    public static void main(String[] args) {
        Team dreamTeam = new Team("Сборная мира",
                new Athlete("Иванов", 20, 8, 25),
                new Athlete("Смит", 22, 2.5, 23),
                new Athlete("Чи", 19.6, 2.9, 22),
                new Athlete("Сорос", 19, 5, 16.9)
        );
        Course stadium = new Course("Динамо",
                new Track("Беговая дорожка", 500, 2),
                new Pool("Большая ванна", 50, 10),
                new SkiTrack("Лыжня", 2000)
        );

        dreamTeam.print();

        System.out.println();

        stadium.doIt(dreamTeam);

        System.out.println();

        dreamTeam.print();
    }
}
