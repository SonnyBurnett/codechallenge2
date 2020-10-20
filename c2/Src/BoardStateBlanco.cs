using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks.Sources;
using System.Windows.Input;
using Tw.Ing.Challenge2.Plumbing;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext
    {
        protected class BoardStateBlanco : Board
        {
            private readonly IDictionary<Pair<char, int>, Cell> _matrix;

            protected internal BoardStateBlanco(IDictionary<Pair<char,int>, Cell> matrix)
            {
                _matrix = matrix;
            }

            public override void End()
            {
                throw new InvalidOperationException("Cannot finish a Blanco board");
            }

            public override void Initialize()
            {
                for (var rowNo = 1; rowNo <= 3; rowNo++)
                {
                    for (var columnName = 'A'; columnName <= 'C'; columnName++)
                    {
                        var cell = new Cell(columnName, rowNo);
                        var cellCoordinate = new Pair<char, int>(columnName, rowNo);
                        _matrix.Add(cellCoordinate, cell);
                    }
                }
            }

            public override void Play(char columnName, int rowNumber, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot play on a Blanco board. Initialize before playing");
            }
        }
    }
}
