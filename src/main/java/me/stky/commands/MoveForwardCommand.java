package me.stky.commands;

import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;

public class MoveForwardCommand implements MoveCommand {
    public Position move(Coordinates coordinates, Direction direction) {
        return new Position(coordinates.moveForward(direction), direction);
    }
}
