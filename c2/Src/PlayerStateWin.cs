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

            public override void Move(Cell cellToPlay)
            {
                throw new NotImplementedException();
            }
        }
    }
}