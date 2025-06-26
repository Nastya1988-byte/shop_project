package app.exceptions;

public class ProductNotFaundExeption extends RuntimeException {
    public ProductNotFaundExeption(String message) {
        super(message);
    }
}
