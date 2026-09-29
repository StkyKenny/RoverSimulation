package me.stky;

public record Position(Coordinates coordinates, Direction direction) {

    public String toString(){
        return coordinates.x() +" " + coordinates().y()+" "+direction().name().charAt(0);
    }
}
