package me.stky;

import me.stky.entities.Obstacle;
import me.stky.entities.Rover;
import me.stky.models.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Plateau implements MovementListener {

    private final int width;
    private final int height;
    private List<Obstacle> obstacles;

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
     */
    public void addRover(Rover rover) {
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

    @Override
    public void onMove(Obstacle obstacle, Coordinates oldCoords) {
        obstacles.removeIf(obs -> obs.getCoordinates().equals(oldCoords));
        obstacles.add(obstacle);
    }
}
