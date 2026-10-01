package UnitTests;

import me.stky.models.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void when_given_N_get_north_direction() {
        String input = "N";

        Direction actualDirection = Direction.getDirection(input);
        Direction expectedDirection = Direction.NORTH;
        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void when_given_E_get_east_direction() {
        String input = "E";

        Direction actualDirection = Direction.getDirection(input);
        Direction expectedDirection = Direction.EAST;
        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void when_given_S_get_south_direction() {
        String input = "S";

        Direction actualDirection = Direction.getDirection(input);
        Direction expectedDirection = Direction.SOUTH;
        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void when_given_W_get_west_direction() {
        String input = "W";

        Direction actualDirection = Direction.getDirection(input);
        Direction expectedDirection = Direction.WEST;
        Assertions.assertEquals(expectedDirection, actualDirection);
    }

    @Test
    public void when_given_cardinal_points_rotate_them_clockwise() {
        Direction[] inputDirections = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        Direction[] expectedDirections = {Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH};


        for (int i = 0; i < inputDirections.length; i++) {
            Direction resultDirection = Direction.rotateClockwise(inputDirections[i]);
            Assertions.assertEquals(expectedDirections[i], resultDirection);
        }
    }

    @Test
    public void when_given_cardinal_points_rotate_them_counter_clockwise() {
        Direction[] inputDirections = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        Direction[] expectedDirections = {Direction.WEST, Direction.NORTH, Direction.EAST, Direction.SOUTH};


        for (int i = 0; i < inputDirections.length; i++) {
            Direction resultDirection = Direction.rotateCounterClockwise(inputDirections[i]);
            Assertions.assertEquals(expectedDirections[i], resultDirection);
        }
    }
}
