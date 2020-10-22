using System;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext
    {
        protected class BoardStateBlanco : Board
        {
            public BoardStateBlanco(BoardContext parent) : base(parent)
            {
            }

            public override Board End()
            {
                throw new InvalidOperationException("Cannot finish a Blanco board");
            }

            public override Board Initialize()
            {
                for (var rowNo = 1; rowNo <= 3; rowNo++)
                {
                    for (var columnName = 'A'; columnName <= 'C'; columnName++)
                    {
                        var coordinate = new Coordinate(columnName, rowNo);
                        var cell = new Cell(coordinate);
                        Parent.Matrix.Add(coordinate, cell);
                    }
                }

                return new BoardStatePlaying(Parent);
            }

            public override Board Draw(Coordinate coordinate, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot play on a Blanco board. Initialize before playing");
            }
        }
    }
}
