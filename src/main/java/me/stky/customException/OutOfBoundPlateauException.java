package me.stky.customException;

import me.stky.Position;

public class OutOfBoundPlateauException extends Exception {

    private final Position position;

    public OutOfBoundPlateauException(Position position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "The position at coordinates :[ " + this.position.x() + " " + this.position.y() + " ]";
    }
}
