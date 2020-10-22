using System;

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

            public override Board Draw(Coordinate coordinate, Cell.Marker mark)
            {
                Parent.Matrix[coordinate].Mark = mark;
                return this;
            }
        }
    }
}
