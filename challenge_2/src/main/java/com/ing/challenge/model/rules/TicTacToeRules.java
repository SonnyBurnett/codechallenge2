package com.ing.challenge.model.rules;

import com.ing.challenge.model.board.Board;
import com.ing.challenge.model.player.TTTPlayer;
import com.ing.challenge.model.player.TTTPlayerType;
import org.eclipse.collections.api.block.function.Function3;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.impl.tuple.Tuples;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TicTacToeRules implements Rules {
    @Override
    public TTTPlayerType whoIsNext(Board board) {
        int x = 0, o = 0;
        char[] gameBoard = board.getBoard();
        for (char n : gameBoard) {
            if (n == 'X' || n == 'x') {
                x++;
            } else if (n == 'O' || n == 'o') {
                o++;
            }
        }
        if (x == o) {
            return TTTPlayerType.X;
        } else {
            return TTTPlayerType.O;
        }
    }

    @Override
    public Optional<TTTPlayerType> isWinner(Board board) {
        var horizontalWin = checkHorizontalWin(board);
        return horizontalWin.or(() -> checkVerticalWin(board)).or(() -> checkDiagonalWin(board));
    }

    Optional<TTTPlayerType> checkHorizontalWin(Board board) {
        return horizontalLineWinner(board.getBoard());
    }

    Optional<TTTPlayerType> checkVerticalWin(Board board) {
        return verticalLineWinner(board.getBoard());
    }

    Optional<TTTPlayerType> checkDiagonalWin(Board board) {
        return diagonalLineWinner(board.getBoard());
    }

    private final Function3<char[],
            TTTPlayerType,
            List<Triple<Integer, Integer, Integer>>,
            Optional<TTTPlayerType>> ruleBase = (line, player, options) -> {
        List<Boolean> checks = options.stream()
                .map(triple -> matchBasedOnPositions(line, player, triple.getOne(), triple.getTwo(), triple.getThree()))
                .collect(Collectors.toList());
        if (checks.stream().anyMatch(c -> c)) {
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    };

    private final BiFunction<char[], TTTPlayerType, Optional<TTTPlayerType>> horizontal = (line, player) -> {
        List<Triple<Integer, Integer, Integer>> triples = List.of(
                Tuples.triple(1, 2, 3),
                Tuples.triple(4, 5, 6),
                Tuples.triple(7, 8, 9)
        );
        return ruleBase.value(line, player, triples);
    };

    private boolean matchBasedOnPositions(char[] line, TTTPlayerType player, int x, int y, int z) {
        return List.of(line[x - 1], line[y -1], line[z -1]).stream()
                .map(String::valueOf)
                .allMatch(c -> c.equals(player.name()));
    }

    private Optional<TTTPlayerType> horizontalLineWinner(char[] board) {
        return horizontal.apply(board, TTTPlayerType.X)
                .or(() -> horizontal.apply(board, TTTPlayerType.O));
    }

    private final BiFunction<char[], TTTPlayerType, Optional<TTTPlayerType>> vertical = (line, player) -> {
        List<Triple<Integer, Integer, Integer>> triples = List.of(
                Tuples.triple(1, 4, 7),
                Tuples.triple(2, 5, 8),
                Tuples.triple(3, 6, 9)
        );
        return ruleBase.value(line, player, triples);
    };

    private Optional<TTTPlayerType> verticalLineWinner(char[] board) {
        return vertical.apply(board, TTTPlayerType.X)
                .or(() -> vertical.apply(board, TTTPlayerType.O));
    }

    private final BiFunction<char[], TTTPlayerType, Optional<TTTPlayerType>> diagonal = (line, player) -> {
        List<Triple<Integer, Integer, Integer>> triples = List.of(
                Tuples.triple(1, 5, 9),
                Tuples.triple(3, 5, 7)
        );
        return ruleBase.value(line, player, triples);
    };

    private Optional<TTTPlayerType> diagonalLineWinner(char[] board) {
        return diagonal.apply(board, TTTPlayerType.X)
                .or(() -> diagonal.apply(board, TTTPlayerType.O));
    }
}
