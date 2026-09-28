package me.stky;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    public static Direction getDirection(String directionName) {
        return switch (directionName.toUpperCase()) {
            case "N" -> Direction.NORTH;
            case "E" -> Direction.EAST;
            case "S" -> Direction.SOUTH;
            case "W" -> Direction.WEST;
            default -> throw new IllegalArgumentException("Direction indicated wasn't valid : " + directionName);
        };
    }

    public static Direction rotateClockwise(Direction direction) {
        return Direction.values()[(direction.ordinal() + 1) % 4];
    }

    public static Direction rotateCounterClockwise(Direction direction) {
        return Direction.values()[(direction.ordinal() + 4 - 1) % 4];
    }
}
