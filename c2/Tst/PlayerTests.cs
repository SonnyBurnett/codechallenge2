using Moq;
using System;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class PlayerTests
    {
        [Fact]
        public void Player_State_NewToActive_Success()
        {
            // ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);

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
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            // ACT
            player.GiveTurn();

            // ASSESS
            Assert.True(player.IsPlaying);
        }

        [Fact]
        public void Player_State_AtHandToActive_Success()
        {
            // ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);
            player.GiveTurn();

            // ACT
            var cellCoordinate = new Coordinate('A', 1);
            player.MakeMove(cellCoordinate);

            // ASSESS
            Assert.False(player.IsPlaying);
        }

        [Fact]
        public void Player_State_AtHandToWin_Success()
        {
            // ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);


            // ACT
            player.GiveTurn();
            var cellCoordinate = new Coordinate('C', 1);
            player.MakeMove(cellCoordinate);

            // ASSESS
            Assert.True(player.HasWon);
        }

        [Fact]
        public void Player_State_AtHandToDraw_Success()
        {
            // ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);
            PlayCell(player, 'B', 2);
            PlayCell(player, 'C', 2);

            player.GiveTurn();

            // ACT
            var cellCoordinate = new Coordinate('A', 3);
            player.MakeMove(cellCoordinate);

            // ASSESS
            Assert.True(player.HasDraw);
        }

        [Fact]
        public void Player_InvalidStates_New()
        {
            //// ARRANGE
            var boardMock = new Mock<IBoardContext>();
            boardMock
                .Setup(m => m.Draw(It.IsAny<Coordinate>(), It.IsAny<Cell.Marker>()))
                .Throws(new InvalidOperationException());
            var player = new PlayerContext(boardMock.Object);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            var cellCoordinate = new Coordinate('A', 1);
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cellCoordinate));
        }

        [Fact]
        public void Player_InvalidStates_Active()
        {
            //// ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            var cellCoordinate = new Coordinate('A', 1);
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cellCoordinate));
        }

        [Fact]
        public void Player_InvalidStates_AtHand()
        {
            //// ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
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
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);
            PlayCell(player, 'C', 1);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            var cellCoordinate = new Coordinate('A', 1);
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(cellCoordinate));
        }

        [Fact]
        public void Player_InvalidStates_Draw()
        {
            //// ARRANGE
            var boardMock = CreateBoardMock();
            var player = new PlayerContext(boardMock.Object);
            player.Register("A", Cell.Marker.Circle);

            PlayCell(player, 'A', 1);
            PlayCell(player, 'B', 1);
            PlayCell(player, 'B', 2);
            PlayCell(player, 'C', 2);
            PlayCell(player, 'A', 3);

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => player.Register("A", Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => player.GiveTurn());
            Assert.Throws<InvalidOperationException>(() => player.MakeMove(new Coordinate('A', 1)));
        }

        private void PlayCell(PlayerContext player, char columName, int rowNumber)
        {
            var cellCoordinate = new Coordinate(columName, rowNumber);
            player.GiveTurn();
            player.MakeMove(cellCoordinate);
        }

        private Mock<IBoardContext> CreateBoardMock()
        {
            var boardMock = new Mock<IBoardContext>();
            boardMock
                .Setup(m => m.Draw(It.IsAny<Coordinate>(), It.IsAny<Cell.Marker>()))
                .Returns((Coordinate coordinate, Cell.Marker mark) =>
                {
                    var cell = new Cell(coordinate);
                    cell.Mark = mark;
                    return cell;
                });
            return boardMock;
        }
    }
}
