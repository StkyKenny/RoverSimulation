package me.stky.customException;

import me.stky.models.Coordinates;

public class PlateauCollisionException extends Exception {

    private final Coordinates coordinates;

    public PlateauCollisionException(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getMessage() {
        return "The coordinate at coordinates :[ " + this.coordinates.x() + " " + this.coordinates.y() + " ]";
    }
}
