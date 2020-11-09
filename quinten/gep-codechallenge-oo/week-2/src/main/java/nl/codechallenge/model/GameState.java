package nl.codechallenge.model;

public enum GameState {
    X_WIN("WINNER X"), O_WIN("WINNER O"), X_NEXT("NEXTMOVE X"), O_NEXT("NEXTMOVE O");

    private final String description;

    GameState(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
