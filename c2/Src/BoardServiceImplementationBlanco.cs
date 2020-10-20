using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardService
    {
        protected class BoardServiceImplementationBlanco : IBoardServiceImplementation
        {
            private readonly Board _board;

            protected internal BoardServiceImplementationBlanco(Board board)
            {
                _board = board;
            }

            public void End()
            {
                throw new InvalidOperationException("Cannot finish a Blanco board");
            }

            public void Initialize()
            {
                _board.Initialize();
            }

            public void Play(char columnName, int rowNumber, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot play on a Blanco board. Initialize before playing");
            }
        }
    }
}
