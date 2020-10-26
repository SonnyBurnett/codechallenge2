﻿using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class BoardContext 
    {
        public class BoardStatePlaying : Board
        {
            public BoardStatePlaying(BoardContext parent) : base(parent)
            {
            }

            public override Board End()
            {
                return new BoardStateFinished(Parent);
            }

            public override Board Initialize()
            {
                throw new InvalidOperationException("Initialize not supported on Board 'draws' (we are playing it)");
            }

            public override Board Play(Coordinate coordinate, Cell.Marker mark)
            {
                Parent.Matrix[coordinate].Mark = mark;
                return this;
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();
                return commandList;
            }

            public override void Draw()
            {
                Console.SetCursorPosition(3, 2);
                Console.Write(" ABC");
                Console.SetCursorPosition(3, 3);
                Console.Write("1");
                Console.SetCursorPosition(3, 4);
                Console.Write("2");
                Console.SetCursorPosition(3, 5);
                Console.Write("3");
            }
        }
    }
}
