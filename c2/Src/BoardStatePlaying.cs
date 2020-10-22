using System;
using System.Collections.Generic;
using System.Numerics;
using System.Text;
using System.Windows.Input;
using Tw.Ing.Challenge2.Plumbing;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext 
    {
        public class BoardStatePlaying : Board
        {
            public BoardStatePlaying(BoardContext parent) : base(parent)
            {
            }

            public override Board End()
            {
                return new BoardStateFinished(Parent);
            }

            public override Board Initialize()
            {
                throw new InvalidOperationException("Initialize not supported on Board 'draws' (we are playing it)");
            }

            public override Board Draw(char columnName, int rowNumber, Cell.Marker mark)
            {
                var cellCoordinate = new Pair<char, int>(columnName, rowNumber);
                Parent.Matrix[cellCoordinate].Mark = mark;
                return this;
            }
        }
    }
}
