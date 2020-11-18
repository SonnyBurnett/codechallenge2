package com.ing.challenge.model.rules;

import com.ing.challenge.model.board.Board;
import com.ing.challenge.model.player.TTTPlayerType;

import java.util.Optional;

public interface Rules {
    TTTPlayerType whoIsNext(Board board);
    Optional<TTTPlayerType> isWinner(Board board);

}
