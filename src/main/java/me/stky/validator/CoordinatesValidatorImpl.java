package me.stky.validator;

import me.stky.Plateau;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.entities.Obstacle;
import me.stky.models.Coordinates;

public class CoordinatesValidatorImpl implements CoordinatesValidator {
    private final Plateau plateau;

    public CoordinatesValidatorImpl(Plateau plateau) {
        this.plateau = plateau;
    }

    @Override
    public void validateCoordinate(Coordinates coordinates) throws OutOfBoundPlateauException, PlateauCollisionException {
        if ((coordinates.x() < 0 || coordinates.y() < 0 ||
                coordinates.x() > plateau.getWidth() || coordinates.y() > plateau.getHeight())) {
            throw new OutOfBoundPlateauException(coordinates);
        }
        for (Obstacle obstacles : plateau.getObstacles()) {
            if (obstacles.getCoordinates().equals(coordinates)) {
                throw new PlateauCollisionException(coordinates);
            }
        }

    }
}
