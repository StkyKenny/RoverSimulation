import me.stky.models.Coordinates;
import me.stky.models.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinatesTest {

    @Test
    public void testMoveNorth() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.NORTH);
        Coordinates expectedCoordinates = new Coordinates(0, 1);
        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    public void testMoveEast() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.EAST);
        Coordinates expectedCoordinates = new Coordinates(1, 0);
        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    public void testMoveSouth() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.SOUTH);
        Coordinates expectedCoordinates = new Coordinates(0, -1);
        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    public void testMoveWest() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.WEST);
        Coordinates expectedCoordinates = new Coordinates(-1, 0);
        assertEquals(expectedCoordinates, newCoordinates);
    }
}
