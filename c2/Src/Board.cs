using System;
using System.Collections.Generic;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Input;
using Tw.Ing.Challenge2.Plumbing;

namespace Tw.Ing.Challenge2
{
    internal class Board
    {
        public IDictionary<Pair<char, int>, Cell> Matrix { get; } = new Dictionary<Pair<char,int>, Cell>();

        public void Initialize()
        {
            for (var rowNo = 1; rowNo <= 3; rowNo ++)
            {
                for(var columnName = 'A'; columnName <= 'C'; columnName ++)
                {
                    var cell = new Cell(columnName, rowNo);
                    var cellCoordinate = new Pair<char, int>(columnName, rowNo);
                    Matrix.Add(cellCoordinate, cell);
                }
            }
        }

        public void Reset()
        {
            Matrix.Clear();
        }
    }
}
