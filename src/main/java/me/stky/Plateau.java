package me.stky;

import me.stky.entities.Obstacle;
import me.stky.entities.Rover;
import me.stky.models.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private final int width;
    private final int height;
    private final List<Obstacle> obstacles;

    public Plateau(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("The Plateau can't have a negative length.");
        }
        this.width = width;
        this.height = height;
        this.obstacles = new ArrayList<>();
    }

    /**
     *
     * @param rover
     * @throws IllegalArgumentException If the rover is placed outside the Plateau
     */
    public void addRover(Rover rover) throws IllegalArgumentException {
        if (checkOutOfBounds(rover.getCoordinates())) {
            throw new IllegalArgumentException("The Rover is outside the Plateau");
        }
        this.obstacles.add(rover);
    }


    /**
     * Check if the newcoordinate with collide with an already present obstacle at the coordinate indicated
     */
    public boolean hasCollision(Coordinates newcoordinate) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getCoordinates().equals(newcoordinate)) {
                return true;
            }
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Check if the coordinate indicated is outside the Plateau
     *
     * @param coordinates to check
     * @return True if outside the Plateau
     */
    public boolean checkOutOfBounds(Coordinates coordinates) {
        return (coordinates.x() < 0 || coordinates.y() < 0 ||
                coordinates.x() > width || coordinates.y() > height);
    }

}
