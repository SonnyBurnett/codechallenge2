using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateNew : Player
        {
            public PlayerStateNew(PlayerContext parent) : base(parent)
            {
                Parent.Name = string.Empty;
                Parent.Mark = Cell.Marker.Empty;
            }

            public override void Move(Cell cellToPlay)
            {
                throw new NotImplementedException();
            }
        }
    }
}