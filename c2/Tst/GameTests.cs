using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class GameTests
    {
        [Fact]
        public void Game_State_NewToActive_Success()
        {
            // ARRANGE
            IGameContext game = new GameContext();
            BoardContext board = new BoardContext();
            PlayerContext p1 = new PlayerContext(board);
            PlayerContext p2 = new PlayerContext(board);
            p1.Register("CrossPlayer", Cell.Marker.Cross);
            p2.Register("CirclePlayer", Cell.Marker.Circle);
            board.Initialize();

            // ACT
            game.Start(p2, p1, board);


            // ASSESS
            Assert.True(game.IsActive);
        }

        [Fact]
        public void Player_InvalidStates_New()
        {
            //// ARRANGE
            IGameContext game = new GameContext();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => game.End());
        }
    }
}
