using System;
using System.Collections.Generic;
using System.Numerics;
using System.Text;
using System.Windows.Input;
using Tw.Ing.Challenge2.Plumbing;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardService 
    {
        public class BoardServiceImplementationDrawn : IBoardServiceImplementation
        {
            private readonly Board _board;

            public BoardServiceImplementationDrawn(Board board)
            {
                _board = board;
            }

            public void End()
            {
                // don't have to do anything here.
            }

            public void Initialize()
            {
                throw new InvalidOperationException("Initialize not supported on Board 'draws' (we are playing it)");
            }

            public void Play(char columnName, int rowNumber, Cell.Marker mark)
            {
                var cellCoordinate = new Pair<char, int>(columnName, rowNumber);
                _board.Matrix[cellCoordinate].Mark = mark;
            }
        }
    }
}
