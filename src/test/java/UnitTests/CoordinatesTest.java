package UnitTests;

import me.stky.models.Coordinates;
import me.stky.models.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinatesTest {

    @Test
    public void when_move_forward_towards_north_have_coordinates_moved_north() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.NORTH);
        Coordinates expectedCoordinates = new Coordinates(0, 1);
        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    public void when_move_forward_towards_east_have_coordinates_moved_east() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.EAST);
        Coordinates expectedCoordinates = new Coordinates(1, 0);
        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    public void when_move_forward_towards_south_have_coordinates_moved_south() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.SOUTH);
        Coordinates expectedCoordinates = new Coordinates(0, -1);
        assertEquals(expectedCoordinates, newCoordinates);
    }

    @Test
    public void when_move_forward_towards_west_have_coordinates_moved_west() {
        Coordinates coordinates = new Coordinates(0, 0);
        Coordinates newCoordinates = coordinates.moveForward(Direction.WEST);
        Coordinates expectedCoordinates = new Coordinates(-1, 0);
        assertEquals(expectedCoordinates, newCoordinates);
    }
}
