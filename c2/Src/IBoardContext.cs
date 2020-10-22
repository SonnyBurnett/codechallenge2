using System;
using System.Collections.Generic;
using System.Text;
using Tw.Ing.Challenge2.Plumbing;

namespace Tw.Ing.Challenge2
{
    interface IBoardContext
    {
        public bool IsInitialized { get; }
        Dictionary<Pair<char, int>, Cell> Matrix { get; }
        void End();
        void Initialize();
        Cell Draw(char columnName, int rowNumber, Cell.Marker mark);
    }
}
