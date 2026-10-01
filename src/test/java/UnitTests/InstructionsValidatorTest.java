package UnitTests;

import me.stky.commands.MoveCommand;
import me.stky.commands.MoveForwardCommand;
import me.stky.commands.TurnLeftCommand;
import me.stky.commands.TurnRightCommand;
import me.stky.validator.InstructionsValidator;
import me.stky.validator.InstructionsValidatorImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructionsValidatorTest {

    @Test
    public void when_given_left_instructions_have_turn_left_commands() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "L";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of(new TurnLeftCommand());
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }

    @Test
    public void when_given_right_instructions_have_turn_right_commands() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "R";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of(new TurnRightCommand());
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }

    @Test
    public void when_given_move_forward_instructions_have_move_forward_commands() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "M";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of(new MoveForwardCommand());
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }

    @Test
    public void when_given_left_right_move_instructions_have_left_right_forward_commands() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "LRM";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of(new TurnLeftCommand(), new TurnRightCommand(), new MoveForwardCommand());
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }

    @Test
    public void when_given_no_instructions_have_no_commands() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of();
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }

    @Test
    public void when_given_unsupported_instructions_have_no_commands() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "YUI1586";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of();
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }
}
