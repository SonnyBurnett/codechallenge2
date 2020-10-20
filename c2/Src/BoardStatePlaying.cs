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
            private readonly IDictionary<Pair<char, int>, Cell> _matrix;

            public BoardStatePlaying(IDictionary<Pair<char, int>, Cell> matrix)
            {
                _matrix = matrix;
            }

            public override void End()
            {
                // don't have to do anything here.
            }

            public override void Initialize()
            {
                throw new InvalidOperationException("Initialize not supported on Board 'draws' (we are playing it)");
            }

            public override void Play(char columnName, int rowNumber, Cell.Marker mark)
            {
                var cellCoordinate = new Pair<char, int>(columnName, rowNumber);
                _matrix[cellCoordinate].Mark = mark;
            }
        }
    }
}
