package app.exceptions;

public class CustomerNotFaundExeption extends RuntimeException {
    public CustomerNotFaundExeption(String message) {
        super(message);
    }
}
