using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class GameContext
    {
        class GameStateActive : Game
        {
            public GameStateActive(GameContext parent) : base(parent)
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
                var commandList = new List<GameCommandBase>();
                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(0, 8);
                Console.Write($"Player {Parent.ActivePlayer.Name} is next.");
                Console.SetCursorPosition(0, 9);
                Console.Write("Press (Q) to quit the Game");
            }
        }
    }
}