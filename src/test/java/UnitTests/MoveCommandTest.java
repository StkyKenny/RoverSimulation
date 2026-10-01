package UnitTests;

import me.stky.commands.MoveForwardCommand;
import me.stky.commands.TurnLeftCommand;
import me.stky.commands.TurnRightCommand;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveCommandTest {

    @Test
    public void when_given_position_to_turn_left_command_update_direction_counter_clockwise() {

        Position inputPosition = createPosition(1, 1, Direction.NORTH);

        var command = new TurnLeftCommand();
        Position resultPosition = command.move(inputPosition.coordinates(), inputPosition.direction());

        Position expectedPosition = createPosition(1, 1, Direction.WEST);
        assertEquals(expectedPosition, resultPosition);
    }

    @Test
    public void when_given_position_to_turn_right_command_update_direction_clockwise() {

        Position inputPosition = createPosition(1, 1, Direction.NORTH);
        var command = new TurnRightCommand();
        Position resultPosition = command.move(inputPosition.coordinates(), inputPosition.direction());

        Position expectedPosition = createPosition(1, 1, Direction.EAST);
        assertEquals(expectedPosition, resultPosition);
    }

    @Test
    public void when_given_position_to_move_forward_command_update_position_to_forward() {

        Position inputPosition = createPosition(1, 1, Direction.NORTH);
        var command = new MoveForwardCommand();
        Position resultPosition = command.move(inputPosition.coordinates(), inputPosition.direction());

        Position expectedPosition = createPosition(1, 2, Direction.NORTH);
        assertEquals(expectedPosition, resultPosition);
    }

    private Position createPosition(int x, int y, Direction direction) {
        return new Position(new Coordinates(x, y), direction);
    }
}
