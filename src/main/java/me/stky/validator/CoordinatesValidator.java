package me.stky.validator;

import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.models.Coordinates;

public interface CoordinatesValidator {
    public void validateCoordinate(Coordinates coordinates) throws OutOfBoundPlateauException, PlateauCollisionException;
}
