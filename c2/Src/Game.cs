using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext
    {
        protected abstract class Game
        {
            public GameContext Parent { get; private set; }

            public Game(GameContext parent) 
            {
                Parent = parent;
            }

            public abstract Game Start(PlayerContext p1, PlayerContext p2, BoardContext board);
            public abstract Game Quit();

            public abstract Game End();
        }
    }
}
