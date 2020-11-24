package board;

import Exceptions.IllegalMoveException;
import directions.Directions2D;
import org.apache.commons.lang3.EnumUtils;
import player.TicTacToeSymbol;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeBoard {
    Map<Integer, BoardPosition> positions = new HashMap<>();
    Directions2D directions = new Directions2D();

    final BoardPositionFactory boardPositionFactory;

    public TicTacToeBoard(BoardPositionFactory boardPositionFactory) {
        this.boardPositionFactory = boardPositionFactory;
        createPositions();
    }

    public TicTacToeBoard(){
        this(new BoardPositionFactory());
    }

    // ToDo: make of directions enums.
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

    public void setPositionValue(int positionNr, TicTacToeSymbol symbol) {
        if (isExistingPosition(positionNr)) {
            BoardPosition position = positions.get(positionNr);
            if (! position.isOccupied()) {
                position.setValue(symbol.toString());
            } else {
                throw new IllegalMoveException("Position " + positionNr + " is already occupied");
            }
        }
    }

    public boolean isExistingPosition(int positionNr) {
        if (! this.positions.containsKey(positionNr)) {
            throw new IllegalArgumentException("Incorrect position number. Expected: 0 - 8, received: " + positionNr);
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

    public Map<Integer, BoardPosition> getPositions() {
        return positions;
    }

    public String hasThreeInRow() {
        String value = hasThreeInRowMiddlePosition();
        if (value != null) {
            return value;
        }

        return hasThreeInRowOnSides();
    }

    public String hasThreeInRowMiddlePosition() {
        BoardPosition middlePosition = positions.get(4);

        String[] directionsToCheck = new String[]{"LU", "U", "RU", "L"};

        for (String direction: directionsToCheck) {
            if (hasThreeInRow(middlePosition, direction)) {
                return  middlePosition.getValue();
            }
        }
        return null;
    }

    public String hasThreeInRowOnSides() {
        int[] positionsNrToCheck = new int[]{1, 3, 5, 7};
        String[] directionsToCheck = new String[]{"L", "U", "U", "L"};

        for (int i=0; i<positionsNrToCheck.length; i++) {
            BoardPosition position = this.positions.get(positionsNrToCheck[i]);
            if (hasThreeInRow(position, directionsToCheck[i])) {
                return position.getValue();
            }
        }

        return null;
    }

    public boolean hasThreeInRow(BoardPosition position, String direction) {
        BoardPosition position1 = position.getNeighbourAtDirection(direction);

        if (position.sameValue(position1)) {
            String oppositeDirection = directions.getOppositeDirection(direction);
            BoardPosition position2 = position.getNeighbourAtDirection(oppositeDirection);

            return position.sameValue(position2);
        }
        return false;
    }

    public BoardPosition gettingWinningPosition(TicTacToeSymbol symbol) {
        BoardPosition position = getWinningPositionMiddlePosition(symbol);
        if (position != null) {
            return position;
        }

        return getWinningInRowOnSides(symbol);
    }

    public BoardPosition getWinningPositionMiddlePosition(TicTacToeSymbol symbol) {
        BoardPosition middlePosition = positions.get(4);

        String[] directionsToCheck = new String[]{"LU", "U", "RU", "L"};

        for (String direction: directionsToCheck) {
            BoardPosition position = gettingWinningPosition(middlePosition, direction, symbol);
            if (position != null) {
                return  position;
            }
        }
        return null;
    }

    public BoardPosition getWinningInRowOnSides(TicTacToeSymbol symbol) {
        int[] positionsNrToCheck = new int[]{1, 3, 5, 7};
        String[] directionsToCheck = new String[]{"L", "U", "U", "L"};

        for (int i=0; i<positionsNrToCheck.length; i++) {
            BoardPosition position = gettingWinningPosition(this.positions.get(positionsNrToCheck[i]), directionsToCheck[i], symbol);

            if (position != null) {
                return position;
            }
        }

        return null;
    }

    public BoardPosition gettingWinningPosition(BoardPosition position, String direction, TicTacToeSymbol symbol) {
        String oppositeDirection = directions.getOppositeDirection(direction);

        BoardPosition position1 = position.getNeighbourAtDirection(direction);
        BoardPosition position2 = position.getNeighbourAtDirection(oppositeDirection);

        if (position.getValue() == null && position1.getValue() != null && position2.getValue() != null) {
            if (position1.sameValue(position2) && position1.getValue().equals(symbol.toString())) {
                return position;
            }
        } else if (position.getValue() != null && position1.getValue() == null && position2.getValue() != null) {
            if (position.sameValue(position2) && position.getValue().equals(symbol.toString())) {
                return position1;
            }
        } else if (position.getValue() != null && position1.getValue() != null && position2.getValue() == null) {
            if (position.sameValue(position1) && position.getValue().equals(symbol.toString())) {
                return position2;
            }
        }


        return null;
    }

}
