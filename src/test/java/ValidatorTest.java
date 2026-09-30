import me.stky.commands.MoveCommand;
import me.stky.commands.MoveForwardCommand;
import me.stky.commands.TurnLeftCommand;
import me.stky.commands.TurnRightCommand;
import me.stky.validator.InstructionsValidator;
import me.stky.validator.InstructionsValidatorImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {
    @Test
    public void testAllInstructions() {
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
    public void testNoInstructions() {
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
    public void testIgnoreBadInstructions() {
        InstructionsValidator instructionsValidator = new InstructionsValidatorImpl();
        String instructions = "YUIM1586";
        List<MoveCommand> actualCommands = instructionsValidator.validateInstructions(instructions);

        List<MoveCommand> expectedCommands = List.of(new MoveForwardCommand());
        assertEquals(expectedCommands.size(), actualCommands.size());
        for (int i = 0; i < actualCommands.size(); i++) {
            assertEquals(expectedCommands.get(i).getClass(), actualCommands.get(i).getClass());
        }
    }
}
