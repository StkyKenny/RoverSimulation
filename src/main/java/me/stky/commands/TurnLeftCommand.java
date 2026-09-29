package me.stky.commands;

import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;

public class TurnLeftCommand implements MoveCommand {
    public Position move(Coordinates position, Direction direction){
        return new Position(position, Direction.rotateCounterClockwise(direction));
    }
}
