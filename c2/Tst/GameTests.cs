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
            PlayerContext p1 = new PlayerContext();
            PlayerContext p2 = new PlayerContext();
            BoardContext b1 = new BoardContext();
            p1.Register("CrossPlayer", Cell.Marker.Cross);
            p2.Register("CirclePlayer", Cell.Marker.Circle);
            b1.Initialize();

            // ACT
            game.Start(p2, p1, b1);


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
