using System.Collections.Generic;
using Tw.Ing.Challenge2.Commands;

namespace Tw.Ing.Challenge2
{
    internal partial class PlayerContext
    {
        protected abstract class Player: ITweContextState
        {
            public PlayerContext Parent { get; private set; }

            public Player(PlayerContext parent) 
            {
                Parent = parent;
            }

            public abstract Player Register(string name, Cell.Marker mark);
            public abstract Player Turn();

            public abstract Player Move();
            public abstract Player SelectColumn(char columnName);
            public abstract Player SelectRow(int rowNumber);
            public abstract IEnumerable<GameCommandBase> GetActionCommands();
            public abstract void Draw();
        }
    }
}
