﻿using System;
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

            public override Game Start()
            {
                throw new InvalidOperationException("Cannot start a game already started");
            }

            public override Game End()
            {
                return new GameStateFinished(Parent);
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();

                if  (   Parent.PlayerCircle.HasWon || Parent.PlayerCross.HasWon
                    ||  Parent.Board.NoMoreMoves
                    )
                {
                    commandList.Add(new EndGameCommand(Parent.Service, Parent));
                }             
                else if ((!Parent.PlayerCircle.IsPlaying) && (!Parent.PlayerCross.IsPlaying))
                {
                    PlayerContext nextPlayer;
                    if (Parent.PlayerCircle.Moves.Count <= Parent.PlayerCross.Moves.Count)
                    {
                        nextPlayer = Parent.PlayerCircle;
                    }
                    else
                    {
                        nextPlayer = Parent.PlayerCross;
                    }

                    if (nextPlayer != null)
                    {
                        commandList.Add(new SwitchPlayerCommand(Parent.Service, nextPlayer));
                        Parent.IsDirty = true;
                    }
                    else
                    {
                        throw new InvalidOperationException("Game has come to a halt. Cannot decided who plays");
                    }
                }


                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(0, 12);
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