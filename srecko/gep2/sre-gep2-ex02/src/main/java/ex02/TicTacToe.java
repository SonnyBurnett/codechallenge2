package ex02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class TicTacToe extends Game {
    private Board board;
    private String currentState = "NEXTMOVE " + DEFAULT_START_PLAYER;
    private static final Player DEFAULT_START_PLAYER = Player.X;
    private final List<List<Integer>> winBoards = GameStates.winConditions();

    public TicTacToe() {
        this.board = new Board();
    }

    private List<Integer> flattensBoard(Player player) {
        String[] playedSet = (board.toString(player.getPlayer()).split(""));
        ArrayList<Integer> returnPlayedSet = new ArrayList<>();
        for (String played : playedSet) {
            returnPlayedSet.add(Integer.parseInt(played));
        }
        return returnPlayedSet;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @Override
    public void play() {
        this.setCurrentState("WINNER " + this.checkWinner().getPlayer());
        if (this.currentState.contains("WINNER") && currentState.contains(".")) {
            this.setCurrentState("NEXTMOVE " + nextPlayer().getPlayer());
        }
    }

    public void load(String fileName) throws Exception {

        try {
            Scanner scanner = new Scanner(new File(fileName));

            StringBuilder allLines = new StringBuilder();
            while (scanner.hasNextLine()) {
                String rawLine = scanner.nextLine().trim();
                allLines.append(rawLine);
            }
            scanner.close();

            String[] listOfCells = allLines.toString().split("");
            this.board = new Board(listOfCells);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            throw new IllegalGameSetup(" The board setup is not in order.");
        }
    }

    public void write(String fileName) throws Exception {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(currentState);
            out.close();
        } catch (Exception error) {
            System.err.println("Write error to " + fileName);
            throw new IllegalGameSetup(" The board state is not written.");
        }
    }

    @Override
    Player nextPlayer() {
        List<Integer> playerXBoard = this.flattensBoard(Player.X);
        List<Integer> playerOBoard = this.flattensBoard(Player.O);

        if (playerOBoard.size() > playerXBoard.size()) {
            return Player.X;
        } else if (playerOBoard.size() < playerXBoard.size()) {
            return Player.O;
        }

        return DEFAULT_START_PLAYER;
    }

    private List<List<Integer>> hasWinnerLines(List<Integer> flatPlayerBoard) {
        List<List<Integer>> differences = new ArrayList<>();
        for (List<Integer> winBoard : winBoards) {
            differences.add(winBoard
                    .stream()
                    .filter(flatPlayerBoard::contains)
                    .collect(Collectors.toList()));
        }
        return differences.stream().filter(i -> i.size() == 3).collect(Collectors.toList());
    }

    @Override
    Player checkWinner() {
        List<List<Integer>> differencesX = hasWinnerLines(this.flattensBoard(Player.X));
        List<List<Integer>> differencesO = hasWinnerLines(this.flattensBoard(Player.O));

        if (differencesX.size() > differencesO.size()) {
            return Player.X;
        } else if (differencesX.size() < differencesO.size()) {
            return Player.O;
        }
        return Player.NONE;
    }
}
