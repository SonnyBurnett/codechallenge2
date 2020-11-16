using System;
using System.Collections.Generic;

namespace Tw.Ing.Challenge2
{
    public readonly struct Coordinate : IEquatable<Coordinate>
    {
        public Coordinate(char columnName, int rowNumber)
        {
            ColumnName = columnName;
            RowNumber = rowNumber;
        }
        public char ColumnName { get; }
        public int RowNumber { get; }

        public override string ToString() => $"({ColumnName}, {RowNumber})";

        public bool Equals(Coordinate other)
        {
            return EqualityComparer<char>.Default.Equals(this.ColumnName, other.ColumnName) &&
                   EqualityComparer<int>.Default.Equals(this.RowNumber, other.RowNumber);
        }

        public override bool Equals(object o)
        {
            if ((o != null) && (o.GetType() == typeof(Coordinate)))
            {
                return Equals((Coordinate)o);
            }
            else
            {
                return false;
            }
        }

        public override int GetHashCode()
        {
            return EqualityComparer<char>.Default.GetHashCode(ColumnName) * 37 +
                   EqualityComparer<int>.Default.GetHashCode(RowNumber);
        }

        public static bool operator ==(Coordinate left, Coordinate right)
        {
            return left.Equals(right);
        }

        public static bool operator !=(Coordinate left, Coordinate right)
        {
            return !(left == right);
        }
    }
}
