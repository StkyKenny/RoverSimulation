package UnitTests;

import me.stky.Plateau;
import me.stky.commands.MoveForwardCommand;
import me.stky.commands.TurnLeftCommand;
import me.stky.commands.TurnRightCommand;
import me.stky.customException.BadInputException;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.entities.Rover;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;
import me.stky.validator.CoordinatesValidator;
import me.stky.validator.InstructionsValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoverTest {

    @Test
    public void given_go_to_the_left_instruction_when_move_then_rover_go_to_left() throws BadInputException, OutOfBoundPlateauException, PlateauCollisionException {
        Position originalPosition = createPosition(0, 0, Direction.NORTH);
        String instructions = "L";

        InstructionsValidator mockInstructionValidator = Mockito.mock(InstructionsValidator.class);
        CoordinatesValidator mockCoordinatesValidator = Mockito.mock(CoordinatesValidator.class);
        when(mockInstructionValidator.validateInstructions(instructions)).thenReturn(List.of(new TurnLeftCommand()));

        Rover rover = new Rover(originalPosition.coordinates(),
                originalPosition.direction(),
                instructions.trim().toUpperCase(),
                mockInstructionValidator,
                mockCoordinatesValidator,
                List.of(new Plateau(5, 5)));

        rover.processCommands();
        assertEquals(Direction.WEST, rover.getCurrentPosition().direction());
    }

    @Test
    public void given_go_to_the_right_instruction_when_move_then_rover_go_to_right() throws BadInputException, OutOfBoundPlateauException, PlateauCollisionException {
        Position originalPosition = createPosition(0, 0, Direction.NORTH);
        String instructions = "R";

        InstructionsValidator mockInstructionValidator = Mockito.mock(InstructionsValidator.class);
        CoordinatesValidator mockCoordinatesValidator = Mockito.mock(CoordinatesValidator.class);
        when(mockInstructionValidator.validateInstructions(instructions)).thenReturn(List.of(new TurnRightCommand()));

        Rover rover = new Rover(originalPosition.coordinates(),
                originalPosition.direction(),
                instructions.trim().toUpperCase(),
                mockInstructionValidator,
                mockCoordinatesValidator,
                List.of(new Plateau(5, 5)));

        rover.processCommands();
        assertEquals(Direction.EAST, rover.getCurrentPosition().direction());
    }

    @Test
    public void given_go_to_forward_instruction_when_move_then_rover_go_forth() throws BadInputException, OutOfBoundPlateauException, PlateauCollisionException {

        Position originalPosition = createPosition(0, 0, Direction.NORTH);
        String instructions = "M";

        InstructionsValidator mockInstructionValidator = Mockito.mock(InstructionsValidator.class);
        CoordinatesValidator mockCoordinatesValidator = Mockito.mock(CoordinatesValidator.class);
        when(mockInstructionValidator.validateInstructions(instructions)).thenReturn(List.of(new MoveForwardCommand()));

        Rover rover = new Rover(originalPosition.coordinates(),
                originalPosition.direction(),
                instructions.trim().toUpperCase(),
                mockInstructionValidator,
                mockCoordinatesValidator,
                List.of(new Plateau(5, 5)));

        rover.processCommands();
        assertEquals(new Coordinates(0, 1), rover.getCurrentPosition().coordinates());

    }

    private Position createPosition(int x, int y, Direction direction) {
        return new Position(new Coordinates(x, y), direction);
    }


}
