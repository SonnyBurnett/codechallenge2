package model;

public enum TicTacToeSymbol {
    X ("X"),
    O ("O");

    private final String name;

    TicTacToeSymbol (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
