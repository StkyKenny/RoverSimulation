import me.stky.Main;
import me.stky.customException.BadInputException;
import me.stky.customException.OutOfBoundPlateauException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverSimulatorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outputErrStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final String testInputsFolder = "src/main/resources/inputTest/";

    private final String parsingErrorMessage = "Error parsing/processing the parameters";


    @Test
    public void testNoInputFile() {
        assertThrows(IOException.class, () -> Main.parseEnvironnment(Path.of("")));
    }

    @Test
    public void testInvalidFilePath() {
        var testFilename = "non_existent.txt";
        assertThrows(IOException.class, () -> Main.parseEnvironnment(Path.of(testFilename)));
    }

    @Test
    public void testNegativePlateau() {
        var testFilename = "inputNegativePlateau.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(BadInputException.class, () -> Main.parseEnvironnment(Path.of(fullPath)));
    }

    @Test
    public void testBadInputPlateau() {
        var testFilename = "inputNaNPlateau.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(NumberFormatException.class, () -> Main.parseEnvironnment(Path.of(fullPath)));
    }

    @Test
    public void testSetupRoverOutsideNegative() {
        var testFilename = "inputSetupRoverNegative.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(OutOfBoundPlateauException.class, () -> Main.parseEnvironnment(Path.of(fullPath)));
    }

    @Test
    public void testSetupRoverOutsideOver() {
        var testFilename = "inputSetupRoverOutsideOver.txt";
        String fullPath = testInputsFolder + testFilename;
        assertThrows(OutOfBoundPlateauException.class, () -> Main.parseEnvironnment(Path.of(fullPath)));
    }

    // ------------------------------------------------------

    @Test
    public void testExample() {
        var testFilename = "input.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        String expectedAnswer =
                "1 3 N" + System.lineSeparator() +
                        "5 1 E" + System.lineSeparator();
        assertEquals(expectedAnswer, outputStream.toString());
    }

    @Test
    public void testNoCommands() {
        var testFilename = "inputNoCommands.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        String expectedAnswer =
                "1 2 N" + System.lineSeparator() +
                        "3 3 E" + System.lineSeparator();
        assertEquals(expectedAnswer, outputStream.toString());
    }


    @Test
    public void testIgnoreBadCommands() {
        var testFilename = "inputWithBadCommands.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        String expectedAnswer =
                "1 3 N" + System.lineSeparator() +
                        "5 1 E" + System.lineSeparator();
        assertEquals(expectedAnswer, outputStream.toString());
    }

    @Test
    public void testOutOfBounds() {
        var testFilename = "inputGoingOutOfBounds.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        String expectedAnswer =
                "SIGNAL LOST : Rover got out of the Plateau" + System.lineSeparator() +
                        "1 6 N" + System.lineSeparator() +
                        "5 1 E" + System.lineSeparator();
        assertEquals(expectedAnswer, outputStream.toString());
    }

    @Test
    public void testCollision() {
        var testFilename = "inputCollide.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        String expectedAnswer =
                "COLLISION : Rover couldn't proceed further" + System.lineSeparator() +
                        "0 3 N" + System.lineSeparator() +
                        "0 4 E" + System.lineSeparator();
        assertEquals(expectedAnswer, outputStream.toString());

    }
}
