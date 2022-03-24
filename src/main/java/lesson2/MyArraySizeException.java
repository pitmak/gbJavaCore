package lesson2;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(int wrongLen) {
        super("Неверный размер массива (" + wrongLen + "). Допускается только 4!");
    }
}
