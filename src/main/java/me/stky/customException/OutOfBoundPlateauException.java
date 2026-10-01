package me.stky.customException;

import me.stky.models.Coordinates;

public class OutOfBoundPlateauException extends Exception {

    private final Coordinates coordinates;

    public OutOfBoundPlateauException(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getMessage() {
        return "The position at coordinates : [ " + this.coordinates.x() + " , " + this.coordinates.y() + " ]";
    }
}
