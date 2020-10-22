using System;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext 
    {
        public class BoardStateFinished : Board
        {
            public BoardStateFinished(BoardContext parent) : base(parent)
            {
            }

            public override Board End()
            {
                throw new InvalidOperationException("Cannot finish an already finished board");
            }

            public override Board Initialize()
            {
                Parent.Matrix.Clear();
                for (var rowNo = 1; rowNo <= 3; rowNo++)
                {
                    for (var columnName = 'A'; columnName <= 'C'; columnName++)
                    {
                        var cellCoordinate = new Coordinate(columnName, rowNo);
                        var cell = new Cell(cellCoordinate);
                        Parent.Matrix.Add(cellCoordinate, cell);
                    }
                }
                return new BoardStatePlaying(Parent);
            }

            public override Board Draw(Coordinate coordinate, Cell.Marker mark)
            {
                throw new InvalidOperationException("Play on a finished board");
            }
        }
    }
}
