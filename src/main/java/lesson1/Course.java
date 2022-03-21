package lesson1;

public class Course {
    private String name;
    private Obstacle[] obstArr;

    public Course(String name, Obstacle... obstArr) {
        this.name = name;
        this.obstArr = new Obstacle[obstArr.length];
        System.arraycopy(obstArr, 0, this.obstArr, 0, obstArr.length);
    }

    public void doIt(Team team) {
        Athlete[] athArr = team.getAthArr();
        System.out.println("Начинаются соревнования: " + name);
        for (Obstacle obst : obstArr) {
            System.out.println("  " + obst.getName() + ":");
            Athlete winner = null;
            double bestRes = 9e99;
            for (Athlete athl : athArr) {
                double res = obst.getOvercomingTime(athl);
                System.out.printf("    %s прошел %s за %.2f\n", athl.getName(), obst.getName(), res);
                if (res < bestRes) {
                    bestRes = res;
                    winner = athl;
                }
            }
            System.out.println("  Победитель - " + winner.getName());
            System.out.println("  ----------");
            winner.addMedal(obst.getName() + " - I место");
        }
    }
}
