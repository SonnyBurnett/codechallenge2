using Moq;
using System;
using Tw.Ing.Challenge2.Services;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class PlayerTests
    {
        [Fact]
        public void Player_State_NewToActive_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);

            // ACT
            player.Register("A", Cell.Marker.Circle);

            // ASSESS
            Assert.Equal("A", player.Name);
            Assert.Equal(Cell.Marker.Circle, player.Mark);
            Assert.False(player.IsPlaying);
        }

        [Fact]
        public void Player_State_ActiveToAtHand_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("a", Cell.Marker.Circle);

            // ACT
            player.GiveTurn();

            // ASSESS
            Assert.True(player.IsPlaying);
        }

        [Fact]
        public void Player_State_AtHandToActive_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();

            // ACT
            player.SelectColumn('a');
            player.SelectRow(1);
            player.MakeMove();

            // ASSESS
            Assert.False(player.IsPlaying);
        }

        [Fact]
        public void Player_State_AtHandToWin_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'a', 1);
            PlayCell(player, 'b', 1);


            // ACT
            player.GiveTurn();
            player.SelectColumn('c');
            player.SelectRow(1);
            player.MakeMove();

            // ASSESS
            Assert.True(player.HasWon);
        }

        [Fact]
        public void Player_State_AtHandToDraw_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'a', 1);
            PlayCell(player, 'b', 1);
            PlayCell(player, 'b', 2);
            PlayCell(player, 'c', 2);

            player.GiveTurn();

            // ACT
            player.SelectColumn('a');
            player.SelectRow(3);
            player.MakeMove();
            // ASSESS
            Assert.True(player.HasDraw);
        }

        [Fact]
        public void Player_InvalidStates_New()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = new Mock<IBoardContext>();
            boardMock
                .Setup(m => m.Play(It.IsAny<Coordinate>(), It.IsAny<Cell.Marker>()))
                .Throws(new InvalidOperationException());
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            Assert.Throws<InvalidOperationException>(() => player.SelectColumn('a'));
            Assert.Throws<InvalidOperationException>(() => player.SelectRow(1));
            Assert.Throws<InvalidOperationException>(() => player.MakeMove());
        }

        [Fact]
        public void Player_InvalidStates_Active()
        {
            var gameServiceMock = new Mock<IGameService>();
            //// ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.SelectColumn('a'));
            Assert.Throws<InvalidOperationException>(() => player.SelectRow(1));
            Assert.Throws<InvalidOperationException>(() => player.MakeMove());
        }

        [Fact]
        public void Player_InvalidStates_AtHand()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
        }


        [Fact]
        public void Player_InvalidStates_Win()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'a', 1);
            PlayCell(player, 'b', 1);
            PlayCell(player, 'c', 1);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            Assert.Throws<InvalidOperationException>(() => player.SelectColumn('a'));
            Assert.Throws<InvalidOperationException>(() => player.SelectRow(1));
            Assert.Throws<InvalidOperationException>(() => player.MakeMove());
        }

        [Fact]
        public void Player_InvalidStates_Draw()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<IGameService>();
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(gameServiceMock.Object, boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'a', 1);
            PlayCell(player, 'b', 1);
            PlayCell(player, 'b', 2);
            PlayCell(player, 'c', 2);
            PlayCell(player, 'a', 3);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            Assert.Throws<InvalidOperationException>(() => player.SelectColumn('a'));
            Assert.Throws<InvalidOperationException>(() => player.SelectRow(1));
            Assert.Throws<InvalidOperationException>(() => player.MakeMove());
        }

        private void PlayCell(PlayerContext player, char columName, int rowNumber)
        {
            player.GiveTurn();

            player.SelectColumn(columName);
            player.SelectRow(rowNumber);
            player.MakeMove();
        }

        private Mock<IBoardContext> CreateBoardMock()
        {
            var boardMock = new Mock<IBoardContext>();
            boardMock
                .Setup(m => m.Play(It.IsAny<Coordinate>(), It.IsAny<Cell.Marker>()))
                .Returns((Coordinate coordinate, Cell.Marker mark) =>
                {
                    var cell = new Cell(coordinate)
                    {
                        Mark = mark
                    };
                    return cell;
                });
            return boardMock;
        }
    }
}
