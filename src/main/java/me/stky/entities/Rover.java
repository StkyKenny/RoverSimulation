package me.stky.entities;

import me.stky.MovementListener;
import me.stky.commands.MoveCommand;
import me.stky.customException.OutOfBoundPlateauException;
import me.stky.customException.PlateauCollisionException;
import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;
import me.stky.validator.CoordinatesValidator;
import me.stky.validator.InstructionsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rover implements Obstacle {
    private final String instructions;
    private final CoordinatesValidator coordinatesValidator;
    private List<MovementListener> listeners = new ArrayList<>();
    private Coordinates coordinates;
    private Direction direction;
    private List<MoveCommand> commands;

    public Rover(Coordinates coordinates, Direction direction, String instructions,
                 InstructionsValidator instructionsValidator, CoordinatesValidator coordinatesValidator,
                 List<MovementListener> listeners) {
        Objects.requireNonNull(direction, "The rover requires a valid initial direction");

        this.coordinates = coordinates;
        this.direction = direction;
        this.instructions = instructions;
        this.commands = instructionsValidator.validateInstructions(instructions);
        this.coordinatesValidator = coordinatesValidator;
        this.listeners = listeners;
    }

    public void processCommands() throws OutOfBoundPlateauException, PlateauCollisionException {
        for (MoveCommand command : commands) {
            Position newPosition = command.move(coordinates, direction);

            this.direction = newPosition.direction();
            if (!newPosition.coordinates().equals(coordinates)) {
                // Validate only when entity has moved
                coordinatesValidator.validateCoordinate(newPosition.coordinates());
                // Observer notify for Plateau to update where the obstacles are
                var oldCoordinates = coordinates;
                this.coordinates = newPosition.coordinates();
                for (MovementListener listener : listeners) {
                    listener.onMove(this, oldCoordinates);
                }
            }

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
