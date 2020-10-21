using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        protected abstract class Player
        {
            public PlayerContext Parent { get; private set; }

            public Player(PlayerContext parent) 
            {
                Parent = parent;
            }

            public abstract void Move(Cell cellToPlay);
        }
    }
}
