package me.stky;

import me.stky.entities.Obstacle;
import me.stky.models.Coordinates;

public interface MovementListener {
    public void onMove(Obstacle obstacle, Coordinates oldCoords);
}
