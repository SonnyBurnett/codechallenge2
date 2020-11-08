import board.BoardPosition;
import board.TicTacToeBoard;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TicTacToePlayerTest {

    private final TicTacToeBoard board = mock(TicTacToeBoard.class);
    private final TicTacToeBoard spyBoard = spy(board);

    private final BoardPosition mockPosition = mock(BoardPosition.class);

    private final TicTacToePlayer player = new TicTacToePlayer(1, "Player1", "X");

    @Test
    public void testDoMoveThreeInRowPossible() {
        // Assign
        int positionNr = 1;
        when(spyBoard.hasTwoInRowForSymbol(anyString())).thenReturn(mockPosition);
        when(mockPosition.getPositionId()).thenReturn(positionNr);

        // Act
        player.doMove(spyBoard);

        // assert
        verify(spyBoard, times(1)).hasTwoInRowForSymbol(anyString());
        verify(spyBoard, times(1)).setPositionValue(positionNr, player.getSymbol());
        verifyNoMoreInteractions(spyBoard);
    }

    @Test
    public void testDoMoveThreeInRowNotPossible() {
        // Assign
        int positionNr = 1;
        when(spyBoard.hasTwoInRowForSymbol(anyString())).thenReturn(null);

        // Act
        player.doMove(spyBoard);

        // assert
        verify(spyBoard, times(1)).hasTwoInRowForSymbol(anyString());
        verifyNoMoreInteractions(spyBoard);
    }
}
