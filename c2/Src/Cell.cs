using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    public class Cell
    {
        private Marker _mark = Marker.Empty;

        public Cell(char column, int row)
        {
            Column = column;
            Row = row;
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
