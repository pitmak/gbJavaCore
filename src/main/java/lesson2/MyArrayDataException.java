package lesson2;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j, String data) {
        super("Недопустимое значение ячейки [" + i + "][" + j + "] = \"" + data + "\"");
    }
}
