using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardService 
    {
        public class BoardServiceImplementationFinished : IBoardServiceImplementation
        {
            private readonly Board _board;

            public BoardServiceImplementationFinished(Board board)
            {
                _board = board;
            }

            public void End()
            {
                throw new InvalidOperationException("Cannot finish an already finished board");
            }

            public void Initialize()
            {
                _board.Reset();
                _board.Initialize();
            }

            public void Play(char columnName, int rowNumber, Cell.Marker mark)
            {
                throw new InvalidOperationException("Play on a finished board");
            }
        }
    }
}
