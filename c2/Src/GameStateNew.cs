using System;
using System.Diagnostics;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext
    {
        class GameStateNew : Game
        {
            public GameStateNew(GameContext parent) : base(parent)
            {
            }

            public override Game Start(PlayerContext p1, PlayerContext p2, BoardContext board)
            {
                Trace.Assert(p1.Mark == Cell.Marker.Circle, "Player 1 always plays the Circle mark");
                Trace.Assert(!String.IsNullOrWhiteSpace(p1.Name), "Player 1 does not have a name");
                Parent.PlayerCircle = p1;
                Trace.Assert(p2.Mark == Cell.Marker.Cross, "Player 2 always plays the Cross mark");
                Trace.Assert(!String.IsNullOrWhiteSpace(p2.Name), "Player 2 does not have a name");
                Parent.PlayerCross = p2;
                Trace.Assert(board.IsInitialized, "Cannot play on an empty board, draw first.");
                Parent.Board = board;

                return new GameStateActive(Parent);
            }

            public override Game Quit()
            {
                return new GameStateFinished(Parent);
            }

            public override Game End()
            {
                throw new InvalidOperationException("Cannot end a game which is not started");
            }

        }
    }
}