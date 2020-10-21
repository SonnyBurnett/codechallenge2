using System;
using System.Collections.Generic;
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

            public override void Move(Cell cellToPlay)
            {
                if (cellToPlay.Mark == Cell.Marker.Empty)
                {
                    cellToPlay.Mark = Parent.Mark;
                }
                else
                {
                    throw new InvalidOperationException($"Cell '{cellToPlay.Column}{cellToPlay.Row}' already played by {cellToPlay.Mark}");
                }
            }
        }
    }
}