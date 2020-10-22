using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Input;
using Tw.Ing.Challenge2.Plumbing;

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
                        var cell = new Cell(columnName, rowNo);
                        var cellCoordinate = new Pair<char, int>(columnName, rowNo);
                        Parent.Matrix.Add(cellCoordinate, cell);
                    }
                }
                return new BoardStatePlaying(Parent);
            }

            public override Board Play(char columnName, int rowNumber, Cell.Marker mark)
            {
                throw new InvalidOperationException("Play on a finished board");
            }
        }
    }
}
