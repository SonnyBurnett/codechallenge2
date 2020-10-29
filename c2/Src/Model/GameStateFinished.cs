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

            public override Game Start()
            {
                return new GameStateNew(Parent);
            }

            public override Game End()
            {
                throw new InvalidOperationException("Cannot end a game which is already finished");
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>
                {
                    new ResetGameCommand(Parent.Service, Parent)
                };
                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(0, 12);
                if (Parent.ActivePlayer.HasWon)
                {
                    Console.Write($"!!!! Player '{Parent.ActivePlayer.Name}' is a Winner !!!!");
                }
                else
                {
                    Console.Write($"Game ended in a draw...");
                }
            }
        }
    }
}