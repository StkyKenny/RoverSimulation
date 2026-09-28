package me.stky;

import java.util.Objects;

public class Rover {
    private Position position;
    private Direction direction;
    private String instructions;

    public Rover(Position pos, Direction direction) {
        Objects.requireNonNull(direction, "The rover requires a valid initial direction");

        this.position = pos;
        this.direction = direction;
        this.instructions = "";
    }


    public void setInstructions(String instructions) {
        Objects.requireNonNull(instructions, "Instructions can't be null");
        this.instructions = instructions;
    }

    public void updateDirection(Direction direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void processCommands(Plateau plateau) {
        for (int idx = 0; idx < instructions.length(); idx++) {
            char currentCommand = instructions.charAt(idx);

            switch (currentCommand) {
                case 'L':
                    updateDirection(Direction.rotateCounterClockwise(this.direction));
                    break;
                case 'R':
                    updateDirection(Direction.rotateClockwise(this.direction));
                    break;
                case 'M':
                    var newPosition = this.position.moveForward(this.direction);

                    if (plateau.hasCollision(newPosition)) {
                        // In case of collision, it is better to stop all commands (and wait for the rectified course of actions)
                        return;
                    }
                    this.position = newPosition;

                    //OUT OF PLATEAU check
                    if (plateau.checkOutOfBounds(position)) {
                        System.out.println("Rover got out of the Plateau");
                        instructions = "";
                        // You can add additional logics, perhaps a flag to disable the rover or destroy it ?
                        return;
                    }
                    break;
                default:
                    // ignore Bad commands
            }
        }
        // Clear commands when done
        instructions = "";

    }

    public void displayLocation() {
        System.out.println(position.x() + " " + position.y() + " " + direction.name().charAt(0));
    }
}
