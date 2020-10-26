using System;
using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        class PlayerStateNew : Player
        {
            public PlayerStateNew(PlayerContext parent) : base(parent)
            {
                Parent.Name = string.Empty;
                Parent.Mark = Cell.Marker.Empty;
            }

            public override Player Move()
            {
                throw new InvalidOperationException("Register player before making a move.");
            }

            public override Player Register(string name, Cell.Marker mark)
            {
                Parent.Name = name;
                Parent.Mark = mark;
                return new PlayerStateActive(Parent);
            }

            public override Player Turn()
            {
                throw new InvalidOperationException("Register player before it is your turn");
            }

            public override Player SelectColumn(char columnName)
            {
                throw new InvalidOperationException("Register player before selecting a column");
            }

            public override Player SelectRow(int rowNumber)
            {
                throw new InvalidOperationException("Register player before selecting a row");
            }

            public override void Draw()
            {
            }

            public override IEnumerable<GameCommandBase> GetActionCommands()
            {
                var commandList = new List<GameCommandBase>();
                return commandList;
            }
        }
    }
}