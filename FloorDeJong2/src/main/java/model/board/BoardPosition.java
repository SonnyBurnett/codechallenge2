package model.board;

import directions.Directions2D;

import java.util.HashMap;
import java.util.Map;

public class BoardPosition {
    private Integer positionId;
    private String value;

    private Map<String, BoardPosition> neighbours;


    public BoardPosition(Integer positionId) {
        this.positionId = positionId;
        this.neighbours = new HashMap<>();
    }

    // ToDo: make more general by taking out logic of Direction2D
    public void addNeighbour(String direction, BoardPosition neighbourPosition){
        this.neighbours.put(direction, neighbourPosition);

        if (! neighbourPosition.getNeighbours().containsValue(this)) {
            neighbourPosition.addNeighbour(new Directions2D().getOppositeDirection(direction), this);
        }
    }

    public BoardPosition getNeighbourAtDirection(String direction) {
        if (this.neighbours.containsKey(direction)) {
            return this.neighbours.get(direction);
        } else {
            throw new IllegalArgumentException("Position " + this.positionId + " has no neighbour in direction " + direction);
        }
    }

    public boolean sameValue(BoardPosition position) {
        if (this.value != null) {
            return this.value.equals(position.getValue());
        }
        return false;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Map<String, BoardPosition> getNeighbours(){
        return this.neighbours;
    }

    public boolean isOccupied() {
        return this.value != null;
    }

    public Integer getPositionId() {
        return positionId;
    }
}
