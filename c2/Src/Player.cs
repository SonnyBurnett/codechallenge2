namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        protected abstract class Player
        {
            public PlayerContext Parent { get; private set; }

            public Player(PlayerContext parent) 
            {
                Parent = parent;
            }

            public abstract Player Register(string name, Cell.Marker mark);
            public abstract Player Turn();

            public abstract Player Move(Coordinate coordinate);
        }
    }
}
