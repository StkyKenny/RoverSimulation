package me.stky.commands;

import me.stky.models.Coordinates;
import me.stky.models.Direction;
import me.stky.models.Position;

public interface MoveCommand {

    public Position move(Coordinates coordinates, Direction direction);
}
