using System;

namespace Tw.Ing.Challenge2
{
    public class Cell
    {
        private Marker _mark = Marker.Empty;

        public Cell(Coordinate coordinate)
        {
            Column = coordinate.ColumnName;
            Row = coordinate.RowNumber;
        }
        public char Column { get; private set; }
        public int Row { get; private set; }

        public Marker Mark 
        {
            get 
            {
                return _mark;
            }
            set
            {
                if (_mark != Marker.Empty)
                    throw new InvalidOperationException("Cannot play a cell which is already played.");
                _mark = value;
            }

        }

        public enum Marker
        {
            Empty,
            Circle,
            Cross
        }
    }

}
