package ex02;

public class PlayTicTacToe {
    public static void main(String[] args) {
        Game ticTacToe = new TicTacToe();
        try {
            ticTacToe.load("gep2/sre-gep2-ex02/src/main/resources/inputs.txt");
            ticTacToe.play();
            ticTacToe.write("gep2/sre-gep2-ex02/src/main/resources/output.csv");
        } catch (Exception exception) {
            System.out.println("No load! ("+exception.toString()+")");
        }
    }
}
