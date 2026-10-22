package exceptions;

public class FigureFormatException extends Exception {

    public FigureFormatException(String message) {
        super(message);
    }

    public FigureFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
