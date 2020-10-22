using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Diagnostics.Tracing;
using System.Linq;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateAtHand : Player
        {
            public PlayerStateAtHand(PlayerContext parent) : base(parent)
            {
                if (parent.Mark == Cell.Marker.Empty)
                {
                    throw new ArgumentOutOfRangeException(nameof(parent), "No Marker to play with (cross or circle)");
                }
            }

            public override Player Move(char columnName, int rowNumber)
            {
                if (Parent.Moves.Count > 5)
                {
                    throw new InvalidOperationException("Already more then 5 moves made");
                }
                var cell = Parent.Board.Draw(columnName, rowNumber, Parent.Mark);
                Parent.Moves.Add(cell);

                if (DidIWin())
                {
                    return new PlayerStateWin(Parent);
                }
                else if (DoINeedToDraw())
                {
                    return new PlayerStateDraw(Parent);
                }
                else
                {
                    return new PlayerStateActive(Parent);
                }
            }

            private bool DidIWin()
            {
                // Check the two diagonals
                if (DiagonalCount(true) == 3) return true;
                if (DiagonalCount(false) == 3) return true;

                // Check the rows
                if (RowCount(1) == 3) return true;
                if (RowCount(2) == 3) return true;
                if (RowCount(3) == 3) return true;

                // Check the columns
                if (ColumnCount('A') == 3) return true;
                if (ColumnCount('B') == 3) return true;
                if (ColumnCount('C') == 3) return true;

                return false;
            }

            private bool DoINeedToDraw()
            {
                return (Parent.Moves.Count >= 5);
            }

            private int DiagonalCount(bool isForward)
            {
                var moveCount = 0;
                var rowNumber = 1;
                var columnName = 'C';
                if (isForward) columnName = 'A';
                for (var i = 1; i <= 3; i++)
                {
                    moveCount += Parent.Moves.Where(c => c.Column == columnName && c.Row == rowNumber).Count();
                    rowNumber++;
                    if (isForward)
                        columnName++;
                    else
                        columnName--;
                }
                return moveCount;
            }

            private int RowCount(int rowNumber)
            {
                var moveCount = 0;
                for (var columnName = 'A'; columnName <= 'C'; columnName ++)
                {
                    moveCount += Parent.Moves.Where(c => c.Column == columnName && c.Row == rowNumber).Count();
                }
                return moveCount;
            }

            private int ColumnCount(char columnName)
            {
                var moveCount = 0;
                for (var rowNumber = 1; rowNumber <= 3; rowNumber++)
                {
                    moveCount += Parent.Moves.Where(c => c.Column == columnName && c.Row == rowNumber).Count();
                }
                return moveCount;
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot register player while At Hand, Please make a move");
            }

            public override Player Turn()
            {
                throw new InvalidOperationException("Already your turn");
            }
        }
    }
}