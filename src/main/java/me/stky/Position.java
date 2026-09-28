package me.stky;

import java.util.Objects;

public record Position(int x, int y) {


    /**
     * Compute the next position and return it
     *
     * @param direction
     * @return
     */
    public Position moveForward(Direction direction) {
        Objects.requireNonNull(direction, "You can't move position without stating a direction");

        return switch (direction) {
            case NORTH -> new Position(x(), y() + 1);
            case EAST -> new Position(x() + 1, y());
            case SOUTH -> new Position(x(), y() - 1);
            case WEST -> new Position(x() - 1, y());
        };
    }
}
