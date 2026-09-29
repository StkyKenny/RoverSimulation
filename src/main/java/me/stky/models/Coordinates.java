package me.stky.models;

import java.util.Objects;

public record Coordinates(int x, int y) {


    /**
     * Compute the next position and return it
     *
     * @param direction
     * @return
     */
    public Coordinates moveForward(Direction direction) {
        Objects.requireNonNull(direction, "You can't move position without stating a direction");

        return switch (direction) {
            case NORTH -> new Coordinates(x(), y() + 1);
            case EAST -> new Coordinates(x() + 1, y());
            case SOUTH -> new Coordinates(x(), y() - 1);
            case WEST -> new Coordinates(x() - 1, y());
        };
    }
}
