using Castle.Core;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using System.Threading.Tasks;
using Tw.Ing.Challenge2.Plumbing;
using Xunit;

namespace Tw.Ing.Challenge2.Tests
{
    public class BoardServiceTests
    {
        [Fact]
        public void Board_State_blancotodrawn_Success()
        {
            // ARRANGE
            var service = new BoardService();

            // ACT
            service.Initialize();

            // ASSESS
            Assert.Equal(9, service.Board.Matrix.Count);
            var a1 = service.Board.Matrix[new Tw.Ing.Challenge2.Plumbing.Pair<char, int>('A', 1)];
            Assert.Equal('A', a1.Column);
            Assert.Equal(1, a1.Row);
        }

        [Fact]
        public void Board_Play_Success()
        {
            // ARRANGE
            var service = new BoardService();
            service.Initialize();

            // ACT
            service.Play('A', 1, Cell.Marker.Circle);

            // ASSERT
            var cellCoordinate = new Plumbing.Pair<char, int>('A', 1);
            var cell = service.Board.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void Board_Play_TwiceError()
        {
            // ARRANGE
            var service = new BoardService();
            service.Initialize();
            service.Play('A', 1, Cell.Marker.Circle);

            // ACT
            Action act = () => service.Play('A', 1, Cell.Marker.Cross);

            // ASSERT
            Assert.Throws<InvalidOperationException>(act);
            var cellCoordinate = new Plumbing.Pair<char, int>('A', 1);
            var cell = service.Board.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void PlayBoard_Success()
        {
            // ARRANGE
            var service = new BoardService();
            service.Initialize();

            // ACT
            service.Play('A', 1, Cell.Marker.Circle);

            // ASSERT
            var cellCoordinate = new Plumbing.Pair<char, int>('A', 1);
            var cell = service.Board.Matrix[cellCoordinate];
            Assert.Equal(Cell.Marker.Circle, cell.Mark);
        }

        [Fact]
        public void Board_State_Drawntofinished_Success()
        {
            // ARRANGE
            var service = new BoardService();
            service.Initialize();

            // ACT
            service.End();

            // ASSERT           
        }


        [Fact]
        public void Board_InvalidStates_Blanco()
        {
            //// ARRANGE
            var service = new BoardService();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => service.Play('A', 1, Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => service.End());
        }

        [Fact]
        public void Board_InvalidStates_Drawn()
        {
            //// ARRANGE
            var service = new BoardService();
            service.Initialize();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => service.Initialize());
        }

        [Fact]
        public void Board_InvalidStates_Finished()
        {
            //// ARRANGE
            var service = new BoardService();
            service.Initialize();
            service.End();

            // ACT / ASSERT
            Assert.Throws<InvalidOperationException>(() => service.Play('A', 1, Cell.Marker.Circle));
            Assert.Throws<InvalidOperationException>(() => service.End());
        }
    }
}
