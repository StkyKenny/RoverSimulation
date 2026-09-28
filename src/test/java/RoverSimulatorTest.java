import me.stky.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoverSimulatorTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outputErrStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final String testInputsFolder = "src/main/resources/inputTest/";

    private final String parsingErrorMessage = "Error parsing/processing the parameters";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(outputErrStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testNoInputFile() {
        Main.main(new String[]{});

        String expectedOutput = "Please have the path of the input when launching" + System.lineSeparator();
        assertEquals(expectedOutput, outputErrStream.toString());
    }

    @Test
    public void testInvalidFilePath() {
        Main.main(new String[]{"non_existent.txt"});
        assertTrue(outputErrStream.toString().contains("Error reading the input file"));
    }

    @Test
    public void testNegativePlateau() {
        var testFilename = "inputNegativePlateau.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        assertTrue(outputErrStream.toString().contains("The Plateau can't have a negative length."));
    }

    @Test
    public void testBadInputPlateau() {
        var testFilename = "inputIncorrectPlateau.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        assertTrue(outputErrStream.toString().contains(parsingErrorMessage));
    }

    @Test
    public void testSetupRoverOutsideNegative() {
        var testFilename = "inputSetupRoverNegative.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        assertTrue(outputErrStream.toString().contains(parsingErrorMessage));
    }

    @Test
    public void testSetupRoverOutsideOver() {
        var testFilename = "inputSetupRoverOutsideOver.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        assertTrue(outputErrStream.toString().contains(parsingErrorMessage));
    }

    // ------------------------------------------------------

    @Test
    public void testExample() {
        var testFilename = "input.txt";
        Main.main(new String[]{testInputsFolder + testFilename});
        String expectedAnswer = "1 3 N" + System.lineSeparator() +
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
    public void testOutOfBounds() {
    }

    @Test
    public void testIgnoreBadCommands() {
    }

    @Test
    public void testCollision() {
    }
}
