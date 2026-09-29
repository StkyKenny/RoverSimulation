package me.stky.customException;

public class BadInputException extends Exception {

    private final String input;

    public BadInputException(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return "The following input is incorrect and couldn't be used: " + input;
    }
}
