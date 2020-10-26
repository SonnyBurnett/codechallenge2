using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateActive : Player
        {
            public PlayerStateActive(PlayerContext parent) : base(parent)
            {
                if (parent.Mark == Cell.Marker.Empty)
                {
                    throw new ArgumentOutOfRangeException(nameof(parent), "No Marker to play with (cross or circle)");
                }
            }

            public override Player Move()
            {
                throw new InvalidOperationException("Cannot make a move while not At Hand");
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                throw new InvalidOperationException("Cannot register player while active");
            }

            public override Player Turn()
            {
                return new PlayerStateAtHand(Parent);
            }

            public override void Draw()
            {

            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();
                return commandList;
            }

            public override Player SelectColumn(char columnName)
            {
                throw new InvalidOperationException("Cannot make a move while not At Hand");
            }

            public override Player SelectRow(int rowNumber)
            {
                throw new InvalidOperationException("Cannot make a move while not At Hand");
            }       
        }
    }
}