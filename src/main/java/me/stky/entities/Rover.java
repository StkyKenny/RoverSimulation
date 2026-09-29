package me.stky.entities;

import me.stky.commands.MoveCommand;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;
import me.stky.validator.InstructionsValidatorImpl;

import java.util.List;
import java.util.Objects;

public class Rover extends Obstacle {
    private Coordinates coordinates;
    private Direction direction;
    private String instructions;
    private List<MoveCommand> commands;

    public Rover(Coordinates coordinates, Direction direction, String instructions,
                 InstructionsValidatorImpl instructionsValidator) {
        Objects.requireNonNull(direction, "The rover requires a valid initial direction");

        this.coordinates = coordinates;
        this.direction = direction;
        this.instructions = instructions;
        this.commands = instructionsValidator.validateInstructions(instructions);
    }

    public void processCommands() {
        for (MoveCommand command : commands) {
            Position newPosition = command.move(coordinates, direction);
            this.coordinates = newPosition.coordinates();
            this.direction = newPosition.direction();
        }
    }

    public Position getCurrentPosition() {
        return new Position(coordinates, direction);
    }
}
