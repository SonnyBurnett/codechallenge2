using System.Collections;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext
    {
        protected abstract class Game: IGameEngineState
        {
            public GameContext Parent { get; private set; }

            public Game(GameContext parent) 
            {
                Parent = parent;
            }

            public abstract Game Start(PlayerContext p1, PlayerContext p2, BoardContext board);
            public abstract Game Quit();
            public abstract Game End();

            public abstract IEnumerable<IGameCommand> GetActionCommands();
            public abstract void Draw();
        }
    }
}
