using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext
    {
        public abstract class Board
        {
            public abstract void End();
            public abstract void Initialize();
            public abstract void Play(char columnName, int rowNumber, Cell.Marker mark);
        }
    }
}
