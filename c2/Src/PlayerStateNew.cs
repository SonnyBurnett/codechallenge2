using System;

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

            public override Player Move(Coordinate coordinate)
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
        }
    }
}