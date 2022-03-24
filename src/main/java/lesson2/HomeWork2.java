package lesson2;

public class HomeWork2 {
    public static void main(String[] args) {
        String[][] test1 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        String[][] test2 = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };
        String[][] test3 = {
                {"1", "2", "3", "error"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            System.out.println("Тест 1:");
            System.out.println("    результат = " + grindSpecificArray(test1));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Тест 2:");
            System.out.println("    результат = " + grindSpecificArray(test2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Тест 3:");
            System.out.println("    результат = " + grindSpecificArray(test3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    private static int grindSpecificArray(String[][] arg)
            throws MyArraySizeException, MyArrayDataException {
        if (arg.length != 4) {
            throw new MyArraySizeException(arg.length);
        }
        int total = 0;
        for (int i = 0; i < 4; i++) {
            if (arg[i].length != 4) {
                throw new MyArraySizeException(arg[i].length);
            }
            for (int j = 0; j < 4; j++) {
                try {
                    total += Integer.parseInt(arg[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, arg[i][j]);
                }
            }
        }

        return total;
    }
}
