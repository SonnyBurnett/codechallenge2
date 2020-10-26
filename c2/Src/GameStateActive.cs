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
                PlayerContext nextPlayer = null;

                if ((!Parent.PlayerCircle.IsPlaying) && (!Parent.PlayerCross.IsPlaying))
                {
                    if (Parent.PlayerCircle.Moves.Count <= Parent.PlayerCross.Moves.Count)
                    {
                        nextPlayer = Parent.PlayerCircle;
                    }
                    else
                    {
                        nextPlayer = Parent.PlayerCross;
                    }
                }

                if (nextPlayer != null)
                {
                    commandList.Add(new SwitchPlayerCommand(Parent.Service, nextPlayer));
                    Parent.IsDirty = true;
                }
                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(0, 8);
                if (Parent.ActivePlayer == null)
                {
                    Console.Write($"...Next Player...");
                }
                else
                {
                    Console.Write($"Player {Parent.ActivePlayer.Name} is next.");
                }
            }
        }
    }
}