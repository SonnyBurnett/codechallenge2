package nl.codechallenge.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Game {

    private static final List<Field> TRIPLE_X = Arrays.asList(Field.X, Field.X, Field.X);
    private static final List<Field> TRIPLE_O = Arrays.asList(Field.O, Field.O, Field.O);

    @NonNull
    private Field topLeft;
    @NonNull
    private Field topCenter;
    @NonNull
    private Field topRight;
    @NonNull
    private Field centerLeft;
    @NonNull
    private Field center;
    @NonNull
    private Field centerRight;
    @NonNull
    private Field bottomLeft;
    @NonNull
    private Field bottomCenter;
    @NonNull
    private Field bottomRight;

    public GameState state() {
        for (List<Field> triple : allTriples()) {
            if (triple.equals(TRIPLE_X)) {
                return GameState.X_WIN;
            }
            if (triple.equals(TRIPLE_O)) {
                return GameState.O_WIN;
            }
        }
        if (oCount() >= xCount()) {
            return GameState.X_NEXT;
        } else {
            // according to the exercise next move O is the only other option, since only 4 game states are described
            return GameState.O_NEXT;
        }
    }

    private List<List<Field>> allTriples() {
        return Arrays.asList(topRow(), centerRow(), bottomRow(), leftColumn(), centerColumn(), rightColumn(),
                diagonalTopLeft(), diagonalTopRight());
    }

    private List<Field> topRow() {
        return Arrays.asList(topLeft, topCenter, topRight);
    }

    private List<Field> centerRow() {
        return Arrays.asList(centerLeft, center, centerRight);
    }

    private List<Field> bottomRow() {
        return Arrays.asList(bottomLeft, bottomCenter, bottomRight);
    }

    private List<Field> leftColumn() {
        return Arrays.asList(topLeft, centerLeft, bottomLeft);
    }

    private List<Field> centerColumn() {
        return Arrays.asList(topCenter, center, bottomCenter);
    }

    private List<Field> rightColumn() {
        return Arrays.asList(topRight, centerRight, bottomRight);
    }

    private List<Field> diagonalTopLeft() {
        return Arrays.asList(topLeft, center, bottomRight);
    }

    private List<Field> diagonalTopRight() {
        return Arrays.asList(topRight, center, bottomLeft);
    }

    private int xCount() {
        return Collections.frequency(fieldsAsList(), Field.X);
    }

    private int oCount() {
        return Collections.frequency(fieldsAsList(), Field.O);
    }

    private List<Field> fieldsAsList() {
        return Arrays.asList(topLeft, topCenter, topRight, centerLeft, center, centerRight, bottomLeft, bottomCenter,
                bottomRight);
    }
}
