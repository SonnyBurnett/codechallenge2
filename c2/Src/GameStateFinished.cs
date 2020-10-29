using System;
using System.Collections;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

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

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>
                {
                    new StartGameCommand(Parent.Service, Parent)
                };
                return commandList;
            }

            public override void Draw()
            {
            }
        }
    }
}