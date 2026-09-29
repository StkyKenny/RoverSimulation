package me.stky;

import me.stky.entities.Rover;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.validator.InstructionsValidatorImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Rover> rovers = new ArrayList<>();
        Plateau plateau;

        if (args.length < 1) {
            System.err.println("Please have the path of the input when launching");
            return;
        }
        Path inputText = Path.of(args[0]);

        // SETUP PHASE
        try {
            List<String> lines = Files.readAllLines(inputText);

            String[] plateauDefinition = lines.getFirst().trim().split(" ");
            if (plateauDefinition.length < 2) {
                System.err.println("Error not enough parameters for the Plateau (2 required)");
                return;
            }
            plateau = new Plateau(Integer.parseInt(plateauDefinition[0]), Integer.parseInt(plateauDefinition[1]));

            // 2 Lines per rover, makes the index incremented twice
            int currentLineIdx = 1;
            while (currentLineIdx < lines.size()) {

                String[] roverDefinition = lines.get(currentLineIdx).trim().split(" ");
                if (roverDefinition.length < 3) {
                    System.err.println("Error not enough parameters for the Rover (3 required)");
                    return;
                }
                currentLineIdx += 1;
                Direction direction = Direction.getDirection(roverDefinition[2]);
                Rover rover = new Rover(
                        new Coordinates(Integer.parseInt(roverDefinition[0]), Integer.parseInt(roverDefinition[1])),
                        direction,
                        lines.get(currentLineIdx).trim().toUpperCase(),
                        new InstructionsValidatorImpl()
                );
                rovers.add(rover);

                plateau.addRover(rover);
                currentLineIdx += 1;
            }


        } catch (IOException e) {
            System.err.println("Error reading the input file : " + e.getMessage());
            return;
        } catch (Exception e) {
            System.err.println("Error parsing/processing the parameters : " + e.getMessage());
            return;
        }

        // RUN PHASE
        for (Rover rover : rovers) {
            rover.processCommands();
            System.out.println(rover.getCurrentPosition());

        }

    }

}