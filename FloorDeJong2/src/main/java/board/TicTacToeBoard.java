package board;

import Exceptions.IllegalMoveException;

public class TicTacToeBoard extends Board2D {

    final BoardPositionFactory boardPositionFactory;

    public TicTacToeBoard(BoardPositionFactory boardPositionFactory) {
        this.boardPositionFactory = boardPositionFactory;
        setAllowedValues();
        createPositions();
    }

    public TicTacToeBoard(){
        this(new BoardPositionFactory());
    }

    @Override
    public void setAllowedValues() {
        this.allowedValues.add("X");
        this.allowedValues.add("O");
    }

    // ToDo: make of directions enums.
    @Override
    public void createPositions() {
        for (int i=0; i<9; i++) {
            positions.put(i, boardPositionFactory.createBoardPosition(i));
        }

        positions.get(1).addNeighbour("L", positions.get(0));
        positions.get(1).addNeighbour("LD", positions.get(3));
        positions.get(1).addNeighbour("D", positions.get(4));
        positions.get(1).addNeighbour("RD", positions.get(5));
        positions.get(1).addNeighbour("R", positions.get(2));

        positions.get(7).addNeighbour("L", positions.get(6));
        positions.get(7).addNeighbour("LU", positions.get(3));
        positions.get(7).addNeighbour("U", positions.get(4));
        positions.get(7).addNeighbour("RU", positions.get(5));
        positions.get(7).addNeighbour("R", positions.get(8));

        positions.get(3).addNeighbour("U", positions.get(0));
        positions.get(3).addNeighbour("R", positions.get(4));
        positions.get(3).addNeighbour("D", positions.get(6));

        positions.get(5).addNeighbour("U", positions.get(2));
        positions.get(5).addNeighbour("L", positions.get(4));
        positions.get(5).addNeighbour("D", positions.get(8));

        positions.get(4).addNeighbour("LU", positions.get(0));
        positions.get(4).addNeighbour("LD", positions.get(6));
        positions.get(4).addNeighbour("RU", positions.get(2));
        positions.get(4).addNeighbour("RD", positions.get(8));
    }

    @Override
    public void setPositionValue(int positionNr, String symbol) {
        if (isExistingPosition(positionNr) && isCorrectValue(symbol)) {
            BoardPosition position = positions.get(positionNr);
            if (! position.isOccupied()) {
                position.setValue(symbol);
            } else {
                throw new IllegalMoveException("Position " + positionNr + " is already occupied");
            }
        }
    }

    @Override
    public boolean isExistingPosition(int positionNr) {
        if (! this.positions.containsKey(positionNr)) {
            throw new IllegalArgumentException("Incorrect position number. Expected: 0 - 8, received: " + positionNr);
        }
        return true;
    }

    @Override
    public boolean isCorrectValue(String value) {
        if (! allowedValues.contains(value)) {
            throw new IllegalArgumentException("Incorrect symbol. Expected: one of " + allowedValues + ", received: " + value);
        }

        return true;
    }

    public int getNumberOccupiedPositions() {
        int occupiedPositions = 0;
        for (int positionNr: positions.keySet()) {
            if (positions.get(positionNr).isOccupied()) {
                occupiedPositions ++;
            }
        }

        return occupiedPositions;
    }

    public String threeInRow() {
        String value = threeInRowMiddlePosition();
        if (value != null) {
            return value;
        }

        value = threeInRowOnSides();

        return value;
    }

    public String threeInRowMiddlePosition() {
        BoardPosition middlePosition = positions.get(4);

        String[] directionsToCheck = new String[]{"LU", "U", "RU", "L"};

        for (String direction: directionsToCheck) {
            if (threeInRowInDirection(middlePosition, direction)) {
                return  middlePosition.getValue();
            }
        }
        return null;
    }

    public String threeInRowOnSides() {
        int[] positionsNrToCheck = new int[]{1, 3, 5, 7};
        String[] directionsToCheck = new String[]{"L", "U", "U", "L"};

        for (int i=0; i<4; i++) {
            if (threeInRowInDirection(this.positions.get(positionsNrToCheck[i]), directionsToCheck[i])) {
                return this.positions.get(i).getValue();
            }
        }

        return null;
    }

    public boolean threeInRowInDirection(BoardPosition position, String direction) {
        BoardPosition position1 = position.getNeighbourAtDirection(direction);

        if (position.sameValue(position1)) {
            String oppositeDirection = directions.getOppositeDirection(direction);
            BoardPosition position2 = position.getNeighbourAtDirection(oppositeDirection);

            return position.sameValue(position2);
        }
        return false;
    }
}
