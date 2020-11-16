using Moq;
using System;
using Tw.Ing.Challenge2.Services;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class BoardTests
    {
        [Fact]
        public void Board_State_BlancToDrawn_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);

            // ACT
            gameBoard.Initialize();

            // ASSESS
            Assert.Equal(9, gameBoard.Matrix.Count);
            var a1 = gameBoard.Matrix[new Coordinate('a', 1)];
            Assert.Equal('a', a1.Column);
            Assert.Equal(1, a1.Row);
        }

        [Fact]
        public void Board_Play_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();

            // ACT
            var cellCoordinate = new Coordinate('a', 1);
            gameBoard.Play(cellCoordinate, Cell.Marker.Circle);

            // ASSERT
            var cell = gameBoard.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void Board_Play_TwiceError()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();
            var cellCoordinate = new Coordinate('a', 1);
            gameBoard.Play(cellCoordinate, Cell.Marker.Circle);

            // ACT
            Action act = () => gameBoard.Play(cellCoordinate, Cell.Marker.Cross);

            // ASSERT
            Assert.Throws<InvalidOperationException>(act);
            var cell = gameBoard.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void PlayBoard_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();

            // ACT
            var cellCoordinate = new Coordinate('a', 1);
            gameBoard.Play(cellCoordinate, Cell.Marker.Circle);

            // ASSERT
            var cell = gameBoard.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void Board_State_DrawnToFinished_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();

            // ACT
            gameBoard.End();

            // ASSERT           
        }


        [Fact]
        public void Board_State_FinishedToBlanco_Success()
        {
            // ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();
            gameBoard.End();

            // ACT
            gameBoard.Initialize();

            // ASSERT         
            
        }

        [Fact]
        public void Board_InvalidStates_Blanco()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);

            // ACT / ASSERT
            var cellCoordinate = new Coordinate('a', 1);
            Assert.Throws<InvalidOperationException>(() => gameBoard.Play(cellCoordinate, Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => gameBoard.End());
        }

        [Fact]
        public void Board_InvalidStates_Drawn()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => gameBoard.Initialize());
        }

        [Fact]
        public void Board_InvalidStates_Finished()
        {
            //// ARRANGE
            var gameServiceMock = new Mock<ITwgeService>();
            var gameBoard = new BoardContext(gameServiceMock.Object);
            gameBoard.Initialize();
            gameBoard.End();

            // ACT / ASSERT
            var cellCoordinate = new Coordinate('a', 1);
            Assert.Throws<InvalidOperationException>(() => gameBoard.Play(cellCoordinate, Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => gameBoard.End());
        }
    }
}
