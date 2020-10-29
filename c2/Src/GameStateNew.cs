using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Windows.Input;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext
    {
        class GameStateNew : Game
        {
            public GameStateNew(GameContext parent) : base(parent)
            {
                parent.Board = new BoardContext(Parent.Service);
                parent.PlayerCross = new PlayerContext(Parent.Service, parent.Board);
                parent.PlayerCircle = new PlayerContext(Parent.Service, parent.Board);

            }

            public override Game Start()
            {
                return new GameStateActive(Parent);
            }

            public override Game Quit()
            {
                return new GameStateFinished(Parent);
            }

            public override Game End()
            {
                throw new InvalidOperationException("Cannot end a game which is not started");
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