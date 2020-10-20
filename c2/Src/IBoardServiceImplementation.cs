using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    interface IBoardServiceImplementation
    {
        void End();
        void Initialize();
        void Play(char columnName, int rowNumber, Cell.Marker mark);
    }
}
