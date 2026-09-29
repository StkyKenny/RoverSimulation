package me.stky;

import me.stky.customException.BadInputException;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.entities.Rover;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.validator.CoordinatesValidatorImpl;
import me.stky.validator.InstructionsValidatorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        if (args.length < 1) {
            System.err.println("Please have the path of the input when launching");
            return;
        }
        Path inputText = Path.of(args[0]);
        List<Rover> rovers;
        // SETUP PHASE
        try {
            rovers = parseEnvironnment(inputText);

        } catch (OutOfBoundPlateauException e) {
            System.err.println("Entity found out of bounds : " + e.getMessage());
            return;
        } catch (PlateauCollisionException e) {
            System.err.println("Entity got stuck on an obstacle : " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.err.println("Couldn't parse a number : " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Error reading the input file : " + e.getMessage());
            return;
        } catch (BadInputException e) {
            System.err.println("Input file can't be read correctly : " + e.getMessage());
            return;
        }

        // RUN PHASE
        for (Rover rover : rovers) {
            try {
                rover.processCommands();
            } catch (OutOfBoundPlateauException e) {
                System.out.println(e.getMessage());
            } catch (PlateauCollisionException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(rover.getCurrentPosition());

        }

    }

    public static List<Rover> parseEnvironnment(Path input) throws OutOfBoundPlateauException, PlateauCollisionException, IOException, BadInputException {
        List<String> lines = Files.readAllLines(input);
        Plateau plateau;
        List<Rover> rovers = new ArrayList<>();

        String firstLine = lines.getFirst();
        String[] plateauDefinition = firstLine.trim().split(" ");
        if (plateauDefinition.length < 2) {
            throw new BadInputException(firstLine);
        }
        plateau = new Plateau(Integer.parseInt(plateauDefinition[0]), Integer.parseInt(plateauDefinition[1]));

        // 2 Lines per rover, makes the index incremented twice
        int currentLineIdx = 1;
        while (currentLineIdx < lines.size()) {

            String currentLine = lines.get(currentLineIdx);
            String[] roverDefinition = currentLine.trim().split(" ");
            if (roverDefinition.length < 3) {
                throw new BadInputException(currentLine);
            }
            currentLineIdx += 1;
            Direction direction = Direction.getDirection(roverDefinition[2]);
            Rover rover = new Rover(
                    new Coordinates(Integer.parseInt(roverDefinition[0]), Integer.parseInt(roverDefinition[1])),
                    direction,
                    lines.get(currentLineIdx).trim().toUpperCase(),
                    new InstructionsValidatorImpl(),
                    new CoordinatesValidatorImpl(plateau),
                    List.of(plateau)
            );

            new CoordinatesValidatorImpl(plateau).validateCoordinate(rover.getCoordinates());
            rovers.add(rover);
            plateau.addRover(rover);
            currentLineIdx += 1;
        }
        return rovers;
    }

}