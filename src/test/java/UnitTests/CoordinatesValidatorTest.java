package UnitTests;

import me.stky.Plateau;
import me.stky.customException.BadInputException;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.entities.Obstacle;
import me.stky.models.Coordinates;
import me.stky.validator.CoordinatesValidator;
import me.stky.validator.CoordinatesValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinatesValidatorTest {

    @Test
    public void when_given_negative_values_throw_out_of_bounds_plateau_exception() throws BadInputException, OutOfBoundPlateauException, PlateauCollisionException {

        Plateau plateau = new Plateau(5, 5);
        CoordinatesValidator coordinatesValidator = new CoordinatesValidatorImpl(plateau);

        Coordinates coordinatesNegativeX = new Coordinates(-2, 0);
        assertThrows(OutOfBoundPlateauException.class, () -> coordinatesValidator.validateCoordinate(coordinatesNegativeX));

        Coordinates coordinatesNegativeY = new Coordinates(0, -1);
        assertThrows(OutOfBoundPlateauException.class, () -> coordinatesValidator.validateCoordinate(coordinatesNegativeY));
    }

    @Test
    public void when_given_coordinates_over_plateaus_bound_throw_out_of_bounds_plateau_exception() throws BadInputException, OutOfBoundPlateauException, PlateauCollisionException {

        Plateau plateau = new Plateau(5, 5);
        CoordinatesValidator coordinatesValidator = new CoordinatesValidatorImpl(plateau);

        Coordinates coordinatesNegativeX = new Coordinates(6, 0);
        assertThrows(OutOfBoundPlateauException.class, () -> coordinatesValidator.validateCoordinate(coordinatesNegativeX));

        Coordinates coordinatesNegativeY = new Coordinates(0, 6);
        assertThrows(OutOfBoundPlateauException.class, () -> coordinatesValidator.validateCoordinate(coordinatesNegativeY));
    }

    @Test
    public void when_given_coordinates_over_another_obstacle_throw_plateau_collision_exception() throws BadInputException, OutOfBoundPlateauException, PlateauCollisionException {

        Plateau plateau = new Plateau(5, 5);
        plateau.addObstacle(new genericObstacle(new Coordinates(3, 3)));
        CoordinatesValidator coordinatesValidator = new CoordinatesValidatorImpl(plateau);

        Coordinates safeCoordinates = new Coordinates(2, 3);
        assertDoesNotThrow(() -> coordinatesValidator.validateCoordinate(safeCoordinates));

        Coordinates upcomingCoordinates = new Coordinates(3, 3);
        assertThrows(PlateauCollisionException.class, () -> coordinatesValidator.validateCoordinate(upcomingCoordinates));

    }


    public class genericObstacle implements Obstacle {
        private final Coordinates coordinates;

        public genericObstacle(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        @Override
        public Coordinates getCoordinates() {
            return coordinates;
        }
    }
}
