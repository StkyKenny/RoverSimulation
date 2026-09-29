package me.stky.customException;

import me.stky.Position;

public class PlateauCollisionException extends Exception {

    private final Position position;

    public PlateauCollisionException(Position position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "The position at coordinates :[ " + this.position.x() + " " + this.position.y() + " ]";
    }
}
