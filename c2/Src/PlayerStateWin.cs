using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateWin : Player
        {
            public PlayerStateWin(PlayerContext parent) : base(parent)
            {
                if (parent.Mark == Cell.Marker.Empty)
                {
                    throw new ArgumentOutOfRangeException(nameof(parent), "No Marker to play with (cross or circle)");
                }
            }

            public override Player Move(char columnName, int rowNumber)
            {
                throw new InvalidOperationException("Cannot Turn, game ended with a Win");
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot Turn, game ended with a Win");
            }

            public override Player Turn()
            {
                throw new InvalidOperationException("Cannot Turn, game ended with a Win");
            }
        }
    }
}