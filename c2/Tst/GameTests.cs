using Moq;
using System;
using Tw.Ing.Challenge2.Services;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class GameTests
    {
        [Fact]
        public void Game_State_NewToActive_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            IGameContext game = new GameContext(gameServiceMock.Object);
            BoardContext board = new BoardContext(gameServiceMock.Object);
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
            var gameServiceMock = new Mock<IGameService>();
            IGameContext game = new GameContext(gameServiceMock.Object);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => game.End());
        }
    }
}
