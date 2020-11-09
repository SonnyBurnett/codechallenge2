package ex02;

public enum Player {
    X("X"),
    O("O"),
    NONE(".");

    private final String player;

    Player(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }
}
