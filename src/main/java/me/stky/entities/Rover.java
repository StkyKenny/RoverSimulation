package me.stky.entities;

import me.stky.commands.MoveCommand;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;
import me.stky.validator.CoordinatesValidator;
import me.stky.validator.InstructionsValidator;

import java.util.List;
import java.util.Objects;

public class Rover implements Obstacle {
    private final String instructions;
    private final CoordinatesValidator coordinatesValidator;
    private Coordinates coordinates;
    private Direction direction;
    private List<MoveCommand> commands;

    public Rover(Coordinates coordinates, Direction direction, String instructions,
                 InstructionsValidator instructionsValidator, CoordinatesValidator coordinatesValidator) {
        Objects.requireNonNull(direction, "The rover requires a valid initial direction");

        this.coordinates = coordinates;
        this.direction = direction;
        this.instructions = instructions;
        this.commands = instructionsValidator.validateInstructions(instructions);
        this.coordinatesValidator = coordinatesValidator;
    }

    public void processCommands() throws OutOfBoundPlateauException, PlateauCollisionException {
        for (MoveCommand command : commands) {
            Position newPosition = command.move(coordinates, direction);

            if (!newPosition.coordinates().equals(coordinates)) {
                // Validate only when entity has moved
                coordinatesValidator.validateCoordinate(newPosition.coordinates());
            }

            this.coordinates = newPosition.coordinates();
            this.direction = newPosition.direction();
        }
    }

    public Position getCurrentPosition() {
        return new Position(coordinates, direction);
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }
}
