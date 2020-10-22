using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext
    {
        public abstract class Board
        {
            public BoardContext Parent { get; private set; }

            public Board(BoardContext parent)
            {
                Parent = parent;
            }
            public abstract Board End();
            public abstract Board Initialize();
            public abstract Board Play(char columnName, int rowNumber, Cell.Marker mark);
        }
    }
}
