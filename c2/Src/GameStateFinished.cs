using System;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext
    {
        class GameStateFinished : Game
        {
            public GameStateFinished(GameContext parent) : base(parent)
            {
            }

            public override Game Start(PlayerContext p1, PlayerContext p2, BoardContext board)
            {
                throw new NotImplementedException();
            }

            public override Game Quit()
            {
                throw new NotImplementedException();
            }

            public override Game End()
            {
                throw new NotImplementedException();
            }

        }
    }
}