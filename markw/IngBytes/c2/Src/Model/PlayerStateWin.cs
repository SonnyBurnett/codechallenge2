﻿using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateWin : Player
        {
            public PlayerStateWin(PlayerContext parent) : base(parent)
            {
                if (parent.Mark == Cell.Marker.Empty)
                {
                    throw new ArgumentOutOfRangeException(nameof(parent), "No Marker to play with (cross or circle)");
                }
            }

            public override Player Move()
            {
                throw new InvalidOperationException("Cannot Turn, game ended with a Win");
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot Turn, game ended with a Win");
            }

            public override Player Turn()
            {
                throw new InvalidOperationException("Cannot Turn, game ended with a Win");
            }

            public override Player SelectColumn(char columnName)
            {
                throw new InvalidOperationException("Cannot select a column, game ended with a Win");
            }

            public override Player SelectRow(int rowNumber)
            {
                throw new InvalidOperationException("Cannot select a row, game ended with a Win");
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();
                return commandList;
            }

            public override void Draw()
            {
            }
        }
    }
}