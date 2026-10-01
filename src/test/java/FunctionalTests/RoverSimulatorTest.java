package FunctionalTests;

import me.stky.Main;
import me.stky.Plateau;
import me.stky.customException.BadInputException;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.entities.Rover;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;
import me.stky.validator.CoordinatesValidator;
import me.stky.validator.CoordinatesValidatorImpl;
import me.stky.validator.InstructionsValidator;
import me.stky.validator.InstructionsValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverSimulatorTest {

    private final String testInputsFolder = "src/main/resources/inputTest/";
    private static Plateau plateau;
    private static InstructionsValidator instructionsValidator;
    private static CoordinatesValidator coordinatesValidator;

    @BeforeEach
    public void setupPlateau() throws BadInputException {
        plateau = new Plateau(5, 5);
        instructionsValidator = new InstructionsValidatorImpl();
        coordinatesValidator = new CoordinatesValidatorImpl(plateau);
    }


    @Test
    public void testNoInputFile() {
        assertThrows(IOException.class, () -> Main.parseEnvironment(Path.of("")));
    }

    @Test
    public void testInvalidFilePath() {
        var testFilename = "non_existent.txt";
        assertThrows(IOException.class, () -> Main.parseEnvironment(Path.of(testFilename)));
    }

    @Test
    public void testNegativePlateau() {
        var testFilename = "inputNegativePlateau.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(BadInputException.class, () -> Main.parseEnvironment(Path.of(fullPath)));
    }

    @Test
    public void testBadInputPlateau() {
        var testFilename = "inputNaNPlateau.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(NumberFormatException.class, () -> Main.parseEnvironment(Path.of(fullPath)));
    }

    @Test
    public void testSetupRoverOutsideNegative() {
        var testFilename = "inputSetupRoverNegative.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(OutOfBoundPlateauException.class, () -> Main.parseEnvironment(Path.of(fullPath)));
    }

    @Test
    public void testSetupRoverOutsideOver() {
        var testFilename = "inputSetupRoverOutsideOver.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(OutOfBoundPlateauException.class, () -> Main.parseEnvironment(Path.of(fullPath)));
    }

    // ------------------------------------------------------

    @Test
    public void testExample1() throws OutOfBoundPlateauException, PlateauCollisionException {
        Position originalPosition = createPosition(1, 2, Direction.NORTH);
        String instructions = "LMLMLMLMM";
        Rover rover = createRover(originalPosition, instructions);

        rover.processCommands();
        Position expectedPosition = createPosition(1, 3, Direction.NORTH);
        assertEquals(expectedPosition, rover.getCurrentPosition());
    }

    @Test
    public void testExample2() throws OutOfBoundPlateauException, PlateauCollisionException {
        Position originalPosition = createPosition(3, 3, Direction.EAST);
        String instructions = "MMRMMRMRRM";
        Rover rover = createRover(originalPosition, instructions);

        rover.processCommands();
        Position expectedPosition = createPosition(5, 1, Direction.EAST);
        assertEquals(expectedPosition, rover.getCurrentPosition());
    }

    @Test
    public void testNoCommands() throws OutOfBoundPlateauException, PlateauCollisionException {
        Position originalPosition = createPosition(3, 3, Direction.EAST);
        String instructions = "";
        Rover rover = createRover(originalPosition, instructions);

        rover.processCommands();
        assertEquals(originalPosition, rover.getCurrentPosition());
    }


    @Test
    public void testIgnoreBadCommands() throws OutOfBoundPlateauException, PlateauCollisionException {
        Position originalPosition = createPosition(1, 2, Direction.NORTH);
        String instructions = "LMLEZMAEFLMBTEBLM13425435M";
        Rover rover = createRover(originalPosition, instructions);

        rover.processCommands();
        Position expectedPosition = createPosition(1, 3, Direction.NORTH);
        assertEquals(expectedPosition, rover.getCurrentPosition());
    }


    @Test
    public void testOutOfBounds() {
        Position originalPosition = createPosition(1, 2, Direction.NORTH);
        String instructions = "MMMMMMMM";
        Rover rover = createRover(originalPosition, instructions);

        assertThrows(OutOfBoundPlateauException.class, rover::processCommands);
    }

    @Test
    public void testCollision() throws BadInputException, IOException, OutOfBoundPlateauException, PlateauCollisionException {
        var testFilename = "inputCollide.txt";
        String fullPath = testInputsFolder + testFilename;
        List<Rover> rovers = Main.parseEnvironment(Path.of(fullPath));

        assertThrows(PlateauCollisionException.class, () -> {
            for (Rover rover : rovers) {
                rover.processCommands();
            }
        });
    }

    private Position createPosition(int x, int y, Direction direction) {
        return new Position(new Coordinates(x, y), direction);
    }

    // This is a facade
    private Rover createRover(Position position, String instructions) {
        return new Rover(position.coordinates(),
                position.direction(),
                instructions.trim().toUpperCase(),
                instructionsValidator,
                coordinatesValidator,
                List.of(plateau));
    }
}
