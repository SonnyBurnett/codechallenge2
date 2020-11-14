using Moq;
using System;
using Tw.Ing.Challenge2.Services;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class TweCommandTests
    {
        [Fact]
        public void Twe_State_NewToActive_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            IGameContext game = new GameContext(gameServiceMock.Object);

            // ACT
            game.Start();


            // ASSESS
            Assert.True(game.IsActive);
        }

        [Fact]
        public void Player_InvalidStates_New()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            IGameContext game = new GameContext(gameServiceMock.Object);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => game.End());
        }
    }
}
