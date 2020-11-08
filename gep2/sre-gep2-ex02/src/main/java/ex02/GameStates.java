package ex02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameStates {
    private final static int rows = 3;
    private final static int columns = 3;
    private final static int totalCells = rows * columns;

    public static List<List<Integer>> winConditions() {
        final Collection<List<Integer>> columnWins = IntStream
                .range(0, totalCells)
                .boxed()
                .collect(Collectors
                        .groupingBy(i -> i % rows))
                .values();

        final Collection<List<Integer>> rowWins = IntStream
                .range(0, totalCells)
                .boxed()
                .collect(Collectors
                        .groupingBy(i -> i / rows))
                .values();

        final List<Integer> diagonalWin149 = IntStream
                .rangeClosed(0, rows - 1)
                .map(i -> i * (rows + 1))
                .boxed()
                .collect(Collectors
                        .toList());

        final List<Integer> diagonalWin347 = IntStream
                .rangeClosed(1, rows)
                .map(i -> i * (rows - 1))
                .boxed()
                .collect(Collectors
                        .toList());

        List<List<Integer>> winCollection = new ArrayList<>();
        winCollection.addAll(columnWins);
        winCollection.addAll(rowWins);
        winCollection.add(diagonalWin149);
        winCollection.add(diagonalWin347);

        return winCollection;
    }

}
