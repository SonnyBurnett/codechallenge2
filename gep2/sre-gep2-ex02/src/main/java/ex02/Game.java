package ex02;

public abstract class Game implements fileLoader, fileWriter {
    abstract Player nextPlayer();
    abstract Player checkWinner();
    abstract void play();
}
