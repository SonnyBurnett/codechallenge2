using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateActive : Player
        {
            public PlayerStateActive(PlayerContext parent) : base(parent)
            {
                if (parent.Mark == Cell.Marker.Empty)
                {
                    throw new ArgumentOutOfRangeException(nameof(parent), "No Marker to play with (cross or circle)");
                }
            }

            public override Player Move(char columnName, int rowNumber)
            {
                throw new InvalidOperationException("Cannot make a move while not At Hand");
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot register player while active");
            }

            public override Player Turn()
            {
                return new PlayerStateAtHand(Parent);
            }
        }
    }
}