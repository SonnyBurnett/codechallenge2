using System;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class BoardTests
    {
        [Fact]
        public void Board_State_BlancToDrawn_Success()
        {
            // ARRANGE
            var gameBoard = new BoardContext();

            // ACT
            gameBoard.Initialize();

            // ASSESS
            Assert.Equal(9, gameBoard.Matrix.Count);
            var a1 = gameBoard.Matrix[new Coordinate('A', 1)];
            Assert.Equal('A', a1.Column);
            Assert.Equal(1, a1.Row);
        }

        [Fact]
        public void Board_Play_Success()
        {
            // ARRANGE
            var gameBoard = new BoardContext();
            gameBoard.Initialize();

            // ACT
            var cellCoordinate = new Coordinate('A', 1);
            gameBoard.Draw(cellCoordinate, Cell.Marker.Circle);

            // ASSERT
            var cell = gameBoard.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void Board_Play_TwiceError()
        {
            // ARRANGE
            var gameBoard = new BoardContext();
            gameBoard.Initialize();
            var cellCoordinate = new Coordinate('A', 1);
            gameBoard.Draw(cellCoordinate, Cell.Marker.Circle);

            // ACT
            Action act = () => gameBoard.Draw(cellCoordinate, Cell.Marker.Cross);

            // ASSERT
            Assert.Throws<InvalidOperationException>(act);
            var cell = gameBoard.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void PlayBoard_Success()
        {
            // ARRANGE
            var gameBoard = new BoardContext();
            gameBoard.Initialize();

            // ACT
            var cellCoordinate = new Coordinate('A', 1);
            gameBoard.Draw(cellCoordinate, Cell.Marker.Circle);

            // ASSERT
            var cell = gameBoard.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void Board_State_DrawnToFinished_Success()
        {
            // ARRANGE
            var gameBoard = new BoardContext();
            gameBoard.Initialize();

            // ACT
            gameBoard.End();

            // ASSERT           
        }


        [Fact]
        public void Board_State_FinishedToBlanco_Success()
        {
            // ARRANGE
            var gameBoard = new BoardContext();
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
            var gameBoard = new BoardContext();

            // ACT / ASSERT
            var cellCoordinate = new Coordinate('A', 1);
            Assert.Throws<InvalidOperationException>(() => gameBoard.Draw(cellCoordinate, Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => gameBoard.End());
        }

        [Fact]
        public void Board_InvalidStates_Drawn()
        {
            //// ARRANGE
            var gameBoard = new BoardContext();
            gameBoard.Initialize();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => gameBoard.Initialize());
        }

        [Fact]
        public void Board_InvalidStates_Finished()
        {
            //// ARRANGE
            var gameBoard = new BoardContext();
            gameBoard.Initialize();
            gameBoard.End();

            // ACT / ASSERT
            var cellCoordinate = new Coordinate('A', 1);
            Assert.Throws<InvalidOperationException>(() => gameBoard.Draw(cellCoordinate, Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => gameBoard.End());
        }
    }
}
