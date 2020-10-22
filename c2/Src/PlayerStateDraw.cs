﻿using System;
using System.Collections.Generic;
using System.Text;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateDraw : Player
        {
            public PlayerStateDraw(PlayerContext parent) : base(parent)
            {
                if (parent.Mark == Cell.Marker.Empty)
                {
                    throw new ArgumentOutOfRangeException(nameof(parent), "No Marker to play with (cross or circle)");
                }
            }

            public override Player Move(char columnName, int rowNumber)
            {
                throw new InvalidOperationException("Cannot move, game ended with a draw");
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot register, game ended with a draw");
            }

            public override Player Turn()
            {
                throw new InvalidOperationException("Cannot turn, game ended with a draw");
            }
        }
    }
}