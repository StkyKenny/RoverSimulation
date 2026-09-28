package me.stky;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private final int width;
    private final int height;
    private final List<Rover> obstacles;

    public Plateau(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("The Plateau can't have a negative length.");
        }
        this.width = width;
        this.height = height;
        this.obstacles = new ArrayList<>();
    }

    public void addRover(Rover rover) {
        if (checkOutOfBounds(rover.getPosition())) {
            throw new IllegalArgumentException("The Rover is outside the Plateau");
        }
        this.obstacles.add(rover);
    }


    /**
     * Check if the newPosition with collide with an already present obstacle at the coordinate indicated
     */
    public boolean hasCollision(Position newPosition) {
        for (Rover obstacle : obstacles) {
            if (obstacle.getPosition() == newPosition) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the position indicated is outside the Plateau
     *
     * @param position to check
     * @return True if outside the Plateau
     */
    public boolean checkOutOfBounds(Position position) {
        return (position.x() < 0 || position.y() < 0 ||
                position.x() > width || position.y() > height);
    }

}
