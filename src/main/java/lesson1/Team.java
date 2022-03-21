package lesson1;


public class Team {
    private String name;
    private Athlete[] athArr;


    public Team(String name, Athlete... athArr) {
        this.name = name;
        this.athArr = new Athlete[athArr.length];
        System.arraycopy(athArr, 0, this.athArr, 0, athArr.length);
    }

    public String getName() {
        return name;
    }

    public Athlete[] getAthArr() {
        return athArr;
    }

    public void print() {
        System.out.println(name + ":");
        for (Athlete athl : athArr) {
            System.out.println(athl);
        }
    }
}
